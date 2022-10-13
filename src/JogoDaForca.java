
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

class customException extends Exception {
	public customException(String mensagem) {
		super(mensagem);
	}
}

public class JogoDaForca {

	private ArrayList<String> palavras = new ArrayList<>(); // lista de palavras lidas do arquivo
	private ArrayList<String> dicas = new ArrayList<>(); // lista de dicas lidas do arquivo
	public ArrayList<String> letrasTentadas = new ArrayList<>();
	private String dica=""; // dica da palavra sorteada
	private String[] letras; // letras da palavra sorteada
	private int acertos; // contador de acertos
	private int penalidade; // penalidade atual
	public String arquivo;
	
	
	public JogoDaForca(String nomearquivo) throws IOException {
		try {
			
			Path path = Paths.get(nomearquivo);
			this.arquivo = Paths.get(nomearquivo).toAbsolutePath().toString();
			Scanner sc = new Scanner(new File(this.arquivo.replace("\\", "\\\\")));
			
		    
			while(sc.hasNextLine()) {
				String[] tokens = sc.nextLine().split(";");

				this.palavras.add(tokens[0]);
				this.dicas.add(tokens[1]);			
				
			}
		} catch(FileNotFoundException e) {
			throw new FileNotFoundException();
		}
		
	}
	
	public void iniciar() {
		Random sorteio = new Random();
		int n = sorteio.nextInt(palavras.size());

		this.letras = palavras.get(n).split("");
		this.dica = dicas.get(n);
	}
		
	public String getDica() {
		return this.dica;
	}

	public int getTamanho() {
		return this.letras.length;
	}
 
	/*
	public void gravarDados(String palavra, String novaDica) throws Exception {
        File file = new File(this.arquivo);

        try {
            FileWriter outputfile = new FileWriter(file, true);
			String novaPalavra = "\n" + palavra.toUpperCase() + ";";

            outputfile.append(novaPalavra);
            outputfile.append(novaDica);
            outputfile.flush();
            outputfile.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    */

	public ArrayList<Integer> getPosicoes(String letra) throws Exception {
		
		letra = letra.toUpperCase();
		
		if (letra.length() == 0) {
			this.penalidade++;
			
			throw new customException("Vazio, digite uma letra");
		}
		
		else if (letra.length() > 1) {
			this.penalidade++;
			
			throw new customException("Digite apenas uma letra");
		}
		
		else if (letrasTentadas.contains(letra)) {
			this.penalidade++;

			throw new customException("Esta letra ja foi tentada");

		} else {
			this.letrasTentadas.add(letra);

			ArrayList<Integer> posicoes = new ArrayList<>();
			boolean acertou = false;

			for (int i = 0; i < this.getTamanho(); i++) {
				if (this.letras[i].equals(letra)) {
					acertou = true;
					posicoes.add(i);
					this.acertos++;

				}
			}

			if (!acertou) {
				this.penalidade++;
			}

			return posicoes;
		}

		
	}

	public int getAcertos(){
		return this.acertos;
	}

	public int getPenalidade(){
		return this.penalidade;
	}

    public String getResultado(){
		if (this.acertos == this.getTamanho()) {
			return "Você venceu";

		} else if(this.acertos < this.getTamanho()) {
			return "Você foi enforcado";

		} else {
			return "jogo erm andamento";
		}
	}

	public boolean terminou() {
		if (this.acertos == this.getTamanho() || this.penalidade == 6) {
			return true;
		} else {
			return false;
		}
	}

}