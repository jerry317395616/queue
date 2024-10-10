package org.jeecg.modules.queue.service.impl;

import org.jeecg.modules.queue.entity.Queues;
import org.jeecg.modules.queue.mapper.QueuesMapper;
import org.jeecg.modules.queue.service.IQueuesService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 队列管理
 * @Author: jeecg-boot
 * @Date:   2024-09-28
 * @Version: V1.0
 */
@Service
public class QueuesServiceImpl extends ServiceImpl<QueuesMapper, Queues> implements IQueuesService {

}
