package org.jeecg.modules.business.client.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClientMapper {

    /**
     * 分诊界面获取诊室信息
     * @param deptId
     * @return
     */
    List<Map<String,Object>> getRoomList(@Param("deptId") String deptId);

}
