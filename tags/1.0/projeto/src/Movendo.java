
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Movendo extends JPanel {

    private ZRectangle zrect;
    private ZEllipse zell;
    private ZLine zln;
    private ZLabel zlb;
    private int xSel,ySel;  
    String a = "a";
    
    public Movendo() {

        MovingAdapter ma = new MovingAdapter();

        addMouseMotionListener(ma);
        addMouseListener(ma);
        addMouseWheelListener(new ScaleHandler());

        zrect = new ZRectangle(50, 50, 50, 50);
        zell = new ZEllipse(150, 70, 80, 80);
        //adicionei 20 p ficar +- no centro
        zln = new ZLine(70,70,170,90);
       zlb = new ZLabel(60, 75);

        setDoubleBuffered(true);
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        Graphics2D gd = (Graphics2D) g;
        Font font = new Font("Serif", Font.BOLD, 40);
        g2d.setFont(font);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        //g2d.setColor(new Color(0, 0, 200));
        g2d.setColor(Color.YELLOW);
        g2d.fill(zrect);
        g2d.setColor(new Color(0, 200, 0));
        g2d.fill(zell);
        g2d.setColor(new Color(0, 0, 200));
        g2d.draw(zln);
        g2d.drawString(a, 0, 75);
      //  g2d.drawL(zlb);
        
//        JLabel jLabel1 = new javax.swing.JLabel();   
//        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36));  
//        jLabel1.setText("texto");
//        jLabel1.setLocation(60, 75);
//        add(jLabel1);
//        jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {  
//            public void mouseDragged(java.awt.event.MouseEvent evt) {  
//              jLabel1MouseDragged(evt);  
//            } } );
      
    }
    

    class ZEllipse extends Ellipse2D.Float {
        public ZEllipse(float x, float y, float width, float height) {
            setFrame(x, y, width, height);
        }

        public boolean isHit(float x, float y) {
            if (getBounds2D().contains(x, y)) {
                return true;
            } else {
                return false;
            }
        }

        public void addX(float x) {
            this.x += x;
        }

        public void addY(float y) {
            this.y += y;
        }

        public void addWidth(float w) {
            this.width += w;
        }

        public void addHeight(float h) {
            this.height += h;
        }
    }

    class ZRectangle extends Rectangle2D.Float {

        public ZRectangle(float x, float y, float width, float height) {
            setRect(x, y, width, height);
        }

        public boolean isHit(float x, float y) {
            if (getBounds2D().contains(x, y)) {
                return true;
            } else {
                return false;
            }
        }

        public void addX(float x) {
            this.x += x;
            
        }

        public void addY(float y) {
            this.y += y;
        }

        public void addWidth(float w) {
            this.width += w;
        }

        public void addHeight(float h) {
            this.height += h;
        }
    }

    class ZLabel extends JLabel{
    	public ZLabel(int x, int y){
    		setLocation(x, y);		
    	}
    	
    	public void addX(int x){
    		x = getLocation().x + x;    		
    	}
    	
    	public void addY(int y){
    		y = getLocation().y + y;
    	}
	
    }
    class ZLine extends Line2D.Float {
        public ZLine(float x1, float y1, float x2, float y2) {
          
        	setLine(x1, y1, x2, y2);
        }

        public void addX1(float x1) {
            this.x1 += x1;
        }

        public void addY1(float y1) {
            this.y1 += y1;
        }

        public void addX2(float x2) {
            this.x2 += x2;
        }

        public void addY2(float y2) {
            this.y2 += y2;
        }
      }
    
    class MovingAdapter extends MouseAdapter {

        private int x;
        private int y;

        public void mousePressed(MouseEvent e) {
            x = e.getX();
            System.out.println(x);
            y = e.getY();
            System.out.println(y);
        }

        public void mouseDragged(MouseEvent e) {

            int dx = e.getX() - x;
          
         
            int dy = e.getY() - y;


            if (zrect.isHit(x, y)) {
                zrect.addX(dx);
                zrect.addY(dy);
                zln.addX1(dx);
                zln.addY1(dy);
                repaint();
            }

            if (zell.isHit(x, y)) {
                zell.addX(dx);
                zell.addY(dy);
                zln.addX2(dx);
                zln.addY2(dy);
                repaint();
            }

            x += dx;
            y += dy;
        }
    }
//
//    private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {                                       
//        
//        xSel = jLabel1.getLocation().x;  
//        ySel = jLabel1.getLocation().y;  
//       jLabel1.setLocation((evt.getX()  + xSel )- (jLabel1.getWidth() / 2),(evt.getY() + ySel) - (jLabel1.getHeight() / 2));  
//        repaint();  
//        xSel = evt.getX();  
//        ySel = evt.getY();  
//      }   
    
    
    class ScaleHandler implements MouseWheelListener {
        public void mouseWheelMoved(MouseWheelEvent e) {

            int x = e.getX();
            int y = e.getY();

            if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {

                if (zrect.isHit(x, y)) {
                    float amount =  e.getWheelRotation() * 5f;
                    zrect.addWidth(amount);
                    zrect.addHeight(amount);
                    repaint();
                }

                if (zell.isHit(x, y)) {
                    float amount =  e.getWheelRotation() * 5f;
                    zell.addWidth(amount);
                    zell.addHeight(amount);
                    repaint();
                }
            }
        }
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Movendo");
        frame.add(new Movendo());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
