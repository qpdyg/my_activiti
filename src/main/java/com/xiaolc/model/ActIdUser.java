package com.xiaolc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import javax.persistence.Id;
/**
 * @Author: lc
 * @Date: 2019/6/21 11:10
 */
@Data
@ApiModel("用户实体类")
@Entity
@Table(name = "ACT_ID_USER")
public class ActIdUser implements Serializable {
    @ApiModelProperty(notes = "主键id")
    @Id
    @Column(name = "ID_")
    private String id;

    @ApiModelProperty(notes = "版本号")
    @Column(name = "REV_")
    private Integer rey;

    @ApiModelProperty(notes = "姓")
    @Column(name = "FIRST_")
    private String first;

    @ApiModelProperty(notes = "名")
    @Column(name = "LAST_")
    private String last;

    @ApiModelProperty(notes = "邮箱")
    @Column(name = "EMAIL_")
    private String email;

    @ApiModelProperty(notes = "密码")
    @Column(name = "PWD_")
    private String pwd;

    @ApiModelProperty(notes = "图片ID")
    @Column(name = "PICTURE_ID_")
    private String pictureId;
}
