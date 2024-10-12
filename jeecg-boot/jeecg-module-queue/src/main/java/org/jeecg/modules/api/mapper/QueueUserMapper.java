package org.jeecg.modules.api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface QueueUserMapper {

    List<Map<String,Object>> getSysUserList(String code);

    List<Map<String,Object>> getSysDeptList(@Param("id") String id);

    List<Map<String,Object>> getSysDeptId(@Param("code") String code);

    List<Map<String,Object>> getSysDeptListByids(@Param("ids") List<String> ids);

    List<Map<String,Object>> getSignDeptList(@Param("mac") String mac);

    List<Map<String ,Object>> getRoomListByDeptId(@Param("id") String id);

    /**
     * 查询医生的队列信息
     * @param id
     * @return
     */
    List<Map<String,Object>> getDoctorQueueList(@Param("id") String id);


    /**
     * 根据科室编码查询科室信息
     * @param codeDept
     * @return
     */
    List<Map<String,Object>> getSysDeptListByCode(@Param("codeDept") String codeDept);



}
