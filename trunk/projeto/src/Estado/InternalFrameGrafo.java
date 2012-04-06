package Estado;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JInternalFrame;
import javax.swing.border.LineBorder;

import Estado.TelaEstado.MovingAdapter;


public class InternalFrameGrafo extends JInternalFrame
{
	
	Estado_elipse e_sel;
	
	ArrayList<Estado_elipse> estados_e = new ArrayList<Estado_elipse>();
	ArrayList<Estado> array_estado = new ArrayList<Estado>();
	
	Estado estado_selecionado;
	Estado_elipse estado_eSelecionado;
	
	
	int x1,y1,x2,y2;
	
	int x = 150; 
	int y = 70;
	int indice = 0;
	
	boolean clicado = false;
	
	public InternalFrameGrafo()
	{
//		JInternalFrame internalFrame = new JInternalFrame("new");
		setIconifiable(true);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setFrameIcon(null);
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(10, 11, 477, 244);
		
	}
	
	public void adicionaEstado( boolean isInicial, boolean isFinal)
	{
		
		int x1 =0;
		int y1=0;
		
		Estado estado = new Estado("q"+indice, true, false, 10+x1, 70+y1);
		
		Estado_elipse ee = new Estado_elipse(estado);
		e_sel = ee;
		estado_selecionado = estado;
		
		estados_e.add(ee);
		array_estado.add(estado);
		
		MovingAdapter ma = new MovingAdapter();

		addMouseMotionListener(ma);
		addMouseListener(ma);
		
		repaint();
		indice++;
		x1++;
		y1++;
	}

    	Estado j = new Estado("j", false, false, 10, 70);
    	Estado_elipse je = new Estado_elipse(j);
    
    
	public void exibeEstados()
	{
		Iterator<Estado_elipse> it = estados_e.iterator();
		int i = 0;
		System.out.print("Estados:");
		while (it.hasNext()) 
		{
			Estado_elipse estado = it.next();
			Estado e = estado.getEstado();
			System.out.println(e.getNome());
		}
		
	}
    
	
	public void paint(Graphics g)
	{
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		if (e_sel!=null)
		{			
			System.out.println(e_sel.getEstado().getNome());
			Estado_elipse e_e = new Estado_elipse(estado_selecionado);
			e_e = e_sel;
			if (e_e!=null)
			{			        
		        estado_selecionado.setXCentral((int)e_e.getXCentro());
		        estado_selecionado.setYCentral((int)e_e.getYcentro());
		        g2d.setColor(Color.WHITE);

		        if ( clicado )
		        	g2d.setPaint(new Color(204,255,204));
//		        	  g2d.setPaint( new Color( 220, 245, 255 ) );
		        else
		            g2d.setPaint( Color.WHITE );
		        
		        
		        g2d.fill(e_e);
		        g2d.setColor(Color.BLACK);
		        
		        g2d.draw(e_e);
		        g2d.draw(je);
//
		        array_estado.add(j);
		        estados_e.add(je);
		        
				FontMetrics fm = g2d.getFontMetrics();
		        g2d.drawString( estado_selecionado.getNome(), 
		               (int)(e_e.getX()+18), 
		               (int)(e_e.getY()+30));
		        System.out.println(estado_selecionado.getXCentral());
		        System.out.println(estado_selecionado.getYCentral());
		        System.out.println(estado_selecionado.getNome());

				//Estado_elipse.desenha(g2d, e_e,estado, clicado);
			}	
		}


	}
	
	class MovingAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			Iterator<Estado_elipse> itr = estados_e.iterator();
			while (itr.hasNext()) {
				Estado_elipse estado = itr.next();
				if (estado.isHit(e.getX(), e.getY())) {
					estado_eSelecionado= estado;
					clicado = true;

					x = e.getX();
					y = e.getY();
				}
			}
		}

		
		public void mouseClicked(MouseEvent e) {
			Iterator<Estado_elipse> itr = estados_e.iterator();
			while (itr.hasNext()) {
				Estado_elipse estado = itr.next();
				if (estado.isHit(e.getX(), e.getY())) {
					estado_eSelecionado = estado;
					clicado = true;
					System.out.println("clicked");
				}
			}

		}

		
		 public void mouseReleased(MouseEvent e)
		 {			 
			 clicado = false;			 repaint();
		 }
		// MotionListener

		public void mouseDragged(MouseEvent e) {
			int dx = e.getX() - x;
			int dy = e.getY() - y;
			Iterator<Estado_elipse> itr = estados_e.iterator();
			while (itr.hasNext()) {
				Estado_elipse estado = itr.next();

				if (estado.isHit(e.getX(), e.getY())) {
					estado.addX(dx);
					estado.addY(dy);
					repaint();
				}
			}
			x += dx;
			y += dy;
		}
	}	
}
