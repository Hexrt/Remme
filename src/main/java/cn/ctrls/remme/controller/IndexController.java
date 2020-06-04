package cn.ctrls.remme.controller;
import cn.ctrls.remme.mapper.UserMapper;
import cn.ctrls.remme.model.RemmeUser;
import cn.ctrls.remme.model.UserMeta;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/***
* @Description:    测试类
* @Author:         Hexrt
* @CreateDate:     2020/5/17 19:45
* @UpdateUser:     Hexrt
* @UpdateDate:     2020/5/17 19:45
* @UpdateRemark:   
* @Version:        1.0
*/
@Controller
public class IndexController {


    @Resource(name="userMapper")
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest,
                        @RequestParam(name = "action",defaultValue = "none") String action,
                        @RequestParam(name = "id", defaultValue = "none") String taskId){
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    RemmeUser remmeUser = userMapper.getUserByToken(token);
                    if (remmeUser!=null){
                        httpServletRequest.getSession().setAttribute("user", remmeUser);
                    }
                    break;
                }
            }
        }
        return "index";//转至index模板库
    }
}
