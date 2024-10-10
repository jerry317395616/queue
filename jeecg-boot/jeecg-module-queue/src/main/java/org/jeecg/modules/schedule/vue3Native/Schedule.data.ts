import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '医生',
    align: "center",
    dataIndex: 'doctor_dictText'
  },
  {
    title: '科室',
    align: "center",
    dataIndex: 'department_dictText'
  },
  {
    title: '排班日期',
    align: "center",
    dataIndex: 'scheduleDate',
    customRender:({text}) =>{
      text = !text ? "" : (text.length > 10 ? text.substr(0,10) : text);
      return text;
    },
  },
  {
    title: '排班时段',
    align: "center",
    dataIndex: 'timeSlot'
  },
  {
    title: '状态',
    align: "center",
    dataIndex: 'status'
  },
];

// 高级查询数据
export const superQuerySchema = {
  doctor: {title: '医生',order: 0,view: 'sel_search', type: 'string',dictTable: "sys_user", dictCode: 'id', dictText: 'realname',},
  department: {title: '科室',order: 1,view: 'sel_search', type: 'string',dictTable: "sys_depart", dictCode: 'id', dictText: 'depart_name',},
  scheduleDate: {title: '排班日期',order: 2,view: 'date', type: 'string',},
  timeSlot: {title: '排班时段',order: 3,view: 'text', type: 'string',},
  status: {title: '状态',order: 4,view: 'text', type: 'string',},
};
