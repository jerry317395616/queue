import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '患者',
    align: "center",
    dataIndex: 'patient_dictText'
  },
  {
    title: '排队序号',
    align: "center",
    dataIndex: 'queueNumber'
  },
  {
    title: '科室名称',
    align: "center",
    dataIndex: 'department_dictText'
  },
  {
    title: '当前队列状态',
    align: "center",
    dataIndex: 'queueStatus_dictText'
  },
  {
    title: '排队优先级',
    align: "center",
    dataIndex: 'queuePriority_dictText'
  },
  {
    title: '患者签到时间',
    align: "center",
    dataIndex: 'checkTime'
  },
  {
    title: '挂号医生',
    align: "center",
    dataIndex: 'doctor_dictText'
  },
  {
    title: '诊室',
    align: "center",
    dataIndex: 'room_dictText'
  },
];

// 高级查询数据
export const superQuerySchema = {
  patient: {title: '患者',order: 0,view: 'sel_search', type: 'string',dictTable: "patients", dictCode: 'id', dictText: 'name',},
  queueNumber: {title: '排队序号',order: 1,view: 'number', type: 'number',},
  department: {title: '科室名称',order: 2,view: 'sel_search', type: 'string',dictTable: "sys_depart", dictCode: 'id', dictText: 'depart_name',},
  queueStatus: {title: '当前队列状态',order: 3,view: 'list', type: 'string',dictCode: 'queue_status',},
  queuePriority: {title: '排队优先级',order: 4,view: 'list', type: 'string',dictCode: 'queue_priority',},
  checkTime: {title: '患者签到时间',order: 5,view: 'datetime', type: 'string',},
  doctor: {title: '挂号医生',order: 6,view: 'sel_search', type: 'string',dictTable: "sys_user", dictCode: 'id', dictText: 'realname',},
  room: {title: '诊室',order: 7,view: 'sel_search', type: 'string',dictTable: "rooms", dictCode: 'id', dictText: 'room_number',},
};
