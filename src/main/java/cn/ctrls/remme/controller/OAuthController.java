package cn.ctrls.remme.controller;

import cn.ctrls.remme.dto.AccessTokenDTO;
import cn.ctrls.remme.dto.GithubUser;
import cn.ctrls.remme.mapper.UserMapper;
import cn.ctrls.remme.mapper.UserMetaMapper;
import cn.ctrls.remme.model.RemmeUser;
import cn.ctrls.remme.model.UserMeta;
import cn.ctrls.remme.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Resource(name = "userMetaMapper")
    private UserMetaMapper userMetaMapper;

    @GetMapping("/callback")
    public String callBack(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_url(redirectUrl);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
//        System.out.println(accessToken);
        GithubUser githubUser = githubProvider.getUser(accessToken);
//        System.out.println(user.getLogin());
        if (githubUser != null){
            RemmeUser remmeUser;
            UserMeta userMeta = userMetaMapper.getUserMetaByGitHubId(githubUser.getId());
            String token = UUID.randomUUID().toString();
            if (userMeta!=null){
                userMapper.updateToken(userMeta.getId(), token);
                remmeUser = userMapper.getUserById(userMeta.getId());
                request.getSession().setAttribute("user", remmeUser);
                response.addCookie(new Cookie("token", token));
                return "index";
            }
            remmeUser = new RemmeUser();
            userMeta = new UserMeta();
            userMeta.setAvatarUrl(githubUser.getAvatarUrl());
            userMeta.setGithubId(githubUser.getId());
            userMetaMapper.insert(userMeta);
            userMeta = userMetaMapper.getUserMetaByGitHubId(githubUser.getId());
            remmeUser.setId(userMeta.getId());
            remmeUser.setName(githubUser.getLogin());
            remmeUser.setToken(token);
            remmeUser.setType(1);
            userMapper.insert(remmeUser);
            request.getSession().setAttribute("user",remmeUser);
            response.addCookie(new Cookie("token", token));
        } else{
            System.out.println("user none!");
        }
        return "index";
    }
}
