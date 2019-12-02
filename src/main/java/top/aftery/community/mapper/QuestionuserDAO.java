package top.aftery.community.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.aftery.community.model.Questionuser;
import top.aftery.community.model.QuestionuserExample;

@Repository
public interface QuestionuserDAO {
    long countByExample(QuestionuserExample example);

    int deleteByExample(QuestionuserExample example);

    int insert(Questionuser record);

    int insertSelective(Questionuser record);

    List<Questionuser> selectByExampleWithBLOBs(QuestionuserExample example);

    List<Questionuser> selectByExample(QuestionuserExample example);

    int updateByExampleSelective(@Param("record") Questionuser record, @Param("example") QuestionuserExample example);

    int updateByExampleWithBLOBs(@Param("record") Questionuser record, @Param("example") QuestionuserExample example);

    int updateByExample(@Param("record") Questionuser record, @Param("example") QuestionuserExample example);
}