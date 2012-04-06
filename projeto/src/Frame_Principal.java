
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JLabel;



public class Frame_Principal extends JFrame {

	private JPanel contentPane;
	private int altura = 320;
	private int largura = 640;
	private boolean clicado_estado;
	private boolean clicado_inicial;
	private boolean clicado_final;
	private boolean clicado_transicao;
	private boolean clicado_excluir;
	//private ArrayList<Estado> estados;
	private ArrayList<Integer> posicoesEstado;
	//private Estado estadoSelecionado;
	private int indice;
    private boolean isInicial;
//    private InternalFrameGrafo internalFrameG;
		
	
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
		
//		estados = new ArrayList< Estado >();
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
//		internalFrameG = new InternalFrameGrafo();
//		internalFrameG.setTitle("new");
//		internalFrameG.setSize(479, 244);
//		internalFrameG.getContentPane().setBackground(Color.WHITE);
//		internalFrameG.setLocation(10, 11);
//		
//		desktop_panel.add(internalFrameG);
		
		table = new JTable();
		table.setBounds(10, 254, 1, 1);
		desktop_panel.add(table);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		toolBar_1.setOrientation(SwingConstants.VERTICAL);
		toolBar_1.setBounds(54, 11, 41, 132);
		panel_principal.add(toolBar_1, BorderLayout.WEST);
		
		JLabel label = new JLabel(" \r\n\r\n\r\n\r\n");
		toolBar_1.add(label);
		
		JLabel lblBotes = new JLabel(" bot\u00F5es");
		toolBar_1.add(lblBotes);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon("C:\\Users\\Jessica\\Pictures\\maq\\inicial.png"));
		toolBar_1.add(button_1);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("C:\\Users\\Jessica\\Pictures\\maq\\addEstado.png"));
		toolBar_1.add(button);
		
		
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
		btnTabelaDeTransicoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTabelaDeTransicoes.setFont(new Font("Script MT Bold", Font.PLAIN, 14));
		toolBar.add(btnTabelaDeTransicoes);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(60, 60));
		panel.setMinimumSize(new Dimension(100, 100));
		panel_principal.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
//		Panel_Fita panel_fita = new Panel_Fita();
//		panel_fita.setBackground(SystemColor.controlHighlight);
//		panel.add(panel_fita);
//		
//		internalFrameG.setVisible(true);
		repaint();
		
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}
