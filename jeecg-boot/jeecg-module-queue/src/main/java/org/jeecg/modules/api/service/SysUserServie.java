package org.jeecg.modules.api.service;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.api.mapper.QueueUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysUserServie {

    @Autowired
    private QueueUserMapper sysUserMapper;


    /**
     * 获取签到机科室列表
     * @param mac
     * @return
     */
    public List<Map<String,Object>> getSignDeptList(String mac){
        return sysUserMapper.getSignDeptList(mac);
    }

    public List<Map<String,Object>> getSysUserList(String code){
        return sysUserMapper.getSysUserList(code);
    }

    public List<Map<String,Object>> getSysDeptList(String id){
        return sysUserMapper.getSysDeptList(id);
    }

    public String getDeptId (String code){
        List<Map<String,Object>> result = sysUserMapper.getSysDeptId(code);
        if(result.size()>0){
            return result.get(0).get("id").toString();
        }else {
            return "";
        }
    }

    public List<Map<String,Object>> getSysDeptListByids(@Param("ids") List<String> ids){
        return sysUserMapper.getSysDeptListByids(ids);
    }




}
