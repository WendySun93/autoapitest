package com.research.repair.springboot.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class GetMethod {
    @GetMapping(value = "/getCookies")
    @ApiOperation(value="测试-获得cookies")
    public String getCookies(HttpServletResponse response) {
//        HttpServletRequest 装请求信息的类             HttpServletResponse装响应信息的类

        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        return "恭喜获得cookie成功";
    }

    /*
    要求客户端携带cookies访问
    这是一个带cookies信息的get请求
     */
    @GetMapping(value = "/getWithCookies")
    @ApiOperation(value="测试-带有cookies请求")
    public String getWithCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return "你必须携带cookie信息来";
        }
        for (Cookie cookie : cookies) {
            if ((cookie.getName().equals("login")) && (cookie.getValue().equals("true"))) {
                return "这是一个需要携带cookies信息才能访问的get请求";
            }
        }
        return "你必须携带cookie信息来";
    }

    /*
    开发一个需要携带参数才能访问的get请求
    *第一种实现方式:url:key=value&key=value
     */
    @GetMapping(value="/getWithParam1")
    @ApiOperation(value = "带有参数的get请求1")
    public Map<String,Integer> getList1 (@RequestParam Integer start, @RequestParam Integer end) {
        Map<String,Integer>  myList = new HashMap<>();
        myList.put("鞋",400);
        myList.put("干脆面",1);
        myList.put("上衣",300);
    return myList;
    }

    /*
  开发一个需要携带参数才能访问的get请求
  *第2种实现方式:url: ip:port/getWithParam2/start/end
   */
    @GetMapping(value="/getWithParam2/{start}/{end}")
    @ApiOperation(value = "带有参数的get请求2")
    public Map<String,Integer> getList2 (@PathVariable Integer start, @PathVariable Integer end) {
        Map<String,Integer>  myList = new HashMap<>();
        myList.put("鞋",400);
        myList.put("干脆面",1);
        myList.put("上衣",300);
        return myList;
    }
}

