package com.example.Manager;

import com.example.Api.User.UserVo;
import com.example.Dao.UserDeatailDao;
import com.example.Pojo.UserDetail;
import com.example.Utils.Redis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserServiceManager {

    private final Redis redis;
    private final UserDeatailDao userDeatailDao;


     @Autowired
     public UserServiceManager(Redis redis,
                               UserDeatailDao userDeatailDao) {
         this.redis = redis;
         this.userDeatailDao = userDeatailDao;
     }

    /**
     * 设置用户的过期时间
     */
    public void setUserLogin(Integer userid)  {
        UUID uuid = UUID.randomUUID();
        String uuid2 = uuid.toString();
        redis.setString(userid + "",uuid2.replaceAll("-",""));
     }

     public String getUserToken(Integer userid){
        return redis.getKey(userid + "", "");
     }


    /**
     * 新增一个用户详情，存储该用户的住房等信息
     * @param userid
     * @param rootid
     * @param roomid
     * @param create
     */
     public void addUserDetail(Integer userid,Integer roomid,Integer rootid,String create) {
         UserDetail userDetail = userDeatailDao.queryFormById(userid);
         if (userDetail != null && userDetail.getId() > 0) {
             userDeatailDao.updateUserDetail( (int) userDetail.getId(),roomid,rootid,create);
         }else {
             userDeatailDao.addUserDetail(userid,roomid,rootid,create);
         }
     }

    /**
     * 查询用户信息
     * @param userid
     * @return
     */
     public UserVo getUserDetail(Integer userid){
         UserVo userDetail = userDeatailDao.getUserDetail(userid);
         return userDetail;
     }


}
