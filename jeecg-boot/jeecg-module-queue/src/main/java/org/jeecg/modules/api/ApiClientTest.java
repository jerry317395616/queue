package org.jeecg.modules.api;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.api.mapper.ApiClientMapper;
import org.jeecg.modules.device.entity.DoctorDevice;
import org.jeecg.modules.device.service.IDoctorDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/client")
public class ApiClientTest {



    @Autowired
    private IDoctorDeviceService doctorDeviceService;

    @Autowired
    private ApiClientMapper apiClientMapper;
    /**
     * 获取在线医生
     * @return
     */
    @PostMapping("getDeptDoctorList")
    public Result getDeptDoctorList(@RequestBody String jsonParam){
        JSONObject param = JSONObject.parseObject(jsonParam);
        String deptId = param.getString("deptId");
        return Result.OK(apiClientMapper.getDeptDoctorList(deptId));
    }
}
