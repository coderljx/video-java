package com.example.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDeatailDao {

    /**
     * 生成一个用户详情信息
     * @param userid
     * @param roomid
     * @param rootid
     * @return
     */
    Integer addUserDetail(@Param("userid") Integer userid,
                          @Param("roomid") Integer roomid,
                          @Param("rootid") Integer rootid,
                          @Param("create") String create);


}
