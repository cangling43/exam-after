package cn.com.testol.dao;

import cn.com.testol.DTO.ApprovalDTO;
import cn.com.testol.entity.Approval;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ApprovalDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Approval record);

    int insertSelective(Approval record);

    Approval selectByPrimaryKey(Integer id);

    Approval selectByRecord(Integer stuId,Integer classesId);

    int updateByPrimaryKeySelective(Approval record);

    int updateByPrimaryKey(Approval record);

    List<ApprovalDTO> selectByTchId(Integer tchId);
}