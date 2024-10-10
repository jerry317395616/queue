<template>
  <a-spin :spinning="confirmLoading">
    <JFormContainer :disabled="disabled">
      <template #detail>
        <a-form ref="formRef" class="antd-modal-form" :labelCol="labelCol" :wrapperCol="wrapperCol" name="PatientsForm">
          <a-row>
						<a-col :span="24">
							<a-form-item label="患者姓名" v-bind="validateInfos.name" id="PatientsForm-name" name="name">
								<a-input v-model:value="formData.name" placeholder="请输入患者姓名"  allow-clear ></a-input>
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="性别" v-bind="validateInfos.gender" id="PatientsForm-gender" name="gender">
								<j-dict-select-tag v-model:value="formData.gender" dictCode="sex" placeholder="请选择性别"  allow-clear />
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="出生日期" v-bind="validateInfos.dateBirth" id="PatientsForm-dateBirth" name="dateBirth">
								<a-date-picker placeholder="请选择出生日期"  v-model:value="formData.dateBirth" value-format="YYYY-MM-DD"  style="width: 100%"  allow-clear />
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="电话" v-bind="validateInfos.phone" id="PatientsForm-phone" name="phone">
								<a-input v-model:value="formData.phone" placeholder="请输入电话"  allow-clear ></a-input>
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="身份证" v-bind="validateInfos.idNumber" id="PatientsForm-idNumber" name="idNumber">
								<a-input v-model:value="formData.idNumber" placeholder="请输入身份证"  allow-clear ></a-input>
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="挂号医生" v-bind="validateInfos.doctor" id="PatientsForm-doctor" name="doctor">
								<j-search-select v-model:value="formData.doctor" dict="sys_user,realname,id"  allow-clear />
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="预约日期" v-bind="validateInfos.appDate" id="PatientsForm-appDate" name="appDate">
								<a-date-picker placeholder="请选择预约日期"  v-model:value="formData.appDate" value-format="YYYY-MM-DD"  style="width: 100%"  allow-clear />
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="预约类型" v-bind="validateInfos.appType" id="PatientsForm-appType" name="appType">
								<a-input v-model:value="formData.appType" placeholder="请输入预约类型"  allow-clear ></a-input>
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="签到时间" v-bind="validateInfos.checkTime" id="PatientsForm-checkTime" name="checkTime">
								<a-date-picker placeholder="请选择签到时间"  v-model:value="formData.checkTime" showTime value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"  allow-clear />
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="预约id" v-bind="validateInfos.appId" id="PatientsForm-appId" name="appId">
								<a-input v-model:value="formData.appId" placeholder="请输入预约id"  allow-clear ></a-input>
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="科室" v-bind="validateInfos.department" id="PatientsForm-department" name="department">
								<j-search-select v-model:value="formData.department" dict="sys_depart,depart_name,id"  allow-clear />
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="诊室" v-bind="validateInfos.room" id="PatientsForm-room" name="room">
								<j-search-select v-model:value="formData.room" dict="rooms,room_number,id"  allow-clear />
							</a-form-item>
						</a-col>
          </a-row>
        </a-form>
      </template>
    </JFormContainer>
  </a-spin>
</template>

<script lang="ts" setup>
  import { ref, reactive, defineExpose, nextTick, defineProps, computed, onMounted } from 'vue';
  import { defHttp } from '/@/utils/http/axios';
  import { useMessage } from '/@/hooks/web/useMessage';
  import JDictSelectTag from '/@/components/Form/src/jeecg/components/JDictSelectTag.vue';
  import JSearchSelect from '/@/components/Form/src/jeecg/components/JSearchSelect.vue';
  import { getValueType } from '/@/utils';
  import { saveOrUpdate } from '../Patients.api';
  import { Form } from 'ant-design-vue';
  import JFormContainer from '/@/components/Form/src/container/JFormContainer.vue';
  const props = defineProps({
    formDisabled: { type: Boolean, default: false },
    formData: { type: Object, default: () => ({})},
    formBpm: { type: Boolean, default: true }
  });
  const formRef = ref();
  const useForm = Form.useForm;
  const emit = defineEmits(['register', 'ok']);
  const formData = reactive<Record<string, any>>({
    id: '',
    name: '',   
    gender: '',   
    dateBirth: '',   
    phone: '',   
    idNumber: '',   
    doctor: '',   
    appDate: '',   
    appType: '',   
    checkTime: '',   
    appId: '',   
    department: '',   
    room: '',   
  });
  const { createMessage } = useMessage();
  const labelCol = ref<any>({ xs: { span: 24 }, sm: { span: 5 } });
  const wrapperCol = ref<any>({ xs: { span: 24 }, sm: { span: 16 } });
  const confirmLoading = ref<boolean>(false);
  //表单验证
  const validatorRules = reactive({
    name: [{ required: true, message: '请输入患者姓名!'},],
    gender: [{ required: true, message: '请输入性别!'},],
    dateBirth: [{ required: true, message: '请输入出生日期!'},],
    phone: [{ required: true, message: '请输入电话!'},],
    idNumber: [{ required: true, message: '请输入身份证!'},],
  });
  const { resetFields, validate, validateInfos } = useForm(formData, validatorRules, { immediate: false });

  // 表单禁用
  const disabled = computed(()=>{
    if(props.formBpm === true){
      if(props.formData.disabled === false){
        return false;
      }else{
        return true;
      }
    }
    return props.formDisabled;
  });

  
  /**
   * 新增
   */
  function add() {
    edit({});
  }

  /**
   * 编辑
   */
  function edit(record) {
    nextTick(() => {
      resetFields();
      const tmpData = {};
      Object.keys(formData).forEach((key) => {
        if(record.hasOwnProperty(key)){
          tmpData[key] = record[key]
        }
      })
      //赋值
      Object.assign(formData, tmpData);
    });
  }

  /**
   * 提交数据
   */
  async function submitForm() {
    try {
      // 触发表单验证
      await validate();
    } catch ({ errorFields }) {
      if (errorFields) {
        const firstField = errorFields[0];
        if (firstField) {
          formRef.value.scrollToField(firstField.name, { behavior: 'smooth', block: 'center' });
        }
      }
      return Promise.reject(errorFields);
    }
    confirmLoading.value = true;
    const isUpdate = ref<boolean>(false);
    //时间格式化
    let model = formData;
    if (model.id) {
      isUpdate.value = true;
    }
    //循环数据
    for (let data in model) {
      //如果该数据是数组并且是字符串类型
      if (model[data] instanceof Array) {
        let valueType = getValueType(formRef.value.getProps, data);
        //如果是字符串类型的需要变成以逗号分割的字符串
        if (valueType === 'string') {
          model[data] = model[data].join(',');
        }
      }
    }
    await saveOrUpdate(model, isUpdate.value)
      .then((res) => {
        if (res.success) {
          createMessage.success(res.message);
          emit('ok');
        } else {
          createMessage.warning(res.message);
        }
      })
      .finally(() => {
        confirmLoading.value = false;
      });
  }


  defineExpose({
    add,
    edit,
    submitForm,
  });
</script>

<style lang="less" scoped>
  .antd-modal-form {
    padding: 14px;
  }
</style>
