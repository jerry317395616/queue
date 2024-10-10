import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '科室',
    align: "center",
    dataIndex: 'department_dictText'
  },
  {
    title: 'ip地址',
    align: "center",
    dataIndex: 'ipAddress'
  },
  {
    title: 'mac地址',
    align: "center",
    dataIndex: 'macAddress'
  },
  {
    title: '诊区',
    align: "center",
    dataIndex: 'clinicArea_dictText'
  },
  {
    title: '可签到科室',
    align: "center",
    dataIndex: 'signableDept_dictText'
  },
];

// 高级查询数据
export const superQuerySchema = {
  department: {title: '科室',order: 0,view: 'sel_search', type: 'string',dictTable: "sys_depart", dictCode: 'id', dictText: 'depart_name',},
  ipAddress: {title: 'ip地址',order: 1,view: 'text', type: 'string',},
  macAddress: {title: 'mac地址',order: 2,view: 'text', type: 'string',},
  clinicArea: {title: '诊区',order: 3,view: 'sel_search', type: 'string',dictTable: "clinic_area", dictCode: 'id', dictText: 'name',},
  signableDept: {title: '可签到科室',order: 4,view: 'list_multi', type: 'string',dictTable: "sys_depart", dictCode: 'id', dictText: 'depart_name',},
};
