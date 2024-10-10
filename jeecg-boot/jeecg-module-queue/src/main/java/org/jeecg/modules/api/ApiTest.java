package org.jeecg.modules.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.JsonObject;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.annotation.WrapResponse;
import org.jeecg.modules.api.mapper.HisPatientMapper;
import org.jeecg.modules.api.mapper.QueueUserMapper;
import org.jeecg.modules.api.service.CardService;
import org.jeecg.modules.api.service.HisPatientServiceImpl;
import org.jeecg.modules.api.service.SysUserServie;
import org.jeecg.modules.check.entity.CheckMachine;
import org.jeecg.modules.check.service.ICheckMachineService;
import org.jeecg.modules.device.entity.DoctorDevice;
import org.jeecg.modules.device.service.IDoctorDeviceService;
import org.jeecg.modules.netty.WebSocketFrameHandler;
import org.jeecg.modules.netty.WebsocketServer;
import org.jeecg.modules.patient.entity.Patients;
import org.jeecg.modules.patient.service.IPatientsService;
import org.jeecg.modules.queue.entity.Queues;
import org.jeecg.modules.queue.service.IQueuesService;
import org.jeecg.modules.room.entity.Rooms;
import org.jeecg.modules.room.service.IRoomsService;
import org.jeecg.modules.screen.entity.DisplayScreens;
import org.jeecg.modules.screen.service.IDisplayScreensService;
import org.jeecg.modules.screen.service.impl.DisplayScreensServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/doctor")
public class ApiTest {

    @Autowired
    private SysUserServie sysUserServie;

    @Autowired
    private IDoctorDeviceService doctorDeviceService;

    @Autowired
    private IRoomsService roomService;

    @Autowired
    private HisPatientServiceImpl hisPatientService;

    @Autowired
    private ICheckMachineService checkMachineService;

    @Autowired
    private IQueuesService queuesService;

    @Autowired
    private IPatientsService patientsService;

    @Autowired
    private CardService cardService;

    @Autowired
    private IRoomsService roomsService;
    @Autowired
    private IDisplayScreensService displayScreensService;

    @Autowired
    private QueueUserMapper queueUserMapper;


    /**
     * 获取科室预约信息
     *
     * @param jsonParam
     * @return
     */
    @PostMapping("getDepartmentAppointments")
    public Result getDepartmentAppointments(@RequestBody String jsonParam) {
        JSONObject param = JSONObject.parseObject(jsonParam);
        String deptId = param.getString("deptId");
        String codeDept = sysUserServie.getSysDeptList(deptId).get(0).get("memo").toString();
        return Result.OK(hisPatientService.getDepartmentAppointments(codeDept));
    }


    /**
     * 获取签到机科室列表
     *
     * @return
     */
    @WrapResponse
    @PostMapping("getSignDeptList")
    public Object getSignDeptList(@RequestBody String jsonParam) {
        JSONObject param = JSONObject.parseObject(jsonParam);
        String mac = param.getString("mac");
        return sysUserServie.getSignDeptList(mac);
    }

    /**
     * 签到机获取医生排班信息
     *
     * @param jsonParam
     * @return
     */
    @WrapResponse
    @PostMapping("getDoctorSchedule")
    public Object getDoctorSchedule(@RequestBody String jsonParam) {
//        JSONObject param = JSONObject.parseObject(jsonParam);
//        String mac = param.getString("mac");
//
//        // 使用 QueryWrapper 构建查询条件
//        QueryWrapper<CheckMachine> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("mac_address", mac);  // 匹配 mac_address
//
//        // 查询并获取 CheckMachine 对象
//        CheckMachine checkMachine = checkMachineService.getOne(queryWrapper);
//
//        String deptIds = checkMachine.getSignableDept();
//        // 将逗号分隔的字符串转换为列表
//        List<String> deptIdList = Arrays.asList(deptIds.split(","));
//        List<Map<String,Object>> deptList = sysUserServie.getSysDeptListByids(deptIdList);
//        List<String> deptCodeList = deptList.stream().map(a->a.get("memo").toString()).collect(Collectors.toList());
//        return hisPatientService.getDoctorSchedule(deptCodeList);
        JSONObject param = JSONObject.parseObject(jsonParam);
        String memo = param.getString("memo");

        // 使用 QueryWrapper 构建查询条件
//        QueryWrapper<CheckMachine> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("mac_address", mac);  // 匹配 mac_address
//
//        // 查询并获取 CheckMachine 对象
//        CheckMachine checkMachine = checkMachineService.getOne(queryWrapper);

        String deptIds = memo;
        // 将逗号分隔的字符串转换为列表
//        List<String> deptIdList = Arrays.asList(deptIds.split(","));
//        List<Map<String,Object>> deptList = sysUserServie.getSysDeptListByids(deptIdList);
//        List<String> deptCodeList = deptList.stream().map(a->a.get("memo").toString()).collect(Collectors.toList());
        List<String> deptCodeList = new ArrayList<>();
        deptCodeList.add(memo);
        return hisPatientService.getDoctorSchedule(deptCodeList);
    }


