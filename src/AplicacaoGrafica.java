import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class AplicacaoGrafica {

	private JFrame frame;
	private JLabel label;
	private JTextField textField;
	private JButton button;
	private String[] penalidades = {"perna1", "perna2", "braço1", "braço2", "tronco", "cabeça"};
	private String letra;
	private String[] letrasAdivinhadas; 	//letras adivinhadas
	private ArrayList<Integer> posicoes;
	private JogoDaForca jogo;//posicoes adivinhadas
	private JButton button_1;
	private JLabel label_1;
	private JLabel label_2;
	private JButton button_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AplicacaoGrafica window = new AplicacaoGrafica();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AplicacaoGrafica() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		frame = new JFrame();
		
		frame.setTitle("Jogo da Forca");
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label = new JLabel("Digite uma letra:");
		
		label.setBounds(40, 97, 102, 14);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		
		textField.setBounds(138, 94, 25, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		label_1 = new JLabel("dica da palavra");
		
		label_1.setBounds(40, 72, 399, 14);
		frame.getContentPane().add(label_1);
		
		button_1 = new JButton("Sortear dica");
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					textField.requestFocus();
					textField.getRootPane().setDefaultButton(button);
					
					jogo = new JogoDaForca("palavras.csv");
					
					jogo.iniciar();
					
					ImageIcon icon = new ImageIcon(AplicacaoGrafica.class.getResource("/imagens/" +jogo.getPenalidade() + ".png"));
					
					label_3.setIcon(icon);
							
					icon.setImage(
							icon.getImage().getScaledInstance(label.getWidth(),
							label_3.getHeight(), Image.SCALE_DEFAULT)
					);
					label_4.setText("");
					label_5.setText("");
					label_1.setText("dica da palavra=" + jogo.getDica());
					
					letrasAdivinhadas = new String[jogo.getTamanho()];	
					
					Arrays.fill(letrasAdivinhadas, "*");
					button.setEnabled(true);
					label_6.setText("");
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
		});
		button_1.setBounds(139, 38, 148, 23);
		frame.getContentPane().add(button_1);
		
		button = new JButton("ok");
		
		button.setEnabled(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					textField.getRootPane().setDefaultButton(button);
					
					letra = textField.getText();
				
					try {
						posicoes = jogo.getPosicoes(letra);
						
						label_6.setText("" + jogo.letrasTentadas);
						
						if (posicoes.size()>0) {
							for(int i : posicoes)
								letrasAdivinhadas[i] = letra;
							
							String x = Arrays.toString(letrasAdivinhadas);
							
							label_5.setText(x);
							label_4.setForeground(Color.GREEN);
							label_4.setText("Parabéns! Você acertou a letra.");
							textField.setText("");
							textField.requestFocus();
							
						} 
						else {
							ImageIcon icon = new ImageIcon(AplicacaoGrafica.class.getResource("/imagens/" +jogo.getPenalidade() + ".png"));
							
							label_3.setIcon(icon);		
							icon.setImage(
									icon.getImage().getScaledInstance(label.getWidth(),
									label_3.getHeight(), Image.SCALE_DEFAULT)
							);
							
							label_4.setForeground(Color.RED);
							label_4.setText("Você errou. Tente novamente!");
							textField.setText("");
							textField.requestFocus();
						}
					}
					catch(Exception e) {
						ImageIcon icon = new ImageIcon(AplicacaoGrafica.class.getResource("/imagens/" +jogo.getPenalidade() + ".png"));
						
						label_3.setIcon(icon);	
						icon.setImage(
								icon.getImage().getScaledInstance(label.getWidth(),
								label_3.getHeight(), Image.SCALE_DEFAULT)
						);
						label_4.setForeground(Color.RED);
						label_4.setText(e.getMessage());
						
					}
				//} else {
					if(jogo.terminou() == true) {
						JOptionPane.showMessageDialog(null, jogo.getResultado());
					}
			
			}
				
		});
		button.setBounds(192, 94, 74, 20);
		frame.getContentPane().add(button);
		
		label_2 = new JLabel("Bem-vindo ao Jogo da Forca");
		
		label_2.setBounds(141, 11, 182, 19);
		frame.getContentPane().add(label_2);
		
		button_2 = new JButton("Sair do jogo");
		
		button_2.setBounds(294, 227, 112, 23);
		frame.getContentPane().add(button_2);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		label_3 = new JLabel("");
		
		label_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_3.setIcon(new ImageIcon(AplicacaoGrafica.class.getResource("/imagens/0.png")));
		label_3.setBounds(364, 38, 112, 125);
		frame.getContentPane().add(label_3);
		
		ImageIcon icon = new ImageIcon(AplicacaoGrafica.class.getResource("/imagens/0.png"));
		
		label_3.setIcon(icon);	
		icon.setImage(
				icon.getImage().getScaledInstance(label.getWidth(),
				label_3.getHeight(), Image.SCALE_DEFAULT)
		);
		
		label_4 = new JLabel("");
		
		label_4.setBorder(null);
		label_4.setBounds(64, 167, 295, 49);
		frame.getContentPane().add(label_4);
		
		label_5 = new JLabel("");
		
		label_5.setBorder(null);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_5.setBounds(111, 122, 163, 34);
		frame.getContentPane().add(label_5);
		label_5.setText("");
		
		label_6 = new JLabel("");
		
		label_6.setBounds(93, 210, 148, 34);
		frame.getContentPane().add(label_6);
		
		label_7 = new JLabel("Letras:");
		
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_7.setBounds(52, 216, 59, 20);
		frame.getContentPane().add(label_7);
	}
}