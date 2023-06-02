package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {
	
	private IO io;
	public FabbricaDiComandiRiflessiva() {
		Scanner scanner = new Scanner(System.in);
		this.io = new IOConsole(scanner);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public AbstractComando costruisciComando(String istruzione) {
		try (Scanner scanner = new Scanner(istruzione)) {
			String nomeComando = null;
			String parametro = null;
			AbstractComando comando = null;
			
			if (scanner.hasNext())
				nomeComando = scanner.next();//prima parola: nome del comando
			if (scanner.hasNext())
				parametro = scanner.next();//seconda parola: eventuale parametro
			
			try {
				String nomeClasse = "it.uniroma3.diadia.comandi.Comando";
				nomeClasse += Character.toUpperCase(nomeComando.charAt(0));
				nomeClasse += nomeComando.substring(1);
				comando = (AbstractComando)Class.forName(nomeClasse).newInstance();
				comando.setParametro(parametro);
			} catch (Exception e) {
				comando = new ComandoNonValido();
				this.io.mostraMessaggio("Comando inesistente");
			}
			return comando;
		}
	}
	
}
