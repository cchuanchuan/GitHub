import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
public class Test extends JFrame{
	JButton button = new JButton("选择文件");
	public Test() {
		super();
		this.setName("测试界面");
		this.setSize(800,500);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(1,1));

		File file = new File("D:\\上机实验\\数据结构\\ClassDiagram");
		this.add(new JScrollPane(new GramJPanel(file.getAbsolutePath())));

        this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String args[]) {
		Test t = new Test();
	}

}
