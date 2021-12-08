package estruturaTabuleiro;

import java.util.Random;

import estruturaJogo.Jogo;
import estruturaTabuleiro.bloco.Bloco;
import estruturaTabuleiro.bloco.BlocoMinado;
import estruturaTabuleiro.bloco.BlocoProxAMinas;
import estruturaTabuleiro.bloco.BlocoVazio;
import exceptions.ValorDeAtributoInvalido;

public class QuadroCampoMaluco extends Quadro{
	//Vai de 1 a 3(usuario escolherá)
	private int maluquiceDoJogo;
	
	public QuadroCampoMaluco(int largura, int altura, Bloco[][] tabuleiro, int qtd_minas,int maluquiceDoJogo) throws ValorDeAtributoInvalido {
		super(largura, altura, tabuleiro, qtd_minas);
		if(maluquiceDoJogo > 3 || maluquiceDoJogo < 1) throw new ValorDeAtributoInvalido("Erro pois esse nível de maluquice está fora do alcance definido");

		this.maluquiceDoJogo = maluquiceDoJogo;			
		
	}
	
	public int getMaluquiceDoJogo() {
		return maluquiceDoJogo;
	}


	public void marcarBandeira(Bloco bloco, Jogo jogo) throws ValorDeAtributoInvalido {
		if(!bloco.isTemBandeira()) {
			jogo.setQtdBandeiras(jogo.getQtdBandeiras()-1);



			System.out.println("Marcando bandeira no bloco x: " + bloco.getX() + "| y:" + bloco.getY() + " | É campo maluco! | Maluquice: " + this.maluquiceDoJogo);

			Random random = new Random();
			float numeroRandom = random.nextFloat();


			if (numeroRandom > (0.66 / this.maluquiceDoJogo)) {
				System.out.println("O bloco de classe: " + bloco.getClass().getSimpleName() + ", mudou para BlocoMinado | " + numeroRandom / this.maluquiceDoJogo + " (" + numeroRandom + ")");
				bloco = new BlocoMinado(bloco.getX(), bloco.getY());


			} else if (numeroRandom <= (0.66 / this.maluquiceDoJogo)) {
				int bombas = qtdBombasAoRedor(bloco);

				if (bombas > 0) {
					System.out.println("O bloco de classe: " + bloco.getClass().getSimpleName() + ", mudou para BlocoProxAMinas | " + numeroRandom / this.maluquiceDoJogo + " (" + numeroRandom + ")");
					bloco = new BlocoProxAMinas(bloco.getX(), bloco.getY(), bombas);

				} else {
					System.out.println("O bloco de classe: " + bloco.getClass().getSimpleName() + ", mudou para BlocoVazio | " + numeroRandom / this.maluquiceDoJogo + " (" + numeroRandom + ")");
					bloco = new BlocoVazio(bloco.getX(), bloco.getY());

				}

			}

			bloco.marcarBlocoComBandeira();
			atualizarBlobo(bloco);
		}

		else{
			bloco.desmarcarBlocoComBandeira();
			jogo.setQtdBandeiras(jogo.getQtdBandeiras()+1);
		}
	       
	    System.out.println("Classe atual: "+bloco.getClass().getSimpleName());
	    
	 }

	 private void atualizarBlobo(Bloco blocoNovo) throws ValorDeAtributoInvalido {
		int x = blocoNovo.getX();
		int y = blocoNovo.getY();

		//Tabuleiro com bloco antigo
		Bloco [][] tabuleiroAntigo = getTabuleiro();
		tabuleiroAntigo[y][x] = blocoNovo;

		//Atualiza o tabuleiro com o valor novo do bloco
		setTabuleiro(tabuleiroAntigo);
	 }
	
	
	
	 
	
	 
	 
	
	

}
