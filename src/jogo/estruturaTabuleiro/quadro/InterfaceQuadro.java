package jogo.estruturaTabuleiro.quadro;

import jogo.estruturaJogo.Jogo;
import jogo.estruturaTabuleiro.bloco.Bloco;
import exceptions.ValorDeAtributoInvalido;

public interface InterfaceQuadro {

	void gerarTabuleiro();
	void marcarBandeira(Bloco bloco, Jogo jogo) throws ValorDeAtributoInvalido;
	void abreCelulaSucessivamente(Bloco blocoSelecionado) throws ValorDeAtributoInvalido;
	int qtdBombasAoRedor(Bloco bloco);
}
