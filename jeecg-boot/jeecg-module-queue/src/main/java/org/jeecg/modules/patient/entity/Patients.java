package org.jeecg.modules.patient.entity;

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
 * @Description: 患者信息
 * @Author: jeecg-boot
 * @Date:   2024-10-05
 * @Version: V1.0
 */
@Data
@TableName("patients")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="patients对象", description="患者信息")
public class Patients implements Serializable {
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
	/**患者姓名*/
	@Excel(name = "患者姓名", width = 15)
    @ApiModelProperty(value = "患者姓名")
    private java.lang.String name;
	/**性别*/
	@Excel(name = "性别", width = 15, dicCode = "sex")
	@Dict(dicCode = "sex")
    @ApiModelProperty(value = "性别")
    private java.lang.String gender;
	/**出生日期*/
	@Excel(name = "出生日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "出生日期")
    private java.util.Date dateBirth;
	/**电话*/
	@Excel(name = "电话", width = 15)
    @ApiModelProperty(value = "电话")
    private java.lang.String phone;
	/**身份证*/
	@Excel(name = "身份证", width = 15)
    @ApiModelProperty(value = "身份证")
    private java.lang.String idNumber;
	/**挂号医生*/
	@Excel(name = "挂号医生", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "id")
	@Dict(dictTable = "sys_user", dicText = "realname", dicCode = "id")
    @ApiModelProperty(value = "挂号医生")
    private java.lang.String doctor;
	/**预约日期*/
	@Excel(name = "预约日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "预约日期")
    private java.util.Date appDate;
	/**预约类型*/
	@Excel(name = "预约类型", width = 15)
    @ApiModelProperty(value = "预约类型")
    private java.lang.String appType;
	/**签到时间*/
	@Excel(name = "签到时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "签到时间")
    private java.util.Date checkTime;
	/**预约id*/
	@Excel(name = "预约id", width = 15)
    @ApiModelProperty(value = "预约id")
    private java.lang.String appId;
	/**科室*/
	@Excel(name = "科室", width = 15, dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
	@Dict(dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
    @ApiModelProperty(value = "科室")
    private java.lang.String department;
	/**诊室*/
	@Excel(name = "诊室", width = 15, dictTable = "rooms", dicText = "room_number", dicCode = "id")
	@Dict(dictTable = "rooms", dicText = "room_number", dicCode = "id")
    @ApiModelProperty(value = "诊室")
    private java.lang.String room;
}
