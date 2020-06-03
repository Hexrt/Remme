package cn.ctrls.remme.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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
    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest,
                        @RequestParam(name = "action",defaultValue = "none") String action,
                        @RequestParam(name = "id", defaultValue = "none") String taskId){
        if (action==null||httpServletRequest.getSession().getAttribute("user")==null)return "index";//转至index模板库
        else if (action.equals("edit")){
            return "private";
        }else if (action.equals("finish")){

        }else if (action.equals("cancel")){

        }else if (action.equals("delay")){

        }
        return "index";
    }
}
