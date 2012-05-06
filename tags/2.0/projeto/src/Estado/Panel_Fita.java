package Estado;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

import javax.swing.JPanel;


public class Panel_Fita extends JPanel 
{
	
	int altura_fita = 60;
	int inicio_fita = 20;
	int largura_celula = 40;
	int altura_celula = 40;
	int tamanho_fita = 100;
	
	private int selectedSymbolIndex = -1;
	protected static final BasicStroke strokeNormal = new BasicStroke(1);
    protected static final BasicStroke strokeBold = new BasicStroke(2);
	
	public Panel_Fita()
	{
		setBackground(Color.WHITE);
		setMaximumSize(new Dimension(99999, altura_fita));
		setMinimumSize(new Dimension(inicio_fita+tamanho_fita*largura_celula,altura_fita));
		
		
		setFocusable(true);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        int y = ySymbol();
        int dx = largura_celula;
        int dy = altura_celula;

        g2d.setColor(Color.black);
        g2d.fillRect(inicio_fita-10, y, dx, altura_celula);
        g2d.setColor(Color.WHITE);
        drawCenteredString(BLANK, 5+(int)(0.5*dx), (int)(y+0.5*dy), g2d);
//        drawCenteredString(s, (int)(x+0.5*dx), (int)(y+0.5*dy), g);
        g2d.setColor(Color.blue);
        g2d.setStroke(strokeBold);
        g2d.drawRect(inicio_fita-10, y, dx, altura_celula);
        g2d.setStroke(strokeNormal);
        

        for(int i=0; i<tamanho_fita; i++) {
            int x = inicio_fita+29+i*dx;

            g2d.drawRect(x, y, dx, dy);
                g2d.setColor(Color.blue);
                g2d.setStroke(strokeNormal);
                g2d.drawRect(x, y, dx, dy);
                g2d.setStroke(strokeNormal);
                g2d.setColor(Color.black);
            
         
           
            drawCenteredString(BLANK, (int)(x+0.5*dx), (int)(y+0.5*dy), g);

           
        }
		
		
	}
	 public int ySymbol() {
	        return (int) (0.5*(altura_fita-altura_celula));
	    }
	 public static void drawCenteredString(String s, int x, int y, Graphics g) {
	        FontMetrics fm = g.getFontMetrics();
	        int xx = (int)(x-fm.stringWidth(s)*0.5);
	        int yy = (int)(y+fm.getHeight()*0.5);
	        g.drawString(s, xx, yy);
	    }
	 final String BLANK = "\u00DF";
}

