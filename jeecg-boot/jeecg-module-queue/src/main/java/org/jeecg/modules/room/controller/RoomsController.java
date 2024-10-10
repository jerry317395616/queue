package org.jeecg.modules.room.controller;

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
import org.jeecg.modules.room.entity.Rooms;
import org.jeecg.modules.room.service.IRoomsService;

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
 * @Description: 诊室信息
 * @Author: jeecg-boot
 * @Date:   2024-10-05
 * @Version: V1.0
 */
@Api(tags="诊室信息")
@RestController
@RequestMapping("/room/rooms")
@Slf4j
public class RoomsController extends JeecgController<Rooms, IRoomsService> {
	@Autowired
	private IRoomsService roomsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param rooms
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "诊室信息-分页列表查询")
	@ApiOperation(value="诊室信息-分页列表查询", notes="诊室信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Rooms>> queryPageList(Rooms rooms,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
        QueryWrapper<Rooms> queryWrapper = QueryGenerator.initQueryWrapper(rooms, req.getParameterMap());
		Page<Rooms> page = new Page<Rooms>(pageNo, pageSize);
		IPage<Rooms> pageList = roomsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param rooms
	 * @return
	 */
	@AutoLog(value = "诊室信息-添加")
	@ApiOperation(value="诊室信息-添加", notes="诊室信息-添加")
	@RequiresPermissions("room:rooms:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody Rooms rooms) {
		roomsService.save(rooms);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param rooms
	 * @return
	 */
	@AutoLog(value = "诊室信息-编辑")
	@ApiOperation(value="诊室信息-编辑", notes="诊室信息-编辑")
	@RequiresPermissions("room:rooms:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody Rooms rooms) {
		roomsService.updateById(rooms);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "诊室信息-通过id删除")
	@ApiOperation(value="诊室信息-通过id删除", notes="诊室信息-通过id删除")
	@RequiresPermissions("room:rooms:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		roomsService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "诊室信息-批量删除")
	@ApiOperation(value="诊室信息-批量删除", notes="诊室信息-批量删除")
	@RequiresPermissions("room:rooms:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.roomsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "诊室信息-通过id查询")
	@ApiOperation(value="诊室信息-通过id查询", notes="诊室信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Rooms> queryById(@RequestParam(name="id",required=true) String id) {
		Rooms rooms = roomsService.getById(id);
		if(rooms==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(rooms);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param rooms
    */
    @RequiresPermissions("room:rooms:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Rooms rooms) {
        return super.exportXls(request, rooms, Rooms.class, "诊室信息");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("room:rooms:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Rooms.class);
    }

}
