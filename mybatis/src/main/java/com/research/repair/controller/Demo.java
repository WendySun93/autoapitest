package com.research.repair.controller;

import com.research.repair.model.DemoUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@Api(value="v1",description = "这是我第一个版本的demo")
@RequestMapping("v1")
public class Demo {
    //首先获取一个执行sql语句的对象
    @Autowired
    private SqlSessionTemplate template;

    @GetMapping(value="/getUserCount")
    @ApiOperation(value="获取到用户数")
    public int getUserCount() {
        return template.selectOne("getUserCount1");
    }

    @PostMapping(value="/addUser")
    @ApiOperation(value="添加用户")
    public int addUser(@RequestBody DemoUser user) {
        int result = template.insert("addUser1",user);
        return result;
    }


    @PostMapping(value="/updateUser")
    @ApiOperation(value="修改用户")
    public int updateUser(@RequestBody DemoUser user) {
        int result = template.update("updateUser1",user);
        return result;
    }
    @PostMapping(value="/deleteUser2")
    @ApiOperation(value="删除用户")
    public int deleteUser(@RequestParam(value = "id") int id) {
        int result = template.delete("deleteUser1",id);
        return result;
    }
    @PostMapping(value="/deleteUser")
    @ApiOperation(value="删除用户")
    public int deleteUser(@RequestBody DemoUser user) {
        int result = template.delete("deleteUser1",user);
        return result;
    }
}
