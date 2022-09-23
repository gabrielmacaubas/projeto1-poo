/*
 * IFPB - TSI - POO - PROJETO1
 * Prof. Fausto Ayres
 * 
 * Aplicação console para testar a classe JogoDaForca
 * 
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AplicacaoConsole {
	public static void main(String[] args) {
		try {
			Scanner teclado = new Scanner (System.in);
			String[] penalidades = {"perna1", "perna2", "braço1", "braço2", "tronco", "cabeça"};
			String letra;
			String[] letrasAdivinhadas; 	//letras adivinhadas
			ArrayList<Integer> posicoes;	//posicoes adivinhadas
			
			JogoDaForca jogo = new JogoDaForca("palavras.csv");
			jogo.iniciar();
			System.out.println("dica da palavra=" + jogo.getDica());
			System.out.println("tamanho da palavra=" + jogo.getTamanho());
			letrasAdivinhadas = new String[jogo.getTamanho()];	
			Arrays.fill(letrasAdivinhadas, "");

			do {
				System.out.println("\ndigite uma letra da palavra ");
				letra = teclado.nextLine();
				try {
					posicoes = jogo.getPosicoes(letra);
					if (posicoes.size()>0) {
						System.out.println("posicoes encontradas="+ posicoes);
						for(int i : posicoes)
							letrasAdivinhadas[i] = letra;
						System.out.println(Arrays.toString(letrasAdivinhadas));
						System.out.println("total de acertos="+jogo.getAcertos());
					} 
					else {
						System.out.println("voce errou - penalidade="+jogo.getPenalidade()+", retirar "+ penalidades[jogo.getPenalidade()-1]);
					}
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			while(!jogo.terminou());

			teclado.close();
			System.out.println("\n---- game over ----");
			System.out.println("resultado final="+jogo.getResultado() );
			System.out.println("situacao final ="+ Arrays.toString(letrasAdivinhadas));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
