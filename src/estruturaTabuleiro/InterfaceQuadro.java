package estruturaTabuleiro;

import estruturaJogo.Jogo;
import estruturaTabuleiro.bloco.Bloco;
import exceptions.ValorDeAtributoInvalido;

public interface InterfaceQuadro {

	void gerarTabuleiro();
	void atualizarTabuleiro();
	void marcarBandeira(Bloco bloco, Jogo jogo) throws ValorDeAtributoInvalido;
	void abrirCelulaEAoRedor(Bloco blocoSelecionado) throws ValorDeAtributoInvalido;
	int qtdBombasAoRedor(Bloco bloco);
}
