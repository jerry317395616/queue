package org.jeecg.modules.screen.entity;

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
 * @Description: 显示屏配置
 * @Author: jeecg-boot
 * @Date:   2024-10-05
 * @Version: V1.0
 */
@Data
@TableName("display_screens")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="display_screens对象", description="显示屏配置")
public class DisplayScreens implements Serializable {
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
	/**IP*/
	@Excel(name = "IP", width = 15)
    @ApiModelProperty(value = "IP")
    private java.lang.String ipAddress;
	/**MAC*/
	@Excel(name = "MAC", width = 15)
    @ApiModelProperty(value = "MAC")
    private java.lang.String macAddress;
	/**类型*/
	@Excel(name = "类型", width = 15)
    @ApiModelProperty(value = "类型")
    private java.lang.String screenType;
	/**是否启用*/
    @Excel(name = "是否启用", width = 15,replace = {"是_Y","否_N"} )
    @ApiModelProperty(value = "是否启用")
    private java.lang.String isActive;
	/**诊区*/
	@Excel(name = "诊区", width = 15, dictTable = "clinic_area", dicText = "name", dicCode = "id")
	@Dict(dictTable = "clinic_area", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "诊区")
    private java.lang.String clinicArea;
	/**科室*/
	@Excel(name = "科室", width = 15, dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
	@Dict(dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
    @ApiModelProperty(value = "科室")
    private java.lang.String depId;
	/**诊室*/
	@Excel(name = "诊室", width = 15, dictTable = "rooms", dicText = "room_number", dicCode = "id")
	@Dict(dictTable = "rooms", dicText = "room_number", dicCode = "id")
    @ApiModelProperty(value = "诊室")
    private java.lang.String room;
	/**界面url*/
	@Excel(name = "界面url", width = 15)
    @ApiModelProperty(value = "界面url")
    private java.lang.String pageUrl;
}
