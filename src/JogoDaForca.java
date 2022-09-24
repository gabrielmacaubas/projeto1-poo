import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class JogoDaForca {

	private ArrayList<String> palavras = new ArrayList<>(); // lista de palavras lidas do arquivo
	private ArrayList<String> dicas = new ArrayList<>(); // lista de dicas lidas do arquivo
	private String dica=""; // dica da palavra sorteada
	private String[] letras; // letras da palavra sorteada
	private int acertos; // contador de acertos
	private int penalidade; // penalidade atual
	public String arquivo;
	
	public JogoDaForca(String nomearquivo) throws Exception {
		try {
			this.arquivo = nomearquivo;
			Scanner sc = new Scanner(new File(arquivo));

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
}