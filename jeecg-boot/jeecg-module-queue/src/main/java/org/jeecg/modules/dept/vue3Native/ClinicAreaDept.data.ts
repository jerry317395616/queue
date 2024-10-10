import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '诊区',
    align: "center",
    dataIndex: 'clinicArea_dictText'
  },
  {
    title: '科室',
    align: "center",
    dataIndex: 'department_dictText'
  },
];

// 高级查询数据
export const superQuerySchema = {
  clinicArea: {title: '诊区',order: 0,view: 'sel_search', type: 'string',dictTable: "clinic_area", dictCode: 'id', dictText: 'name',},
  department: {title: '科室',order: 1,view: 'sel_search', type: 'string',dictTable: "sys_depart", dictCode: 'id', dictText: 'depart_name',},
};
