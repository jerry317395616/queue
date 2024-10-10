package org.jeecg.modules.queue.controller;

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
import org.jeecg.modules.queue.entity.Queues;
import org.jeecg.modules.queue.service.IQueuesService;

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
 * @Description: 队列管理
 * @Author: jeecg-boot
 * @Date:   2024-09-28
 * @Version: V1.0
 */
@Api(tags="队列管理")
@RestController
@RequestMapping("/queue/queues")
@Slf4j
public class QueuesController extends JeecgController<Queues, IQueuesService> {
	@Autowired
	private IQueuesService queuesService;
	
	/**
	 * 分页列表查询
	 *
	 * @param queues
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "队列管理-分页列表查询")
	@ApiOperation(value="队列管理-分页列表查询", notes="队列管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Queues>> queryPageList(Queues queues,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
        QueryWrapper<Queues> queryWrapper = QueryGenerator.initQueryWrapper(queues, req.getParameterMap());
		Page<Queues> page = new Page<Queues>(pageNo, pageSize);
		IPage<Queues> pageList = queuesService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param queues
	 * @return
	 */
	@AutoLog(value = "队列管理-添加")
	@ApiOperation(value="队列管理-添加", notes="队列管理-添加")
	@RequiresPermissions("queue:queues:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody Queues queues) {
		queuesService.save(queues);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param queues
	 * @return
	 */
	@AutoLog(value = "队列管理-编辑")
	@ApiOperation(value="队列管理-编辑", notes="队列管理-编辑")
	@RequiresPermissions("queue:queues:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody Queues queues) {
		queuesService.updateById(queues);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "队列管理-通过id删除")
	@ApiOperation(value="队列管理-通过id删除", notes="队列管理-通过id删除")
	@RequiresPermissions("queue:queues:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		queuesService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "队列管理-批量删除")
	@ApiOperation(value="队列管理-批量删除", notes="队列管理-批量删除")
	@RequiresPermissions("queue:queues:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.queuesService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "队列管理-通过id查询")
	@ApiOperation(value="队列管理-通过id查询", notes="队列管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Queues> queryById(@RequestParam(name="id",required=true) String id) {
		Queues queues = queuesService.getById(id);
		if(queues==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(queues);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param queues
    */
    @RequiresPermissions("queue:queues:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Queues queues) {
        return super.exportXls(request, queues, Queues.class, "队列管理");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("queue:queues:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Queues.class);
    }

}
