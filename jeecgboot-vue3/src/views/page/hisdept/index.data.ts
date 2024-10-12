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
    dataIndex: 'NAME_DEPT'
  },
  {
    title: '科室编码',
    align: "center",
    dataIndex: 'CODE_DEPT'
  },

];

// 高级查询数据
export const superQuerySchema = {
  name: {title: '医生姓名',order: 0,view: 'text', type: 'string',},
  gender: {title: '医生编码',order: 1,view: 'list', type: 'string',dictCode: 'sex',},

};
