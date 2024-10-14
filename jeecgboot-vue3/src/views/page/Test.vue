<script setup lang="ts">
  import axios from 'axios';
  import { ref, onMounted } from 'vue';
  import type { CSSProperties } from 'vue';
  import { notification } from 'ant-design-vue';
  import { Modal } from 'ant-design-vue'; // 新增
  import { useRoute } from 'vue-router'; // 新增

  const myMethod = ref(() => {
    alert('13213123');
  });

  // 样式定义
  const headerStyle: CSSProperties = {
    textAlign: 'center',
    color: '#fff',
    height: 64,
    paddingInline: 50,
    lineHeight: '64px',
    backgroundColor: '#fff',
  };

  const contentStyle: CSSProperties = {
    textAlign: 'center',
    minHeight: 120,
    lineHeight: '120px',
    color: '#fff',
    backgroundColor: '#fff',
  };

  const footerStyle: CSSProperties = {
    textAlign: 'center',
    color: '#fff',
    backgroundColor: 'hotpink',
  };

  // 记录等候列表数据
  const waitList = ref([]); // 存储患者列表数据
  const departmentCode = '20230817'; // 这里替换为实际的科室代码
  const currentDate = ref('');
  const currentTime = ref('');

  // 从后端获取等待列表数据
  const fetchWaitList = async () => {
    try {
      const response = await axios.post('/jeecgboot/api/doctor/getWaitList', { codeDept: departmentCode });
      waitList.value = response.data;
    } catch (error) {
      console.error('获取等候列表时出错:', error);
    }
  };

  // 更新时间
  const updateTime = () => {
    const now = new Date();
    currentDate.value = `${now.getFullYear()}年${String(now.getMonth() + 1).padStart(2, '0')}月${String(now.getDate()).padStart(2, '0')}日 星期${'日一二三四五六'.charAt(now.getDay())}`;
    currentTime.value = now.toLocaleTimeString();
  };

  // 新增：WebSocket 相关代码
  const ws = ref<WebSocket | null>(null);

  const route = useRoute(); // 新增

  // 从路由的查询参数中获取 clientId
  const clientId = ref(route.query.clientId || 'defaultClientId'); // 新增

  const connectWebSocket = () => {
    // 替换为您的 WebSocket 服务器地址
    ws.value = new WebSocket('ws://localhost:8081/ws');

    ws.value.onopen = () => {
      console.log('WebSocket 已连接');
      // 如果需要，您可以在此处发送注册消息，例如发送 clientId
      ws.value.send(JSON.stringify({ type: 'register', clientId: clientId.value }));
    };

    ws.value.onmessage = (event) => {
      const message = event.data;
      console.log('收到服务器消息:', message);
      // 当收到服务器消息时，触发弹框显示内容
      showNotification(message);
      // 播放语音
      playVoice(message);
    };

    ws.value.onclose = () => {
      console.log('WebSocket 已关闭');
      // 可根据需要实现自动重连
    };

    ws.value.onerror = (error) => {
      console.error('WebSocket 发生错误:', error);
    };
  };

  const isModalVisible = ref(false);
  const modalMessage = ref('');

  // 定义一个函数来显示弹框
  const showNotification = (message: string) => {
    // 使用浏览器的 alert 来简化弹框，也可以使用 Ant Design Vue 的组件
    // alert(`服务器通知: ${message}`);
    // notification.open({
    //   message: '服务器通知',
    //   description: message,
    //   duration: 0, // 通知将一直显示，直到手动关闭
    // });
    modalMessage.value = message;
    isModalVisible.value = true;
    // 5 秒后自动关闭弹窗
    setTimeout(() => {
      isModalVisible.value = false;
    }, 5000);
  };

  const playVoice = (text: string) => {
    if ('speechSynthesis' in window) {
      const utterance = new SpeechSynthesisUtterance(text);
      // 可选：设置语言、语速等
      utterance.lang = 'zh-CN';
      utterance.rate = 1; // 语速，默认值为1

      window.speechSynthesis.speak(utterance);
    } else {
      console.warn('当前浏览器不支持 SpeechSynthesis API');
    }
  };

  // 组件挂载时获取数据
  onMounted(() => {
    fetchWaitList();
    updateTime();
    setInterval(updateTime, 1000); // 每秒更新一次时间
    // 建立 WebSocket 连接
    connectWebSocket();
    window.myMethod = myMethod.value;
  });

  const x = () => {
    window.chrome.webview.postMessage({ action: 'showMessage', data: 'Hello from Vue!' });
  };

  // // 在组件卸载时关闭 WebSocket 连接
  // onBeforeUnmount(() => {
  //   if (ws.value) {
  //     ws.value.close();
  //   }
  // });
</script>

<template>
  <a-layout style="background-color: white">
    <a-layout-header style="padding-right: 0px; margin-bottom: 20px" :style="headerStyle">
      <a-row>
        <a-col :span="6">
          <img src="../../assets/images/screen_logo.png" />
        </a-col>
        <a-col :span="6">
          <div @click="x" style="background-color: hotpink; border-bottom-left-radius: 50px; border-bottom-right-radius: 50px"> 神经外科 </div>
        </a-col>
        <a-col :span="6">
          <div style="color: black">{{ currentDate }}</div>
        </a-col>
        <a-col :span="6">
          <div style="color: black; background-color: hotpink; border-top-left-radius: 100px; border-bottom-left-radius: 100px">
            {{ currentTime }}
          </div>
        </a-col>
      </a-row>
    </a-layout-header>
    <a-layout-content :style="contentStyle">
      <div style="background-color: deeppink; height: 660px; border-top-left-radius: 50px; border-top-right-radius: 50px; padding: 30px">
        <div style="background-color: hotpink; border-top-left-radius: 50px; border-top-right-radius: 50px; margin-bottom: 5px">
          <a-row>
            <a-col :span="4">
              <div style="line-height: 50px; text-align: center">诊室名称</div>
            </a-col>
            <a-col :span="4">
              <div style="line-height: 50px; text-align: center">正在就诊</div>
            </a-col>
            <a-col :span="16">
              <div style="line-height: 50px; text-align: center">等候就诊</div>
            </a-col>
          </a-row>
        </div>
        <div v-for="(queue, index) in waitList" :key="index" style="background-color: hotpink; margin-bottom: 5px">
          <a-row>
            <a-col :span="4">
              <div>{{ queue.roomName || '未知诊室' }}</div>
            </a-col>
            <a-col :span="4">
              <div>{{ queue.currentPatient || '无' }}</div>
            </a-col>
            <a-col :span="16">
              <div>
                <span v-for="(patient, idx) in queue.waitingPatients" :key="idx" style="margin-right: 10px">{{ patient.name }}</span>
              </div>
            </a-col>
          </a-row>
        </div>
      </div>
    </a-layout-content>
    <a-layout-footer :style="footerStyle">请其他患者在大厅候诊区耐心等候</a-layout-footer>
  </a-layout>
  <!-- 添加 Modal 组件 -->
  <a-modal
    v-model:visible="isModalVisible"
    :style="{ borderRadius: '15px' }"
    centered
    title="服务器通知"
    width="400px"
    :footer="null"
    :closable="false"
  >
    <p style="font-size: 18px; text-align: center">{{ modalMessage }}</p>
  </a-modal>
</template>

