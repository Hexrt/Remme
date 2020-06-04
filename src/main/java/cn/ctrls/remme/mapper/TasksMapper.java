package cn.ctrls.remme.mapper;

import cn.ctrls.remme.model.RemmeTask;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository(value = "tasksMapper")
public interface TasksMapper {

    /**
     * 发布任务
     * @param remmeTask
     */
    @Insert("INSERT INTO tasks (type,description,memo,gmt_start,gmt_end,owner) VALUES (#{type},#{description},#{memo},#{gmtStart},#{gmtEnd},#{owner})")
    void publish(RemmeTask remmeTask);

    /**
     * 通过任务id获取任务
     * @param id
     * @return
     */
    @Select("SELECT * FROM tasks WHERE id=#{id}")
    RemmeTask getTaskById(Integer id);

    /**
     * 获取所有公共任务
     * @return
     */
    @Select("SELECT * FROM tasks WHERE owner=-1")
    ArrayList<RemmeTask> getComTask();

}