    @WrapResponse
    @PostMapping("listDepts")
    public Object test(@RequestBody String json) {
        System.out.println("收到的参数");
        System.out.println(json);

        JSONObject param = JSONObject.parseObject(json);
        String code = param.getString("loginName");

        // 假设从服务中获取数据
        Object list = sysUserServie.getSysUserList(code);

        JSONObject data = new JSONObject();
        data.put("isExpert", "0");
        data.put("visitDepts", list);

        return data;  // 返回的结果将会被AOP拦截包装到root对象中
    }

    @WrapResponse
    @PostMapping("listClinics")
    public Object rooms(@RequestBody String json) {
        JSONObject param = JSONObject.parseObject(json);
        String deptId = param.getString("pkDept");
        QueryWrapper<Rooms> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("department", deptId);
        // 假设从服务中获取数据
        List<Rooms> list = roomService.list(queryWrapper);
        JSONArray jsonArray = new JSONArray();
        for (Rooms rooms : list) {
            JSONObject data = new JSONObject();
            data.put("pkClinic", rooms.getId());
            data.put("nameClinic", rooms.getRoomNumber());
            jsonArray.add(data);
        }
        return jsonArray;  // 返回的结果将会被AOP拦截包装到root对象中
    }


    /**
     * 医生登录
     *
     * @param json
     * @return
     */
    @WrapResponse
    @PostMapping("login")
    public Object login(@RequestBody String json) {
        System.out.println("登录参数");
        System.out.println(json);
        JSONObject param = JSONObject.parseObject(json);
        String code = param.getString("loginName");

        String doctorId = sysUserServie.getSysUserList(code).get(0).get("userid").toString();

        String deptId = param.getString("pkDept");
        String ip = param.getString("ipAddress");
        String mac = param.getString("macAddress");
        String room = param.getString("pkClinic");
        QueryWrapper<DoctorDevice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mac", mac);
        List<DoctorDevice> result = doctorDeviceService.list(queryWrapper);
        if (result.size() > 0) {
            DoctorDevice device = result.get(0);
            device.setDoctor(doctorId);
            device.setIpAddress(ip);
            device.setDepartment(deptId);
            device.setRoom(room);
            device.setLastActive(new Date());
            device.setStatus("在线");
            doctorDeviceService.updateById(device);

        } else {
            DoctorDevice device = new DoctorDevice();
            device.setDepartment(deptId);
            device.setDoctor(doctorId);
            device.setIpAddress(ip);
            device.setMac(mac);
            device.setDepartment(deptId);
            device.setRoom(room);
            device.setLastActive(new Date());
            device.setStatus("在线");
            doctorDeviceService.save(device);
        }
        List<Map<String, Object>> object = sysUserServie.getSysUserList(code);

        return object.size() > 0 ? object.get(0) : null;
    }


    @WrapResponse
    @PostMapping("app-patient")
    public Object getAppPatient(@RequestBody String json) {
        System.out.println("登录参数");
        System.out.println(json);
        JSONObject param = JSONObject.parseObject(json);
        String code = param.getString("loginName");
        List<Map<String, Object>> result = hisPatientService.getAppPatient(code);

        return result;
    }

