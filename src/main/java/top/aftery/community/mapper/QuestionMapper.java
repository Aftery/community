package top.aftery.community.mapper;

import cn.hutool.db.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.aftery.community.model.Question;

import java.util.List;

/**
 * @Author Aftery
 * @Date 2019/11/20 20:37
 * @Version 1.0
 **/
@Mapper
@SuppressWarnings("all")
public interface QuestionMapper {

    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{page},#{size}")
    List<Question> list(@Param("page")Integer page, @Param("size") Integer size);

    @Select("select * from question where creator=#{userId}")
    List<Question> listUser(@Param("userId") Integer userId);

    @Select("select * from question where id=#{id}")
    Question getById(@Param(value = "id") Integer id);
}
