package Estado;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Component;
import javax.swing.JButton;


import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Button;
import javax.swing.JLabel;
import javax.swing.JInternalFrame;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.awt.Rectangle;
import java.awt.Dimension;



public class Frame_Principal extends JFrame {

	private JPanel contentPane;
	private int altura = 320;
	private int largura = 640;
	private boolean clicado_estado;
	private boolean clicado_inicial;
	private boolean clicado_final;
	private boolean clicado_transicao;
	private boolean clicado_excluir;
	private ArrayList<Estado> estados;
	private ArrayList<Integer> posicoesEstado;
	private Estado estadoSelecionado;
	private int indice;
    private boolean isInicial;
    private InternalFrameGrafo internalFrameG;
		
	
	final JButton btnTabelaDeTransicoes = new JButton(" tabela de Transicoes");
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_Principal frame = new Frame_Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public Frame_Principal() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

		setTitle("Simulador M\u00E1quina deTuring");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Jessica\\Pictures\\maq\\turing.png"));
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //Windows
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 679, 446);
		setLocationRelativeTo(null);
		clicado_estado = false;
		
		estados = new ArrayList< Estado >();
        isInicial = true;
        initComponents();
	}
        private void initComponents() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mntmNovo = new JMenuItem("Novo");
		mnArquivo.add(mntmNovo);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mnArquivo.add(mntmAbrir);
		
	
		mnArquivo.addSeparator();
		
		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mnArquivo.add(mntmSalvar);
		
		JMenuItem mntmSalvarComo = new JMenuItem("Salvar como...");
		mnArquivo.add(mntmSalvarComo);
		
		JMenu mnImportar = new JMenu("Importar");
		mnArquivo.add(mnImportar);
		
		JMenuItem mntmImagemjpg = new JMenuItem("Imagem (.jpg)");
		mnImportar.add(mntmImagemjpg);
		
		JMenuItem mntmImagempng = new JMenuItem("Imagem (.png)");
		mnImportar.add(mntmImagempng);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JPanel panel_principal = new JPanel();
		panel_principal.setLayout(new BorderLayout(0, 0));
		contentPane.add(panel_principal);
		
		JDesktopPane desktop_panel = new JDesktopPane();
		desktop_panel.setBackground(new Color(255, 250, 240));
		panel_principal.add(desktop_panel, BorderLayout.CENTER);
		desktop_panel.setLayout(null);
		internalFrameG = new InternalFrameGrafo();
		internalFrameG.setTitle("new");
		internalFrameG.setSize(479, 244);
		internalFrameG.getContentPane().setBackground(Color.WHITE);
		internalFrameG.setLocation(10, 11);
		
		desktop_panel.add(internalFrameG);
		
		table = new JTable();
		table.setBounds(10, 254, 1, 1);
		desktop_panel.add(table);
		
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 327, 35);
		panel_principal.add(toolBar, BorderLayout.NORTH);
		
		JButton button_novo = new JButton("");
		button_novo.setToolTipText("novo");
		button_novo.setIcon(new ImageIcon("D:\\7 periodo\\projeto\\icons\\welcome_editor.gif"));
		toolBar.add(button_novo);

		JButton button_abrir = new JButton("");
		button_abrir.setToolTipText("abrir");

		button_abrir.setIcon(new ImageIcon("D:\\7 periodo\\projeto\\icons\\fldr_obj.gif"));
		toolBar.add(button_abrir);

		JButton button_salvar = new JButton("");
		button_salvar.setToolTipText("salvar");
		button_salvar.setIcon(new ImageIcon("D:\\7 periodo\\projeto\\icons\\save_edit.gif"));
		toolBar.add(button_salvar);
		toolBar.addSeparator();

		JButton button_executar = new JButton("");
		button_executar.setToolTipText("executar");
		button_executar.setIcon(new ImageIcon("D:\\7 periodo\\projeto\\icons\\start.gif"));
		toolBar.add(button_executar);

		JButton button_passo = new JButton("");
		button_passo.setToolTipText("passo-a-passo");
		button_passo.setIcon(new ImageIcon("D:\\7 periodo\\projeto\\icons\\nav_forward.gif"));
		toolBar.add(button_passo);

		JButton button_pausar = new JButton("");
		button_pausar.setToolTipText("pausar");
		button_pausar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_pausar.setIcon(new ImageIcon("D:\\7 periodo\\projeto\\icons\\suspend_co.gif"));
		toolBar.add(button_pausar);

		JButton button_parar = new JButton("");
		button_parar.setToolTipText("parar");
		button_parar.setIcon(new ImageIcon("D:\\7 periodo\\projeto\\icons\\launch_stop.gif"));
		toolBar.add(button_parar);
		toolBar.addSeparator();
		toolBar.addSeparator();
		btnTabelaDeTransicoes.setFont(new Font("Script MT Bold", Font.PLAIN, 14));
		toolBar.add(btnTabelaDeTransicoes);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(60, 60));
		panel.setMinimumSize(new Dimension(100, 100));
		panel_principal.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		Panel_Fita panel_fita = new Panel_Fita();
		panel_fita.setBackground(SystemColor.controlHighlight);
		panel.add(panel_fita);
		
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		panel_principal.add(toolBar_1, BorderLayout.WEST);
		toolBar_1.setOrientation(SwingConstants.VERTICAL);
		
		JSeparator separator = new JSeparator();
		toolBar_1.add(separator);
		
		toggleButton_novoEstado = new JToggleButton("");
		toggleButton_novoEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
					toggleButton_transicao.setSelected(false);
					toggleButton_estadoInicial.setSelected(false);
					toggleButton_estadoFinal.setSelected(false);
					toggleButton_excluir.setSelected(false);
				
					if(toggleButton_novoEstado.isSelected())
					{
						internalFrameG.adicionaEstado(true, false);
						
					}
					
