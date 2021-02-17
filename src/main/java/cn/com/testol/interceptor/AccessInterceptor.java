package cn.com.testol.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.testol.utils.JwtUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

public class AccessInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object object) throws Exception {

        //response.setCharacterEncoding("UTF-8");
        //response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,Token");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        response.setHeader("Access-Control-Expose-Headers", "Token");

//        验证token
        String token = request.getParameter("token");
        if(!StringUtils.isBlank(token)){
            boolean result = JwtUtil.verify(token);
            if (result) {
                return true;
            }else{
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                JSONObject res = new JSONObject();
                res.put("code","300");
                res.put("msg","token验证失败,请先登录");
                PrintWriter out = null ;
                out = response.getWriter();
                out.write(res.toString());
                out.flush();
                out.close();
                return false;
            }
        }else {
            return true;
        }



    }
}