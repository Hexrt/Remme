package cn.ctrls.remme.controller;
import cn.ctrls.remme.controller.utils.CookieUtil;
import cn.ctrls.remme.mapper.TasksMapper;
import cn.ctrls.remme.model.RemmeTask;
import cn.ctrls.remme.model.RemmeUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

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


    @Resource(name="tasksMapper")
    private TasksMapper tasksMapper;

    @Resource(name="cookieUtil")
    private CookieUtil cookieUtil;

    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest,
                        @RequestParam(name = "action", required = false) String action,
                        @RequestParam(name = "taskId",required = false) Integer taskId){
        //开始获取任务列表（公共任务）type = -1
        ArrayList<RemmeTask> taskList = tasksMapper.getComTask();
        //加载任务列表到请求中
        httpServletRequest.setAttribute("taskList", taskList);
        //加载活动任务到请求中，显示
        if (taskList!=null){
            RemmeTask task = taskId==null?taskList.get(0):tasksMapper.getTaskById(taskId);
            if (task!=null&&task.getOwner()!=-1){ task = null;}
            httpServletRequest.setAttribute("activeTask", task);
        }
        return "index";//转至index模板库
    }
}
