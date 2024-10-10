<!--<script setup lang="ts">-->
<!--  import axios from 'axios';-->
<!--  import { ref, onMounted } from 'vue';-->
<!--  import type { CSSProperties } from 'vue';-->
<!--  import { notification } from 'ant-design-vue';-->
<!--  import { Modal } from 'ant-design-vue'; // 新增-->
<!--  import { useRoute } from 'vue-router'; // 新增-->

<!--  const myMethod = ref(() => {-->
<!--    alert('13213123');-->
<!--  });-->

<!--  // 样式定义-->
<!--  const headerStyle: CSSProperties = {-->
<!--    textAlign: 'center',-->
<!--    color: '#fff',-->
<!--    height: 64,-->
<!--    paddingInline: 50,-->
<!--    lineHeight: '64px',-->
<!--    backgroundColor: '#fff',-->
<!--  };-->

<!--  const contentStyle: CSSProperties = {-->
<!--    textAlign: 'center',-->
<!--    minHeight: 120,-->
<!--    lineHeight: '120px',-->
<!--    color: '#fff',-->
<!--    backgroundColor: '#fff',-->
<!--  };-->

<!--  const footerStyle: CSSProperties = {-->
<!--    textAlign: 'center',-->
<!--    color: '#fff',-->
<!--    backgroundColor: 'hotpink',-->
<!--  };-->

<!--  // 记录等候列表数据-->
<!--  const waitList = ref([]); // 存储患者列表数据-->
<!--  const departmentCode = '20230817'; // 这里替换为实际的科室代码-->
<!--  const currentDate = ref('');-->
<!--  const currentTime = ref('');-->

<!--  // 从后端获取等待列表数据-->
<!--  const fetchWaitList = async () => {-->
<!--    try {-->
<!--      const response = await axios.post('/jeecgboot/api/doctor/getWaitList', { codeDept: departmentCode });-->
<!--      waitList.value = response.data;-->
<!--    } catch (error) {-->
<!--      console.error('获取等候列表时出错:', error);-->
<!--    }-->
<!--  };-->

<!--  // 更新时间-->
<!--  const updateTime = () => {-->
<!--    const now = new Date();-->
<!--    currentDate.value = `${now.getFullYear()}年${String(now.getMonth() + 1).padStart(2, '0')}月${String(now.getDate()).padStart(2, '0')}日 星期${'日一二三四五六'.charAt(now.getDay())}`;-->
<!--    currentTime.value = now.toLocaleTimeString();-->
<!--  };-->

<!--  // 新增：WebSocket 相关代码-->
<!--  const ws = ref<WebSocket | null>(null);-->

<!--  const route = useRoute(); // 新增-->

<!--  // 从路由的查询参数中获取 clientId-->
<!--  const clientId = ref(route.query.clientId || 'defaultClientId'); // 新增-->

<!--  const connectWebSocket = () => {-->
<!--    // 替换为您的 WebSocket 服务器地址-->
<!--    ws.value = new WebSocket('ws://localhost:8081/ws');-->

<!--    ws.value.onopen = () => {-->
<!--      console.log('WebSocket 已连接');-->
<!--      // 如果需要，您可以在此处发送注册消息，例如发送 clientId-->
<!--      ws.value.send(JSON.stringify({ type: 'register', clientId: clientId.value }));-->
<!--    };-->

<!--    ws.value.onmessage = (event) => {-->
<!--      const message = event.data;-->
<!--      console.log('收到服务器消息:', message);-->
<!--      // 当收到服务器消息时，触发弹框显示内容-->
<!--      showNotification(message);-->
<!--      // 播放语音-->
<!--      playVoice(message);-->
<!--    };-->

<!--    ws.value.onclose = () => {-->
<!--      console.log('WebSocket 已关闭');-->
<!--      // 可根据需要实现自动重连-->
<!--    };-->

<!--    ws.value.onerror = (error) => {-->
<!--      console.error('WebSocket 发生错误:', error);-->
<!--    };-->
<!--  };-->

<!--  const isModalVisible = ref(false);-->
<!--  const modalMessage = ref('');-->

<!--  // 定义一个函数来显示弹框-->
<!--  const showNotification = (message: string) => {-->
<!--    // 使用浏览器的 alert 来简化弹框，也可以使用 Ant Design Vue 的组件-->
<!--    // alert(`服务器通知: ${message}`);-->
<!--    // notification.open({-->
<!--    //   message: '服务器通知',-->
<!--    //   description: message,-->
<!--    //   duration: 0, // 通知将一直显示，直到手动关闭-->
<!--    // });-->
<!--    modalMessage.value = message;-->
<!--    isModalVisible.value = true;-->
<!--    // 5 秒后自动关闭弹窗-->
<!--    setTimeout(() => {-->
<!--      isModalVisible.value = false;-->
<!--    }, 5000);-->
<!--  };-->

