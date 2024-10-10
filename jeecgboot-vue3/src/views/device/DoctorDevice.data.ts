import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '设备名称',
    align: "center",
    dataIndex: 'deviceName'
  },
  {
    title: '医生',
    align: "center",
    dataIndex: 'doctor_dictText'
  },
  {
    title: '位置描述',
    align: "center",
    dataIndex: 'location'
  },
  {
    title: '设备状态',
    align: "center",
    dataIndex: 'status'
  },
  {
    title: '最后一次活跃时间',
    align: "center",
    dataIndex: 'lastActive'
  },
  {
    title: 'ip地址',
    align: "center",
    dataIndex: 'ipAddress'
  },
  {
    title: '科室',
    align: "center",
    dataIndex: 'department_dictText'
  },
  {
    title: 'MAC地址',
    align: "center",
    dataIndex: 'mac'
  },
  {
    title: '诊室',
    align: "center",
    dataIndex: 'room_dictText'
  },
];

// 高级查询数据
export const superQuerySchema = {
  deviceName: {title: '设备名称',order: 0,view: 'text', type: 'string',},
  doctor: {title: '医生',order: 1,view: 'sel_search', type: 'string',dictTable: "sys_user", dictCode: 'id', dictText: 'realname',},
  location: {title: '位置描述',order: 2,view: 'text', type: 'string',},
  status: {title: '设备状态',order: 3,view: 'text', type: 'string',},
  lastActive: {title: '最后一次活跃时间',order: 4,view: 'datetime', type: 'string',},
  ipAddress: {title: 'ip地址',order: 5,view: 'text', type: 'string',},
  department: {title: '科室',order: 6,view: 'sel_search', type: 'string',dictTable: "sys_depart", dictCode: 'id', dictText: 'depart_name',},
  mac: {title: 'MAC地址',order: 7,view: 'text', type: 'string',},
  room: {title: '诊室',order: 8,view: 'sel_search', type: 'string',dictTable: "rooms", dictCode: 'id', dictText: 'room_number',},
};
