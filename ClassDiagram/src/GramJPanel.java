import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.plaf.basic.*;

public class GramJPanel extends JPanel implements FocusListener, MouseListener{
	private int x=-185;
	private int y=0;
	public GramJPanel(String filepath) {
		super();
		this.setLayout(null);
		List<Class> clazzs = new ArrayList<Class>();
		File file = new File(filepath);
		String filepath2 = file.getAbsolutePath().replaceAll("src", "bin");
		String packageName = file.getName();
		if(packageName.equals("src")) {
			packageName = "";
		}
		
		GramJPanel.findClassInPackageByFile(filepath2,true,clazzs);
		for(Class c:clazzs) {
			this.addClass(c);
		}
		
	}
	
	public void addClass(Class clazz) {
		Clazzs c = getClazzs(clazz);
		JInternalFrame internaljframe = new JInternalFrame(c.getName()+"<Java Class>");
		internaljframe.addFocusListener(this);
		internaljframe.setLayout(new GridLayout(1,1));
		JTextArea text = new JTextArea();
		text.setName(c.getName());
		text.addMouseListener(this);
		for(String s:c.getFields()) {
			text.append(s+"\n");
		}
		text.append("----------------------------------------------------------------------"+"\n");
		for(String s:c.getMethods()) {
			text.append(s+"\n");
		}
		text.add(new JButton("1212:"));
		internaljframe.add(text);
		internaljframe.setSize(180, (c.getFields().size() + c.getMethods().size()+1)*20+20);
		if((x+300) >= 800) {
			x=-185;
			y=y+200;
		}
		internaljframe.setLocation((x=x+185), y);
		internaljframe.setBorder(null);
		internaljframe.setBackground(Color.white);
		internaljframe.setVisible(true);
		this.add(internaljframe);
	}
	
	public Clazzs getClazzs(Class clazz) {
		Clazzs c = new Clazzs();
		if(clazz != null) {
			c.setName(clazz.getSimpleName());
			Field[] fields = clazz.getDeclaredFields();
			for(Field f : fields) {
				String fie = f.getModifiers()%2==1?"+":"-";
				fie = fie+" "+f.getName()+" : "+f.getType().getSimpleName();
				c.getFields().add(fie);
			}
			Method[] methods = clazz.getDeclaredMethods();
			for(Method m : methods) {
				String meth = m.getModifiers()%2==1?"+":"-";
				meth = meth+" "+m.getName()+"() : "+m.getReturnType().getSimpleName();
				c.getMethods().add(meth);
			}
			String url = clazz.getResource("").toString();
			try {
				byte[] b = url.getBytes("GBK");
				url = new String(b,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println();
			/*File f = new File(clazz.getResource("").toExternalForm());
			System.out.println(f.getAbsolutePath());
			System.out.println(f.exists());*/
			/*System.out.println(clazz.getResource("/"));
			System.out.println(this.getClass().getResource(""));*/
		}
		return c;
	}
	
	
	public static void findClassInPackageByFile(String filePath, final boolean recursive, List<Class> clazzs) {
	  
		File dir = new File(filePath);
		//System.out.println(dir.getAbsolutePath());
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		// 在给定的目录下找到所有的文件，并且进行条件过滤
		File[] dirFiles = dir.listFiles(new FileFilter() {
	
			public boolean accept(File file) {
				boolean acceptDir = recursive && file.isDirectory();//接受dir目录
				boolean acceptClass = file.getName().endsWith("class");//接受class文件
				return acceptDir || acceptClass;
			}
	  });
		
		for (File file : dirFiles) {
			System.out.println("绝对路径："+file.getAbsolutePath());
			if (file.isDirectory()) {
				findClassInPackageByFile(file.getAbsolutePath(), recursive, clazzs);
			} else {
				String className = file.getName().substring(0, file.getName().length() - 6);
				System.out.println(className);
				try {
					String filepath = file.getAbsolutePath();
					int index = filepath.indexOf("bin")+4;
					int end = filepath.length()-className.length()-7;
					String packageName = "";
					if(index <= end) {
						packageName = filepath.substring(index ,end );
						packageName = packageName.replace('\\', '.');
						System.out.println(packageName);
					}
					String str = packageName.equals("")?className:(packageName + "." + className);
					System.out.println("包地址"+str);
					//clazzs.add(Thread.currentThread().getContextClassLoader().loadClass(str));
					clazzs.add(Class.forName(str));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent().getParent() instanceof JInternalFrame) {
			JInternalFrame jif = (JInternalFrame)e.getComponent().getParent();
			System.out.println(jif.getName());
		}else {
			JTextArea text = (JTextArea)e.getSource();
			System.out.println(text.getName());
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
}
