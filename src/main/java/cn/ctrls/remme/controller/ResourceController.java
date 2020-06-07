package cn.ctrls.remme.controller;

import cn.ctrls.remme.controller.utils.CookieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Wrapper;

@Controller
public class ResourceController {

    @Resource(name="cookieUtil")
    private CookieUtil cookieUtil;

    @GetMapping("/wrapper")
    public String wrapperRouter(@RequestParam(value = "wrapperId", required = false) String id,
                                HttpServletRequest request,
                                HttpServletResponse response){
        final int wrapperCnt = 3;
        if (id==null){
            Integer wrapperId = cookieUtil.getWrapperId(request);
            if (wrapperId!=null){//如果存在了用过户设置的背景，就一直显示
                id=String.valueOf(wrapperId);
            }
            else{//当用户为新用户，没有背景图的时候选择
                id = String.valueOf((int)(Math.random()*(wrapperCnt+1))%wrapperCnt+1);
            }
            response.addCookie(new Cookie("wrapperId",id));
        }
        //防止恶意参数
        try{Integer.parseInt(id);}catch (Exception e){ id = "1";}
        String path = "redirect:/res/wrapper/wrapper-"+id+".html";
        return path;
    }
}
