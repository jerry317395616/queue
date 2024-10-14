package org.jeecg.modules.api.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.api.CardUtil;
import org.jeecg.modules.api.mapper.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@DS("multi-datasource2")
public class CardService {

    @Autowired
    private CardMapper cardMapper;

    public String getCardNo(String cardNo) {
        List<Map<String,Object>> result = cardMapper.getCardNo(cardNo);
        if(result.size()>0){
            Map<String,Object> map = result.get(0);
            return (String) map.get("PK_PI");
        }
        return "";
    }

    public List<Map<String,Object>> getPatientInfo(String cardNo) {
        List<Map<String,Object>> result = cardMapper.getCardNo(cardNo);
        return result;
    }
}
