package com.example.Dao;


import com.example.Pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    /**
     * 根据id查询用户信息
     * @param userid
     * @return
     */
    public User queryFormUserByid(Integer userid);

    /**
     * 验证用户的token和其他信息是否真实存在
     * @param userid
     * @param appid
     * @param token
     * @return
     */
    public int queryUserFormIdAndToken(Integer userid,Integer appid,String token);

    


}
