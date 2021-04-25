package cn.com.testol.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * approval
 * @author 
 */
    @ApiModel(value="cn.com.testol.entity.Approval")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
public class Approval implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
     * 申请人id
     */
    @ApiModelProperty(value="申请人id")
    private Integer studentId;

    /**
     * 教师id
     */
    @ApiModelProperty(value="教师id")
    private Integer teacherId;

    /**
     * 班级id
     */
    @ApiModelProperty(value="班级id")
    private Integer classesId;

    /**
     * 申请时间
     */
    @ApiModelProperty(value="申请时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date applyDate;

    /**
     * 状态0：审批中，1：同意，2：拒绝
     */
    @ApiModelProperty(value="状态0：审批中，1：同意，2：拒绝")
    private Integer status;

    private static final long serialVersionUID = 1L;
}