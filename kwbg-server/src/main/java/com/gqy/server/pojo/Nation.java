package com.gqy.server.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 耿沁园
 * @since 2022-02-22
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false,of = "name")
@Accessors(chain = true)
@TableName("t_nation")
@ApiModel(value = "菜单实体类",description = "")
public class Nation implements Serializable {
    @ApiModelProperty(value = "id")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @NonNull
    @Excel(name = "民族")
    @ApiModelProperty("民族名称")
    private String name;
}