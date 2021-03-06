package test;

import java.io.File;
import java.io.FileFilter;
import java.util.Set;

public class test2 {

/**

 * 以文件的形式来获取包下的所有Class

 * 

 * @param packageName

 * @param packagePath

 * @param recursive

 * @param classes

 */

public static void findAndAddClassesInPackageByFile(String packageName,

        String packagePath, final boolean recursive, Set<Class<?>> classes) {

    // 获取此包的目录 建立一个File

    File dir = new File(packagePath);

    // 如果不存在或者 也不是目录就直接返回

    if (!dir.exists() || !dir.isDirectory()) {

        // log.warn("用户定义包名 " + packageName + " 下没有任何文件");

        return;

    }

    // 如果存在 就获取包下的所有文件 包括目录

    File[] dirfiles = dir.listFiles(new FileFilter(){

 

        // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)

        public boolean accept(File file) {

            return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));

        }

    });

    // 循环所有文件

    for (File file : dirfiles) {

        // 如果是目录 则继续扫描

        if (file.isDirectory()) {

            findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive,

                    classes);

        } else {

            // 如果是java类文件 去掉后面的.class 只留下类名

            String className = file.getName().substring(0, file.getName().length() - 6);

            try {

                // 添加到集合中去

                classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));

            } catch (ClassNotFoundException e) {

                e.printStackTrace();

            }

        }

    }

}

}
