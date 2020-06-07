package cn.ctrls.remme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResourceController {
    @GetMapping("/wrapper")
    public String wrapperRouter(@RequestParam(value = "wrapperId", required = false) String id){
        final int wrapperCnt = 3;
        if (id==null){
            id = String.valueOf((int)(Math.random()*(wrapperCnt+1))%wrapperCnt+1);
        }
        String path = "redirect:/res/wrapper/wrapper-"+id+".html";
        return path;
    }
}
