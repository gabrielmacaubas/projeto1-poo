import java.io.FileNotFoundException;
import java.util.Arrays;

public class TesteForca {
	public static void main (String[] args) throws Exception {
		try {
			JogoDaForca jogo = new JogoDaForca("palavras.csv");

			//jogo.gravarDados("SAMUEL", "o maior reizinho da sala");

			jogo.iniciar();

			System.out.println(jogo.getDica());
			System.out.println(jogo.getTamanho());

		} catch (FileNotFoundException e) {
			System.out.println("Arquivo inexistente");
		}
	}
}
