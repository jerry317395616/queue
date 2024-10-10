import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: 'IP',
    align: "center",
    dataIndex: 'ipAddress'
  },
  {
    title: 'MAC',
    align: "center",
    dataIndex: 'macAddress'
  },
  {
    title: '类型',
    align: "center",
    dataIndex: 'screenType'
  },
  {
    title: '是否启用',
    align: "center",
    dataIndex: 'isActive',
    customRender:({text}) => {
       return  render.renderSwitch(text, [{text:'是',value:'Y'},{text:'否',value:'N'}]);
     },
  },
  {
    title: '诊区',
    align: "center",
    dataIndex: 'clinicArea_dictText'
  },
  {
    title: '科室',
    align: "center",
    dataIndex: 'depId_dictText'
  },
  {
    title: '诊室',
    align: "center",
    dataIndex: 'room_dictText'
  },
  {
    title: '界面url',
    align: "center",
    dataIndex: 'pageUrl'
  },
];

// 高级查询数据
export const superQuerySchema = {
  ipAddress: {title: 'IP',order: 0,view: 'text', type: 'string',},
  macAddress: {title: 'MAC',order: 1,view: 'text', type: 'string',},
  screenType: {title: '类型',order: 2,view: 'text', type: 'string',},
  isActive: {title: '是否启用',order: 3,view: 'switch', type: 'string',},
  clinicArea: {title: '诊区',order: 4,view: 'sel_search', type: 'string',dictTable: "clinic_area", dictCode: 'id', dictText: 'name',},
  depId: {title: '科室',order: 5,view: 'sel_search', type: 'string',dictTable: "sys_depart", dictCode: 'id', dictText: 'depart_name',},
  room: {title: '诊室',order: 6,view: 'sel_search', type: 'string',dictTable: "rooms", dictCode: 'id', dictText: 'room_number',},
  pageUrl: {title: '界面url',order: 7,view: 'textarea', type: 'string',},
};
