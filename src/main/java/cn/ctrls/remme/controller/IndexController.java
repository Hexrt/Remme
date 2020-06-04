package cn.ctrls.remme.controller;
import cn.ctrls.remme.controller.utils.CookieUtil;
import cn.ctrls.remme.mapper.TasksMapper;
import cn.ctrls.remme.mapper.UserMapper;
import cn.ctrls.remme.model.RemmeTask;
import cn.ctrls.remme.model.RemmeUser;
import cn.ctrls.remme.model.UserMeta;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
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
                        @RequestParam(name = "action",defaultValue = "none") String action,
                        @RequestParam(name = "id", defaultValue = "none") String taskId){
        cookieUtil.checkLogin(httpServletRequest);
        //开始获取任务列表（公共任务）type = -1
        ArrayList<RemmeTask> taskList = tasksMapper.getComTask();
        //加载到请求中
        httpServletRequest.setAttribute("taskList", taskList);
        return "index";//转至index模板库
    }
}
