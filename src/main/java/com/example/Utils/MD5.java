package com.example.Utils;

import com.example.Dao.UserDao;
import com.example.Exception.AuthException;
import com.example.Exception.DataException;
import com.example.Pojo.User;
import lombok.SneakyThrows;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

public class MD5 {

    @Resource
    private UserDao userDao;


    public void validate(Integer userid,Integer appid,String sign,String token,Map<String,Object> params) throws AuthException {
        if (!params.containsKey("appid")) {
            params.put("appid",appid);
        }
        if (!params.containsKey("userid")) {
            params.put("userid",userid);
        }
        if (StringHelper.isEmply(token)) {
            throw new AuthException("E00001.00003");
        }
        params.put("token",token);
        int isAlive = userDao.queryUserFormIdAndToken(userid,appid, token);
        if (isAlive <= 0) {
            throw new AuthException("E00001.00003");
        }
        if (sign == null || sign.equals("")) {
            throw new AuthException("E00001.00004");
        }
        String newSign = DigestUtils.md5Hex(parseMap(params));
        System.out.println(newSign);
        if (!sign.equals(newSign)) {
            throw new AuthException("E00001.00004");
        }
    }


    public User valdate(Integer userid, Integer appid, String sign, String token, Map<String,Object> params) throws AuthException {
        validate(userid,appid,sign,token,params);
        return userDao.queryFormUserByid(userid);
    }


    private String parseMap(Map<String,Object> params){
        Set<Map.Entry<String, Object>> entries = params.entrySet();
        StringBuilder stringBuffer = new StringBuilder();
        for (Map.Entry<String, Object> map : entries) {
            stringBuffer.append(map.getKey()).append(map.getValue());
        }
        return stringBuffer.toString();
    }



}
