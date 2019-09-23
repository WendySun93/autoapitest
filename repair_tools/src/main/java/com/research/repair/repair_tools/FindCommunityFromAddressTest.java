package com.research.repair.repair_tools;
import com.alibaba.fastjson.JSON;
import com.research.repair.repair_tools.PythonAddress;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.research.repair.utils.restclient.RestClient;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

public class FindCommunityFromAddressTest {
    private static final int RESPNSE_STATUS_CODE_200 = 200;
//    String host = "http://10.160.16.218:8082";
    String host = "http://10.150.26.160:5888";
    String url;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;

    @BeforeClass
    public void setUp() {
        url = host + "/v1.0/find_community_from_address/";
        System.out.println(url  + " 已经执行了?");
    }

    //省市村庄都有
    @Test
    public  void TestcommunitySearchWithProvinceCity() throws IOException {
        restClient = new RestClient();
        HashMap<String,String> headermap = new HashMap<String,String>();
        headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到
        PythonAddress address = new PythonAddress("河北省沧州杜林乡杜林村东街东场73号");
        String encoderAddress = URLEncoder.encode(address.getAddress());


        System.out.println(url + address.toString());
//        System.out.println("encoder"+ encoderAddress);

        closeableHttpResponse = restClient.post(url + encoderAddress , headermap);

        //验证状态码是不是200
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200,"status code is not 200");
        //断言响应json内容中name和job是不是期待结果
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity());
        System.out.println(responseString);
    }
    //不填省份
    @Test
    public  void TestcommunitySearchWithoutProvince() throws IOException {
        restClient = new RestClient();
        HashMap<String,String> headermap = new HashMap<String,String>();
        headermap.put("Content-Type", "application/json;charset=UTF-8"); //这个在postman中可以查询到
        PythonAddress address = new PythonAddress("沧州杜林乡杜林村东街东场73号");

        String encoderAddress = URLEncoder.encode(address.getAddress());


        System.out.println(url + address.toString());
//        System.out.println("encoder"+ encoderAddress);

        closeableHttpResponse = restClient.post(url + encoderAddress , headermap);
        //验证状态码是不是200
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200,"status code is not 200");

        //断言响应json内容中字段是不是期待结果
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity());
        System.out.println(responseString);
    }
    //不填县
    @Test
    public  void TestcommunitySearchWithoutCounty() throws IOException {
        restClient = new RestClient();
        HashMap<String,String> headermap = new HashMap<String,String>();
        headermap.put("Content-Type", "application/json;charset=UTF-8"); //这个在postman中可以查询到
        PythonAddress address = new PythonAddress("杜林村东街东场73号");

        String encoderAddress = URLEncoder.encode(address.getAddress());


        System.out.println(url + address.toString());
//        System.out.println("encoder"+ encoderAddress);

        closeableHttpResponse = restClient.post(url + encoderAddress , headermap);
        //验证状态码是不是200
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200,"status code is not 200");

        //断言响应json内容中name和job是不是期待结果
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity());
        System.out.println(responseString);
        System.out.println(JSON.parseObject(responseString).get("id"));

    }
}