    /**
     * 顺呼
     *
     * @param clientId
     * @return
     */
    @PostMapping("/call")
    public ResponseEntity<String> callPatient(@RequestParam String clientId) {

        //根据mac获取叫号器的医生
        QueryWrapper<DoctorDevice> doctorDeviceQueryWrapper = new QueryWrapper<>();
        doctorDeviceQueryWrapper.eq("mac", clientId);
        doctorDeviceQueryWrapper.eq("status", "在线");
        DoctorDevice doctorDevice = doctorDeviceService.getOne(doctorDeviceQueryWrapper);
        String doctorId = doctorDevice.getDoctor();

        //根据医生科室信息查询科室大屏
        String deptId = doctorDevice.getDepartment();
        QueryWrapper<DisplayScreens> displayScreensQueryWrapper = new QueryWrapper<>();
        displayScreensQueryWrapper.eq("dep_id", deptId);
        displayScreensQueryWrapper.eq("screen_type", "大屏");
        List<DisplayScreens> displayScreensList = displayScreensService.list(displayScreensQueryWrapper);


        // 从队列中获取下一个需要被呼叫的患者
        QueryWrapper<Queues> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("queue_status", "等待中")// 等待叫号的患者
                .eq("doctor", doctorId)
                .orderByAsc("queue_number")    // 按照排队序号升序
                .last("LIMIT 1");              // 只取一个患者

        Queues nextQueue = queuesService.getOne(queryWrapper);

        if (nextQueue != null) {
            // 更新队列状态为 'called'
            nextQueue.setQueueStatus("已呼叫");
            queuesService.updateById(nextQueue);

            // 获取患者信息
            Patients patient = patientsService.getById(nextQueue.getPatient());
            String patientNumber = nextQueue.getQueueNumber().toString();
            String patientName = patient.getName();

            // 构建消息内容
            String message = "请 " + patientNumber + " 号患者 " + patientName + " 到诊室就诊。";


            List<String> macList = displayScreensList.stream().map(a -> a.getMacAddress()).collect(Collectors.toList());

            for (String mac : macList) {
                // 获取指定 clientId 的 Channel
                Channel channel = WebSocketFrameHandler.clientIdChannelMap.get(mac);
                if (channel != null && channel.isActive()) {
                    // 向指定的客户端发送消息
                    channel.writeAndFlush(new TextWebSocketFrame(message));

                }
            }

//            // 获取指定 clientId 的 Channel
//            Channel channel = WebSocketFrameHandler.clientIdChannelMap.get(clientId);
//            if (channel != null && channel.isActive()) {
//                // 向指定的客户端发送消息
//                channel.writeAndFlush(new TextWebSocketFrame(message));
//                return ResponseEntity.ok("已呼叫 " + patientNumber + " 号患者到客户端 ID：" + clientId);
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("客户端 " + clientId + " 未连接");
//            }
        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("当前没有等待叫号的患者");
        }


        return ResponseEntity.ok("已呼叫 ");


//        // 业务逻辑处理，例如更新数据库等
//        String message = "请 " + patientNumber + " 号患者就诊。";
//
//        // 获取指定 clientId 的 Channel
//        Channel channel = WebSocketFrameHandler.clientIdChannelMap.get(clientId);
//        if (channel != null && channel.isActive()) {
//            // 向指定的客户端发送消息
//            channel.writeAndFlush(new TextWebSocketFrame(message));
//            return ResponseEntity.ok("已呼叫 " + patientNumber + " 号患者到客户端 ID：" + clientId);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("客户端 " + clientId + " 未连接");
//        }
    }


    @PostMapping("/recall")
    public ResponseEntity<String> recallPatient(@RequestParam String clientId) {

        //根据mac获取叫号器的医生
        QueryWrapper<DoctorDevice> doctorDeviceQueryWrapper = new QueryWrapper<>();
        doctorDeviceQueryWrapper.eq("mac", clientId);
        doctorDeviceQueryWrapper.eq("status", "在线");
        DoctorDevice doctorDevice = doctorDeviceService.getOne(doctorDeviceQueryWrapper);
        String doctorId = doctorDevice.getDoctor();

        //根据医生科室信息查询科室大屏
        String deptId = doctorDevice.getDepartment();
        QueryWrapper<DisplayScreens> displayScreensQueryWrapper = new QueryWrapper<>();
        displayScreensQueryWrapper.eq("dep_id", deptId);
        displayScreensQueryWrapper.eq("screen_type", "大屏");
        List<DisplayScreens> displayScreensList = displayScreensService.list(displayScreensQueryWrapper);


        // 从队列中获取下一个需要被呼叫的患者
        QueryWrapper<Queues> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("queue_status", "已呼叫") // 等待叫号的患者
                .eq("doctor", doctorId)
                .orderByAsc("queue_number")    // 按照排队序号升序
                .last("LIMIT 1");              // 只取一个患者

        Queues nextQueue = queuesService.getOne(queryWrapper);

        if (nextQueue != null) {
            // 更新队列状态为 'called'
            nextQueue.setQueueStatus("已呼叫");
            queuesService.updateById(nextQueue);

            // 获取患者信息
            Patients patient = patientsService.getById(nextQueue.getPatient());
            String patientNumber = nextQueue.getQueueNumber().toString();
            String patientName = patient.getName();

            // 构建消息内容
            String message = "请 " + patientNumber + " 号患者 " + patientName + " 到诊室就诊。";

            List<String> macList = displayScreensList.stream().map(a -> a.getMacAddress()).collect(Collectors.toList());

            for (String mac : macList) {
                // 获取指定 clientId 的 Channel
                Channel channel = WebSocketFrameHandler.clientIdChannelMap.get(mac);
                if (channel != null && channel.isActive()) {
                    // 向指定的客户端发送消息
                    channel.writeAndFlush(new TextWebSocketFrame(message));

                }
            }

//            // 获取指定 clientId 的 Channel
//            Channel channel = WebSocketFrameHandler.clientIdChannelMap.get(clientId);
//            if (channel != null && channel.isActive()) {
//                // 向指定的客户端发送消息
//                channel.writeAndFlush(new TextWebSocketFrame(message));
//                return ResponseEntity.ok("已呼叫 " + patientNumber + " 号患者到客户端 ID：" + clientId);
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("客户端 " + clientId + " 未连接");
//            }
        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("当前没有等待叫号的患者");
        }
        return ResponseEntity.ok("已呼叫 ");
    }


