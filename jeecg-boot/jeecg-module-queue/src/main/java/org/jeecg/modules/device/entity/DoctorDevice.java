package org.jeecg.modules.device.entity;

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
 * @Description: 医生叫号器
 * @Author: jeecg-boot
 * @Date:   2024-09-23
 * @Version: V1.0
 */
@Data
@TableName("doctor_device")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="doctor_device对象", description="医生叫号器")
public class DoctorDevice implements Serializable {
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
	/**设备名称*/
	@Excel(name = "设备名称", width = 15)
    @ApiModelProperty(value = "设备名称")
    private java.lang.String deviceName;
	/**医生*/
	@Excel(name = "医生", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "id")
	@Dict(dictTable = "sys_user", dicText = "realname", dicCode = "id")
    @ApiModelProperty(value = "医生")
    private java.lang.String doctor;
	/**位置描述*/
	@Excel(name = "位置描述", width = 15)
    @ApiModelProperty(value = "位置描述")
    private java.lang.String location;
	/**设备状态*/
	@Excel(name = "设备状态", width = 15)
    @ApiModelProperty(value = "设备状态")
    private java.lang.String status;
	/**最后一次活跃时间*/
	@Excel(name = "最后一次活跃时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后一次活跃时间")
    private java.util.Date lastActive;
	/**ip地址*/
	@Excel(name = "ip地址", width = 15)
    @ApiModelProperty(value = "ip地址")
    private java.lang.String ipAddress;
	/**科室*/
	@Excel(name = "科室", width = 15, dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
	@Dict(dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
    @ApiModelProperty(value = "科室")
    private java.lang.String department;
	/**MAC地址*/
	@Excel(name = "MAC地址", width = 15)
    @ApiModelProperty(value = "MAC地址")
    private java.lang.String mac;
	/**诊室*/
	@Excel(name = "诊室", width = 15, dictTable = "rooms", dicText = "room_number", dicCode = "id")
	@Dict(dictTable = "rooms", dicText = "room_number", dicCode = "id")
    @ApiModelProperty(value = "诊室")
    private java.lang.String room;
}
