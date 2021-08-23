package br.com.automacao.main;

public class Main {

	public static void main(String[] args) {
		
		try {
			new LigarArcondicionado().run();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}
	
}
