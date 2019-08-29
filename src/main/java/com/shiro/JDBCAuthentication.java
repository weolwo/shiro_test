package com.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * Created By poplar on 2019/8/29
 * 使用jdbc作为数据源
 */
public class JDBCAuthentication {
    public static void main(String[] args) {
        // 构建 SecurityManager 工厂，IniSecurityManagerFactory 可以 从 ini 文件中初始化 SecurityManager 环境
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        //将 securityManager 设置到运行环境中
        SecurityUtils.setSecurityManager(securityManager);

        //获取subject
        Subject subject = SecurityUtils.getSubject();
        //创建用户名,密码身份验证 Token
        //外面"用户"传进来的,而shiro.ini中的用户名密码相当于数据库获取的
        UsernamePasswordToken token = new UsernamePasswordToken("Tom", "123456");
        try {
            //执行登录操作
            subject.login(token);
            //通过subject来判断用户是否通过验证
            if (subject.isAuthenticated()) {
                System.out.println("登录成功");
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败");
        }
    }
}

