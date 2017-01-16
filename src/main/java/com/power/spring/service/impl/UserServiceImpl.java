package com.power.spring.service.impl;

import com.power.spring.annotations.MyController;
import com.power.spring.annotations.Mapping;
import com.power.spring.base.BaseService;
import com.power.spring.bean.User;
import com.power.spring.bean.UserSession;
import com.power.spring.dao.UserDao;
import com.power.spring.service.UserService;
import com.power.spring.utils.UserSessionUtils;

import java.util.List;
import java.util.UUID;

/**
 * Created by shenli on 2017/1/1.
 */
@MyController
public class UserServiceImpl extends BaseService<User> implements UserService{

//    private UserDao userDao = (UserDao)getDao();/*new UserDaoByFile();*/

    @Override
    protected void doCheckDomain(User domain) {
        super.doCheckDomain(domain);
        System.out.println("UserServiceImpl.doCheckDomain");
    }

    @Mapping(path = "/user/create")
    public boolean createUser(User user) {
        return saveDomain(user);
//        return userDao.createUser(user);
    }

    @Mapping(path = "/user/delete")
    public boolean deleteUser(long userId) {
        return deleteDomain(userId);
//        return userDao.deleteUser(userId);
    }

    @Mapping(path = "/user/disable")
    public boolean disableUser(long userId)
    {
        return ((UserDao)getDao()).disableUser(userId);
    }

    @Mapping(path = "/user/queryUsers")
    public List<User> queryUsers(String userNamePrex, boolean onlyValidUser) {
        return ((UserDao)getDao()).queryUser(userNamePrex,onlyValidUser);
    }

    /**
     * 如果密码不对，返回的UserSession对象里sessionId为空，客户端可以依次判断，参照UserSession.isValid方法
     * @param userName
     * @param md5EncodedPassword
     * @return
     */
    @Mapping(path = "/user/login")
    public UserSession login(String userName, String md5EncodedPassword)
    {
        User u = ((UserDao)getDao()).loadUserByNamePasswd(userName, md5EncodedPassword);
        UserSession us = new UserSession();

        if (u != null) {
            if (u.isEnabled()) {
                us.setUserName(u.getUserName());
                us.setUserId(u.getUserId());
                us.setValidSeconds(UserSessionUtils.VALID_TIME);
                us.setCreateTime(System.currentTimeMillis());
                us.setSessionId(UUID.randomUUID().toString());
                UserSessionUtils.addUserSession(us);
            }
        }
        return us;
    }
}
