package org.jeecg.modules.business.client.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.util.JeecgDataAutorUtils;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.system.vo.SysUserCacheInfo;
import org.jeecg.modules.api.service.SysUserServie;
import org.jeecg.modules.business.client.mapper.ClientMapper;
import org.jeecg.modules.client.entity.TabTriageClient;
import org.jeecg.modules.client.service.ITabTriageClientService;
import org.jeecg.modules.room.entity.Rooms;
import org.jeecg.modules.room.service.IRoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * 客户端的操作
 */
@RestController
@RequestMapping("business/client")
public class ClientController {

    @Autowired
    private ITabTriageClientService tabTriageClientService;

    @Autowired
    private SysUserServie sysUserServie ;

    @Autowired
    private IRoomsService roomsService;

    @Autowired
    private ClientMapper clientMapper;

    /**
     * 获取分诊客户端配置信息
     * @param jsonParam
     * @param request
     */
    @PostMapping("getTriageClientInfo")
    public Result getTriageClientInfo(@RequestBody String jsonParam, HttpServletRequest request ){
        JSONObject param = JSONObject.parseObject(jsonParam);
        String mac = param.getString("mac");
        String ip = param.getString("ip");
        QueryWrapper<TabTriageClient> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mac_address",mac);
        TabTriageClient tabTriageClient = tabTriageClientService.getOne(queryWrapper);
        if(ObjectUtils.isEmpty(tabTriageClient)){
            //查询当前登录人的科室
            String username = JwtUtil.getUserNameByToken(request);
            List<Map<String,Object>> sysUserList = sysUserServie.getSysUserList(username);
            return Result.OK(sysUserList);
        }

        return Result.ok(tabTriageClient);
    }

    /**
     * 获取诊室信息
     * @param jsonParam
     * @return
     */
    @PostMapping("getRoomList")
    public Result getRoomList(@RequestBody String jsonParam){
        JSONObject param = JSONObject.parseObject(jsonParam);
        String deptId = param.getString("deptId");
        List<Map<String,Object>> roomsList = clientMapper.getRoomList(deptId);
        return Result.OK(roomsList);
    }
}
