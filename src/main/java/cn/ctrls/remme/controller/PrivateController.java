package cn.ctrls.remme.controller;

import cn.ctrls.remme.mapper.TasksMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PrivateController {

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

}
