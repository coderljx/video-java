package com.example.Pojo;

import lombok.Data;

@Data
public class Root {

  private long id;
  private String name;
  private java.sql.Timestamp createDate;
  private String createBy;
  private java.sql.Timestamp modifyDate;
  private String modifyBy;
  private long isDelete;
}
