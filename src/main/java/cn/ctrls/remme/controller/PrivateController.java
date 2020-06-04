package cn.ctrls.remme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PrivateController {

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
                              @RequestParam(name = "action",defaultValue = "none") String action,
                              @RequestParam(name = "id", defaultValue = "none") String taskId){
        if (action==null||taskId==null){
            return "redirect:/";
        }else if (action.equals("edit")){
            httpServletRequest.getSession().setAttribute("taskId", taskId);
            return "private/private";
        }else if (action.equals("finish")){
            return "redirect:/";
        }else if (action.equals("cancel")){
            return "redirect:/";
        }else if (action.equals("delay")){
            return "redirect:/";
        }
        return "redirect:/";
    }

}
