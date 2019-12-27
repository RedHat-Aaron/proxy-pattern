package com.athena.dbroute;


import lombok.Getter;
import lombok.Setter;

/**
 * @Author: xiaoxiang.zhang
 * @Description: 订单实体对象
 * @Date: Create in 4:37 PM 2019/11/26
 */
@Getter
@Setter
public class Order {

    private String id;

    //订单的创建时间按年进行分库

    private Object orderInfo;

    private Long createTime;
}
