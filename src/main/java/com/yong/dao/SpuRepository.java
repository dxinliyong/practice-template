package com.yong.dao;

import com.yong.entity.Spu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @Author: liyong
 * @Date: 2024/5/9 16:43
 * @Email: dixin_liyong@163.com
 * @Desc：
 */

public interface SpuRepository extends JpaRepository<Spu, String>, JpaSpecificationExecutor<Spu> {
}
