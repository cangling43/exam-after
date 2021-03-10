package cn.com.testol.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * subjects
 * @author 
 */
@ApiModel(value="cn.com.testol.entity.Subjects")
@Data
public class Subjects implements Serializable {
    /**
     * 科目id
     */
    @ApiModelProperty(value="科目id")
    private Integer subjectsId;

    /**
     * 科目名称
     */
    @ApiModelProperty(value="科目名称")
    private String subjectsName;

    /**
     * 创建者id
     */
    @ApiModelProperty(value="创建者id")
    private Integer createId;

    /**
     * 创建者名称
     */
    @ApiModelProperty(value="创建者名称")
    private String createName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    /**
     * 修改时间
     */
    @ApiModelProperty(value="修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateDate;

    private static final long serialVersionUID = 1L;
}