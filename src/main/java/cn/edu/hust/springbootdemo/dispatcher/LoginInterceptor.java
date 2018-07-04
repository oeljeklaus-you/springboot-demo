package cn.edu.hust.springbootdemo.dispatcher;

import cn.edu.hust.springbootdemo.domain.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Configuration
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 这里做一个简单的拦截，用于判断是否登录
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器开始使用。。。。");
        User user=(User)request.getSession().getAttribute("user");
        //打开注释开启拦截器
        /**
        if(user==null)
        {
            response.sendRedirect("/user/login");
            return false;
        }*/
        return true;
    }

    /**
     * 可以修改调用结果
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println(" 调用中");
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("调用成功。。。");
    }
}
