<script setup lang="ts">
  import { onMounted, ref } from 'vue';
  import Room from '@/views/page/triage/components/room.vue';
  import axios from 'axios';
  import { useUserStore } from '@/store/modules/user';
  import { getDeptList } from '@/views/page/triage/index.api';
  import TabDept from '@/views/page/triage/components/TabDept.vue';
  const activeKey = ref('1');
  const rooms = ref<{ room_number: string; realname: string }[]>([]);
  const deptList = ref<any>(null); // 定义 deptList 变量
  onMounted(() => {
    const userStore = useUserStore();
    const deptId = userStore.getLoginInfo.departs[0].id;
    console.log('科室id', userStore.getLoginInfo.departs[0].id);
    if (chrome.webview && chrome.webview.hostObjects) {
      const frame = chrome.webview.hostObjects.viewHost;
      frame
        .GetMac()
        .then((res) => {
          console.log('客户端返回', res);
          let param = JSON.parse(res);
          let mac = param.mac;
          let ip = param.ip;
          console.log('客户端mac', mac);
          console.log('客户端ip', ip);

          getDeptList({ mac: mac, ip: ip }).then((res) => {
            deptList.value = res; // 将返回值赋给 deptList
            if (deptList.value.length > 0) {
              activeKey.value = 1; // 设置第一个标签为默认选中
              console.log('科室id', JSON.stringify({ pkDept: deptList.value[0].pkDept, nameDept: deptList.value[0].nameDept }));
              frame.SendDept(JSON.stringify({ pkDept: deptList.value[0].pkDept, nameDept: deptList.value[0].nameDept }));
            }
          });
        })
        .catch((error) => {
          console.error('Error retrieving MAC Address:', error);
        });
    } else {
      let mac = '00FF0EF4FFBC';
      getDeptList({ mac: mac }).then((res) => {
        deptList.value = res; // 将返回值赋给 deptList
        if (deptList.value.length > 0) {
          activeKey.value = 1; // 设置第一个标签为默认选中
        }
      });
    }

    // 发送请求到服务器
    // axios
    //   .post('/jeecgboot/api/doctor/getRoomsByDeptId', { deptId: deptId })
    //   .then((response) => {
    //     rooms.value = response.data;
    //     // 设置默认选中第一个标签
    //     if (rooms.value.length > 0) {
    //       activeKey.value = 1; // 设置为第一个选项卡的 key
    //     }
    //     console.log('数据获取成功:', JSON.stringify(response.data));
    //     // 在这里可以设置组件的状态或者执行其他操作
    //   })
    //   .catch((error) => {
    //     console.error('数据获取失败:', error);
    //   });
  });
</script>

<template>
  <div>
    <a-tabs v-model:activeKey="activeKey">
      <!-- 动态生成 a-tab-pane -->
      <a-tab-pane v-for="(dept, index) in deptList" :key="index + 1" :tab="dept.nameDept" force-render>
        <tab-dept :deptId="dept.pkDept" />
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<style scoped lang="less"></style>
