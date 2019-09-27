package com.research.repair.cases;

import com.research.repair.config.TestConfig;
import com.research.repair.model.GetUserInfoCase;
import com.research.repair.model.GetUserListCase;
import com.research.repair.utils.DatabaseUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetUserInfoTest {
    @Test(dependsOnGroups ="loginTrue", description = "获取userID为1的用户信息")
    public void  getUserInfo() throws IOException {
        SqlSession session = DatabaseUtils.getSqlSession();
        GetUserInfoCase getUserInfoCase =  session.selectOne("getUserInfoCase",1);
        System.out.println(getUserInfoCase.toString());
        System.out.println(TestConfig.getUserInfoUrl);
    }

}
