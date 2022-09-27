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

    public int queryUserFormIdAndToken(Integer userid,Integer appid,String token);

    


}
