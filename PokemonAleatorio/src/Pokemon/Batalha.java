package Pokemon;

import java.util.Random;

public class Batalha extends Controller{
	Treinador treinador = new Treinador("Ash", "Pikachu", "Choque do Trovao", 32.0, "Esfera Eletrica", 28.0, "Investida Trovao", 30.0, "Ataque Rapido", 28.0,
			"Charmander", "Brasas", 28.0, "Lanca-chamas", 32.0, "Garra de Metal", 30.0, "Furia", 25.0,
			"Squirtle", "Bolhas", 27.0, "Rajada de Bolhas", 32.0, "Hidro Bomba", 28.0, "Raio de Gelo", 30.0,
			"Bulbasaur", "Raio Solar", 25.0, "Folha Navalha", 28.0, "Chicote de Cipo", 29.0, "Investida", 30.0); 
	Treinador treinador2 = new Treinador("Selvagem", "Rattata", "Investida", 23.0, "Chicote de Cauda", 25.0, "Ataque Rapido", 22.0, "Hiper Presa", 20.0);
	int mapa[][] = new int[3][3];
	
	private void criaMapa(){
		mapa[0][0] = 0;
		mapa[0][1] = 1;
		mapa[0][2] = 0;
		mapa[1][0] = 1;
		mapa[1][1] = 1;
		mapa[1][2] = 1;
		mapa[2][0] = 0;
		mapa[2][1] = 1;
		mapa[2][2] = 0;
	}
	
	private class Anda extends Event {
		private int direcao;
		private int acao;
		private Treinador trainer;
		
		public Anda(long eventTime, int direcao, Treinador trainer){
			super(eventTime);
			this.direcao = direcao;
			this.trainer = trainer;
		}
		
		public void action(){
			acao = trainer.mudaPosicao(direcao, mapa);
		}
		
		public String description() {
			if(acao == 0)
				return "Ainda no chao.";
			else
				return "Na grama.";
		}
	}

	private class Ataque extends Event {
		private boolean decisao = true;
		private Treinador trainer;
		private Treinador trainer2;
		
		public Ataque(long eventTime, Treinador trainer, Treinador trainer2) {
			super(eventTime);
			this.trainer = trainer;
			this.trainer2 = trainer2;
		}
		
		public void action(){
			decisao = trainer.acertarHit(trainer2);
		}
		
		public String description() {
			if(decisao == false)
				if(trainer.getSelvagem() == false)
					return ""+trainer.getNome()+" usou o ataque "+trainer.getAtaque()+" do pokemon "+trainer.getPokemonAtual()+"\nFim da luta com "+trainer.getNome()+" vencedor!";
				else
					return ""+trainer.getNome()+" usou o ataque "+trainer.getAtaque()+"\nO treinador "+trainer2.getNome()+" perdeu!";
			else
				if(trainer.getSelvagem() == false)
					return ""+trainer.getNome()+" usou o ataque "+trainer.getAtaque()+" do pokemon "+trainer.getPokemonAtual()+".";
				else
					return ""+trainer.getNome()+" usou o ataque "+trainer.getAtaque()+".";
		}
	}
	
	private class trocaPokemonAtivo extends Event {	
		private Treinador trainer;
		public trocaPokemonAtivo(long eventTime, Treinador trainer) {
			super(eventTime);
			this.trainer = trainer;
		}
		public void action(){
			trainer.mudaPokemon();
		}
		public String description() {
			return ""+trainer.getNome()+" trocou para o pokemon "+trainer.getPokemonAtual()+".";
		}
	}

	private class curaPokemonAtivo extends Event {
		private Treinador trainer;
		public curaPokemonAtivo(long eventTime, Treinador trainer) {
			super(eventTime);
			this.trainer = trainer;
		}
		public void action(){
			trainer.recuperaVida();
		}
		public String description() {
			return ""+trainer.getNome()+" usou item de cura no pokemon "+trainer.getPokemonAtual()+".";
		}
	}
	
	private class correr extends Event {
		private Treinador trainer;
		private Treinador trainer2;
		public correr(long eventTime, Treinador trainer, Treinador trainer2) {
			super(eventTime);
			this.trainer = trainer;
			this.trainer2 = trainer2;
		}
		public void action(){
			return;
		}
		public String description(){
			return ""+trainer.getNome()+" correu da luta.\n"+trainer2.getNome()+" eh o vencedor.";
		}
	}
	
	private class Battle extends Event {
		private Treinador trainer;
		private Treinador trainer2;
		private long tm;
		public Battle(long eventTime, Treinador trainer, Treinador trainer2){
			super(eventTime);
			tm = eventTime;
			this.trainer = trainer;
			this.trainer2 = trainer2;
		}
		public void action(){
			for(int i = 4; trainer.getPokeVivos() > 0 && trainer2.getVida() > 0; i += 10){
				addEvent(new Ataque(tm + i, trainer, trainer2));
				if(trainer2.getVida() > 0){
					addEvent(new Ataque(tm + 2 + i, trainer2, trainer));
					if(trainer.getVida() < 0){
						addEvent(new trocaPokemonAtivo(tm + 4 + i, trainer));
					}
				}
			}
			return;
		}
		
		public String description(){
			return "Um pokemon selvagem apareceu!";
		}
	}
	
	private class Inicio extends Event {
		public Inicio(long eventTime) {
			super(eventTime);
		}
		public void action() {
			long tm = System.currentTimeMillis();
			Random generator = new Random();
			
			addEvent(new Anda(tm, 2, treinador));
			
			if(treinador.probabilidadeGrama(generator) == true){
				addEvent(new Battle(tm + 10, treinador, treinador2));
				return;
			}
			
			else{
				addEvent(new Anda(tm + 20, 2, treinador));
				addEvent(new Anda(tm + 30, 3, treinador));
				if(treinador.probabilidadeGrama(generator) == true){
					addEvent(new Battle(tm + 40, treinador, treinador2));
					return;
				}
				else{
					addEvent(new Anda(tm + 40, 4, treinador));
					if(treinador.probabilidadeGrama(generator) == true){
						addEvent(new Battle(tm + 50, treinador, treinador2));
						return;
					}
					else
						addEvent(new Anda(tm + 50, 4, treinador));
						if(treinador.probabilidadeGrama(generator) == true){
							addEvent(new Battle(tm + 60, treinador, treinador2));
							return;
						}
						else{
							addEvent(new Anda(tm + 60, 3, treinador));
							addEvent(new Anda(tm + 70, 2, treinador));
							if(treinador.probabilidadeGrama(generator) == true){
								addEvent(new Battle(tm + 80, treinador, treinador2));
							}
							else{
								addEvent(new Anda(tm + 80, 2, treinador));
							}
							return;
						}
				}
				
			}	
			
		}
		public String description() {
			return "Comeca a andar";
		}
		//Este metodo se assemelha ao Restart do exemplo, ja que ele agrega os
		//as chamadas de criada de novos eventos. Porem, foi chamada de Inicio
		//por parecer mais natural para este caso.
	}
	
	public static void main(String[] args) {
		Batalha gc = new Batalha();
		gc.criaMapa();
		long tm = System.currentTimeMillis();
		gc.addEvent(gc.new Inicio(tm));
		gc.run();
	}

}
