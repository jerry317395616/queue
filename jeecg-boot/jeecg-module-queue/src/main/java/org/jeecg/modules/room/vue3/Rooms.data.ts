import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '诊室名称',
    align:"center",
    dataIndex: 'roomNumber'
   },
   {
    title: '所属科室',
    align:"center",
    dataIndex: 'department_dictText'
   },
   {
    title: '状态',
    align:"center",
    dataIndex: 'status',
    customRender:({text}) => {
       return  render.renderSwitch(text, [{text:'是',value:'Y'},{text:'否',value:'N'}])
     },
   },
   {
    title: '所属诊区',
    align:"center",
    dataIndex: 'clinicArea_dictText'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '诊室名称',
    field: 'roomNumber',
    component: 'Input',
  },
  {
    label: '所属科室',
    field: 'department',
     component: 'JSelectDept',
     componentProps:{
      },
  },
  {
    label: '状态',
    field: 'status',
     component: 'JSwitch',
     componentProps:{
     },
  },
  {
    label: '所属诊区',
    field: 'clinicArea',
    component: 'JSearchSelect',
    componentProps:{
       dict:"clinic_area,name,id"
    },
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];

// 高级查询数据
export const superQuerySchema = {
  roomNumber: {title: '诊室名称',order: 0,view: 'text', type: 'string',},
  department: {title: '所属科室',order: 1,view: 'sel_depart', type: 'string',},
  status: {title: '状态',order: 2,view: 'switch', type: 'string',},
  clinicArea: {title: '所属诊区',order: 3,view: 'sel_search', type: 'string',dictTable: "clinic_area", dictCode: 'id', dictText: 'name',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}