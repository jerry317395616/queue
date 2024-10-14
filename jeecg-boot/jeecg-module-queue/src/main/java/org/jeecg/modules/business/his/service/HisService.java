package org.jeecg.modules.business.his.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.business.his.mapper.HisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Service
@DS("multi-datasource1")
public class HisService {


    @Autowired
    private HisMapper hisMapper;

    /**
     * 查询患者预约信息
     * @param pkPi
     * @return
     */
    public List<Map<String,Object>> getPatientAppInfo(String pkPi){
        return hisMapper.getPatientAppInfo(pkPi);
    }

}
