<template>
  <a-spin :spinning="confirmLoading">
    <JFormContainer :disabled="disabled">
      <template #detail>
        <a-form ref="formRef" class="antd-modal-form" :labelCol="labelCol" :wrapperCol="wrapperCol" name="DisplayScreensForm">
          <a-row>
						<a-col :span="24">
							<a-form-item label="IP" v-bind="validateInfos.ipAddress" id="DisplayScreensForm-ipAddress" name="ipAddress">
								<a-input v-model:value="formData.ipAddress" placeholder="请输入IP"  allow-clear ></a-input>
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="MAC" v-bind="validateInfos.macAddress" id="DisplayScreensForm-macAddress" name="macAddress">
								<a-input v-model:value="formData.macAddress" placeholder="请输入MAC"  allow-clear ></a-input>
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="类型" v-bind="validateInfos.screenType" id="DisplayScreensForm-screenType" name="screenType">
								<a-input v-model:value="formData.screenType" placeholder="请输入类型"  allow-clear ></a-input>
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="是否启用" v-bind="validateInfos.isActive" id="DisplayScreensForm-isActive" name="isActive">
								<j-switch v-model:value="formData.isActive"  ></j-switch>
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="诊区" v-bind="validateInfos.clinicArea" id="DisplayScreensForm-clinicArea" name="clinicArea">
								<j-search-select v-model:value="formData.clinicArea" dict="clinic_area,name,id"  allow-clear />
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="科室" v-bind="validateInfos.depId" id="DisplayScreensForm-depId" name="depId">
								<j-search-select v-model:value="formData.depId" dict="sys_depart,depart_name,id"  allow-clear />
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="诊室" v-bind="validateInfos.room" id="DisplayScreensForm-room" name="room">
								<j-search-select v-model:value="formData.room" dict="rooms,room_number,id"  allow-clear />
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="界面url" v-bind="validateInfos.pageUrl" id="DisplayScreensForm-pageUrl" name="pageUrl">
								<a-textarea v-model:value="formData.pageUrl" :rows="4" placeholder="请输入界面url" />
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
  import JSwitch from '/@/components/Form/src/jeecg/components/JSwitch.vue';
  import JSearchSelect from '/@/components/Form/src/jeecg/components/JSearchSelect.vue';
  import { getValueType } from '/@/utils';
  import { saveOrUpdate } from '../DisplayScreens.api';
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
    ipAddress: '',   
    macAddress: '',   
    screenType: '',   
    isActive: '',   
    clinicArea: '',   
    depId: '',   
    room: '',   
    pageUrl: '',   
  });
  const { createMessage } = useMessage();
  const labelCol = ref<any>({ xs: { span: 24 }, sm: { span: 5 } });
  const wrapperCol = ref<any>({ xs: { span: 24 }, sm: { span: 16 } });
  const confirmLoading = ref<boolean>(false);
  //表单验证
  const validatorRules = reactive({
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
