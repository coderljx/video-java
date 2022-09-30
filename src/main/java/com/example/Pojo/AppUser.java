package com.example.Pojo;


import lombok.Data;

@Data
public class AppUser {

  private long id;
  private java.sql.Timestamp createDate;
  private String createBy;
  private java.sql.Timestamp modifyDate;
  private String modifyBy;
  private long appid;
  private String token;
  private long userid;





}
