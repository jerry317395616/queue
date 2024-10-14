package org.jeecg.modules.business.his.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.MapUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.api.CardUtil;
import org.jeecg.modules.api.service.CardService;
import org.jeecg.modules.business.his.mapper.HisMapper;
import org.jeecg.modules.business.his.service.HisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("business/his")
public class HisController {

    @Autowired
    private HisService hisService;

    @Autowired
    private CardService cardService;

    /**
     * 获取患者信息
     * @return
     */
    public Result getPatientInfo(@RequestBody String jsonParam){
        JSONObject param = JSONObject.parseObject(jsonParam);
        String card = param.getString("cardNo");
        String cardNo = CardUtil.parseCardNo(card);
        //获取患者在四中心中的pkPi
        List<Map<String,Object>> patientCardInfoList = cardService.getPatientInfo(cardNo);
        if (ObjectUtils.isEmpty(patientCardInfoList)) {

            return Result.error("没有查询到患者卡信息");
        }

        //查询排队系统中的签到信息


        //查询患者在his系统中的预约信息
        //CODE_DEPT,CODE,DATE_APPT
        List<Map<String,Object>> patientAppInfoList = hisService.getPatientAppInfo(MapUtils.getString(patientCardInfoList.getFirst(),"pk_pi"));
        if(ObjectUtils.isEmpty(patientAppInfoList)){
            //没有预约信息
        }else {
            //有预约信息
        }



        return null;
    }
}
