package org.jeecg.modules.api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



import java.util.List;
import java.util.Map;

@Mapper
public interface ApiClientMapper {

    /**
     * 获取科室在线的医生
     * @param deptId
     * @return
     */
    List<Map<String,Object>> getDeptDoctorList(@Param("deptId") String deptId);

}
