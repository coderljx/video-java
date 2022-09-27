package com.example.Api;




import com.example.Exception.DataException;
import com.example.Exception.Read;
import com.example.Manager.UserServiceManager;
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



    @PostMapping("/add/{userid}/{appid}")
    public Response<?> addUserDetail(
            @PathVariable("userid") Integer userid,
            @PathVariable("appid") Integer appid,
            @RequestParam("sign") String sign,
            @RequestParam("rootid") Integer rootid,
            @RequestParam("roomid") Integer roomid
            )
    {
        Map<String,Object> params = new HashMap<>();
        Response<?> response = null;
        Coco coco = Coco.nuli;

        try {
            if (roomid < 0) {
                throw new DataException("roomid");
            }
            if (rootid < 0) {
                throw new DataException("rootid");
            }

            coco = Coco.ok;
        }catch (DataException dataException){
            String message = dataException.getMessage();
            coco.message = Read.readData(message);
        }finally {
            response = new Response<>(coco);
        }
        return response;
    }













}
