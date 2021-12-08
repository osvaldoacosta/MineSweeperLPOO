package estruturaJogo;

import estruturaTabuleiro.Quadro;
import estruturaTabuleiro.QuadroCampoMaluco;
import estruturaTabuleiro.bloco.Bloco;
import estruturaTabuleiro.bloco.BlocoMinado;
import estruturaTabuleiro.bloco.BlocoVazio;
import estruturaTabuleiro.bloco.EstadoBloco;
import exceptions.ValorDeAtributoInvalido;

public class Jogo {
    private Integer tempoSeg;
    private Integer qtdBandeiras;
    private Jogador jogador;
    private Quadro quadro;
    private Dificuldade dificuldade;

    public Jogo(Jogador jogador, Quadro quadro, Dificuldade dificuldade) throws ValorDeAtributoInvalido {
        
    	this.jogador = jogador;
        this.quadro = quadro;
        this.dificuldade = dificuldade;
        this.tempoSeg = 0;
        this.qtdBandeiras = 10;
    }

    //Como vou adicionar hardcoded não precisa de verficação
    public void setTempoSeg(Integer tempoSeg) throws ValorDeAtributoInvalido {
    	if(tempoSeg <= 0) 
    		throw new ValorDeAtributoInvalido("Erro pois foi passado um valor negativo (ou nulo) para o tempo");
    	else this.tempoSeg = tempoSeg;
    }



    public void setQtdBandeiras(Integer qtdBandeiras) throws ValorDeAtributoInvalido {
    	if(qtdBandeiras < 0)
    		throw new ValorDeAtributoInvalido("Erro pois foi passado um valor negativo para a quantidade de bandeiras");
    	this.qtdBandeiras = qtdBandeiras;
    }

    public Integer getQtdBandeiras() {
		return qtdBandeiras;
	}


	public Integer getTempoSeg() {
        return tempoSeg;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public Quadro getQuadro() {
        return quadro;
    }

    public Dificuldade getDificuldade() {
        return dificuldade;
    }


    //N sei se a lógica está boa, melhorar no futuro
    public void novoJogo(){
        //Pedir uma altura e largura ou setar hardcoded mesmo(melhor não)
        //int altura;
        //int largura;

        /*
        switch (dificuldade){
            case FACIL:
        }
        */

    	//TODO: substituir por switch
        if (dificuldade.equals(Dificuldade.FACIL)){
            //Adicionar um valor para quantidade de bombas
            //quadro = new estrutura.Quadro(largura,altura,10(Exemplo));
            //quadro.gerarQuadro();
        }
        else if (dificuldade.equals(Dificuldade.MEDIO)){

        }
        else if(dificuldade.equals(Dificuldade.DIFICIL)){

        }
    }


    public void terminarOJogo(){
        System.out.println("FIM DE JOGO!");
        reiniciarJogo();
        salvaRecorde();

    }

    public void comecarOJogo(){
        System.out.println("Iniciando o jogo");
        
    }




    public boolean isFimDeJogo(Bloco bloco){
        if(bloco instanceof BlocoMinado && bloco.getEstadoBloco().equals(EstadoBloco.EXPOSTO)){
            System.out.println("Infelizmente você perdeu =(");
            terminarOJogo();
            return true;
        }
        else if(isJogoGanho()){
            System.out.println("Parabéns você ganhou =)");
            terminarOJogo();
            return true;
        }

        return false;

    }

    private boolean isJogoGanho(){
        int qtdDeBlocosNaoBombaQuadro = (quadro.getALTURA_ESPACO() * quadro.getLARGURA_ESPACO()) - quadro.getQTD_MINAS();
        int qtdDeBlocosNaoBombaExpostos = 0;
        for (int i = 0; i < quadro.getLARGURA_ESPACO(); i++){
            for(int j = 0; j < quadro.getALTURA_ESPACO(); j++){
                Bloco bloco = quadro.getTabuleiro()[j][i];
                if(bloco.getEstadoBloco().equals(EstadoBloco.EXPOSTO) && !(bloco instanceof BlocoMinado))
                    qtdDeBlocosNaoBombaExpostos++;
            }
        }

        return (qtdDeBlocosNaoBombaExpostos == qtdDeBlocosNaoBombaQuadro) ? true : false;

    }

    public void reiniciarJogo(){
        //Resetar valores e gera um novo quadro no final
        //quadro.gerarQuadro();

    }

    //não se se será necessário, ver se vale a pena manter dps
    public void salvaRecorde(){
        jogador.gerarRecorde();
    }


    public void calcularPontuacao() throws ValorDeAtributoInvalido {
        int pontuacao = 0;
        for (int i = 0; i < quadro.getLARGURA_ESPACO(); i++){
            for(int j = 0; j < quadro.getALTURA_ESPACO(); j++){
                Bloco bloco = quadro.getTabuleiro()[j][i];
                if(bloco.getEstadoBloco().equals(EstadoBloco.EXPOSTO) && !(bloco instanceof BlocoMinado))
                    pontuacao += 10;
            }
        }
        jogador.setPontuacao(pontuacao);
    }

}
