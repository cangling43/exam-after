package cn.com.testol.controller;

import cn.com.testol.service.ApprovalService;
import cn.com.testol.utils.JwtUtil;
import cn.com.testol.utils.Msg;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
public class ApprovalController {
    @Autowired
    private ApprovalService approvalService;

    @ApiOperation(value = "查找审批列表")
    @GetMapping("/approval/list")
    public Msg approvalList(HttpServletRequest request, @RequestParam int pageSize, @RequestParam int currentPage){
        String token =  request.getHeader("token");
        int u_id=Integer.parseInt(Objects.requireNonNull(JwtUtil.getUserId(token)));
        return approvalService.getApprovalList(u_id,pageSize,currentPage);
    }

    @ApiOperation(value = "审批")
    @GetMapping("/approval")
    public Msg approval(HttpServletRequest request,int approvalId,int status){
        String token =  request.getHeader("token");
        int u_id=Integer.parseInt(Objects.requireNonNull(JwtUtil.getUserId(token)));
        return approvalService.approval(u_id,approvalId,status);
    }
}
