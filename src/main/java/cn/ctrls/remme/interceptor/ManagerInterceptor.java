package cn.ctrls.remme.interceptor;

import cn.ctrls.remme.controller.utils.CookieUtil;
import cn.ctrls.remme.mapper.UserMapper;
import cn.ctrls.remme.model.RemmeUser;
import okhttp3.Interceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service(value="managerInterceptor")
public class ManagerInterceptor implements HandlerInterceptor {

    @Resource(name="cookieUtil")
    private CookieUtil cookieUtil;

    @Resource(name="userMapper")
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //检验Cookie中的Token是否在数据库中，是否最新
        String token = cookieUtil.getToken(request);
        //没有登录，拦截
        if (token==null)return false;
        RemmeUser remmeUser = userMapper.getUserByToken(token);
        //token失效，拦截
        if (remmeUser==null)return false;
        request.getSession().setAttribute("user", remmeUser);
        //非管理员登录，拦截
        if (remmeUser.getType()!=0)return false;
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
