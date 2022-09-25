package com.example.Pojo;


import lombok.Data;

@Data
public class User {

  private long id;
  private String name;
  private String password;
  private String token;
  private java.sql.Timestamp createDate;
  private String createBy;
  private java.sql.Timestamp modifyDate;
  private String modifyBy;



}
