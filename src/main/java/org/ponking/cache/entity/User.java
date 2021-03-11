package org.ponking.cache.entity;

import java.io.Serializable;

/**
 * @Author ponking
 * @Date 2021/3/11 15:53
 */

public class User implements Serializable {

    private static final long serialVersionUID = -6249397911566315813L;

    private String userName;

    private Integer id;

    private String address;

    private Integer age;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", id=" + id +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
