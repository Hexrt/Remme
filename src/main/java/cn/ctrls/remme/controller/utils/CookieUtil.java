package cn.ctrls.remme.controller.utils;

import cn.ctrls.remme.mapper.UserMapper;
import cn.ctrls.remme.model.RemmeUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component(value = "cookieUtil")
public class CookieUtil {


    @Resource(name="userMapper")
    private UserMapper userMapper;


    public boolean checkLogin(HttpServletRequest request){
        //检验Cookie中的Token是否在数据库中，是否最新
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    RemmeUser remmeUser = userMapper.getUserByToken(token);
                    if (remmeUser!=null){
                        request.getSession().setAttribute("user", remmeUser);
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }
}
