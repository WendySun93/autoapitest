package com.research.repair.model;

import lombok.Data;

@Data
public class GetUserListCase {
    private String userName;
    private  String age;
    private String sex;
    private String expected;
}
