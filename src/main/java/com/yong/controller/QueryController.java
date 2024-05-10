package com.yong.controller;

import com.itextpdf.text.pdf.PRIndirectReference;
import com.yong.dao.SpuRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liyong
 * @Date: 2024/5/10 10:25
 * @Email: dixin_liyong@163.com
 * @Desc：
 */
@Api(tags = {"查询"})
@RestController
@RequestMapping(value = "/query")
public class QueryController {
    @Autowired
    private SpuRepository spuRepository;

    @GetMapping("v1")
    public Object getResult() {
        // 由调用方传递
        // 函数式编程可以看做是代码片段的引用
        // 之可以这样写Specification 其它的方法都有默认的实现
        // 这个方法没被实现 @Nullable	Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder);
        // 这里的原理就是运行时会创建一个匿名类重写这方法,被消费者进行调用
        return spuRepository.findAll((root, query, builder) -> builder.equal(root.get("id"), "10000006409700"));
    }
}
