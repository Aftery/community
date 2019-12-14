package top.aftery.community.mapper;


import top.aftery.community.model.Question;

import java.util.List;

public interface QuestionExtDAO {


    int incView(Question record);

    List<Question> selectRelated(Question question);


}