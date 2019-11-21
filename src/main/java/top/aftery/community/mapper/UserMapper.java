package top.aftery.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.aftery.community.model.User;

/**
 * @Author Aftery
 * @Date 2019/11/18 20:51
 * @Version 1.0
 **/
@Mapper
public interface UserMapper {

    @Insert("Insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void saveUser(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(String token);


    @Select("select * from user where id=#{id}")
    User findById(Integer id);
}
