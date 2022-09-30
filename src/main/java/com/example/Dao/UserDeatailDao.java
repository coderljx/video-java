package com.example.Dao;

import com.example.Api.User.UserVo;
import com.example.Pojo.User;
import com.example.Pojo.UserDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDeatailDao {


    /**
     * 根据用户id验证该用户是否已经在小区物业系统了
     * @param userid
     * @return
     */
    UserDetail queryFormById(@Param("userid") Integer userid);

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


    /**
     * 修改用户的住房信息，例如搬家
     * @param roomid
     * @param rootid
     * @param create
     * @return
     */
    Integer updateUserDetail(@Param("id") Integer id,
                             @Param("roomid") Integer roomid,
                             @Param("rootid") Integer rootid,
                             @Param("create") String create);


    /**
     * 根据用户id，查询该用户的详情
     * @param userid
     * @return
     */
    UserVo getUserDetail(@Param("userid") Integer userid);


}
