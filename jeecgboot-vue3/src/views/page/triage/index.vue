<script setup lang="ts">
  import { onMounted, ref } from 'vue';
  import Room from '@/views/page/triage/components/room.vue';
  import axios from 'axios';
  import { useUserStore } from '@/store/modules/user';
  const activeKey = ref('1');
  const rooms = ref<{ room_number: string; realname: string }[]>([]);
  onMounted(() => {
    const userStore = useUserStore();
    const deptId = userStore.getLoginInfo.departs[0].id;
    console.log('科室id', userStore.getLoginInfo.departs[0].id);

    // 发送请求到服务器
    axios
      .post('/jeecgboot/api/doctor/getRoomsByDeptId', { deptId: deptId })
      .then((response) => {
        rooms.value = response.data;
        // 设置默认选中第一个标签
        if (rooms.value.length > 0) {
          activeKey.value = 1; // 设置为第一个选项卡的 key
        }
        console.log('数据获取成功:', JSON.stringify(response.data));
        // 在这里可以设置组件的状态或者执行其他操作
      })
      .catch((error) => {
        console.error('数据获取失败:', error);
      });
  });
</script>

<template>
  <div>
    <a-tabs v-model:activeKey="activeKey">
      <!-- 动态生成 a-tab-pane -->
      <a-tab-pane
        v-for="(room, index) in rooms"
        :key="index + 1"
        :tab="`${room.room_number} (${room.realname})`"
        force-render
      >
        <room :roomId="room.id" :doctorId="room.doctor" />
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<style scoped lang="less"></style>

