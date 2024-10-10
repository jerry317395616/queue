import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '诊区名称',
    align: "center",
    dataIndex: 'name'
  },
  {
    title: '位置',
    align: "center",
    dataIndex: 'location'
  },
  {
    title: '备注',
    align: "center",
    dataIndex: 'remarks'
  },
  {
    title: '是否激活',
    align: "center",
    dataIndex: 'isActive',
    customRender:({text}) => {
       return  render.renderSwitch(text, [{text:'是',value:'Y'},{text:'否',value:'N'}]);
     },
  },
];

// 高级查询数据
export const superQuerySchema = {
  name: {title: '诊区名称',order: 0,view: 'text', type: 'string',},
  location: {title: '位置',order: 1,view: 'text', type: 'string',},
  remarks: {title: '备注',order: 2,view: 'text', type: 'string',},
  isActive: {title: '是否激活',order: 3,view: 'switch', type: 'string',},
};
