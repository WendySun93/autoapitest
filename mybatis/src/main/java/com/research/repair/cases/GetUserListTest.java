package com.research.repair.cases;

import com.research.repair.config.TestConfig;
import com.research.repair.model.GetUserListCase;
import com.research.repair.utils.DatabaseUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetUserListTest {
    @Test(dependsOnGroups ="loginTrue", description = "获取性别为男的用户信息")
    public void  getUserlistInfo() throws IOException {
        SqlSession session = DatabaseUtils.getSqlSession();
        GetUserListCase getUserListCase =  session.selectOne("getUserListCase",1);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);
    }

}
