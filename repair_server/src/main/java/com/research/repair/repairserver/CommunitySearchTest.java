package com.research.repair.repairserver;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.research.repair.utils.restclient.RestClient;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class CommunitySearchTest {
    private static final int RESPNSE_STATUS_CODE_200 = 200;
    private ResourceBundle bundle = ResourceBundle.getBundle("application");
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;
    private  String url;
    @BeforeClass
    public void setUp() {
        url = bundle.getString("test.url");
        System.out.println("已经执行了?");
    }

    //省市村庄都有
    @Test
    public void TestcommunitySearchWithProvinceCity() throws IOException {
        String testUrl = url + bundle.getString("commutity.uri") ;
        restClient = new RestClient();
        HashMap<String, String> headermap = new HashMap<String, String>();
        headermap.put("Content-Type", "application/json;charset=UTF-8"); //这个在postman中可以查询到

        Map<String, String> map = new HashMap();
        map.put("param", "河北省沧州杜林乡杜林村东街东场73号");
        String addressJson = JSON.toJSONString(map);
        System.out.println(addressJson);
        closeableHttpResponse = restClient.post(testUrl, addressJson, headermap);
        //验证状态码是不是200
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");
        //断言响应json内容
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity());
        JSONObject responseJson = JSON.parseObject(responseString);
        System.out.println(responseJson);
    }

    //不填省份
    @Test
    public void TestcommunitySearchWithoutProvince() throws IOException {
        String testUrl = url + bundle.getString("commutity.uri") ;
        restClient = new RestClient();
        HashMap<String, String> headermap = new HashMap<String, String>();
        headermap.put("Content-Type", "application/json;charset=UTF-8"); //这个在postman中可以查询到
        //参数赋值
        Map<String, String> map = new HashMap();
        map.put("param", "沧州杜林乡杜林村东街东场73号");
        closeableHttpResponse = restClient.post(testUrl, JSON.toJSONString(map), headermap);
        //验证状态码是不是200
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");

        //断言响应json内容
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity());
        JSONObject responseJson = JSON.parseObject(responseString);
        System.out.println(responseJson);
    }

    //不填县
    @Test
    public void TestcommunitySearchWithoutCounty() throws IOException {
        String testUrl = url + bundle.getString("commutity.uri") ;
        restClient = new RestClient();
        HashMap<String, String> headermap = new HashMap<String, String>();
        headermap.put("Content-Type", "application/json;charset=UTF-8");
        Map<String, String> map = new HashMap();
        //参数赋值
        map.put("param", "杜林村东街东场73号");
        String addressJson = JSON.toJSONString(map);
        System.out.println(addressJson);
        closeableHttpResponse = restClient.post(testUrl, addressJson, headermap);
        //验证状态码是不是200
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "status code is not 200");

        //断言响应json内容
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity());
        JSONObject responseJson = JSON.parseObject(responseString);
        System.out.println(responseJson);
        Object zipcode = ((JSONObject) responseJson.get("data")).get("zipCode");
        Assert.assertEquals(zipcode, "054700");

    }
}
