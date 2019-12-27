package com.athena.staticProxy;

/**
 * @Author: xiaoxiang.zhang
 * @Description:静态代理-父亲
 * @Date: Create in 4:11 PM 2019/11/26
 */
public class Father {

    //给人介绍对象必须得有照片和个人信息
    private Son son;

    public Father(Son son) {
        this.son = son;
    }

    public void findLove() {
        System.out.println("父亲物色对象");
        son.findLove();
        System.out.println("双方父母同意!");
    }
}
