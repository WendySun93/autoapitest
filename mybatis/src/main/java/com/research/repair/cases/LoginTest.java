package com.research.repair.cases;

import com.alibaba.fastjson.JSON;
import com.research.repair.config.TestConfig;
import com.research.repair.model.InterfaceName;
import com.research.repair.model.LoginCase;
import com.research.repair.model.User;
import com.research.repair.utils.ConfigFile;
import com.research.repair.utils.DatabaseUtils;
import com.research.repair.utils.restclient.RestClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginTest {
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;

    @BeforeTest(groups = "loginTrue",description = "测试准备工作")
    public void beforeTest() {
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSER);
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.defaultHttpClient = new DefaultHttpClient();


    }

    @Test(groups="loginTrue", description = "用户登录成功接口测试")
    public void loginTrue() throws IOException {
        SqlSession session = DatabaseUtils.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
        //发请求,获取结果
        String result = getResult(loginCase);
        System.out.println(result);


    }

    private String getResult(LoginCase loginCase) throws IOException {
        restClient = new RestClient();
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json;charset=UTF-8"); //这个在postman中可以查询到

        Map<String, String> bodyMap = new HashMap();
        bodyMap.put("userName", "wenjuan");
        bodyMap.put("password", "123456");

        String requestBodyToJson = JSON.toJSONString(bodyMap);
        closeableHttpResponse = restClient.post(TestConfig.loginUrl, requestBodyToJson, headerMap);
        return  EntityUtils.toString(closeableHttpResponse.getEntity());
    }
}
