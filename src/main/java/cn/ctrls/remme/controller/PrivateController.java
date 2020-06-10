package cn.ctrls.remme.controller;

import cn.ctrls.remme.mapper.TasksMapper;
import cn.ctrls.remme.mapper.UserMapper;
import cn.ctrls.remme.mapper.UserMetaMapper;
import cn.ctrls.remme.model.RemmeUser;
import cn.ctrls.remme.model.UserMeta;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.security.util.Password;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class PrivateController {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Resource(name = "userMetaMapper")
    private UserMetaMapper userMetaMapper;

    @Resource(name = "tasksMapper")
    private TasksMapper tasksMapper;

    @GetMapping("/private/message")
    public String privateMessage(){
        return "/private/privateMessage";
    }

    @GetMapping("/private/information")
    public String privateInformation(){
        return "/private/privateInformation";
    }

    @GetMapping("/private/setting")
    public String privateSetting(){
        return "/private/privateSetting";
    }

    @GetMapping("/private/logout")
    public String privateLogout(){
        return "/private/privateLogout";
    }

    @GetMapping("/private/tasks")
    public String privateTasks(HttpServletRequest httpServletRequest,
                              @RequestParam(name = "action",required = false) String action,
                              @RequestParam(name = "id", required = false) Integer taskId){
        if (action==null||taskId==null){
            return "redirect:/";
        }else if (action.equals("edit")){
            httpServletRequest.getSession().setAttribute("taskId", taskId);
            return "private/private";
        }else if (action.equals("finish")){
            tasksMapper.modifyTypeById(2,taskId);
            return "redirect:/";
        }else if (action.equals("cancel")){
            tasksMapper.modifyTypeById(3,taskId);
            return "redirect:/";
        }else if (action.equals("delay")){
            tasksMapper.modifyTypeById(4,taskId);
            return "redirect:/";
        }
        return "redirect:/";
    }


    @GetMapping("/private/login")
    private String privateLoginPage(){
        return "/private/login";
    }

    @PostMapping("/private/login")
    private String privateLogin(HttpServletRequest request,
                                HttpServletResponse response,
                                @RequestParam(name = "email", required = false) String emailUrl,
                                @RequestParam(name = "password", required = false) String password){
        if (emailUrl==null||password==null) return"/index";
        UserMeta userMeta = userMetaMapper.getUserMetaByEmailUrl(emailUrl);
        if (userMeta==null)return"/index";
        RemmeUser user = userMapper.getUserById(userMeta.getId());
        if (user==null)return"/index";
        if (user.getPassword().equals(password)){
            request.getSession().setAttribute("user", user);
            String token = UUID.randomUUID().toString();
            userMapper.updateToken(user.getId(),token);//更新token
            Cookie tkCookie = new Cookie("token", token);
            tkCookie.setPath("/");
            response.addCookie(tkCookie);
        }
        return"/index";
    }
}
