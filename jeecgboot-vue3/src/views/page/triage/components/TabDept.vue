<template>
  <div>
    <!-- 显示加载状态 -->
    <a-spin :spinning="isLoading" tip="正在加载诊室信息...">
      <!-- 如果有错误，显示错误信息 -->
      <div v-if="error" class="error">
        <a-alert message="错误" description="无法获取诊室列表，请稍后再试。" type="error" show-icon />
      </div>

      <!-- 如果没有错误且有诊室列表，显示卡片 -->
      <div v-else-if="roomList.length > 0">
        <a-row :gutter="[8, 8]">
          <!-- 减少栅格间距 -->
          <a-col v-for="room in roomList" :key="room.id" :xs="24" :sm="12" :md="8" :lg="6" :xl="4">
            <!-- 增加每行卡片数量 -->
            <a-card class="room-card" :title="room.room_number" bordered hoverable @click="handleCardClick(room)">
              <!-- 第一行：医生名称和在线状态 -->
              <div class="card-header">
                <span class="room-name">{{ room.realname }}</span>
                <span class="doctor-name">{{ room.status }}</span>
              </div>
              <!-- 第二行：当前呼叫 -->
              <div class="card-content">
                <p>当前呼叫：{{ room.currentCall }}</p>
              </div>

              <!-- 第三行：等候、过号、已叫 -->
              <div class="card-content">
                <p> 等候：{{ room.waiting }}，过号：{{ room.passed }}，已叫：{{ room.called }} </p>
              </div>

              <!-- 根据需要添加更多信息 -->
            </a-card>
          </a-col>
        </a-row>

        <div>
          <room :room-id="selectedRoomId" :doctor-id="selectedDoctorId" />
        </div>
      </div>

      <!-- 如果没有诊室信息，显示提示 -->
      <div v-else>
        <a-alert message="暂无诊室信息。" type="info" show-icon />
      </div>
    </a-spin>
  </div>
</template>

<script setup lang="ts">
  import { defineProps, onMounted, ref } from 'vue';
  import { getRoomList } from '@/views/page/triage/index.api';
  // 导入 Ant Design Vue 组件
  import { Row, Col, Card, Spin, Alert } from 'ant-design-vue';
  import Room from '@/views/page/triage/components/room.vue';

  // 定义组件接收的 props
  const props = defineProps<{
    deptId: string;
  }>();

  // 定义诊室的数据类型（推荐，替换 any）
  interface Room {
    doctorid: string;
    roomid: string;
    roomNumber: string;
    status: string;
    realname: string;
    currentCall: string;
    waiting: number;
    passed: number;
    called: number;
    // 添加其他属性...
  }

  // 定义响应式变量来存储诊室列表
  const roomList = ref<Room[]>([]);

  // 定义加载状态和错误信息
  const isLoading = ref<boolean>(false);
  const error = ref<string | null>(null);

  // 定义选中的诊室 ID 和医生 ID
  const selectedRoomId = ref<string | null>(null);
  const selectedDoctorId = ref<string | null>(null);

  // 在组件挂载后调用 getRoomList
  onMounted(async () => {
    console.log('接收到的 deptId:', props.deptId);
    isLoading.value = true;
    error.value = null;
    try {
      const res = await getRoomList({ deptId: props.deptId });
      // 假设返回的数据在 res.data 中
      roomList.value = res;
      console.log('诊室列表:', roomList.value);
    } catch (err) {
      console.error('获取诊室列表失败:', err);
      error.value = '无法获取诊室列表，请稍后再试。';
    } finally {
      isLoading.value = false;
    }
  });

  // 可选：卡片点击事件处理
  const handleCardClick = (room: Room) => {
    console.log('点击的诊室:', room);
    // 设置选中的诊室 ID 和医生 ID
    selectedRoomId.value = room.doctorid;
    selectedDoctorId.value = room.roomid;

    // 如果 <room> 组件是一个模态框，可以在这里触发显示模态框
    // 例如，如果使用 Ant Design Vue 的 Modal 组件
    // showModal.value = true;
  };
</script>

<style scoped lang="less">
  .error {
    margin: 20px 0;
  }

  .card-header {
    display: flex;
    justify-content: space-between;
    margin-bottom: 8px; /* 减少下边距 */
  }

  .room-name {
    font-weight: bold;
    font-size: 14px; /* 减小字体大小 */
  }

  .doctor-name {
    font-weight: bold;
    font-size: 14px; /* 减小字体大小 */
    color: #1890ff; /* 使用 Ant Design 的主要颜色 */
  }

  .card-content {
    margin-bottom: 4px; /* 减少下边距 */
  }

  .card-content p {
    margin: 0;
    font-size: 12px; /* 减小字体大小 */
    color: #595959;
  }

  .room-card {
    padding: 8px; /* 可选：进一步减小内边距 */
  }
</style>
