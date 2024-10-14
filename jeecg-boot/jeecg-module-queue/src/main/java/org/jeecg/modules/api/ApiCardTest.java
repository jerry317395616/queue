package org.jeecg.modules.api;


import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.api.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/card")
public class ApiCardTest {

    @Autowired
    private CardService cardService;

    /**
     * 获取患者卡信息
     */
    @PostMapping("patient-card")
    public Result getPatientCardInfo(@RequestBody String jsonParam){
        JSONObject param = JSONObject.parseObject(jsonParam);
        String card = param.getString("cardNo");
        String cardNo = CardUtil.parseCardNo(card);
        //获取患者在his系统中的pkPi
        List<Map<String,Object>> patientInfoList = cardService.getPatientInfo(cardNo);
        if(ObjectUtils.isEmpty(patientInfoList)){
            return Result.error("没有查询到患者信息");
        }
        return Result.ok(patientInfoList.get(0));

    }
}
