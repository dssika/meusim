import javax.swing.*;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
public class ExemploToggleButton extends JFrame {
        public static void main(String[]args) {
                new ExemploToggleButton();
        }
        public ExemploToggleButton() {
                super("Exemplo de ToggleButton");
                JToggleButton tg1 = new JToggleButton("Opcao 1");
                JToggleButton tg2 = new JToggleButton("Opcao 2");
                JToggleButton tg3 = new JToggleButton("Opcao 3");
                JToggleButton tg4 = new JToggleButton("Opcao 4");
                Container pane = this.getContentPane();
                pane.setLayout(new GridLayout(6,1));
                pane.add(new JLabel("Selecione as opcoes desejadas:"));
                pane.add(new JLabel(""));
                pane.add(tg1);
                pane.add(tg2);
                pane.add(tg3);
                pane.add(tg4);
                ToggleButtonMonitor tgm = new ToggleButtonMonitor();
                tg1.addItemListener(tgm);
                tg2.addItemListener(tgm);
                tg3.addItemListener(tgm);
                tg4.addItemListener(tgm);
                this.addWindowListener(new WindowListener() {
					
					@Override
					public void windowOpened(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
                this.pack();
                this.setVisible(true);
        }
}
class ToggleButtonMonitor implements ItemListener {
        public void itemStateChanged(ItemEvent evt) {
                JToggleButton tg = (JToggleButton)evt.getItemSelectable();
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                        System.out.println(tg.getText() + " marcado.");
                } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
                        System.out.println(tg.getText() + " desmarcado.");
                }
        }
}