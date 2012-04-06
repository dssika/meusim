package Estado;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

public class Estado_elipse  extends Ellipse2D.Float {
	
	private Estado estado;
	private boolean selecionado;
	private double Xcentro;
	private double Ycentro;
	
	final float WIDTH = 50;
	final float HEIGHT = 50;
	
    public Estado_elipse(Estado estado) {
    	 this.estado = estado;
        setFrame(this.estado.getX(), this.estado.getY(), WIDTH, HEIGHT);
    }

    public boolean isHit(float x, float y) {
    	
        if (contains(x, y)) {
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
    
    public static void desenha(Graphics g, Estado_elipse e, Estado estado,boolean clicado)
    {
    	Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
        
        estado.setXCentral((int)e.getXCentro());
        estado.setYCentral((int)e.getYcentro());
        g2d.setColor(Color.WHITE);

        if ( clicado )
        	g2d.setPaint(new Color(204,255,204));
//        	  g2d.setPaint( new Color( 220, 245, 255 ) );
        else
            g2d.setPaint( Color.WHITE );
        
        
        g2d.fill(e);
        g2d.setColor(Color.BLACK);
        
        g2d.draw(e);

		FontMetrics fm = g2d.getFontMetrics();
        g2d.drawString( estado.getNome(), 
               (int)(e.getX()+18), 
               (int)(e.getY()+30));
        System.out.println(estado.getXCentral());
        System.out.println(estado.getYCentral());
        System.out.println(estado.getNome());
//        g2d.drawString("q0 teste", estado.getXCentral()-5 , estado.getYCentral()+5);
    }

	public double getXCentro() {
		
		return getCenterX();
	}

	public void setXCentro(double Xcentro, Estado e) {
		e.setXCentral((int)Xcentro);
		this.Xcentro = Xcentro;
	}

	public double getYcentro() {
		return getCenterY();
	}

	public void setYcentro(double ycentro, Estado e) {
		e.setYCentral((int)Ycentro);
		Ycentro = ycentro;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

    }