<!--  const playVoice = (text: string) => {-->
<!--    if ('speechSynthesis' in window) {-->
<!--      const utterance = new SpeechSynthesisUtterance(text);-->
<!--      // 可选：设置语言、语速等-->
<!--      utterance.lang = 'zh-CN';-->
<!--      utterance.rate = 1; // 语速，默认值为1-->

<!--      window.speechSynthesis.speak(utterance);-->
<!--    } else {-->
<!--      console.warn('当前浏览器不支持 SpeechSynthesis API');-->
<!--    }-->
<!--  };-->

<!--  // 组件挂载时获取数据-->
<!--  onMounted(() => {-->
<!--    fetchWaitList();-->
<!--    updateTime();-->
<!--    setInterval(updateTime, 1000); // 每秒更新一次时间-->
<!--    // 建立 WebSocket 连接-->
<!--    connectWebSocket();-->
<!--    window.myMethod = myMethod.value;-->
<!--  });-->

<!--  const x = () => {-->
<!--    window.chrome.webview.postMessage({ action: 'showMessage', data: 'Hello from Vue!' });-->
<!--  };-->

<!--  // // 在组件卸载时关闭 WebSocket 连接-->
<!--  // onBeforeUnmount(() => {-->
<!--  //   if (ws.value) {-->
<!--  //     ws.value.close();-->
<!--  //   }-->
<!--  // });-->
<!--</script>-->

<!--<template>-->
<!--  <a-layout style="background-color: white">-->
<!--    <a-layout-header style="padding-right: 0px; margin-bottom: 20px" :style="headerStyle">-->
<!--      <a-row>-->
<!--        <a-col :span="6">-->
<!--          <img src="../../assets/images/screen_logo.png" />-->
<!--        </a-col>-->
<!--        <a-col :span="6">-->
<!--          <div @click="x" style="background-color: hotpink; border-bottom-left-radius: 50px; border-bottom-right-radius: 50px"> 神经外科 </div>-->
<!--        </a-col>-->
<!--        <a-col :span="6">-->
<!--          <div style="color: black">{{ currentDate }}</div>-->
<!--        </a-col>-->
<!--        <a-col :span="6">-->
<!--          <div style="color: black; background-color: hotpink; border-top-left-radius: 100px; border-bottom-left-radius: 100px">-->
<!--            {{ currentTime }}-->
<!--          </div>-->
<!--        </a-col>-->
<!--      </a-row>-->
<!--    </a-layout-header>-->
<!--    <a-layout-content :style="contentStyle">-->
<!--      <div style="background-color: deeppink; height: 660px; border-top-left-radius: 50px; border-top-right-radius: 50px; padding: 30px">-->
<!--        <div style="background-color: hotpink; border-top-left-radius: 50px; border-top-right-radius: 50px; margin-bottom: 5px">-->
<!--          <a-row>-->
<!--            <a-col :span="4">-->
<!--              <div style="line-height: 50px; text-align: center">诊室名称</div>-->
<!--            </a-col>-->
<!--            <a-col :span="4">-->
<!--              <div style="line-height: 50px; text-align: center">正在就诊</div>-->
<!--            </a-col>-->
<!--            <a-col :span="16">-->
<!--              <div style="line-height: 50px; text-align: center">等候就诊</div>-->
<!--            </a-col>-->
<!--          </a-row>-->
<!--        </div>-->
<!--        <div v-for="(queue, index) in waitList" :key="index" style="background-color: hotpink; margin-bottom: 5px">-->
<!--          <a-row>-->
<!--            <a-col :span="4">-->
<!--              <div>{{ queue.roomName || '未知诊室' }}</div>-->
<!--            </a-col>-->
<!--            <a-col :span="4">-->
<!--              <div>{{ queue.currentPatient || '无' }}</div>-->
<!--            </a-col>-->
<!--            <a-col :span="16">-->
<!--              <div>-->
<!--                <span v-for="(patient, idx) in queue.waitingPatients" :key="idx" style="margin-right: 10px">{{ patient.name }}</span>-->
<!--              </div>-->
<!--            </a-col>-->
<!--          </a-row>-->
<!--        </div>-->
<!--      </div>-->
<!--    </a-layout-content>-->
<!--    <a-layout-footer :style="footerStyle">请其他患者在大厅候诊区耐心等候</a-layout-footer>-->
<!--  </a-layout>-->
<!--  &lt;!&ndash; 添加 Modal 组件 &ndash;&gt;-->
<!--  <a-modal-->
<!--    v-model:visible="isModalVisible"-->
<!--    :style="{ borderRadius: '15px' }"-->
<!--    centered-->
<!--    title="服务器通知"-->
<!--    width="400px"-->
<!--    :footer="null"-->
<!--    :closable="false"-->
<!--  >-->
<!--    <p style="font-size: 18px; text-align: center">{{ modalMessage }}</p>-->
<!--  </a-modal>-->
<!--</template>-->

