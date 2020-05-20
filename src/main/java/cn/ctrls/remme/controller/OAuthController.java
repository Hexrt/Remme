package cn.ctrls.remme.controller;

import cn.ctrls.remme.dto.AccessTokenDTO;
import cn.ctrls.remme.dto.GithubUser;
import cn.ctrls.remme.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/***
* @Description:    授权管理类
* @Author:         Hexrt
* @CreateDate:     2020/5/17 21:16
* @UpdateUser:     Hexrt
* @UpdateDate:     2020/5/17 21:16
* @UpdateRemark:   
* @Version:        1.0
*/
@Controller
public class OAuthController {
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.client.redirectUrl}")
    private String redirectUrl;

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callBack(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_url(redirectUrl);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
//        System.out.println(accessToken);
        GithubUser user = githubProvider.getUser(accessToken);
//        System.out.println(user.getLogin());
        if (user != null){
            request.getSession().setAttribute("user",user);
        } else{

        }
        return "index";
    }
}
