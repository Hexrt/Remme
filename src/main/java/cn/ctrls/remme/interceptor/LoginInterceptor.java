package cn.ctrls.remme.interceptor;

import cn.ctrls.remme.controller.utils.CookieUtil;
import cn.ctrls.remme.mapper.UserMapper;
import cn.ctrls.remme.model.RemmeUser;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * 对所有页面进行登录状态的实时更新
 */
@Service(value = "loginInterceptor")
public class LoginInterceptor implements HandlerInterceptor {

    @Resource(name="cookieUtil")
    private CookieUtil cookieUtil;

    @Resource(name = "userMapper")
    UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //检验Cookie中的Token是否在数据库中，是否最新
        Cookie token = cookieUtil.getCookieByName(request,"token");
        if (token != null){
            RemmeUser remmeUser = userMapper.getUserByToken(token.getValue());
            //更新用户
            if (remmeUser!=null)
                request.getSession().setAttribute("user", remmeUser);
        }
        //继续进行
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