    @PostMapping("/pass")
    public void passPatient(@RequestParam String clientId) {

        //根据mac获取叫号器的医生
        QueryWrapper<DoctorDevice> doctorDeviceQueryWrapper = new QueryWrapper<>();
        doctorDeviceQueryWrapper.eq("mac", clientId);
        doctorDeviceQueryWrapper.eq("status", "在线");
        DoctorDevice doctorDevice = doctorDeviceService.getOne(doctorDeviceQueryWrapper);
        String doctorId = doctorDevice.getDoctor();

        // 从队列中获取下一个需要被呼叫的患者
        QueryWrapper<Queues> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("queue_status", "已呼叫") // 等待叫号的患者
                .eq("doctor", doctorId)
                .orderByAsc("queue_number")    // 按照排队序号升序
                .last("LIMIT 1");              // 只取一个患者

        Queues nextQueue = queuesService.getOne(queryWrapper);

        if (nextQueue != null) {
            // 更新队列状态为 'called'
            nextQueue.setQueueStatus("已过号");
            queuesService.updateById(nextQueue);


        }

    }


    @PostMapping("/joinQueue")
    @WrapResponse
    public Object joinQueue(@RequestBody String json) throws ParseException {
        JSONObject param = JSONObject.parseObject(json);
        //医生编码
        String doctorCode = param.getString("doctorCode");
        String dectorId = sysUserServie.getSysUserList(doctorCode).get(0).get("userid").toString();
        //科室编码
        String deptId = param.getString("deptId");
        String cardNo = param.getString("cardNo");
        cardNo = parseCardNo(cardNo);
        //获取患者在his系统中的pkPi
        List<Map<String, Object>> patientInfo = cardService.getPatientInfo(cardNo);
        String patientName = patientInfo.get(0).get("REGNAME").toString();
        String sex = patientInfo.get(0).get("SEX").toString();
        String birthDay = patientInfo.get(0).get("BIRTHDAY").toString();
        String teleNo = patientInfo.get(0).get("TELENO").toString();
        String credentialNo = patientInfo.get(0).get("CREDENTIALNO").toString();
        Patients patients = new Patients();
        patients.setName(patientName);
        if ("02".equals(sex)) {
            patients.setGender("1");
        } else {
            patients.setGender("2");
        }
        patients.setDateBirth(DateUtils.parseDate(birthDay, "yyyy-MM-dd"));
        patients.setPhone(teleNo);
        patients.setAppDate(new Date());
        patients.setCheckTime(new Date());
        patients.setIdNumber(credentialNo);
        patients.setDoctor(dectorId);
        patients.setAppType("线下");
        patients.setDepartment(deptId);
        patientsService.save(patients);


        Queues queues = new Queues();
        queues.setPatient(patients.getId());
        // Retrieve the current maximum queueNumber
        QueryWrapper<Queues> queueQueryWrapper = new QueryWrapper<>();
        queueQueryWrapper.select("MAX(queue_number) as maxQueueNumber");
        Map<String, Object> result = queuesService.getMap(queueQueryWrapper);

        // Extract the maxQueueNumber and increment it
        Integer maxQueueNumber = 0;
        if (result != null && result.get("maxQueueNumber") != null) {
            maxQueueNumber = Integer.valueOf(result.get("maxQueueNumber").toString());
        }
        queues.setQueueNumber(maxQueueNumber + 1);
        queues.setDepartment(deptId);
        queues.setQueueStatus("等待中");
        queues.setQueuePriority("普通");
        queues.setCheckTime(new Date());
        queues.setDoctor(dectorId);

//                    查询医生诊室
        QueryWrapper<DoctorDevice> doctorDeviceQueryWrapper = new QueryWrapper<>();
        doctorDeviceQueryWrapper.eq("doctor", dectorId);
        doctorDeviceQueryWrapper.eq("status", "在线"); // 假设 'online' 表示设备在线

        List<DoctorDevice> onlineDevices = doctorDeviceService.list(doctorDeviceQueryWrapper);
        String room = onlineDevices.get(0).getRoom();
        queues.setRoom(room);
        queuesService.save(queues);
        return queues;

    }


