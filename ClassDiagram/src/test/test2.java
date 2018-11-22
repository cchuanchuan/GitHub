package test;

import java.io.File;
import java.io.FileFilter;
import java.util.Set;

public class test2 {

/**

 * ���ļ�����ʽ����ȡ���µ�����Class

 * 

 * @param packageName

 * @param packagePath

 * @param recursive

 * @param classes

 */

public static void findAndAddClassesInPackageByFile(String packageName,

        String packagePath, final boolean recursive, Set<Class<?>> classes) {

    // ��ȡ�˰���Ŀ¼ ����һ��File

    File dir = new File(packagePath);

    // ��������ڻ��� Ҳ����Ŀ¼��ֱ�ӷ���

    if (!dir.exists() || !dir.isDirectory()) {

        // log.warn("�û�������� " + packageName + " ��û���κ��ļ�");

        return;

    }

    // ������� �ͻ�ȡ���µ������ļ� ����Ŀ¼

    File[] dirfiles = dir.listFiles(new FileFilter(){

 

        // �Զ�����˹��� �������ѭ��(������Ŀ¼) ��������.class��β���ļ�(����õ�java���ļ�)

        public boolean accept(File file) {

            return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));

        }

    });

    // ѭ�������ļ�

    for (File file : dirfiles) {

        // �����Ŀ¼ �����ɨ��

        if (file.isDirectory()) {

            findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive,

                    classes);

        } else {

            // �����java���ļ� ȥ�������.class ֻ��������

            String className = file.getName().substring(0, file.getName().length() - 6);

            try {

                // ��ӵ�������ȥ

                classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));

            } catch (ClassNotFoundException e) {

                e.printStackTrace();

            }

        }

    }

}

}
