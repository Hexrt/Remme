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

    /**
     * 通过名字获取值
     * @param name
     * @return
     */
    private Cookie getCookieByName(HttpServletRequest request,String name){
        Cookie[] cookies = request.getCookies();
        if (cookies==null)return null;
        for (Cookie cookie : cookies){
            if (cookie.getName().equals(name)){
                return cookie;
            }
        }
        return null;
    }

    /**
     * 检验是否登录
     * @param request
     * @return
     */
    public boolean checkLogin(HttpServletRequest request){
        //检验Cookie中的Token是否在数据库中，是否最新
        Cookie token = getCookieByName(request,"token");
        if (token ==null)return false;
        RemmeUser remmeUser = userMapper.getUserByToken(token.getValue());
        if (remmeUser==null)return false;
        request.getSession().setAttribute("user", remmeUser);
        return true;
    }

    /**
     * 获取背景的id
     * @param request
     * @return
     */
    public Integer getWrapperId(HttpServletRequest request){
        Integer id = null;
        Cookie wrapperId = getCookieByName(request,"wrapperId");
        if (wrapperId==null)return null;
        //防止恶意参数进入
        try{
            id = Integer.parseInt(wrapperId.getValue());
        }catch (Exception e){
            id = null;
        }
        return id;
    }
}
