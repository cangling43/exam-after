package com.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * classes
 * @author 
 */
@Data
public class Classes implements Serializable {
    /**
     * 班级id
     */
    private Integer classesId;

    /**
     * 班级名称
     */
    private String classesName;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 班级简介
     */
    private String introduction;

    /**
     * 班级人数
     */
    private Integer peopleNum;

    /**
     * 创建者名称
     */
    private String creatorName;

    /**
     * 创建者id
     */
    private Integer creatorId;

    /**
     * 班级加入方式  0:不允许加入 1:允许任何人加入  2:需要管理员同意
     */
    private String joinway;

    private static final long serialVersionUID = 1L;
}