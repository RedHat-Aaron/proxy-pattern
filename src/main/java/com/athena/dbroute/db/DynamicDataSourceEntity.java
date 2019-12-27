package com.athena.dbroute.db;

/**
 * @Author: xiaoxiang.zhang
 * @Description:动态数据源实体
 * @Date: Create in 4:47 PM 2019/11/26
 */
public class DynamicDataSourceEntity {
    private static final String DEFAULT_SOURCE = null;

    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    private DynamicDataSourceEntity() {
    }

    //提供一个重置数据库的方法
    public static void resetStore() {
        local.set(DEFAULT_SOURCE);
    }


    public String get() {
        return local.get();
    }

    //DB_2018
    //DB_2019

    public static void set(String name) {
        local.set(name);
    }

    public static void set(int year) {
        local.set("DB_" + year);
    }
}
