package com.example.Utils;

import com.example.Dao.UserDao;
import com.example.Exception.DataException;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

public class MD5 {

    @Resource
    private UserDao userDao;


    public void validate(Integer userid,Integer appid,String sign,String token,Map<String,Object> params) throws DataException {
        if (!params.containsKey("appid")) {
            params.put("appid",appid);
        }
        if (!params.containsKey("userid")) {
            params.put("userid",userid);
        }
        int isAlive = userDao.queryUserFormIdAndToken(userid,appid, token);
        if (isAlive <= 0) {
            throw new DataException("E00001.00003");
        }
        if (sign == null || sign.equals("")) {
            throw new DataException("E00001.00004");
        }
        String newSign = DigestUtils.md5Hex(parseMap(params));
        if (!sign.equals(newSign)) {
            throw new DataException("E00001.00004");
        }
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
