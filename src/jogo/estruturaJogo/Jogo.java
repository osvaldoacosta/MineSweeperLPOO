package jogo.estruturaJogo;

import jogo.estruturaTabuleiro.quadro.tipos.Quadro;
import jogo.estruturaTabuleiro.bloco.Bloco;
import jogo.estruturaTabuleiro.bloco.tipos.BlocoMinado;
import jogo.estruturaTabuleiro.bloco.EstadoBloco;
import exceptions.ValorDeAtributoInvalido;

import java.io.*;


public class Jogo implements Serializable{

    private Integer qtdBandeiras;
    private Jogador jogador;
    private Quadro quadro;
    private Dificuldade dificuldade;

    public Jogo(Jogador jogador, Quadro quadro, Dificuldade dificuldade){
        
    	this.jogador = jogador;
        this.quadro = quadro;
        this.dificuldade = dificuldade;
        this.qtdBandeiras = 10;
    }

    //Construtor vazio
    public Jogo() {
    }

    //Como vou adicionar hardcoded não precisa de verficação




    public void setQtdBandeiras(Integer qtdBandeiras) throws ValorDeAtributoInvalido {
    	if(qtdBandeiras < 0)
    		throw new ValorDeAtributoInvalido("Erro pois foi passado um valor negativo para a quantidade de bandeiras");
    	this.qtdBandeiras = qtdBandeiras;
    }

    public Integer getQtdBandeiras() {
		return qtdBandeiras;
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


    private boolean isJogoGanho(){
        int qtdDeBlocosNaoBombaQuadro = (quadro.getQTD_BLOCOS_ALTURA() * quadro.getQTD_BLOCOS_LARGURA()) - quadro.getQTD_MINAS();
        int qtdDeBlocosNaoBombaExpostos = 0;
        for (int i = 0; i < quadro.getQTD_BLOCOS_LARGURA(); i++){
            for(int j = 0; j < quadro.getQTD_BLOCOS_ALTURA(); j++){
                Bloco bloco = quadro.getTabuleiro()[j][i];
                if(bloco.getEstadoBloco().equals(EstadoBloco.EXPOSTO) && !(bloco instanceof BlocoMinado))
                    qtdDeBlocosNaoBombaExpostos++;
            }
        }

        return (qtdDeBlocosNaoBombaExpostos == qtdDeBlocosNaoBombaQuadro) ? true : false;

    }

    //METODOS QUE USAM SYSOUT
    public void finalizarJogo(){
        System.out.println("FIM DE JOGO!");
        Arquivos.SAVEGAME.apagarArquivo();
    }

    //Metodo retorna uma array de boolean contendo se é fim de jogo [0] e se ganhou o jogo[1]
    public boolean[] isFimDeJogo(Bloco bloco) throws ValorDeAtributoInvalido {
        boolean[] arrayFimDeJogo = new boolean[2];

        if(bloco instanceof BlocoMinado && !bloco.isFechado()){
            //Perdeu
            arrayFimDeJogo[1] = false;
        }
        else if(isJogoGanho()){
            //ganhou
            arrayFimDeJogo[1] = true;
        }
        else {
            //Não terminou
            arrayFimDeJogo[0] = false;
            arrayFimDeJogo[1] = false;
            return arrayFimDeJogo;
        }
        arrayFimDeJogo[0] = true;
        finalizarJogo();
        return arrayFimDeJogo;



    }



    //Sistemas de arquivos -----------------------------------------
    public void salvarJogo(){
        try (FileOutputStream fos = new FileOutputStream(Arquivos.SAVEGAME.getEndereco());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            Jogo jogo = this;
            oos.writeObject(jogo);
            System.out.println("Jogo Salvo");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public Jogo resgatarJogo(){
        try (FileInputStream fis = new FileInputStream(Arquivos.SAVEGAME.getEndereco());
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Jogo jogo = (Jogo) ois.readObject();
            return jogo;
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            System.err.println("Save do jogo não encontrado!");
        }

        return null;
    }
    //-------------------------------------------------------------------





}
