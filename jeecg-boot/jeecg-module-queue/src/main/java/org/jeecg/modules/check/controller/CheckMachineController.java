package org.jeecg.modules.check.controller;

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
import org.jeecg.modules.check.entity.CheckMachine;
import org.jeecg.modules.check.service.ICheckMachineService;

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
 * @Description: 签到机
 * @Author: jeecg-boot
 * @Date:   2024-10-05
 * @Version: V1.0
 */
@Api(tags="签到机")
@RestController
@RequestMapping("/check/checkMachine")
@Slf4j
public class CheckMachineController extends JeecgController<CheckMachine, ICheckMachineService> {
	@Autowired
	private ICheckMachineService checkMachineService;
	
	/**
	 * 分页列表查询
	 *
	 * @param checkMachine
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "签到机-分页列表查询")
	@ApiOperation(value="签到机-分页列表查询", notes="签到机-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<CheckMachine>> queryPageList(CheckMachine checkMachine,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
        QueryWrapper<CheckMachine> queryWrapper = QueryGenerator.initQueryWrapper(checkMachine, req.getParameterMap());
		Page<CheckMachine> page = new Page<CheckMachine>(pageNo, pageSize);
		IPage<CheckMachine> pageList = checkMachineService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param checkMachine
	 * @return
	 */
	@AutoLog(value = "签到机-添加")
	@ApiOperation(value="签到机-添加", notes="签到机-添加")
	@RequiresPermissions("check:check_machine:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody CheckMachine checkMachine) {
		checkMachineService.save(checkMachine);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param checkMachine
	 * @return
	 */
	@AutoLog(value = "签到机-编辑")
	@ApiOperation(value="签到机-编辑", notes="签到机-编辑")
	@RequiresPermissions("check:check_machine:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody CheckMachine checkMachine) {
		checkMachineService.updateById(checkMachine);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "签到机-通过id删除")
	@ApiOperation(value="签到机-通过id删除", notes="签到机-通过id删除")
	@RequiresPermissions("check:check_machine:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		checkMachineService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "签到机-批量删除")
	@ApiOperation(value="签到机-批量删除", notes="签到机-批量删除")
	@RequiresPermissions("check:check_machine:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.checkMachineService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "签到机-通过id查询")
	@ApiOperation(value="签到机-通过id查询", notes="签到机-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<CheckMachine> queryById(@RequestParam(name="id",required=true) String id) {
		CheckMachine checkMachine = checkMachineService.getById(id);
		if(checkMachine==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(checkMachine);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param checkMachine
    */
    @RequiresPermissions("check:check_machine:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CheckMachine checkMachine) {
        return super.exportXls(request, checkMachine, CheckMachine.class, "签到机");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("check:check_machine:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, CheckMachine.class);
    }

}
