import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '患者姓名',
    align: "center",
    dataIndex: 'name'
  },
  {
    title: '性别',
    align: "center",
    dataIndex: 'gender_dictText'
  },
  {
    title: '电话',
    align: "center",
    dataIndex: 'phone'
  },
  {
    title: '身份证',
    align: "center",
    dataIndex: 'idNumber'
  },
  {
    title: '挂号医生',
    align: "center",
    dataIndex: 'doctor_dictText'
  },
  {
    title: '预约日期',
    align: "center",
    dataIndex: 'appDate',
    customRender:({text}) =>{
      text = !text ? "" : (text.length > 10 ? text.substr(0,10) : text);
      return text;
    },
  },
  {
    title: '预约类型',
    align: "center",
    dataIndex: 'appType'
  },
  {
    title: '签到时间',
    align: "center",
    dataIndex: 'checkTime'
  },
  {
    title: '预约id',
    align: "center",
    dataIndex: 'appId'
  },
  {
    title: '科室',
    align: "center",
    dataIndex: 'department_dictText'
  },
  {
    title: '诊室',
    align: "center",
    dataIndex: 'room_dictText'
  },
  {
    title: '预约时间段',
    align: "center",
    dataIndex: 'appointmentTimeSlot'
  },
];

// 高级查询数据
export const superQuerySchema = {
  name: {title: '患者姓名',order: 0,view: 'text', type: 'string',},
  gender: {title: '性别',order: 1,view: 'list', type: 'string',dictCode: 'sex',},
  phone: {title: '电话',order: 2,view: 'text', type: 'string',},
  idNumber: {title: '身份证',order: 3,view: 'text', type: 'string',},
  doctor: {title: '挂号医生',order: 4,view: 'sel_search', type: 'string',dictTable: "sys_user", dictCode: 'id', dictText: 'realname',},
  appDate: {title: '预约日期',order: 5,view: 'date', type: 'string',},
  appType: {title: '预约类型',order: 6,view: 'text', type: 'string',},
  checkTime: {title: '签到时间',order: 7,view: 'datetime', type: 'string',},
  appId: {title: '预约id',order: 8,view: 'text', type: 'string',},
  department: {title: '科室',order: 9,view: 'sel_search', type: 'string',dictTable: "sys_depart", dictCode: 'id', dictText: 'depart_name',},
  room: {title: '诊室',order: 10,view: 'sel_search', type: 'string',dictTable: "rooms", dictCode: 'id', dictText: 'room_number',},
  appointmentTimeSlot: {title: '预约时间段',order: 11,view: 'text', type: 'string',},
};
