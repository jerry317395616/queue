import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: 'MAC地址',
    align: "center",
    dataIndex: 'macAddress'
  },
  {
    title: 'IP地址',
    align: "center",
    dataIndex: 'ipAddress'
  },
  {
    title: '位置',
    align: "center",
    dataIndex: 'location'
  },
  {
    title: '管理科室',
    align: "center",
    dataIndex: 'depts_dictText'
  },
];

// 高级查询数据
export const superQuerySchema = {
  macAddress: {title: 'MAC地址',order: 0,view: 'text', type: 'string',},
  ipAddress: {title: 'IP地址',order: 1,view: 'text', type: 'string',},
  location: {title: '位置',order: 2,view: 'text', type: 'string',},
  depts: {title: '管理科室',order: 3,view: 'list_multi', type: 'string',dictTable: "sys_depart", dictCode: 'id', dictText: 'depart_name',},
};
