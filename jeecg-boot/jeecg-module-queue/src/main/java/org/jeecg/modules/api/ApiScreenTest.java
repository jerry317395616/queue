package org.jeecg.modules.api;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.screen.entity.DisplayScreens;
import org.jeecg.modules.screen.service.IDisplayScreensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/screen")
public class ApiScreenTest {

    @Autowired
    private IDisplayScreensService displayScreensService;



    @PostMapping("save-mac")
    public Result saveMac(@RequestBody String jsonParam){
        JSONObject param = JSONObject.parseObject(jsonParam);
        String mac = param.getString("macAddress");
        String ip = param.getString("ipAddress");
        String screenType = param.getString("screenType");
        DisplayScreens displayScreens=new DisplayScreens();
        displayScreens.setMacAddress(mac);
        displayScreens.setIpAddress(ip);
        displayScreens.setScreenType(screenType);
        displayScreensService.save(displayScreens);
        return Result.OK(displayScreens);
    }
}
