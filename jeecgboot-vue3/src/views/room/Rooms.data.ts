import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '诊室名称',
    align: "center",
    dataIndex: 'roomNumber'
  },
  {
    title: '所属科室',
    align: "center",
    dataIndex: 'department_dictText'
  },
  {
    title: '状态',
    align: "center",
    dataIndex: 'status',
    customRender:({text}) => {
       return  render.renderSwitch(text, [{text:'是',value:'Y'},{text:'否',value:'N'}]);
     },
  },
  {
    title: '所属诊区',
    align: "center",
    dataIndex: 'clinicArea_dictText'
  },
];

// 高级查询数据
export const superQuerySchema = {
  roomNumber: {title: '诊室名称',order: 0,view: 'text', type: 'string',},
  department: {title: '所属科室',order: 1,view: 'sel_search', type: 'string',dictTable: "sys_depart", dictCode: 'id', dictText: 'depart_name',},
  status: {title: '状态',order: 2,view: 'switch', type: 'string',},
  clinicArea: {title: '所属诊区',order: 3,view: 'sel_search', type: 'string',dictTable: "clinic_area", dictCode: 'id', dictText: 'name',},
};
