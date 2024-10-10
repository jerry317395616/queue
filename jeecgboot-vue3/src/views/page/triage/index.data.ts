import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
    {
        title: '排队序号',
        align: "center",
        dataIndex: 'queue_number'
    },
    {
        title: '姓名',
        align: "center",
        dataIndex: 'name'
    },
    {
        title: 'ID',
        align: "center",
        dataIndex: 'department_dictText'
    },
    {
        title: '性别',
        align: "center",
        dataIndex: 'gender'
    },
    {
        title: '年龄',
        align: "center",
        dataIndex: 'age'
    },
    {
        title: '看诊时间',
        align: "center",
        dataIndex: 'checkTime'
    },
    {
        title: '分诊状态',
        align: "center",
        dataIndex: 'queue_status'
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
