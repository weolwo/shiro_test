package com.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created By poplar on 2019/8/29
 * 自定义realm
 */
public class UserRealm extends AuthorizingRealm {

    @Override
    public String getName() {
        return "userRealm";
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String principal = (String) principalCollection.getPrimaryPrincipal();
        //感觉身份信息获取数据
        //模拟从数据库获取到相关的权限
        List<String> list = Arrays.asList("user:add", "user:delete", "user:update", "user:find");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(list);
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        String passWord = getPassWord();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, passWord, getName());
        if (info != null) {
            return info;

        }
        return null;
    }

    //模拟从数据库中获取密码
    public String getPassWord() {

        return "123456";
    }
}
