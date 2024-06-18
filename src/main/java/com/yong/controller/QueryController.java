package com.yong.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yong.dao.SpuRepository;
import com.yong.entity.Spu;
import com.yong.usemybatis.common.MybatisSpuMapper;
import com.yong.usemybatis.plus.mapper.SpuMapper;
import io.swagger.annotations.Api;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liyong
 * @Date: 2024/5/10 10:25
 * @Email: dixin_liyong@163.com
 * @Desc： 整合 查询
 */
@Api(tags = {"查询"})
@RestController
@RequestMapping(value = "/query")
public class QueryController {
    @Autowired
    private SpuRepository spuRepository;

    @Autowired
    SpuMapper spuMapper;

    @Autowired
    MybatisSpuMapper mybatisSpuMapper;

    /**
     * jpa 查询
     * @return
     */

    @GetMapping("vJpa")
    public Object getResultJpa() {
        // 由调用方传递
        // 函数式编程可以看做是代码片段的引用
        // 之可以这样写Specification 其它的方法都有默认的实现
        // 这个方法没被实现 @Nullable	Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder);
        // 这里的原理就是运行时会创建一个匿名类重写这方法,被消费者进行调用
        return spuRepository.findAll((root, query, builder) -> builder.equal(root.get("id"), "10000006409700"));

    }

    /**
     * mybatis 查询
     * @return
     */
    @GetMapping("vMybatisPlus")
    public Object getResultMybatisPlus() {
        // mybatis-plus
        QueryWrapper<Spu> queryWrapper = new QueryWrapper<>();
        Spu spu = new Spu();
        spu.setId("10000006409700");
        queryWrapper.setEntity(spu);
        List<Spu> spuList = spuMapper.selectList(queryWrapper);
        return spuList;
    }

    /**
     * mybatis 查询
     * @return
     */
    @GetMapping("vMybatis")
    public Object getResultMybatis(@RequestParam("id") String id) {
        List<Spu> spuList = mybatisSpuMapper.selectList(id);
        return spuList;
    }
}
