<!--<template>-->
<!--  <a-table :columns="columns" :data-source="data" bordered>-->
<!--    &lt;!&ndash;    <template #title>&ndash;&gt;-->
<!--    &lt;!&ndash;      <div style="display: flex">&ndash;&gt;-->
<!--    &lt;!&ndash;        <div>王源源</div>&ndash;&gt;-->
<!--    &lt;!&ndash;        <div style="margin-left: 20px">候诊人数:</div>&ndash;&gt;-->
<!--    &lt;!&ndash;        <div style="color: red">8</div>&ndash;&gt;-->
<!--    &lt;!&ndash;      </div>&ndash;&gt;-->
<!--    &lt;!&ndash;    </template>&ndash;&gt;-->
<!--    &lt;!&ndash;    <template #footer>Footer</template>&ndash;&gt;-->
<!--    <template #action="{ record }">-->
<!--      &lt;!&ndash; 操作下拉菜单 &ndash;&gt;-->
<!--      <a-dropdown>-->
<!--        <a-button @click="() => {}">操作 <a-icon type="down" /></a-button>-->
<!--        <template #overlay>-->
<!--          <a-menu @click="(key) => handleMenuClick(record, key)">-->
<!--            <a-menu-item key="delete">签到</a-menu-item>-->
<!--            <a-menu-item key="view">优先</a-menu-item>-->
<!--            <a-menu-item key="view">延后</a-menu-item>-->
<!--            <a-menu-item key="view">病人标识</a-menu-item>-->
<!--            <a-menu-item key="view">取消病人标识</a-menu-item>-->
<!--            <a-menu-item key="view">指定医生</a-menu-item>-->
<!--            <a-menu-item key="view">生命体征</a-menu-item>-->
<!--            <a-menu-item key="view">分诊取消</a-menu-item>-->
<!--          </a-menu>-->
<!--        </template>-->
<!--      </a-dropdown>-->
<!--    </template>-->
<!--  </a-table>-->
<!--</template>-->
<!--<script lang="ts" setup>-->
<!--  import { onMounted, ref } from 'vue';-->

<!--  import axios from 'axios';-->

<!--  const props = defineProps<{-->
<!--    roomId: string;-->
<!--    doctorId: string;-->
<!--  }>();-->

<!--  const data = ref([]); // 使用 ref 创建一个响应式数组-->

<!--  onMounted(() => {-->
<!--    // 发送请求到服务器-->
<!--    axios-->
<!--      .post('/jeecgboot/api/doctor/getDoctorQueueList', { doctorId: props.doctorId })-->
<!--      .then((response) => {-->
<!--        console.log('队列信息', response);-->
<!--        data.value = response.data; // 将返回的数据赋值给 data-->
<!--      })-->
<!--      .catch((error) => {-->
<!--        console.error('数据获取失败:', error);-->
<!--      });-->
<!--  });-->

<!--  const handleMenuClick = (record: any, key: string) => {-->
<!--    console.log(`Clicked action ${key} for record:`, record);-->
<!--    // 执行不同操作的逻辑-->
<!--  };-->

<!--  const columns = [-->
<!--    {-->
<!--      title: '排队序号',-->
<!--      dataIndex: 'queue_number',-->
<!--      key: 'queue_number',-->
<!--    },-->
<!--    {-->
<!--      title: '姓名',-->
<!--      dataIndex: 'name',-->
<!--      key: 'name',-->
<!--    },-->
<!--    {-->
<!--      title: 'ID',-->
<!--      dataIndex: 'address',-->
<!--      key: 'address',-->
<!--    },-->
<!--    {-->
<!--      title: '性别',-->
<!--      key: 'gender',-->
<!--      dataIndex: 'gender',-->
<!--    },-->
<!--    {-->
<!--      title: '年龄',-->
<!--      key: 'age',-->
<!--      dataIndex: 'age',-->
<!--    },-->
<!--    {-->
<!--      title: '看诊时间',-->
<!--      key: 'tags',-->
<!--      dataIndex: 'tags',-->
<!--    },-->
<!--    {-->
<!--      title: '分诊状态',-->
<!--      key: 'queue_status',-->
<!--      dataIndex: 'queue_status',-->
<!--    },-->
<!--    {-->
<!--      title: '操作',-->
<!--      key: 'action',-->
<!--      dataIndex: 'action',-->
<!--      // 使用 slots 实现自定义渲染-->
<!--      slots: { customRender: 'action' },-->
<!--    },-->
<!--  ];-->
<!--</script>-->

<!--<style scoped lang="less"></style>-->
<template>
  <div class="p-2">
    <!--查询区域-->
    <div class="jeecg-basic-table-form-container">
      <a-form ref="formRef" @keyup.enter.native="searchQuery" :model="queryParam" :label-col="labelCol" :wrapper-col="wrapperCol">
        <a-row :gutter="24" />
      </a-form>
    </div>
    <!--引用表格-->
    <BasicTable @register="registerTable" :rowSelection="rowSelection">
      <!--插槽:table标题-->
      <template #tableTitle>
        <a-button type="primary" v-auth="'queue:queues:add'" @click="handleAdd" preIcon="ant-design:plus-outlined"> 新增</a-button>
        <a-button type="primary" v-auth="'queue:queues:exportXls'" preIcon="ant-design:export-outlined" @click="onExportXls"> 导出</a-button>
        <j-upload-button type="primary" v-auth="'queue:queues:importExcel'" preIcon="ant-design:import-outlined" @click="onImportXls"
          >导入</j-upload-button
        >
        <a-dropdown v-if="selectedRowKeys.length > 0">
          <template #overlay>
            <a-menu>
              <a-menu-item key="1" @click="batchHandleDelete">
                <Icon icon="ant-design:delete-outlined" />
                删除
              </a-menu-item>
            </a-menu>
          </template>
          <a-button v-auth="'queue:queues:deleteBatch'"
            >批量操作
            <Icon icon="mdi:chevron-down" />
          </a-button>
        </a-dropdown>
        <!-- 高级查询 -->
        <super-query :config="superQueryConfig" @search="handleSuperQuery" />
      </template>
      <!--操作栏-->
      <template #action="{ record }">
        <TableAction :actions="getTableAction(record)" :dropDownActions="getDropDownAction(record)" />
      </template>
      <template #bodyCell="{ column, record, index, text }"> </template>
    </BasicTable>
    <!-- 表单区域 -->
    <QueuesModal ref="registerModal" @success="handleSuccess" />
  </div>
