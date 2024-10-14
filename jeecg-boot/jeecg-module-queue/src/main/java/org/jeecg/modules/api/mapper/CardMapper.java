package org.jeecg.modules.api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface CardMapper {



    List<Map<String, Object>> getCardNo(@Param("cardNo") String cardNo);
}
