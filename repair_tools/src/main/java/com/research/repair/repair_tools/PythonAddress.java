package com.research.repair.repair_tools;

public class PythonAddress {


    public PythonAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return address ;
    }

    private  String address;

}
