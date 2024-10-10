package org.jeecg.modules.area.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.area.entity.ClinicArea;
import org.jeecg.modules.area.service.IClinicAreaService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 诊区
 * @Author: jeecg-boot
 * @Date:   2024-09-07
 * @Version: V1.0
 */
@Api(tags="诊区")
@RestController
@RequestMapping("/area/clinicArea")
@Slf4j
public class ClinicAreaController extends JeecgController<ClinicArea, IClinicAreaService> {
	@Autowired
	private IClinicAreaService clinicAreaService;
	
	/**
	 * 分页列表查询
	 *
	 * @param clinicArea
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "诊区-分页列表查询")
	@ApiOperation(value="诊区-分页列表查询", notes="诊区-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ClinicArea>> queryPageList(ClinicArea clinicArea,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ClinicArea> queryWrapper = QueryGenerator.initQueryWrapper(clinicArea, req.getParameterMap());
		Page<ClinicArea> page = new Page<ClinicArea>(pageNo, pageSize);
		IPage<ClinicArea> pageList = clinicAreaService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param clinicArea
	 * @return
	 */
	@AutoLog(value = "诊区-添加")
	@ApiOperation(value="诊区-添加", notes="诊区-添加")
	@RequiresPermissions("area:clinic_area:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody ClinicArea clinicArea) {
		clinicAreaService.save(clinicArea);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param clinicArea
	 * @return
	 */
	@AutoLog(value = "诊区-编辑")
	@ApiOperation(value="诊区-编辑", notes="诊区-编辑")
	@RequiresPermissions("area:clinic_area:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody ClinicArea clinicArea) {
		clinicAreaService.updateById(clinicArea);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "诊区-通过id删除")
	@ApiOperation(value="诊区-通过id删除", notes="诊区-通过id删除")
	@RequiresPermissions("area:clinic_area:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		clinicAreaService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "诊区-批量删除")
	@ApiOperation(value="诊区-批量删除", notes="诊区-批量删除")
	@RequiresPermissions("area:clinic_area:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.clinicAreaService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "诊区-通过id查询")
	@ApiOperation(value="诊区-通过id查询", notes="诊区-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<ClinicArea> queryById(@RequestParam(name="id",required=true) String id) {
		ClinicArea clinicArea = clinicAreaService.getById(id);
		if(clinicArea==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(clinicArea);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param clinicArea
    */
    @RequiresPermissions("area:clinic_area:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ClinicArea clinicArea) {
        return super.exportXls(request, clinicArea, ClinicArea.class, "诊区");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("area:clinic_area:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ClinicArea.class);
    }

}
