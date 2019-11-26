package top.aftery.community.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.aftery.community.mapper.UserMapper;
import top.aftery.community.model.User;

/**
 * @Author Aftery
 * @Date 2019/11/26 21:06
 * @Version 1.0
 **/
@Slf4j
@Service
@SuppressWarnings("all")
public class UserService {

    @Autowired
    private UserMapper mapper;


    public void saveOrUpdate(User user) {
        User dbUser=mapper.findByAccountId(user.getAccountId());
        if (dbUser == null) {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            mapper.saveUser(user);
        }else{
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            mapper.update(dbUser);
        }
    }
}
