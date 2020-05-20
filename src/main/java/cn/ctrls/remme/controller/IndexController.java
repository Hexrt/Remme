package cn.ctrls.remme.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String index(){
        return "index";//转至index模板库
    }
}