</template>

<script lang="ts" name="queue-queues" setup>
  import { ref, reactive } from 'vue';
  import { BasicTable, useTable, TableAction } from '/@/components/Table';
  import { useListPage } from '/@/hooks/system/useListPage';
  import { columns, superQuerySchema } from '../index.data';
  import {
    list,
    deleteOne,
    batchDelete,
    getImportUrl,
    getExportUrl,
    getDeptList
  } from '../index.api';

  import QueuesModal from './IndexModal.vue';
  import { useUserStore } from '/@/store/modules/user';

  const props = defineProps<{
    roomId: string;
    doctorId: string;
  }>();

  const formRef = ref();
  const queryParam = reactive<any>({});
  const toggleSearchStatus = ref<boolean>(false);
  const registerModal = ref();
  const userStore = useUserStore();
  //注册table数据
  const { prefixCls, tableContext, onExportXls, onImportXls } = useListPage({
    tableProps: {
      pagination: false,
      title: '队列管理',
      api: list,
      columns,
      canResize: false,
      useSearchForm: false,
      actionColumn: {
        width: 120,
        fixed: 'right',
      },
      beforeFetch: async (params) => {
        return Object.assign(params, queryParam, { doctorId: props.doctorId });
      },
    },
    exportConfig: {
      name: '队列管理',
      url: getExportUrl,
      params: queryParam,
    },
    importConfig: {
      url: getImportUrl,
      success: handleSuccess,
    },
  });
  const [registerTable, { reload, collapseAll, updateTableDataRecord, findTableDataRecord, getDataSource }, { rowSelection, selectedRowKeys }] =
    tableContext;
  const labelCol = reactive({
    xs: 24,
    sm: 4,
    xl: 6,
    xxl: 4,
  });
  const wrapperCol = reactive({
    xs: 24,
    sm: 20,
  });

  // 高级查询配置
  const superQueryConfig = reactive(superQuerySchema);

  /**
   * 高级查询事件
   */
  function handleSuperQuery(params) {
    Object.keys(params).map((k) => {
      queryParam[k] = params[k];
    });
    searchQuery();
  }

  /**
   * 新增事件
   */
  function handleAdd() {
    // registerModal.value.disableSubmit = false;
    // registerModal.value.add();

    const frame = chrome.webview.hostObjects.viewHost;
    frame
      .OpReadFrm()
      .then((res) => {
        console.log('res')
      })
      .catch((error) => {
        console.error('Error retrieving MAC Address:', error);
      });
  }

  /**
   * 编辑事件
   */
  function handleEdit(record: Recordable) {
    registerModal.value.disableSubmit = false;
    registerModal.value.edit(record);
  }

  /**
   * 详情
   */
  function handleDetail(record: Recordable) {
    registerModal.value.disableSubmit = true;
    registerModal.value.edit(record);
  }

  /**
   * 删除事件
   */
  async function handleDelete(record) {
    await deleteOne({ id: record.id }, handleSuccess);
  }

  /**
   * 批量删除事件
   */
  async function batchHandleDelete() {
    await batchDelete({ ids: selectedRowKeys.value }, handleSuccess);
  }

  /**
   * 成功回调
   */
  function handleSuccess() {
    (selectedRowKeys.value = []) && reload();
  }

  /**
   * 操作栏
   */
  function getTableAction(record) {
    return [
      {
        label: '编辑',
        onClick: handleEdit.bind(null, record),
        auth: 'queue:queues:edit',
      },
    ];
  }

  /**
   * 下拉操作栏
   */
  function getDropDownAction(record) {
    return [
      {
        label: '详情',
        onClick: handleDetail.bind(null, record),
      },
      {
        label: '删除',
        popConfirm: {
          title: '是否确认删除',
          confirm: handleDelete.bind(null, record),
          placement: 'topLeft',
        },
        auth: 'queue:queues:delete',
      },
    ];
  }

  /**
   * 查询
   */
  function searchQuery() {
    reload();
  }

  /**
   * 重置
   */
  function searchReset() {
    formRef.value.resetFields();
    selectedRowKeys.value = [];
    //刷新数据
    reload();
  }
</script>

<style lang="less" scoped>
  .jeecg-basic-table-form-container {
    padding: 0;
    .table-page-search-submitButtons {
      display: block;
      margin-bottom: 24px;
      white-space: nowrap;
    }
    .query-group-cust {
      min-width: 100px !important;
    }
    .query-group-split-cust {
      width: 30px;
      display: inline-block;
      text-align: center;
    }
    .ant-form-item:not(.ant-form-item-with-help) {
      margin-bottom: 16px;
      height: 32px;
    }
    :deep(.ant-picker),
    :deep(.ant-input-number) {
      width: 100%;
    }
  }
</style>
