package com.athena.dynamicProxy.gpProxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author: xiaoxiang.zhang
 * @Description:
 * @Date: Create in 7:38 PM 2019/11/26
 */
public class GPClassLoader extends ClassLoader {

    private File classPathFile;

    public GPClassLoader() {
        String classPath = GPClassLoader.class.getResource("").getPath();
        this.classPathFile = new File(classPath);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String className = GPClassLoader.class.getPackage().getName() + "." + name;
        if (null != classPathFile) {
            //去拿到生成好的class文件
            File classFile = new File(classPathFile, name.replace("\\.", "/") + ".class");
            //将这个class文件手动加载进入jvm中
            if (classFile.exists()) {
                FileInputStream in = null;
                ByteArrayOutputStream out = null;
                try {
                    in = new FileInputStream(classFile);
                    out = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len ;
                    while ((len = in.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                    //这里是jdk底层的方法
                    return defineClass(className,out.toByteArray(),0,out.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(null != out ){
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            if(null != in){
                                try {
                                    in.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
