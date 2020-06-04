package cn.ctrls.remme.provider;

import cn.ctrls.remme.dto.AccessTokenDTO;
import cn.ctrls.remme.dto.GithubUser;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    /**
     * 获取授权Token
     * @param accessTokenDTO
     * @return
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        String url = "https://github.com/login/oauth/access_token";
        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO),mediaType);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
//            System.out.println(string);
            String token = string.split("&")[0].split("=")[1];
            return token;
        }catch (IOException e){
        }
        return null;
    }

    /**
     * 获取用户信息
     * @param accessToken
     * @return
     */
    public GithubUser getUser(String accessToken){
        String url = "https://api.github.com/user?access_token=" + accessToken;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println(string);
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class); //fastJson的快速转化为对象
            return githubUser;
        }catch (IOException e){
        }
        return null;
    }
}
