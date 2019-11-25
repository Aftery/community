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

}
