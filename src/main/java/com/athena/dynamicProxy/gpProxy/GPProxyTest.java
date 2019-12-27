package com.athena.dynamicProxy.gpProxy;

import com.athena.dynamicProxy.jdkProxy.Girl;

/**
 * @Author: xiaoxiang.zhang
 * @Description:
 * @Date: Create in 7:51 PM 2019/11/26
 */
public class GPProxyTest {
    public static void main(String[] args) {
        GPMeipo gpMeipo = new GPMeipo(new Girl());
        gpMeipo.getInstance().findLove();
    }
}
