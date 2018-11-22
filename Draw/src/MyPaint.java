import java.awt.Color;  
import java.awt.Container;  
import java.awt.Dimension;  
import java.awt.Graphics;  
import java.awt.Point;  
import java.awt.event.MouseEvent;  
import java.awt.event.MouseListener;  
import java.awt.event.MouseMotionListener;  
import java.util.Vector;  
import javax.swing.JFrame;  
import javax.swing.JPanel;  
  
public class MyPaint 
{  
    public static void main(String[] args) 
    {  
        new PaintFrame();  
    }  
}  
  
class MyCanvas extends JPanel implements MouseListener, MouseMotionListener 
{  //curve对象用于保存每条曲线，而curves对象保存所有绘制的独立曲线
    private Vector<Point> curve;
    private Vector<Vector<Point>> curves;
      
    private Point ptFrom = new Point();  
    private Point ptTo = new Point();  
      
    MyCanvas() 
    {  
        curve = new Vector<Point>();  
        curves = new Vector<Vector<Point>>();  
      //this.setPreferredSize(new Dimension(300, 200));//设置一个宽度300,高度200的Canvas
        this.addMouseListener( this );  
        this.addMouseMotionListener( this );  
    }  
      
    public void paint( Graphics g ) //paintComponent
    {  
        g.setColor(Color.RED);
        for(Vector<Point> points:curves)
        {
            Point pt0 = points.get(0);
            for( int i = 1; i < points.size(); ++i )
            {
                Point point= points.get( i );
                g.drawLine(pt0.x, pt0.y, point.x, point.y);
                pt0 = point;
            }
        }
    }
  
    //鼠标按下，实现MouseListener接口
    public void mousePressed(MouseEvent e)
    {
        ptFrom.x = e.getX();
        ptFrom.y = e.getY();
        curve.add(new Point(ptFrom));
    }  
  
    //鼠标释放
    public void mouseReleased(MouseEvent e) 
    {  
        ptTo.x = e.getX();  
        ptTo.y = e.getY();  
        curve.add(new Point(ptTo));  
        curves.add( new Vector<Point>( curve ) );  
        curve.clear(); 
    }
  
    //拖动鼠标实现MouseMotionListener接口
    public void mouseDragged(MouseEvent e) 
    {  
    	ptTo.x = e.getX();  
	    ptTo.y = e.getY();  
	    curve.add(new Point(ptTo));  
	    Graphics g = getGraphics();  
	    g.setColor(Color.RED);  
	    g.drawLine( ptFrom.x, ptFrom.y, ptTo.x, ptTo.y );  
	    ptFrom.x = ptTo.x;  
	    ptFrom.y = ptTo.y;    
    }  
  
    @Override  
    public void mouseEntered(MouseEvent e) {  

    }  
  
    @Override  
    public void mouseExited(MouseEvent e) {  

    }  
  
    @Override  
    public void mouseClicked(MouseEvent e) {  
 
    }  
  
    @Override  
    public void mouseMoved(MouseEvent e) {  
   
    }  
  
}  
  
class PaintFrame extends JFrame 
{  
  
    private MyCanvas canvas = new MyCanvas();  
  
    PaintFrame() 
    {  
        super("画图");  
        this.add(canvas);  
        setSize(800, 600);
        this.setLocationRelativeTo(null);
        setVisible(true);  
    }
}