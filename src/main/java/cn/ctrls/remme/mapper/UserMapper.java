package cn.ctrls.remme.mapper;

import cn.ctrls.remme.model.RemmeUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository(value = "userMapper")
public interface UserMapper {
    @Insert("INSERT INTO users (id,name,type,token,password) VALUES (#{id},#{name},#{type},#{token},#{password})")
    void insert(RemmeUser remmeUser);

    @Update("UPDATE users SET token=#{token} WHERE id=#{id}")
    void updateToken(Integer id, String token);

    @Select("SELECT * FROM users WHERE token=#{token}")
    RemmeUser getUserByToken(String token);

    @Select("SELECT * FROM users WHERE id=#{id}")
    RemmeUser getUserById(Integer id);
}
