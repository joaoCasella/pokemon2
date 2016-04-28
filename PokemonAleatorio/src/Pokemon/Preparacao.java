package Pokemon;

//TRANSFORMAR EM ALGO PASSADO NO CONSTRUTOR
public class Preparacao {
	//Como o nome indica, serve como base para a criacao dos treinadores que estao
	//sendo usados. Com alteracoes nesta e com ajustes no eventos, pode-se criar 
	//batalhas diferentes.
	public void associaPokemons(Treinador treinador, Treinador treinador2){
		String pokemons[] = new String[4];
		String pokemon2 = "Rattata";
		
		pokemons[0] = "Pikachu";
		pokemons[1] = "Charmander";
		pokemons[2] = "Squirtle";
		pokemons[3] = "Bulbasaur";
			
		treinador.criaVetorPokemons();
		treinador.preenchePokemons(pokemons);
		treinador2.criaPokemon();
		treinador2.preenchePokemon(pokemon2);
	}
	
	public void associaAtaques(Treinador treinador, Treinador treinador2){
		String ataques[][] = new String[4][4];
		double dano[][] = new double[4][4];
		String ataques2[] = new String[4];
		double dano2[] = new double[4];
		
		ataques[0][0] = "Choque do Trovao";
		dano[0][0] = 32.0;
		ataques[0][1] = "Esfera Eletrica";
		dano[0][1] = 28.0;
		ataques[0][2] = "Investida Trovao";
		dano[0][2] = 30.0;
		ataques[0][3] = "Ataque Rapido";
		dano[0][3] = 28.0;
		
		ataques[1][0] = "Brasas";
		dano[1][0] = 28.0;
		ataques[1][1] = "Lanca-chamas";
		dano[1][1] = 32.0;
		ataques[1][2] = "Garra de Metal";
		dano[1][2] = 30.0;
		ataques[1][3] = "Furia";
		dano[1][3] = 25.0;
		
		ataques[2][0] = "Bolhas";
		dano[2][0] = 27.0;
		ataques[2][1] = "Rajada de Bolhas";
		dano[2][1] = 32.0;
		ataques[2][2] = "Hidro Bomba";
		dano[2][2] = 28.0;
		ataques[2][3] = "Raio de Gelo";
		dano[2][3] = 30.0;
		
		ataques[3][0] = "Raio Solar";
		dano[3][0] = 25.0;
		ataques[3][1] = "Folha Navalha";
		dano[3][1] = 28.0;
		ataques[3][2] = "Chicote de Cipo";
		dano[3][2] = 29.0;
		ataques[3][3] = "Investida";
		dano[3][3] = 30.0;
		
		treinador.ataquesPokemons(ataques, dano);
		
		ataques2[0] = "Investida";
		dano2[0] = 23.0;
		ataques2[1] = "Chicote de Cauda";
		dano2[1] = 25.0;
		ataques2[2] = "Ataque Rapido";
		dano2[2] = 22.0;
		ataques2[3] = "Hiper Presa";
		dano2[3] = 20.0;
		
		treinador2.ataquePokemon(ataques2, dano2);
	}	
	
	public void associaMapa(int[][] matriz){
		for(int i = 0; i < 10; i ++){
			for(int j = 0; j < 10; j ++){
				matriz[i][j] = 0;
			}
		}
		for(int i = 1; i < 9; i += 2){
			for(int j = 2; j < 6; j ++){
				matriz[i][j] = 0;
			}
		}
	}
}
