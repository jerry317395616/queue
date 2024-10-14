package org.jeecg.modules.business.his.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface HisMapper {

    /**
     * 查询患者预约信息
     * @return
     */
    List<Map<String,Object>> getPatientAppInfo(@Param("pkPi") String pkPi);
}
