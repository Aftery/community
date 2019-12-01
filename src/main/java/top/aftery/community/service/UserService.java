package top.aftery.community.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.nio.cs.ext.GB18030;
import sun.security.pkcs11.Secmod;
import top.aftery.community.mapper.UserDAO;
import top.aftery.community.model.User;
import top.aftery.community.model.UserExample;

import java.util.List;

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
    private UserDAO mapper;


    public void saveOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = mapper.selectByExample(userExample);
        if (CollUtil.isNotEmpty(users)) {
            User dbUser = users.get(0);
            if (dbUser == null) {
                user.setGmtCreate(System.currentTimeMillis());
                user.setGmtModified(user.getGmtCreate());
                mapper.insert(user);
            } else {
                User updateUser=new User();
                updateUser.setGmtModified(System.currentTimeMillis());
                updateUser.setAvatarUrl(user.getAvatarUrl());
                updateUser.setName(user.getName());
                updateUser.setToken(user.getToken());
                UserExample userExample1 = new UserExample();
                userExample1.createCriteria().andIdEqualTo(dbUser.getId());
                mapper.updateByExampleSelective(updateUser,userExample1);
            }
        }
    }
}
