package org.jeecg.modules.check.entity;

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
 * @Description: 签到机
 * @Author: jeecg-boot
 * @Date:   2024-10-05
 * @Version: V1.0
 */
@Data
@TableName("check_machine")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="check_machine对象", description="签到机")
public class CheckMachine implements Serializable {
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
	/**科室*/
	@Excel(name = "科室", width = 15, dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
	@Dict(dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
    @ApiModelProperty(value = "科室")
    private java.lang.String department;
	/**ip地址*/
	@Excel(name = "ip地址", width = 15)
    @ApiModelProperty(value = "ip地址")
    private java.lang.String ipAddress;
	/**mac地址*/
	@Excel(name = "mac地址", width = 15)
    @ApiModelProperty(value = "mac地址")
    private java.lang.String macAddress;
	/**诊区*/
	@Excel(name = "诊区", width = 15, dictTable = "clinic_area", dicText = "name", dicCode = "id")
	@Dict(dictTable = "clinic_area", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "诊区")
    private java.lang.String clinicArea;
	/**可签到科室*/
	@Excel(name = "可签到科室", width = 15, dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
	@Dict(dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
    @ApiModelProperty(value = "可签到科室")
    private java.lang.String signableDept;
}
