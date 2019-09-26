package com.research.repair.controller;

import com.research.repair.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@Api(value = "v1", description = "用户管理系统")
@RequestMapping()
public class UserManager {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @PostMapping(value = "/login")
    @ApiOperation(value = "登录接口")
    public boolean login(HttpServletResponse response, @RequestBody User user) {
        int i = sqlSessionTemplate.selectOne("login", user);
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        log.info("查询到的结果是" + i);
        if (i > 0) {
            log.info("登录的用户是:" + user.getName());
            return true;
        }
        return false;
    }

    @PostMapping(value = "/addUser")
    @ApiOperation(value = "添加用户接口")
    public boolean addUser(HttpServletRequest request, @RequestBody User user) {
        Boolean x = verifyCookies(request);
        int result = 0;
        if (x == true) {
            result = sqlSessionTemplate.insert("addUser", user);

        }
        if (result > 0) {
            log.info("添加用户的数量是" + result);
        }
        return false;
    }

    @PostMapping(value = "/getUserInfo")
    @ApiOperation(value = "获取用户(列表)信息接口")
    public List<User> getUserInfo(HttpServletRequest request, @RequestBody User user) {
        Boolean x = verifyCookies(request);
        if (x == true) {
            List<User> users = sqlSessionTemplate.selectList("getUserList", user);
            log.info("getUserInfo获取到的用户数量是:" + users.size());
            return users;
        }
        return null;

    }

    @PostMapping(value = "/updateUserInfo")
    @ApiOperation(value = "更新/删除用户接口")
    public int updateUser(HttpServletRequest request, @RequestBody User user) {
        Boolean x = verifyCookies(request);
        int result = 0;
        if (x == true) {
            result = sqlSessionTemplate.update("updateUserInfo", user);
        }
        log.info("更新数据的条目数是:" + result);
        return result;
    }


    private Boolean verifyCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            log.info("cookies为空");
            return false;
        }
        for (Cookie cookie : cookies) {
            if ("login".equals(cookie.getName()) && "true".equals(cookie.getValue())) {
                log.info("cookies验证通过");
                return true;
            }

        }
        return false;
    }
}