//					internalFrameG.repaint();
////				       Estado e1 = null;
//			            
//			            if ( isInicial ) {
//			                e1 = new Estado( "q" + indice, true, false, 150, 70);
//			                isInicial = false;
//			            } else {
//			                e1 = new Estado( "q" + indice, false, false,150, 70);
//			            }
//			            
//			            indice++;
//			            
//			            TelaEstado t = new TelaEstado();
////			            Panel_Estado p = new Panel_Estado( e );
//			            estados.add( e1 );
//			            
//			            internalFrameG.getContentPane().add(t);
//			            repaint();
//			            internalFrameG.repaint();  
//			            
				
			}
		});
		toggleButton_novoEstado.setIcon(new ImageIcon("C:\\Users\\Jessica\\Pictures\\maq\\addEstado.png"));
		toolBar_1.add(toggleButton_novoEstado);
		
		toggleButton_estadoInicial = new JToggleButton("");
		toggleButton_estadoInicial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					toggleButton_novoEstado.setSelected(false);
					toggleButton_transicao.setSelected(false);
					toggleButton_estadoFinal.setSelected(false);
					toggleButton_excluir.setSelected(false);
				
			}
		});
		toggleButton_estadoInicial.setIcon(new ImageIcon("C:\\Users\\Jessica\\Pictures\\maq\\inicial.png"));
		toolBar_1.add(toggleButton_estadoInicial);
		
		toggleButton_estadoFinal = new JToggleButton("");
		toggleButton_estadoFinal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					toggleButton_novoEstado.setSelected(false);
					toggleButton_estadoInicial.setSelected(false);
					toggleButton_transicao.setSelected(false);
					toggleButton_excluir.setSelected(false);
					
				
			}
		});
		toggleButton_estadoFinal.setIcon(new ImageIcon("C:\\Users\\Jessica\\Pictures\\maq\\final.png"));
		toolBar_1.add(toggleButton_estadoFinal);
		
		toggleButton_transicao = new JToggleButton("");
		toggleButton_transicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					toggleButton_novoEstado.setSelected(false);
					toggleButton_estadoInicial.setSelected(false);
					toggleButton_estadoFinal.setSelected(false);
					toggleButton_excluir.setSelected(false);
				
			}
		});
		toggleButton_transicao.setIcon(new ImageIcon("C:\\Users\\Jessica\\Pictures\\maq\\transicao.png"));
		toolBar_1.add(toggleButton_transicao);
		
		toggleButton_excluir = new JToggleButton("");
		toggleButton_excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					toggleButton_novoEstado.setSelected(false);
					toggleButton_estadoInicial.setSelected(false);
					toggleButton_estadoFinal.setSelected(false);
					toggleButton_transicao.setSelected(false);
					
				
			}
		});
		toolBar_1.add(toggleButton_excluir);
		toggleButton_excluir.setIcon(new ImageIcon("C:\\Users\\Jessica\\Pictures\\maq\\lx.png"));
		
		JSeparator separator_3 = new JSeparator();
		toolBar_1.add(separator_3);
		
		JSeparator separator_2 = new JSeparator();
		toolBar_1.add(separator_2);
		
		JSeparator separator_1 = new JSeparator();
		toolBar_1.add(separator_1);
		
		
		
		internalFrameG.setVisible(true);
		repaint();
		
	}
	
        private void btnNovoEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEstadoActionPerformed
            
     
            
        }

	private static void addPopup(Component component, final JPopupMenu popup) {
	}
	JToggleButton toggleButton_estadoInicial;
	JToggleButton toggleButton_novoEstado ;
	JToggleButton toggleButton_estadoFinal;
	JToggleButton toggleButton_transicao;
	JToggleButton toggleButton_excluir ;
}
