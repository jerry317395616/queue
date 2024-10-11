package org.jeecg.modules.patient.controller;

import java.util.Arrays;
import java.util.HashMap;
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
import org.jeecg.common.system.query.QueryRuleEnum;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.patient.entity.Patients;
import org.jeecg.modules.patient.service.IPatientsService;

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
 * @Description: 患者信息
 * @Author: jeecg-boot
 * @Date:   2024-10-10
 * @Version: V1.0
 */
@Api(tags="患者信息")
@RestController
@RequestMapping("/patient/patients")
@Slf4j
public class PatientsController extends JeecgController<Patients, IPatientsService> {
	@Autowired
	private IPatientsService patientsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param patients
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "患者信息-分页列表查询")
	@ApiOperation(value="患者信息-分页列表查询", notes="患者信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Patients>> queryPageList(Patients patients,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
        QueryWrapper<Patients> queryWrapper = QueryGenerator.initQueryWrapper(patients, req.getParameterMap());
		Page<Patients> page = new Page<Patients>(pageNo, pageSize);
		IPage<Patients> pageList = patientsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param patients
	 * @return
	 */
	@AutoLog(value = "患者信息-添加")
	@ApiOperation(value="患者信息-添加", notes="患者信息-添加")
	@RequiresPermissions("patient:patients:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody Patients patients) {
		patientsService.save(patients);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param patients
	 * @return
	 */
	@AutoLog(value = "患者信息-编辑")
	@ApiOperation(value="患者信息-编辑", notes="患者信息-编辑")
	@RequiresPermissions("patient:patients:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody Patients patients) {
		patientsService.updateById(patients);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "患者信息-通过id删除")
	@ApiOperation(value="患者信息-通过id删除", notes="患者信息-通过id删除")
	@RequiresPermissions("patient:patients:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		patientsService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "患者信息-批量删除")
	@ApiOperation(value="患者信息-批量删除", notes="患者信息-批量删除")
	@RequiresPermissions("patient:patients:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.patientsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "患者信息-通过id查询")
	@ApiOperation(value="患者信息-通过id查询", notes="患者信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Patients> queryById(@RequestParam(name="id",required=true) String id) {
		Patients patients = patientsService.getById(id);
		if(patients==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(patients);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param patients
    */
    @RequiresPermissions("patient:patients:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Patients patients) {
        return super.exportXls(request, patients, Patients.class, "患者信息");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("patient:patients:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Patients.class);
    }

}