    @PostMapping("/joinQueueWithoutCard")
    @WrapResponse
    public Object joinQueueWithoutCard(@RequestBody String json) throws ParseException {
        JSONObject param = JSONObject.parseObject(json);
        //科室编码
        String deptId = param.getString("deptId");


        Queues queues = new Queues();

        // Retrieve the current maximum queueNumber
        QueryWrapper<Queues> queueQueryWrapper = new QueryWrapper<>();
        queueQueryWrapper.select("MAX(queue_number) as maxQueueNumber");
        Map<String, Object> result = queuesService.getMap(queueQueryWrapper);

        // Extract the maxQueueNumber and increment it
        Integer maxQueueNumber = 0;
        if (result != null && result.get("maxQueueNumber") != null) {
            maxQueueNumber = Integer.valueOf(result.get("maxQueueNumber").toString());
        }
        queues.setQueueNumber(maxQueueNumber + 1);
        queues.setDepartment(deptId);
        queues.setQueueStatus("等待中");
        queues.setQueuePriority("普通");
        queues.setCheckTime(new Date());


//                    查询医生诊室
        QueryWrapper<DoctorDevice> doctorDeviceQueryWrapper = new QueryWrapper<>();

        doctorDeviceQueryWrapper.eq("status", "在线"); // 假设 'online' 表示设备在线

        List<DoctorDevice> onlineDevices = doctorDeviceService.list(doctorDeviceQueryWrapper);
        String room = onlineDevices.get(0).getRoom();
        queues.setRoom(room);
        queuesService.save(queues);
        return queues;

    }

    /**
     * 护士站签到
     * @return
     */
    @PostMapping("/nurseSign")
    public Result nurseSign(@RequestBody String json) throws ParseException {
        JSONObject param = JSONObject.parseObject(json);
        Date checkTime = new Date();
        Patients patients = new Patients();
        patients.setName(param.getString("NAME_PI"));
        patients.setGender("男".equals(param.getString("GENDER"))?"1":"2");
        patients.setPhone(param.getString("MOBILE"));
        patients.setAppDate(DateUtils.parseDate(param.getString("DATE_APPT"),"yyyy-MM-dd"));
        patients.setAppType(param.getString("APPTYPE"));
        patients.setAppId(param.getString("ID"));
        patients.setIdNumber(param.getString("ID_NO"));
        patients.setCheckTime(checkTime);

        return null;
    }


