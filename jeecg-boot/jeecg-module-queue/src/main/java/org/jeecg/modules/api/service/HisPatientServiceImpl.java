package org.jeecg.modules.api.service;

import com.baomidou.dynamic.datasource.annotation.DS;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.api.mapper.HisPatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@DS("multi-datasource1")
public class HisPatientServiceImpl{

    @Autowired
    private HisPatientMapper hisPatientMapper;


    /**
     * 查询科室预约患者
     * @param codeDept
     * @return
     */
    public List<Map<String,Object>> getDepartmentAppointments(String codeDept){
        return hisPatientMapper.getDepartmentAppointments(codeDept);
    }

    /**
     * 查询医生当天排班信息
     * @param deptCodeList
     * @return
     */
    public List<Map<String,Object>> getDoctorSchedule(List<String> deptCodeList){
        List<Map<String, Object>> result = hisPatientMapper.getDoctorSchedule(deptCodeList);
        return result;
    }

    public List<Map<String, Object>> getAppPatient(String code) {
        List<Map<String, Object>> result = hisPatientMapper.getAppPatients(code);
        return result;
    }

    /**
     * 获取当天预约信息
     * @param id
     * @return
     */
    public List<Map<String, Object>> getAppPatientById(String id) {
        return hisPatientMapper.getAppPatientByPkPi(id);
    }

    /**
     * 获取his人员信息
     * @param codeEmp
     * @param name
     * @param mobile
     * @return
     */
    public List<Map<String,Object>> getHisEmp(String codeEmp,String name,String mobile){
        return hisPatientMapper.getHisEmp(codeEmp,name,mobile);
    }


    /**
     * 获取HIS科室信息
     * @param codeEmp
     * @param nameDept
     * @return
     */
    public List<Map<String,Object>> getHisDept(String codeEmp,String nameDept){
        return hisPatientMapper.getHisDept(codeEmp,nameDept);
    }
}
