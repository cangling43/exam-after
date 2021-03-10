package cn.com.testol.dao;

import cn.com.testol.entity.Subjects;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectsDao {
    int deleteByPrimaryKey(Integer subjectsId);

    int insert(Subjects record);

    int insertSelective(Subjects record);

    Subjects selectByPrimaryKey(Integer subjectsId);

    int updateByPrimaryKeySelective(Subjects record);

    int updateByPrimaryKey(Subjects record);
}