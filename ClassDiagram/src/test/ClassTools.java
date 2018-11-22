package test;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * ��ð���������е�class
 * 
 * @param pack
 *            package��������
 * @return List��������class��ʵ��
 */

public class ClassTools {
	public static void main(String args[]) {
		List<Class> clazzs = ClassTools.getClasssFromPackage("elevator");
		for(Class clazz:clazzs) {
			System.out.println(clazz.getName());}
			/*Method[] methods = clazz.getDeclaredMethods();
			for(Method m : methods) {
				System.out.println(m.getModifiers());
				System.out.println(m.getReturnType().getSimpleName());
				System.out.println(m.getName());
			}
			
			Field[] fields = clazz.getDeclaredFields();
			for(Field f : fields) {
				System.out.println(f.getName());
				System.out.println(f.getType().getSimpleName());
				System.out.println(f.getModifiers());
			}
		}*/
		/*File file = new File("/D:/�ϻ�ʵ��/���ݽṹ/ClassDiagram/bin");
		String packageName = "elevator";
		if (file.isDirectory()) {
		      findClassInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), true, clazzs);
		    } else {
		      String className = file.getName().substring(0, file.getName().length() - 6);
		      try {
		        clazzs.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className));
		      } catch (Exception e) {
		        e.printStackTrace();
		      }
		 }*/
	}
	
	
	public static List<Class> getClasssFromPackage(String pack) {
	  List<Class> clazzs = new ArrayList<Class>();
	
	  // �Ƿ�ѭ�������Ӱ�
	  boolean recursive = true;
	
	  // ������
	  String packageName = pack;
	  // ������Ӧ��·������
	  String packageDirName = packageName.replace('.', '/');
	  Enumeration<URL> dirs;
	  try {
	    dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
	    while (dirs.hasMoreElements()) {
	      URL url = dirs.nextElement();
	      String protocol = url.getProtocol();
	      if ("file".equals(protocol)) {
	        System.out.println("file���͵�ɨ��");
	        String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
	        System.out.println(packageName);
	        System.out.println(filePath);
	        System.out.println(recursive);
	        findClassInPackageByFile(packageName, filePath, recursive, clazzs);
	      }
	    }
	
	  } catch (Exception e) {
	    e.printStackTrace();
	  }
	
	  return clazzs;
	}
	
	/**
	 * ��package��Ӧ��·�����ҵ����е�class
	 * 
	 * @param packageName
	 *            package����
	 * @param filePath
	 *            package��Ӧ��·��
	 * @param recursive
	 *            �Ƿ������package
	 * @param clazzs
	 *            �ҵ�class�Ժ��ŵļ���
	 */
	public static void findClassInPackageByFile(String packageName, String filePath, final boolean recursive, List<Class> clazzs) {
	  System.out.println(packageName);
	  System.out.println(filePath);
	  System.out.println(recursive);
	  System.out.println();
	  File dir = new File(filePath);
	  if (!dir.exists() || !dir.isDirectory()) {
	    return;
	  }
	  // �ڸ�����Ŀ¼���ҵ����е��ļ������ҽ�����������
	  File[] dirFiles = dir.listFiles(new FileFilter() {
	    public boolean accept(File file) {
	      boolean acceptDir = recursive && file.isDirectory();// ����dirĿ¼
	      boolean acceptClass = file.getName().endsWith("class");// ����class�ļ�
	      return acceptDir || acceptClass;
	    }
	  });
	
	  
	  for (File file : dirFiles) {
		  System.out.println(file.getAbsolutePath());
	    if (file.isDirectory()) {
	      findClassInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, clazzs);
	    } else {
	      String className = file.getName().substring(0, file.getName().length() - 6);
	      try {
	        clazzs.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className));
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	    }
	  }
	  
	}
	
	

}