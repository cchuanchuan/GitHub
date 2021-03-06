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
 * 获得包下面的所有的class
 * 
 * @param pack
 *            package完整名称
 * @return List包含所有class的实例
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
		/*File file = new File("/D:/上机实验/数据结构/ClassDiagram/bin");
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
	
	  // 是否循环搜索子包
	  boolean recursive = true;
	
	  // 包名字
	  String packageName = pack;
	  // 包名对应的路径名称
	  String packageDirName = packageName.replace('.', '/');
	  Enumeration<URL> dirs;
	  try {
	    dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
	    while (dirs.hasMoreElements()) {
	      URL url = dirs.nextElement();
	      String protocol = url.getProtocol();
	      if ("file".equals(protocol)) {
	        System.out.println("file类型的扫描");
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
	 * 在package对应的路径下找到所有的class
	 * 
	 * @param packageName
	 *            package名称
	 * @param filePath
	 *            package对应的路径
	 * @param recursive
	 *            是否查找子package
	 * @param clazzs
	 *            找到class以后存放的集合
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
	  // 在给定的目录下找到所有的文件，并且进行条件过滤
	  File[] dirFiles = dir.listFiles(new FileFilter() {
	    public boolean accept(File file) {
	      boolean acceptDir = recursive && file.isDirectory();// 接受dir目录
	      boolean acceptClass = file.getName().endsWith("class");// 接受class文件
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