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
    dataIndex: 'NAME_PI'
  },
  {
    title: '性别',
    align: "center",
    dataIndex: 'GENDER'
  },
  {
    title: '电话',
    align: "center",
    dataIndex: 'MOBILE'
  },
  {
    title: '身份证',
    align: "center",
    dataIndex: 'ID_NO'
  },
  {
    title: '挂号医生',
    align: "center",
    dataIndex: 'NAME_EMP'
  },
  {
    title: '预约日期',
    align: "center",
    dataIndex: 'DATE_APPT',
    customRender:({text}) =>{
      text = !text ? "" : (text.length > 10 ? text.substr(0,10) : text);
      return text;
    },
  },
  {
    title: '预约时段',
    align: "center",
    dataIndex: 'APPOINT_TIME'
  },

  {
    title: '预约类型',
    align: "center",
    dataIndex: 'APPTYPE'
  },
  {
    title: '预约id',
    align: "center",
    dataIndex: 'ID'
  },
  {
    title: '科室',
    align: "center",
    dataIndex: 'NAME_DEPT'
  },
];

// 高级查询数据
export const superQuerySchema = {
  name: {title: '患者姓名',order: 0,view: 'text', type: 'string',},
  gender: {title: '性别',order: 1,view: 'list', type: 'string',dictCode: 'sex',},
  dateBirth: {title: '出生日期',order: 2,view: 'date', type: 'string',},
  phone: {title: '电话',order: 3,view: 'text', type: 'string',},
  idNumber: {title: '身份证',order: 4,view: 'text', type: 'string',},
  doctor: {title: '挂号医生',order: 5,view: 'sel_search', type: 'string',dictTable: "sys_user", dictCode: 'id', dictText: 'realname',},
  appDate: {title: '预约日期',order: 6,view: 'date', type: 'string',},
  appType: {title: '预约类型',order: 7,view: 'text', type: 'string',},
  checkTime: {title: '签到时间',order: 8,view: 'datetime', type: 'string',},
  appId: {title: '预约id',order: 9,view: 'text', type: 'string',},
  department: {title: '科室',order: 10,view: 'sel_search', type: 'string',dictTable: "sys_depart", dictCode: 'id', dictText: 'depart_name',},
  room: {title: '诊室',order: 11,view: 'sel_search', type: 'string',dictTable: "rooms", dictCode: 'id', dictText: 'room_number',},
};
