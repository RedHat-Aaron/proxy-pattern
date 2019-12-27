package com.athena.dynamicProxy.gpProxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @Author: xiaoxiang.zhang
 * @Description:生成代理类
 * @Date: Create in 7:37 PM 2019/11/26
 */
public class GPProxy {

    private static final String ln = "\r\n";

    public static Object newProxyInstance(GPClassLoader loader,
                                          Class<?>[] interfaces,
                                          GPInvocationHandler h) {

        try {
            //1.动态生成源码，通过代码手动创建一个代理类
            String code = generaterCode(interfaces);
            //2.将源码存储到硬盘中
            String filePath = GPProxy.class.getResource("").getPath();
            File file = new File(filePath, "$Proxy0.java");
            FileWriter fw = new FileWriter(file);
            fw.write(code);
            fw.flush();
            fw.close();
            //3.将class文件输出至target目录
            JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = javaCompiler.getStandardFileManager(null, null, null);
            Iterable iterable = manager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task = javaCompiler.getTask(null, manager, null, null, null, iterable);
            task.call();
            manager.close();
            //调用InvicationHandler的findClass方法将对应的字节码文件找出
            //findClass是将字节码加载进入jvm
            Class proxyClass = loader.findClass("$Proxy0");
            Constructor constructor = proxyClass.getConstructor(GPInvocationHandler.class);
            return constructor.newInstance(h);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String generaterCode(Class<?>[] interfaces) {
        StringBuilder sb = new StringBuilder(300);
        sb.append("package com.athena.dynamicProxy.gpProxy;" + ln);
        sb.append("import com.athena.dynamicProxy.Person;" + ln);
        sb.append("import java.lang.reflect.*;" + ln);
        sb.append("public class $Proxy0 implements " + interfaces[0].getName() + "{" + ln);
        //申明InvocationHandler变量
        sb.append("private GPInvocationHandler h;" + ln);
        //为变量h赋值
        sb.append("public $Proxy0 (GPInvocationHandler h) {" + ln);
        sb.append("this.h = h;" + ln);
        sb.append("}" + ln);
        for (Method m : interfaces[0].getMethods()) {
            sb.append("public " + m.getReturnType() + " " + m.getName() + "() {" + ln);
            sb.append("try{" + ln);
            //这里是去接口中查找名为m.getName()的方法
            sb.append("Method m =" + interfaces[0].getName() + ".class.getMethod(\"" + m.getName() + "\",new Class[]{});" + ln);
            sb.append("this.h.invoke(this,m,null);");
            sb.append("}catch(Throwable e){ e.printStackTrace();}" + ln);
            sb.append("}" + ln);
        }
        sb.append("}" + ln);
        return sb.toString();
    }
}
