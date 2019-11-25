package top.aftery.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.aftery.community.dto.QuestionUserDTO;
import top.aftery.community.model.Question;

import java.util.List;

/**
 * @Author Aftery
 * @Date 2019/11/25 21:30
 * @Version 1.0
 **/
@Mapper
@SuppressWarnings("all")
public interface QuestionUserDTOMapper {

    @Select("select * from questionuser")
    List<QuestionUserDTO> list();



    @Select("select * from  questionuser where creator=#{userId}")
    List<QuestionUserDTO> listUser(@Param("userId") Integer userId);

    @Select("select * from  questionuser where id=#{id}")
    QuestionUserDTO getById(Integer id);
}