<template>
  <div>
    <a-card title="客户好评登记" class="feedback-form-card">
      <a-form layout="vertical" @submit.prevent="submitFeedback">
        <!-- 客户基本信息 -->
        <a-form-item label="客户姓名" required>
          <a-input v-model="customerName" placeholder="请输入客户姓名" />
        </a-form-item>
        <a-form-item label="联系方式">
          <a-input v-model="contactInfo" placeholder="请输入联系方式（可选）" />
        </a-form-item>

        <!-- 产品或服务信息 -->
        <a-form-item label="服务项目" required>
          <a-input v-model="product" placeholder="请输入产品名称" />
        </a-form-item>
        <a-form-item label="服务日期">
          <a-date-picker v-model="purchaseDate" style="width: 100%" />
        </a-form-item>

        <!-- 好评内容 -->
        <a-form-item label="好评标题">
          <a-input v-model="feedbackTitle" placeholder="请输入好评标题（可选）" />
        </a-form-item>
        <a-form-item label="好评内容" required>
          <a-textarea v-model="feedbackContent" placeholder="请输入好评内容" rows="4" />
        </a-form-item>

        <!-- 评分 -->
        <a-form-item label="评分" required>
          <a-rate v-model="rating" />
        </a-form-item>

        <!-- 附件上传 -->
        <a-form-item label="上传附件">
          <a-upload :before-upload="handleFileUpload" :show-upload-list="false">
            <a-button icon="upload">选择文件</a-button>
          </a-upload>
        </a-form-item>

        <!-- 提交按钮 -->
        <a-form-item>
          <a-button type="primary" html-type="submit" block>提交</a-button>
        </a-form-item>
      </a-form>
    </a-card>

    <a-card title="客户投诉登记" class="complaint-form-card">
      <a-form layout="vertical" @submit.prevent="submitComplaint">
        <!-- 客户基本信息 -->
        <a-form-item label="客户姓名" required>
          <a-input v-model="customerName" placeholder="请输入客户姓名" />
        </a-form-item>
        <a-form-item label="联系方式" required>
          <a-input v-model="contactInfo" placeholder="请输入联系方式" />
        </a-form-item>

        <!-- 投诉类型 -->
        <a-form-item label="投诉类型" required>
          <a-select v-model="complaintType" placeholder="请选择投诉类型">
            <a-select-option value="product">产品问题</a-select-option>
            <a-select-option value="service">服务问题</a-select-option>
            <a-select-option value="logistics">物流问题</a-select-option>
            <a-select-option value="other">其他问题</a-select-option>
          </a-select>
        </a-form-item>

        <!-- 投诉日期 -->
        <a-form-item label="投诉日期" required>
          <a-date-picker v-model="complaintDate" style="width: 100%" />
        </a-form-item>

        <!-- 投诉内容 -->
        <a-form-item label="投诉内容" required>
          <a-textarea v-model="complaintContent" placeholder="请输入投诉内容" rows="4" />
        </a-form-item>

        <!-- 投诉优先级 -->
        <a-form-item label="投诉优先级" required>
          <a-radio-group v-model="priority">
            <a-radio value="low">低</a-radio>
            <a-radio value="medium">中</a-radio>
            <a-radio value="high">高</a-radio>
          </a-radio-group>
        </a-form-item>

        <!-- 附件上传 -->
        <a-form-item label="上传附件">
          <a-upload :before-upload="handleFileUpload" :show-upload-list="false">
            <a-button icon="upload">选择文件</a-button>
          </a-upload>
        </a-form-item>

        <!-- 提交按钮 -->
        <a-form-item>
          <a-button type="primary" html-type="submit" block>提交投诉</a-button>
        </a-form-item>
      </a-form>
    </a-card>

    <a-card title="客户建议登记" class="suggestion-form-card">
      <a-form layout="vertical" @submit.prevent="submitSuggestion">
        <!-- 客户基本信息 -->
        <a-form-item label="客户姓名" required>
          <a-input v-model="customerName" placeholder="请输入客户姓名" />
        </a-form-item>
        <a-form-item label="联系方式" required>
          <a-input v-model="contactInfo" placeholder="请输入联系方式" />
        </a-form-item>

        <!-- 建议主题 -->
        <a-form-item label="建议主题" required>
          <a-input v-model="suggestionTitle" placeholder="请输入建议主题" />
        </a-form-item>

        <!-- 建议类别 -->
        <a-form-item label="建议类别" required>
          <a-select v-model="suggestionCategory" placeholder="请选择建议类别">
            <a-select-option value="product">产品改进</a-select-option>
            <a-select-option value="service">服务优化</a-select-option>
            <a-select-option value="other">其他</a-select-option>
          </a-select>
        </a-form-item>

        <!-- 建议内容 -->
        <a-form-item label="建议内容" required>
          <a-textarea v-model="suggestionContent" placeholder="请输入建议内容" rows="4" />
        </a-form-item>

        <!-- 附件上传 -->
        <a-form-item label="上传附件">
          <a-upload :before-upload="handleFileUpload" :show-upload-list="false">
            <a-button icon="upload">选择文件</a-button>
          </a-upload>
        </a-form-item>

        <!-- 提交按钮 -->
        <a-form-item>
          <a-button type="primary" html-type="submit" block>提交建议</a-button>
        </a-form-item>
      </a-form>
    </a-card>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        customerName: '',
        contactInfo: '',
        product: '',
        purchaseDate: null,
        feedbackTitle: '',
        feedbackContent: '',
        rating: 0,
        attachment: null,
        complaintType: null,
        complaintDate: null,
        complaintContent: '',
        priority: 'medium',

        suggestionTitle: '',
        suggestionCategory: null,
        suggestionContent: '',
      };
    },
    methods: {
      handleFileUpload(file) {
        this.attachment = file;
        return false; // 阻止自动上传
      },
      submitFeedback() {
        const feedback = {
          customerName: this.customerName,
          contactInfo: this.contactInfo,
          product: this.product,
          purchaseDate: this.purchaseDate,
          feedbackTitle: this.feedbackTitle,
          feedbackContent: this.feedbackContent,
          rating: this.rating,
          attachment: this.attachment,
        };

        console.log('Feedback submitted:', feedback);

        this.customerName = '';
        this.contactInfo = '';
        this.product = '';
        this.purchaseDate = null;
        this.feedbackTitle = '';
        this.feedbackContent = '';
        this.rating = 0;
        this.attachment = null;

        this.$message.success('好评提交成功！');
      },

      submitComplaint() {
        const complaint = {
          customerName: this.customerName,
          contactInfo: this.contactInfo,
          complaintType: this.complaintType,
          complaintDate: this.complaintDate,
          complaintContent: this.complaintContent,
          priority: this.priority,
          attachment: this.attachment,
        };

        console.log('Complaint submitted:', complaint);

        // 清空表单数据
        this.customerName = '';
        this.contactInfo = '';
        this.complaintType = null;
        this.complaintDate = null;
        this.complaintContent = '';
        this.priority = 'medium';
        this.attachment = null;

        this.$message.success('投诉提交成功！');
      },

      submitSuggestion() {
        const suggestion = {
          customerName: this.customerName,
          contactInfo: this.contactInfo,
          suggestionTitle: this.suggestionTitle,
          suggestionCategory: this.suggestionCategory,
          suggestionContent: this.suggestionContent,
          attachment: this.attachment,
        };

        console.log('Suggestion submitted:', suggestion);

        // 清空表单数据
        this.customerName = '';
        this.contactInfo = '';
        this.suggestionTitle = '';
        this.suggestionCategory = null;
        this.suggestionContent = '';
        this.attachment = null;

        this.$message.success('建议提交成功！');
      },
    },
  };
</script>

<style scoped>
  .feedback-form-card {
    max-width: 500px;
    margin: 2rem auto;
    padding: 1rem;
  }

  .complaint-form-card {
    max-width: 500px;
    margin: 2rem auto;
    padding: 1rem;
  }
  .suggestion-form-card {
    max-width: 500px;
    margin: 2rem auto;
    padding: 1rem;
  }
</style>
