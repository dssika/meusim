package Estado;

import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TelaEstado extends JPanel {

	int x1, y1, x2, y2;

	int x = 150;
	int y = 70;
	
	private boolean clicado;
	
	Estado estado;
	private Estado_elipse estado1;
	//private Estado_elipse estado2 = new Estado_elipse(15, 70);
	Estado_elipse estadoSelecionado;

	Cursor curCursor;

	private ArrayList<Estado_elipse> estados;

	public TelaEstado() {
		
		estado = new Estado("q"+0, true, false, 150, 70);
	
		estado1 = new Estado_elipse(estado);
		

		MovingAdapter ma = new MovingAdapter();

		addMouseMotionListener(ma);
		addMouseListener(ma);

		estados = new ArrayList<Estado_elipse>();
		estados.add(estado1);
		//estados.add(estado2);

	}

	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		
		Estado_elipse.desenha(g2d, estado1,estado, clicado);
		

		if ( curCursor != null ) 
	         setCursor ( curCursor ) ; 

	}

	class MovingAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			Iterator<Estado_elipse> itr = estados.iterator();
			while (itr.hasNext()) {
				Estado_elipse estado = itr.next();
				if (estado.isHit(e.getX(), e.getY())) {
					estadoSelecionado = estado;
					clicado = true;

					x = e.getX();
					y = e.getY();
				}
			}
		}

		
		public void mouseClicked(MouseEvent e) {
			Iterator<Estado_elipse> itr = estados.iterator();
			while (itr.hasNext()) {
				Estado_elipse estado = itr.next();
				if (estado.isHit(e.getX(), e.getY())) {
					estadoSelecionado = estado;
					clicado = true;
					System.out.println("clicked");

				}
			}

		}

		
		 public void mouseReleased(MouseEvent e)
		 {			 
			 clicado = false;
			 repaint();
		 }
		// MotionListener

		public void mouseDragged(MouseEvent e) {
			int dx = e.getX() - x;
			int dy = e.getY() - y;
			Iterator<Estado_elipse> itr = estados.iterator();
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

		public void mouseMoved(MouseEvent e) {
			Iterator<Estado_elipse> itr = estados.iterator();
			while (itr.hasNext()) {
				Estado_elipse estado = itr.next();

				if (estado != null) {

					if ( estado.contains ( e.getX () , e.getY ())) { 
						curCursor = Cursor
								.getPredefinedCursor(Cursor.HAND_CURSOR);
					} else {
						curCursor = Cursor.getDefaultCursor();
					}
				}
			}
			repaint();
		}
	}

	public static void main(String[] args) {

		JFrame frame = new JFrame("Movendo");
		frame.add(new TelaEstado());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
}