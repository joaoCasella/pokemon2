package Pokemon;

public class Batalha extends Controller{
	Treinador treinador = new Treinador("Ash"); 
	Treinador treinador2 = new Treinador("Selvagem");
	int mapa[][] = new int[5][5];
	
	public void prepara(){
		Preparacao teste = new Preparacao();
		teste.associaPokemons(treinador, treinador2);
		teste.associaAtaques(treinador, treinador2);
		teste.associaMapa(mapa);
	}
	//Todos os metodos que servirao de base para a criacao de eventos estao separa
	//dos em 1 e 2. Embora seja possivel fazer um metodo chamado ataque que serve
	//para o uso dos dois treinadores, foi escolhido este para permitir uma melhor
	//vizualizacao do que acontecera e pois nao se consegue confundir no seu uso
	//(ou seja, evita erros desnecessarios).
	
	//EVITAR REPETICAO DE METODOS.
	private class AtaqueDe1em2 extends Event {
		private int decisao = 1;
		
		public AtaqueDe1em2(long eventTime, Treinador treinador, Treinador treinador2) {
			super(eventTime);
		}
		
		public void action(){
			decisao = treinador2.tomaHit(treinador.acertarHit());
		}
		
		public String description() {
			if(decisao == 0)
				return ""+treinador.getNome()+" usou o ataque "+treinador.getAtaque()+" do pokemon "+treinador.getPokemonAtual()+"\nFim da luta com "+treinador.getNome()+" vencedor!";
			else
				return ""+treinador.getNome()+" usou o ataque "+treinador.getAtaque()+" do pokemon "+treinador.getPokemonAtual()+".";
		}
		//Aqui se percebe o motivo de o metodo tomarHit retornar int. Isso acontece
		//pois cada resposta leva a uma mensagem exibida diferente. O numero 0 indi
		//ca que todos os pokemons do treinador atingido estao sem vida, logo ele 
		//perdeu, o que emite a mensagem de vitoria do outro. Ja o numero 1 indica
		//que o pokemon atual que recebeu o dano ainda esta vivo, e segue lutando.
		//Ja 2 indica que o ataque incapacitou o pokemon que recebeu o hit, e como
		//o metodo ja muda o pokemon ativo, informa-se qual o pokemon que sera usa
		//a partir de agora pelo treinador.
	}
	
	private class AtaqueDe2em1 extends Event {
		private int decisao = 0;
		
		
		public AtaqueDe2em1(long eventTime) {
			super(eventTime);
		}
		public void action(){
			decisao = treinador.tomarHit(treinador2.acertaHit());
		}
		public String description() {
			if(decisao == 0)
				return ""+treinador2.getNome()+" usou o ataque "+treinador2.getAtaque()+" do pokemon "+treinador2.getPokemonAtual()+"\n O "+treinador.getNome()+" fugiu para recuperar seus pokemons!";
			else if(decisao == 1)
				return ""+treinador2.getNome()+" usou o ataque "+treinador2.getAtaque()+" do pokemon "+treinador2.getPokemonAtual()+".";
			else
				return ""+treinador2.getNome()+" usou o ataque "+treinador2.getAtaque()+" do pokemon "+treinador2.getPokemonAtual()+".\n"+treinador.getNome()+" agora esta com "+treinador.getPokemonAtual()+"";
		}//Troca, colocar troca como novo evento
	}
	
	private class trocaPokemonAtivo extends Event {	
		public trocaPokemonAtivo(long eventTime) {
			super(eventTime);
		}
		public void action(){
			treinador.mudaPokemon();
		}
		public String description() {
			return ""+treinador.getNome()+" trocou para o pokemon "+treinador.getPokemonAtual()+".";
		}
	}

	private class curaPokemonAtivo extends Event {	
		public curaPokemonAtivo(long eventTime) {
			super(eventTime);
		}
		public void action(){
			treinador.mudaPokemon();
		}
		public String description() {
			return ""+treinador.getNome()+" usou item de cura no pokemon "+treinador.getPokemonAtual()+".";
		}
	}
	
	private class correr extends Event {
		public correr(long eventTime) {
			super(eventTime);
		}
		public void action(){
			
		}
		public String description(){
			return ""+treinador.getNome()+" correu da luta.\n"+treinador2.getNome()+" eh o vencedor.";
		}
	}
	
	private class Inicio extends Event {
		public Inicio(long eventTime) {
			super(eventTime);
		}
		public void action() {
			long tm = System.currentTimeMillis();
			
			addEvent(new Ataque(tm, t1, t2));
			addEvent(new AtaqueDe2em1(tm + 100));
			
			addEvent(new AtaqueDe1em2(tm + 200));
			addEvent(new AtaqueDe2em1(tm + 300));
			
			addEvent(new AtaqueDe1em2(tm + 400)); 
			addEvent(new AtaqueDe2em1(tm + 500));
			
			addEvent(new AtaqueDe1em2(tm + 600));
			
			addEvent(new curaPokemonAtivo(tm + 700));
			addEvent(new AtaqueDe2em1(tm + 800));
			
			addEvent(new AtaqueDe1em2(tm + 900));
			addEvent(new AtaqueDe2em1(tm + 1000));
			
			addEvent(new AtaqueDe1em2(tm + 1100));
			addEvent(new AtaqueDe2em1(tm + 1200));
			
			addEvent(new AtaqueDe1em2(tm + 1300));
			addEvent(new AtaqueDe2em1(tm + 1400));
			
			addEvent(new AtaqueDe1em2(tm + 1500));
			
			addEvent(new AtaqueDe1em2(tm + 1600));
			addEvent(new AtaqueDe2em1(tm + 1700));
			
			addEvent(new AtaqueDe1em2(tm + 1800));
			addEvent(new AtaqueDe2em1(tm + 1900));
			
			addEvent(new AtaqueDe1em2(tm + 2000));
			addEvent(new AtaqueDe2em1(tm + 2100));
			
			addEvent(new trocaPokemonAtivo2(tm + 2200));
			addEvent(new AtaqueDe1em2(tm + 2300));
			
			addEvent(new AtaqueDe1em2(tm + 2400));
			addEvent(new AtaqueDe2em1(tm + 2500));
			
			addEvent(new AtaqueDe1em2(tm + 2600));
			addEvent(new AtaqueDe2em1(tm + 2700));
			
			addEvent(new AtaqueDe1em2(tm + 2800));
			
			addEvent(new curaPokemonAtivo2(tm + 2900));
			addEvent(new AtaqueDe1em2(tm + 3000));
		}
		public String description() {
			return "Comeca a batalha";
		}
		//Este metodo se assemelha ao Restart do exemplo, ja que ele agrega os
		//as chamadas de criada de novos eventos. Porem, foi chamada de Inicio
		//por parecer mais natural para este caso.
	}
	
	public static void main(String[] args) {
		Batalha gc = new Batalha();
		gc.prepara();
		long tm = System.currentTimeMillis();
		gc.addEvent(gc.new Inicio(tm));
		gc.run();
	}

}
