package org.jeecg.modules.patient.service.impl;

import org.jeecg.modules.patient.entity.Patients;
import org.jeecg.modules.patient.mapper.PatientsMapper;
import org.jeecg.modules.patient.service.IPatientsService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 患者信息
 * @Author: jeecg-boot
 * @Date:   2024-10-05
 * @Version: V1.0
 */
@Service
public class PatientsServiceImpl extends ServiceImpl<PatientsMapper, Patients> implements IPatientsService {

}
