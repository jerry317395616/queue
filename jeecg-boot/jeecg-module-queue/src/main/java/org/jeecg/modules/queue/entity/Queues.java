package org.jeecg.modules.queue.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import org.jeecg.common.constant.ProvinceCityArea;
import org.jeecg.common.util.SpringContextUtils;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 队列管理
 * @Author: jeecg-boot
 * @Date:   2024-09-28
 * @Version: V1.0
 */
@Data
@TableName("queues")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="queues对象", description="队列管理")
public class Queues implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**患者*/
	@Excel(name = "患者", width = 15, dictTable = "patients", dicText = "name", dicCode = "id")
	@Dict(dictTable = "patients", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "患者")
    private java.lang.String patient;
	/**排队序号*/
	@Excel(name = "排队序号", width = 15)
    @ApiModelProperty(value = "排队序号")
    private java.lang.Integer queueNumber;
	/**科室名称*/
	@Excel(name = "科室名称", width = 15, dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
	@Dict(dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
    @ApiModelProperty(value = "科室名称")
    private java.lang.String department;
	/**当前队列状态*/
	@Excel(name = "当前队列状态", width = 15, dicCode = "queue_status")
	@Dict(dicCode = "queue_status")
    @ApiModelProperty(value = "当前队列状态")
    private java.lang.String queueStatus;
	/**排队优先级*/
	@Excel(name = "排队优先级", width = 15, dicCode = "queue_priority")
	@Dict(dicCode = "queue_priority")
    @ApiModelProperty(value = "排队优先级")
    private java.lang.String queuePriority;
	/**患者签到时间*/
	@Excel(name = "患者签到时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "患者签到时间")
    private java.util.Date checkTime;
	/**挂号医生*/
	@Excel(name = "挂号医生", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "id")
	@Dict(dictTable = "sys_user", dicText = "realname", dicCode = "id")
    @ApiModelProperty(value = "挂号医生")
    private java.lang.String doctor;
	/**诊室*/
	@Excel(name = "诊室", width = 15, dictTable = "rooms", dicText = "room_number", dicCode = "id")
	@Dict(dictTable = "rooms", dicText = "room_number", dicCode = "id")
    @ApiModelProperty(value = "诊室")
    private java.lang.String room;
}