    /**
     * 签到
     *
     * @param json
     * @return
     * @throws ParseException
     */
    @PostMapping("/checkin")
//    @WrapResponse
    public Result patientSign(@RequestBody String json) throws ParseException {
        JSONObject param = JSONObject.parseObject(json);
        String mac = param.getString("mac");
        String cardNo = param.getString("pkPi");
        cardNo = parseCardNo(cardNo);
        //获取患者在his系统中的pkPi
        String pkPi = cardService.getCardNo(cardNo);
        //查询患者his系统中的预约信息
        List<Map<String, Object>> appList = hisPatientService.getAppPatientById(pkPi);
        if (ObjectUtils.isEmpty(appList)) {
            return Result.error("没有查询到预约信息");
        }
        Date checkDate = new Date();
        String namePi = appList.get(0).get("NAME_PI").toString();
        String dtSex = appList.get(0).get("DT_SEX").toString();
        String brithDate = appList.get(0).get("BIRTH_DATE").toString();
        String mobile = appList.get(0).get("MOBILE").toString();
        String dateAppt = appList.get(0).get("DATE_APPT").toString();
        String idNo = appList.get(0).get("ID_NO").toString();
        String appId = appList.get(0).get("PK_SCHAPPT").toString();
        String doctorCode = appList.get(0).get("CODE").toString();
        String appType = appList.get(0).get("APPTYPE").toString();
        String codeDept = appList.get(0).get("CODE_DEPT").toString();

        //查询是否存在签到信息
        QueryWrapper<Patients> patientQueryWrapper = new QueryWrapper<>();
        patientQueryWrapper.eq("app_id", appId);
        List<Patients> patientsList = patientsService.list(patientQueryWrapper);
        if(!ObjectUtils.isEmpty(patientsList)){
            return Result.error("已经存在预约信息");
        }



        //查询本系统科室信息
        String appDeptId = sysUserServie.getDeptId(codeDept);
        //查询签到机对应科室信息
        QueryWrapper<CheckMachine> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mac_address", mac);
        List<CheckMachine> checkMachineList = checkMachineService.list(queryWrapper);
        if (ObjectUtils.isEmpty(checkMachineList)) {
            return Result.error("签到机没有对应科室信息");
        }
        CheckMachine checkMachine = checkMachineList.get(0);
        String signableDept = checkMachine.getSignableDept();
        if(ObjectUtils.isEmpty(signableDept)){
            return Result.error("签到机没有对应科室信息");
        }
        if(!signableDept.contains(appDeptId)){
            return Result.error("预约科室不属于签到机对应科室");
        }

        //查询本系统医生信息
        List<Map<String, Object>> doctorList = sysUserServie.getSysUserList(doctorCode);
        if (ObjectUtils.isEmpty(doctorList)) {
            return Result.error("没有查询到医生信息");
        }
        Map<String, Object> doctor = sysUserServie.getSysUserList(doctorCode).get(0);
        String doctorId = doctor.get("userid").toString();

        Patients patients = new Patients();
        patients.setName(namePi);
        if ("02".equals(dtSex)) {
            patients.setGender("1");
        } else {
            patients.setGender("2");
        }
        patients.setDateBirth(DateUtils.parseDate(brithDate, "yyyy-MM-dd"));
        patients.setPhone(mobile);
        patients.setAppDate(DateUtils.parseDate(dateAppt, "yyyy-MM-dd"));
        patients.setCheckTime(checkDate);
        patients.setIdNumber(idNo);
        patients.setAppId(appId);
        patients.setDoctor(doctorId);
        patients.setAppType(appType);
        patients.setDepartment(appDeptId);
        //保存患者签到信息
        patientsService.save(patients);


        Queues queues = new Queues();
        queues.setPatient(patients.getId());
        // Retrieve the current maximum queueNumber
        QueryWrapper<Queues> queueQueryWrapper = new QueryWrapper<>();
        queueQueryWrapper.eq("doctor",doctorId);
        queueQueryWrapper.select("MAX(queue_number) as maxQueueNumber");
        Map<String, Object> result = queuesService.getMap(queueQueryWrapper);

        // Extract the maxQueueNumber and increment it
        Integer maxQueueNumber = 0;
        if (result != null && result.get("maxQueueNumber") != null) {
            maxQueueNumber = Integer.valueOf(result.get("maxQueueNumber").toString());
        }
        queues.setQueueNumber(maxQueueNumber + 1);
        queues.setDepartment(appDeptId);
        queues.setQueueStatus("等待中");
        queues.setQueuePriority("普通");
        queues.setCheckTime(checkDate);
        queues.setDoctor(doctorId);
//              查询医生诊室
        QueryWrapper<DoctorDevice> doctorDeviceQueryWrapper = new QueryWrapper<>();
        doctorDeviceQueryWrapper.eq("doctor", doctorId);
        doctorDeviceQueryWrapper.eq("status", "在线"); // 假设 'online' 表示设备在线
        List<DoctorDevice> onlineDevices = doctorDeviceService.list(doctorDeviceQueryWrapper);
        if(ObjectUtils.isEmpty(onlineDevices)){
            return Result.error("没有查询到医生诊室信息");
        }
        String room = onlineDevices.get(0).getRoom();
        queues.setRoom(room);
        queuesService.save(queues);
        return Result.ok(queues);
    }


