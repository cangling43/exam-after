package cn.com.testol.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel
@Data
public class ApprovalDTO {
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
     * 申请人名称
     */
    @ApiModelProperty(value="申请人名称")
    private String studentName;

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
     * 班级名称
     */
    @ApiModelProperty(value="班级名称")
    private String classesName;

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
}
