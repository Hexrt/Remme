package cn.ctrls.remme.controller;

import cn.ctrls.remme.controller.utils.CookieUtil;
import cn.ctrls.remme.mapper.TasksMapper;
import cn.ctrls.remme.model.RemmeTask;
import cn.ctrls.remme.model.RemmeUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ManagerController {

    @Resource(name = "tasksMapper")
    private TasksMapper tasksMapper;


    @Resource(name = "cookieUtil")
    private CookieUtil cookieUtil;

    //路由方法
    //
    //路由方法
    /**
     * 管理页面
     * @return
     */
    @GetMapping("/manager")
    public String Manager(){
        return "/manager/index";
    }

    /**
     * 后台发布界面
     * @return
     */
    @GetMapping("/manager/publish")
    public String Publish(){
        return "/manager/publish";
    }

    /**
     * 管理用户界面
     * @return
     */
    @GetMapping("/manager/user")
    public String User(){
        return "/manager/user";
    }

    /**
     * 后台设置界面
     * @return
     */
    @GetMapping("/manager/setting")
    public String Setting(){
        return "/manager/setting";
    }

    @PostMapping("/manager/publish")
    public String postPublish(
            @RequestParam("type") Integer type,
            @RequestParam("description") String description,
            @RequestParam("memo") String memo,
            @RequestParam("gmtStart") Long gmtStart,
            @RequestParam("gmtEnd") Long gmtEnd,
            HttpServletRequest request){
        if (!cookieUtil.checkLogin(request))return"/manager/publish";//如果出现问题，没有用户数据，则取消发布信息
        RemmeUser remmeUser = (RemmeUser)request.getSession().getAttribute("user");
        //如果是管理员则是公共任务，个人用户就是他自己的私有任务
        Integer owner = remmeUser.getType()==0?-1:remmeUser.getId();
        RemmeTask remmeTask = new RemmeTask();
        remmeTask.setDescription(description);
        remmeTask.setGmtStartTime(gmtStart);
        remmeTask.setGmtEndTime(gmtEnd);
        remmeTask.setMemo(memo);
        remmeTask.setOwner(owner);
        remmeTask.setType(type);
        tasksMapper.publish(remmeTask);
        return "/manager/publish";
    }
}
