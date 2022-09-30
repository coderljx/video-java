package com.example.Pojo;

import lombok.Data;

@Data
public class UserDetail {

  private long id;
  private java.sql.Timestamp createDate;
  private String createBy;
  private java.sql.Timestamp modifyDate;
  private String modifyBy;
  private long userid;
  private long rootid;
  private long roomid;



}
