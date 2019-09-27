package com.research.repair.cases;

import com.research.repair.config.TestConfig;
import com.research.repair.model.AddUserCase;
import com.research.repair.model.InterfaceName;
import com.research.repair.model.LoginCase;
import com.research.repair.model.User;
import com.research.repair.utils.ConfigFile;
import com.research.repair.utils.DatabaseUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserTest {

    @Test(dependsOnGroups="loginTrue", description = "添加用户接口测试")
    public void addUser() throws IOException {
        SqlSession session = DatabaseUtils.getSqlSession();
        AddUserCase addUserCase = session.selectOne("addUserCase",1);
        System.out.println(addUserCase.toString());
        System.out.println(TestConfig.addUserUrl);
        //发请求,获取结果
        String result = getResult(addUserCase);

        //验证返回结果
        User user = session.selectOne("addUser",addUserCase);



    }

    private String getResult(AddUserCase addUserCase) {
        return  null;
    }
}
