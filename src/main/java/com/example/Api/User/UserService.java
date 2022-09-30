package com.example.Api.User;




import com.example.Exception.AuthException;
import com.example.Exception.DataException;
import com.example.Exception.Read;
import com.example.Manager.UserServiceManager;
import com.example.Pojo.User;
import com.example.Utils.Coco;
import com.example.Utils.MD5;
import com.example.Utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserService extends MD5 {

     private final  UserServiceManager userServiceManager;

     @Autowired
     public UserService(UserServiceManager userServiceManager) {
         this.userServiceManager = userServiceManager;
     }


    /**
     * 登录接口
     * @param userid
     * @param appid
     * @param sign
     * @param token
     * @param psd
     * @return
     */
    @PostMapping("/login/{userid}/{appid}")
    public Response<?> userLogin(
            @PathVariable("userid") Integer userid,
            @PathVariable("appid") Integer appid,
            @RequestParam("sign") String sign,
            @RequestParam("token") String token,
            @RequestParam("psd") String psd
    )
    {
        Map<String,Object> params = new HashMap<>();
        return new Response<>(userServiceManager.getUserToken(userid));
    }


    /**
     * 新增用户详情
     * @param userid
     * @param appid
     * @param sign
     * @param rootid
     * @param roomid
     * @return
     */
    @PostMapping("/add/{userid}/{appid}")
    public Response<?> addUserDetail(
            @PathVariable("userid") Integer userid,
            @PathVariable("appid") Integer appid,
            @RequestParam("sign") String sign,
            @RequestParam("rootid") Integer rootid,
            @RequestParam("roomid") Integer roomid,
            @RequestParam("token") String token
            )
    {
        Map<String,Object> params = new HashMap<>();
        Response<?> response;
        Coco coco = Coco.nuli;

        try {
            if (roomid < 0) {
                throw new DataException("roomid");
            }
            params.put("roomid",roomid);
            if (rootid < 0) {
                throw new DataException("rootid");
            }
            params.put("rootid",rootid);

            User us = super.valdate(userid,appid,sign,token,params);
            userServiceManager.addUserDetail(userid,roomid,rootid,us.getName());

            coco = Coco.ok;
        }catch (DataException dataException) {
            coco.message =  dataException.getMessage();
        }catch (AuthException authException) {
            String message = authException.getMessage();
            coco.message = Read.readData(message);
        } finally {
            response = new Response<>(coco);
        }
        return response;
    }


    /**
     * 查询该用户的详情
     * @param userid
     * @param appid
     * @param sign
     * @param token
     * @return
     */
    @GetMapping("/get/list/{userid}/{appid}")
    public Response<?> getUserDetail(
            @PathVariable("userid") Integer userid,
            @PathVariable("appid") Integer appid,
            @RequestParam("sign") String sign,
            @RequestParam("token") String token
    )
    {
        Map<String,Object> params = new HashMap<>();
        Response<UserVo> response;
        Coco coco = Coco.nuli;
        UserVo vo = null;
        try {
            super.valdate(userid,appid,sign,token,params);

            vo = userServiceManager.getUserDetail(userid);

            coco = Coco.ok;
        } catch (AuthException authException) {
            String message = authException.getMessage();
            coco.message = Read.readData(message);
        } finally {
            response = new Response<>(coco);
            if (coco == Coco.ok) {
                response.setData(vo);
            }
        }
        return response;
    }















}
