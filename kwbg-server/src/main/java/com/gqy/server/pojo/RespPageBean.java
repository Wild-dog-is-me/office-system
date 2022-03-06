package com.gqy.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ice_water
 * @Date: 2022/03/06/2:14 PM
 * @Description: 要做耿沁园的男人
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPageBean {
    // 总条数
    private Long total;

    // 数据list
    private List<?> data;
}
