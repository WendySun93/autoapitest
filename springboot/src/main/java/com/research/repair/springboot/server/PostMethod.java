package com.research.repair.springboot.server;

import com.research.repair.springboot.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/", description = "这是我全部的post方法")
@RequestMapping(value = "v1")
public class PostMethod {

    private static Cookie cookie;

    @PostMapping(value = "/login")
    @ApiOperation(value = "登录接口,成功后获取cookies信息")
    public String login(HttpServletResponse response, @RequestParam(value = "userName", required = true) String userName,
                        @RequestParam(value = "passWd", required = true) String password) {
        if (userName.equals("zhangsan") && password.equals("123456")) {
            cookie = new Cookie("login", "true");
            response.addCookie(cookie);
            return "恭喜你登录成功了!";

        }
        return "用户名或密码错误";
    }
    @PostMapping(value = "/login1")
    @ApiOperation(value = "登录接口,成功后获取cookies信息")
    public String login1(HttpServletResponse response, @RequestBody User u ){
        if (u.getUserName().equals("zhangsan") && u.getPassWd().equals("123456")) {
            cookie = new Cookie("login", "true");
            response.addCookie(cookie);
            return "恭喜你登录成功了!";

        }
        return "用户名或密码错误";
    }

    @PostMapping(value = "/getUserList")
    @ApiOperation(value = "获取用户列表信息")
    public String getUserList(HttpServletRequest request, @RequestBody User u) {
        Cookie[] cookies = request.getCookies();
        User user;
        //验证cookies是否合法
        for(Cookie c:cookies) {
            if(c.getName().equals("login") && c.getValue().equals("true") && u.getUserName().equals("zhangsan") && u.getPassWd().equals("123456")) {
                user = new User();
                user.setName("lisi");
                user.setAge("18");
                user.setSex("男");
                return user.toString();
            }
        }
        return "参数不合法";
    }


}
