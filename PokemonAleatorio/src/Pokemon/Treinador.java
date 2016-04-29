package Pokemon;

import java.util.Random;

class Ataque{
	private String nome;
	private double dano;
	
	public Ataque(String nome, double dano){
		this.nome = nome;
		this.dano = dano;
	}
	
	public String getNomeAtaque(){
		return nome;
	}
	
	public double getDano(){
		return dano;
	}
}

class Pokemon {
	private String nome;
	private double HP = 100.0;
	private int ataqueAtual = 0;
	private Random gerador = new Random();
	private Ataque ataques[] = new Ataque[4];
	
	public Pokemon(String nome, String ataque, double dano, String ataque2, double dano2, String ataque3, double dano3, String ataque4, double dano4){
		this.nome = nome;
		ataques[0] = new Ataque(ataque, dano);
		ataques[1] = new Ataque(ataque2, dano2);
		ataques[2] = new Ataque(ataque3, dano3);
		ataques[3] = new Ataque(ataque4, dano4);
	}
	
	public double getHp(){
		return HP;
	}
	
	public String getNomePokemon(){
		return nome;
	}
	
	public void itemCura(){
		HP += 20.0;
	}
	
	public void dano(double damage){
		HP -= damage;
	}
	
	public boolean vivo(){
		if(HP <= 0){
			return false;
		}
		return true;
	}
	
	public String getAtaqueAtual(){
		return ataques[ataqueAtual].getNomeAtaque(); 
	}
	
	public void mudaAtaqueAtual(){
		if(ataqueAtual == 3)
			ataqueAtual = 0;
		else
			ataqueAtual ++; 
		//Como simplificacao, foi determinado que ele somente avanca para o proximo
		//do vetor de ataques.
	}
	
	public double usarAtaqueAtual(){
		return ataques[ataqueAtual].getDano(); 
	}
	
	public double usarAtaqueRandom(){
		return ataques[gerador.nextInt(4)].getDano();
	}
}

public class Treinador {
	private String id;
	private int pokeAtual = 0;
	private int pokeVivos = 4;
	private int coordenadax = 0;
	private int coordenaday = 0;
	private Pokemon[] pokemons = new Pokemon[4];
	private boolean selvagem;
	
	//Para nao se tornar repetitivo demais, foram colocados somente 4 pokemons 
	//por treinador.
	
	public Treinador(String id, String poke, String ataque, double dano, String ataque2, double dano2, String ataque3, double dano3, String ataque4, double dano4, 
			String poke2, String ataque21, double dano21, String ataque22, double dano22, String ataque23, double dano23, String ataque24, double dano24,
			String poke3, String ataque31, double dano31, String ataque32, double dano32, String ataque33, double dano33, String ataque34, double dano34, 
			String poke4, String ataque41, double dano41, String ataque42, double dano42, String ataque43, double dano43, String ataque44, double dano44){
		this.id = id;
		selvagem = false;

		pokemons[0] = new Pokemon(poke, ataque, dano, ataque2, dano2, ataque3, dano3, ataque4, dano4);
		pokemons[1] = new Pokemon(poke2, ataque21, dano21, ataque22, dano22, ataque23, dano23, ataque24, dano24);
		pokemons[2] = new Pokemon(poke3, ataque31, dano31, ataque32, dano32, ataque33, dano33, ataque34, dano34);
		pokemons[3] = new Pokemon(poke4, ataque41, dano41, ataque42, dano42, ataque43, dano43, ataque44, dano44);
	}
	
	public Treinador(String id, String poke, String ataque, double dano, String ataque2, double dano2, String ataque3, double dano3, String ataque4, double dano4){
		this.id = id;
		selvagem = true;
		pokeVivos = 1;
		
		pokemons[0] = new Pokemon(poke, ataque, dano, ataque2, dano2, ataque3, dano3, ataque4, dano4);
	}
	
	public String getNome(){
		return id;
	}
	
	public double getVida(){
		return pokemons[pokeAtual].getHp();
	}
	
	public String getPokemonAtual(){
		return pokemons[pokeAtual].getNomePokemon();
	}
	
	public String getAtaque(){
		return pokemons[pokeAtual].getAtaqueAtual();
	}
	
	public int getPokeVivos(){
		return pokeVivos;
	}
	
	public boolean getSelvagem(){
		return selvagem;
	}
	
	public void mudaPokemon(){	
		while(pokemons[pokeAtual].vivo() == false){
			pokeAtual ++;
			if(pokeAtual == 4){
				pokeAtual = 0;					
			}
		}
		//Novamente para simplificar, foi adotado que ele somente avanca no vetor
		//de pokemons, e ja assume que a funcao so eh chamada quando o numero de 
		//pokemons vivos eh maior que zero.
	}
	
	public void tomarHit(double hit){
		pokemons[pokeAtual].dano(hit);
		if(pokemons[pokeAtual].vivo() == false){
			pokeVivos --;
		}
	}
	
	public boolean acertarHit(Treinador trainer){
		if(selvagem == false){
			trainer.tomarHit(pokemons[pokeAtual].usarAtaqueAtual());
			pokemons[pokeAtual].mudaAtaqueAtual();
		}
		else{
			trainer.tomarHit(pokemons[pokeAtual].usarAtaqueRandom());
		}
		if(trainer.getPokeVivos() == 0)
			return false;
		return true;
	}
	
	public int mudaPosicao(int direcao, int mapa[][]){
		if(direcao == 1){
			coordenaday --;
		}
		if(direcao == 2){
			coordenadax ++;
		}
		if(direcao == 3){
			coordenaday ++;
		}
		else{
			coordenadax --;
		}
		return mapa[coordenadax][coordenaday];
		//1 sobe, 2 direita, 3 desce, 4 esquerda.
	}
	
	public boolean probabilidadeGrama(Random generator){
		double probabilidade = 0.5;
		
		if(generator.nextDouble() < probabilidade)
			return true;
		else
			return false;
	}
	
	public void recuperaVida(){
		pokemons[pokeAtual].itemCura();
	}
}