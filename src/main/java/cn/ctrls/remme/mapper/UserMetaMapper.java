package cn.ctrls.remme.mapper;

import cn.ctrls.remme.model.UserMeta;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository(value = "userMetaMapper")
public interface UserMetaMapper {
    @Insert({"INSERT INTO user_meta (avatar_url, github_id, email_url) VALUES (#{avatarUrl},#{githubId}),#{emailUrl}"})
    void insert(UserMeta userMeta);

    @Select("SELECT * FROM user_meta WHERE github_id = #{githubId}")
    UserMeta getUserMetaByGitHubId(Integer githubId);

    @Select("SELECT * FROM user_meta WHERE email_url = #{emailUrl}")
    UserMeta getUserMetaByEmailUrl(String emailUrl);
}