    @PostMapping("/listNoCalled")
    @WrapResponse
    public Object getWaitingPatients(@RequestBody String json) {
        JSONObject param = JSONObject.parseObject(json);
        String doctorCode = param.getString("loginName");
        List<Map<String, Object>> doctorList = sysUserServie.getSysUserList(doctorCode);
        String doctorId = doctorList.get(0).get("userid").toString();
        QueryWrapper<Queues> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("doctor", doctorId);
        List<Queues> queues = queuesService.list(queryWrapper);
        JSONArray jsonArray = new JSONArray();
        for (Queues queue : queues) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("orderNum", queue.getQueueNumber());
            jsonObject.put("age", "");
            jsonObject.put("cardNo", "");
            jsonObject.put("cateRegistered", "");
            jsonObject.put("codePatient", queue.getPatient());
            jsonObject.put("gender", "");
            jsonObject.put("isPreRegistered", "");
            jsonObject.put("markPatient", "");
            jsonObject.put("namePatient", queue.getPatient());
            jsonObject.put("pkClinicTriage", "");
            jsonObject.put("pkRegistered", "");
            jsonObject.put("statusQueue", "1");
            jsonObject.put("nameDeptItemRegistered", "");
            jsonObject.put("statusJump", "1");
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @PostMapping("/listCalled")
    @WrapResponse
    public Object getCalledPatients(@RequestBody String json) {
        JSONObject param = JSONObject.parseObject(json);
        String doctorCode = param.getString("loginName");
        List<Map<String, Object>> doctorList = (List<Map<String, Object>>) sysUserServie.getSysUserList(doctorCode);
        String doctorId = doctorList.get(0).get("userid").toString();
        QueryWrapper<Queues> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("doctor", doctorId);
        List<Queues> queues = queuesService.list(queryWrapper);
        JSONArray jsonArray = new JSONArray();
        for (Queues queue : queues) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("orderNum", queue.getQueueNumber());
            jsonObject.put("age", "");
            jsonObject.put("cardNo", "");
            jsonObject.put("cateRegistered", "");
            jsonObject.put("codePatient", queue.getPatient());
            jsonObject.put("gender", "");
            jsonObject.put("isPreRegistered", "");
            jsonObject.put("markPatient", "");
            jsonObject.put("namePatient", queue.getPatient());
            jsonObject.put("pkClinicTriage", "");
            jsonObject.put("pkRegistered", "");
            jsonObject.put("statusQueue", "1");
            jsonObject.put("nameDeptItemRegistered", "");
            jsonObject.put("statusJump", "1");
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }


    @PostMapping("/listExpired")
    @WrapResponse
    public Object getPassPatients(@RequestBody String json) {
        JSONObject param = JSONObject.parseObject(json);
        String doctorCode = param.getString("loginName");
        List<Map<String, Object>> doctorList = (List<Map<String, Object>>) sysUserServie.getSysUserList(doctorCode);
        String doctorId = doctorList.get(0).get("userid").toString();
        QueryWrapper<Queues> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("doctor", doctorId);
        List<Queues> queues = queuesService.list(queryWrapper);
        JSONArray jsonArray = new JSONArray();
        for (Queues queue : queues) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("orderNum", queue.getQueueNumber());
            jsonObject.put("age", "");
            jsonObject.put("cardNo", "");
            jsonObject.put("cateRegistered", "");
            jsonObject.put("codePatient", queue.getPatient());
            jsonObject.put("gender", "");
            jsonObject.put("isPreRegistered", "");
            jsonObject.put("markPatient", "");
            jsonObject.put("namePatient", queue.getPatient());
            jsonObject.put("pkClinicTriage", "");
            jsonObject.put("pkRegistered", "");
            jsonObject.put("statusQueue", "1");
            jsonObject.put("nameDeptItemRegistered", "");
            jsonObject.put("statusJump", "1");
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }


    public static String sign(SortedMap<String, String> params) {
        String privateKey = "ea729ae5fb29bcae175b94af4df53473";
        List<String> keys = new ArrayList<String>(params.keySet());
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = String.valueOf(params.get(key));

            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        prestr = prestr + privateKey;
        return DigestUtils.md5Hex(prestr);
    }

    public static String parseCardNo(String paramStr) {
        String cardNo = "";
        if (StringUtils.isEmpty(paramStr)) {
            return "电子卡号为空！";
        }
        String cardNoAndTimeStr = null;
        try {
            if (paramStr.length() > 34) {
                if (paramStr.length() > 48) {// 密文96位
                    cardNoAndTimeStr = CryptoUtil.decode(paramStr);
                } else {// 密文48位
                    cardNoAndTimeStr = CryptoUtil.decryptCFB(paramStr);
                }
            } else {
                if (paramStr.indexOf("$") > 0) {// 不加密，输出原码
                    cardNoAndTimeStr = paramStr.replace("$", "&");
                } else {// 【自主研发】混淆算法：解密
                    cardNoAndTimeStr = decryptHX(paramStr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("电子卡号有误！");
        }
        if (ObjectUtils.isEmpty(cardNoAndTimeStr) || !cardNoAndTimeStr.contains("&")) {
            throw new RuntimeException("电子卡号有误！");
        }
        String[] cardNoAndTimeArr = cardNoAndTimeStr.split("&");
        if (cardNoAndTimeArr.length < 2) {
            throw new RuntimeException("电子卡号有误！");
        }
        String time = cardNoAndTimeArr[1];
        //从redis获取电子卡过期时间
        String outTimeStr = "86400000";

        Long outTime = Long.valueOf(outTimeStr);
        if (!ObjectUtils.isEmpty(time) && System.currentTimeMillis() - Long.valueOf(time) > outTime) {
            throw new RuntimeException("电子卡号已过期，请刷新后重试！");
        }
        cardNo = cardNoAndTimeArr[0];

        return cardNo;
    }


    public static void main(String[] args) {
        String cardNo = "6cb9c6a1eec85acb7319eb53fa567dda8d8c5f9ec9e831e4a995c478854e1de20955fe3484fdb16c0b1c4039dcbcee22";
        String txtTime = System.currentTimeMillis() + "";
        String merNo = "207";
        SortedMap<String, String> params = new TreeMap<>();
        params.put("txTime", txtTime);
        params.put("merNo", merNo);
        params.put("cardNo", cardNo);
        String sign = sign(params);
        StringBuffer sbf = new StringBuffer();
        sbf.append("<req>");
        sbf.append("<txTime>").append(txtTime).append("</txTime>");
        sbf.append("<merNo>").append(merNo).append("</merNo>");
        sbf.append("<cardNo>").append(cardNo).append("</cardNo>");
        sbf.append("<sign>").append(sign).append("</sign>");
        sbf.append("</req>");
        String param = sbf.toString();
        System.out.println(param);

        String cardNo1 = parseCardNo(cardNo);
        System.out.println(cardNo1);

    }


    public static String decryptHX(String content) {
        try {
            String eCardNo = content.substring(19, 20).toUpperCase() + content.substring(4, 5).toUpperCase()
                    + content.substring(28, 31) + content.substring(13, 16) + content.substring(20, 23) +
                    content.substring(5, 8) + content.substring(11, 13) + content.substring(26, 28) + content.substring(31);
            String timeStr = content.substring(23, 26) + content.substring(16, 19) + content.substring(8, 11) + content.substring(0, 4);
            return eCardNo + "&" + timeStr;
        } catch (Exception ex) {
            throw new RuntimeException("decryptHX error:" + ex);
        }
    }


    /**
     * 根据科室查询候诊患者
     *
     * @return
     */
    @PostMapping("/getWaitList")
    public Object getWaitListByDeptCode(@RequestBody String param) {
        JSONObject paramObj = JSONObject.parseObject(param);
        String codeDept = paramObj.getString("codeDept");
        //查询科室id
        String deptId = sysUserServie.getDeptId(codeDept);
        //查询科室的队列信息
        QueryWrapper<Queues> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("department", deptId);
        List<Queues> queuesList = queuesService.list(queryWrapper);
        //根据队列信息中的患者id查询患者信息
        List<String> patientIdList = queuesList.stream().map(a -> a.getPatient()).collect(Collectors.toList());
        QueryWrapper<Patients> patientsQueryWrapper = new QueryWrapper<>();
        patientsQueryWrapper.in("id", patientIdList);
        List<Patients> patientsList = patientsService.list(patientsQueryWrapper);
        //根据科室查询诊室
        QueryWrapper<Rooms> roomsQueryWrapper = new QueryWrapper<>();
        roomsQueryWrapper.eq("department", deptId);
        List<Rooms> roomsList = roomsService.list(roomsQueryWrapper);
        JSONArray result = new JSONArray();
        for (Rooms room : roomsList) {
            JSONObject roomsJson = new JSONObject();
            roomsJson.put("roomName", room.getRoomNumber());
            //查询诊室对应的队列
            List<Queues> roomQueueList = queuesList.stream().filter(a -> a.getRoom().equals(room.getId())).collect(Collectors.toList());
            //诊室对应的患者队列
            JSONArray waitingPatients = new JSONArray();
            for (Queues queue : roomQueueList) {
                JSONObject waitingPatient = new JSONObject();
                String patientName = patientsList.stream().filter(a -> a.getId().equals(queue.getPatient())).findFirst().get().getName();
                waitingPatient.put("name", patientName);
                if ("就诊中".equals(queue.getQueueStatus())) {
                    waitingPatient.put("currentPatient", patientName);
                }
                waitingPatients.add(waitingPatient);
            }
            roomsJson.put("waitingPatients", waitingPatients);
            result.add(roomsJson);
        }


        return result;
    }


    @PostMapping("/getRoomsByDeptId")
    public List<Map<String, Object>> getRoomsByDeptId(@RequestBody String param) {
        JSONObject paramObj = JSONObject.parseObject(param);
        String deptId = paramObj.getString("deptId");
        List<Map<String, Object>> roomList = queueUserMapper.getRoomListByDeptId(deptId);
        return roomList;
    }

    /**
     * 查询当前医生的队列信息
     *
     * @param param
     * @return
     */
    @PostMapping("/getDoctorQueueList")
    public Result<List<Map<String, Object>>> getDoctorQueueList(@RequestBody String param) {
        JSONObject paramObj = JSONObject.parseObject(param);
        String doctorId = paramObj.getString("doctorId");
        List<Map<String, Object>> result = queueUserMapper.getDoctorQueueList(doctorId);
        return Result.OK(result);
    }


}
