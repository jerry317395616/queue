package org.jeecg.modules.api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface HisPatientMapper {

    List<Map<String, Object>> getAppPatients(@Param("code") String code);

    /**
     * 获取患者当天挂号信息
     * @param pkPi
     * @return
     */
    List<Map<String,Object>> getAppPatientByPkPi(@Param("pkPi") String pkPi);

    List<Map<String,Object>> getWaitingPatients(@Param("doctorId") String doctorId);

    /**
     * 查询医生当天排班信息
     * @param deptCodeList
     * @return
     */
    List<Map<String,Object>> getDoctorSchedule(@Param("deptCodeList") List<String> deptCodeList);

    /**
     * 获取科室预约患者
     * @param codeDept
     * @return
     */
    List<Map<String,Object>> getDepartmentAppointments(@Param("codeDept") String codeDept);
}
