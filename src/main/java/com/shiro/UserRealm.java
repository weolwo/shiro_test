package com.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created By poplar on 2019/8/29
 * 自定义realm
 */
public class UserRealm extends AuthorizingRealm {

    @Override
    public String getName() {
        return "userRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        String passWord = getPassWord();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName,passWord,getName());
        if (info!=null){
            return  info;

        }
        return null;
    }

    //模拟从数据库中获取密码
    public String getPassWord() {

        return "11111";
    }
}
