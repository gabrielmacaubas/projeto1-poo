
import java.io.File;
import java.util.*;

public class JogoDaForca {

	private ArrayList<String> palavras = new ArrayList<>(); // lista de palavras lidas do arquivo
	private ArrayList<String> dicas = new ArrayList<>(); // lista de dicas lidas do arquivo
	private String dica=""; // dica da palavra sorteada
	private String[] letras; // letras da palavra sorteada
	private int acertos; // contador de acertos
	private int penalidade; // penalidade atual
	
	public JogoDaForca(String nomearquivo) throws Exception {
	        Scanner sc = new Scanner(new File("Louise\\eclipse-workspace\\projeto1-Jogo-da-forca\\palavras.csv"));

	        while(sc.hasNextLine()) {
	            String[] tokens = sc.nextLine().split(";");
	            System.out.println(tokens[0]);
	            System.out.println(tokens[1]);
	            
	        }
	}
	
	public void iniciar() {
		
	}
	
	
}
