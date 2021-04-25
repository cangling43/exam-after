package cn.com.testol.service;

import cn.com.testol.utils.Msg;

public interface ApprovalService {

    public Msg getApprovalList(Integer tchId,int pageSize,int currentPage);

    public Msg approval(Integer tchId,Integer approvalId,Integer status);
}
