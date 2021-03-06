package cn.wolfcode.rbac.web.interceptor;

import cn.wolfcode.rbac.domain.Employee;
import com.alibaba.druid.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean loginFlag=true;

        if(handler instanceof HandlerMethod) {
            HttpSession session = request.getSession();
            Employee employee = (Employee) session.getAttribute("USER_IN_SESSION");
            if (employee == null) {
                response.sendRedirect("/login.html");
                loginFlag = false;
            }
        }

        return loginFlag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
