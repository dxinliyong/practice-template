package com.yong.usemybatis.common;

import com.yong.entity.Spu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: liyong
 * @Date: 2024/6/18 15:22
 * @Email: dixin_liyong@163.com
 * @Desc：
 */
@Mapper
public interface MybatisSpuMapper {
    List<Spu> selectList(@Param("id") String id);
}
