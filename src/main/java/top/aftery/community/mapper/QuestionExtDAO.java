package top.aftery.community.mapper;


import org.apache.ibatis.annotations.Param;
import top.aftery.community.model.Question;
import top.aftery.community.model.Questionuser;

import java.util.List;

public interface QuestionExtDAO {


    /**
     * 增加记录数
     * @param record
     * @return
     */
    int incView(Question record);

    /**
     * 查询相关问题
     * @param question
     * @return
     */
    List<Question> selectRelated(Question question);


    /**
     * 搜索查询
     * @param search
     * @return
     */
    List<Questionuser> selectSearch(@Param(value="search")String search);
}