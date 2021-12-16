package jogo.estruturaTabuleiro.quadro.tipos;


import jogo.estruturaJogo.Jogo;
import jogo.estruturaTabuleiro.bloco.Bloco;
import jogo.estruturaTabuleiro.bloco.EstadoBloco;

import exceptions.ValorDeAtributoInvalido;
import jogo.estruturaTabuleiro.bloco.tipos.BlocoMinado;
import jogo.estruturaTabuleiro.bloco.tipos.BlocoProxAMinas;
import jogo.estruturaTabuleiro.bloco.tipos.BlocoVazio;
import jogo.estruturaTabuleiro.quadro.InterfaceQuadro;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class Quadro extends JPanel implements InterfaceQuadro, Serializable {

    //Quantidade de blocos na largura e altura
    private final int QTD_BLOCOS_LARGURA;
    private final int QTD_BLOCOS_ALTURA;

    private final int QTD_MINAS;

    private Bloco[][] tabuleiro;

    public Quadro(int largura, int altura, Bloco[][] tabuleiro, int qtd_minas) {
        QTD_BLOCOS_LARGURA = largura;
        QTD_BLOCOS_ALTURA = altura;
        this.tabuleiro = tabuleiro;
        QTD_MINAS = qtd_minas;
    }

    public int getQTD_BLOCOS_LARGURA() {
        return QTD_BLOCOS_LARGURA;
    }

    public int getQTD_BLOCOS_ALTURA() {
        return QTD_BLOCOS_ALTURA;
    }

    public int getQTD_MINAS() {
        return QTD_MINAS;
    }

    public Bloco[][] getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Bloco[][] tabuleiro) throws ValorDeAtributoInvalido {
        if(tabuleiro == null)
            throw new ValorDeAtributoInvalido("Erro pois esse tabuleiro é nulo");
        else
            this.tabuleiro = tabuleiro;
    }


    @Override
    public void gerarTabuleiro(){
        gerarTabuleiroVazio();
        gerarMinas();
        gerarBlocoProxAMinas();
    }

    //Gera um bloco prox a minas se um bloco vazio estiver perto de uma mina
    private void gerarBlocoProxAMinas(){
        for (int i = 0; i < QTD_BLOCOS_LARGURA; i++){
            for(int j = 0; j < QTD_BLOCOS_ALTURA; j++){
                if (tabuleiro[j][i] instanceof BlocoVazio){
                    int qtdBombas = qtdBombasAoRedor(tabuleiro[j][i]);
                    if(qtdBombas>0)
                        tabuleiro[j][i] = new BlocoProxAMinas(i,j,qtdBombas);
                }
            }
        }
    }

    //Gera um tabuleiro vazio para ser preenchido com minas
    private void gerarTabuleiroVazio(){
        for (int i = 0; i < QTD_BLOCOS_LARGURA; i++){
            for(int j = 0; j < QTD_BLOCOS_ALTURA; j++){
                tabuleiro[j][i] = new BlocoVazio(i,j);
            }
        }
    }

    //Gera minas para o quadro
    private void gerarMinas(){
        //Distribuir a qtd de bombas aleatoriamente nesses blocos
        System.out.println("Gerando minas:\nX  |  Y");

        for(int i = 0;i<this.QTD_MINAS;i++){
            Random random = new Random();
            int x = random.nextInt(QTD_BLOCOS_LARGURA);
            int y = random.nextInt(QTD_BLOCOS_ALTURA);
            System.out.println(x+"  |  "+y);

            if(tabuleiro[y][x] instanceof BlocoMinado) i--;
            tabuleiro[y][x] = new BlocoMinado(x,y);
        }
    }
   

    //Marca ou desmarca um bloco com bandeira
    @Override
    public void marcarBandeira(Bloco bloco, Jogo jogo) throws ValorDeAtributoInvalido {
	    System.out.println("Bloco x: "+bloco.getX()+"| y: "+bloco.getY()+" | Marcado com bandeira: "+bloco.isTemBandeira()+" | É campo normal!");
        if (!bloco.isTemBandeira()){
            bloco.marcarBlocoComBandeira();
            jogo.setQtdBandeiras(jogo.getQtdBandeiras()-1);
        }
        else{
            bloco.desmarcarBlocoComBandeira();
            jogo.setQtdBandeiras(jogo.getQtdBandeiras()+1);
        }

    }

    //Abre a célula e sucessivamente, abre as ao redor
    @Override
    public void abreCelulaSucessivamente(Bloco blocoSelecionado){
        int xInicial = (blocoSelecionado.getX() - 1 < 0) ? blocoSelecionado.getX() : blocoSelecionado.getX()-1;
        int yInicial = (blocoSelecionado.getY() - 1 < 0) ? blocoSelecionado.getY() : blocoSelecionado.getY()-1;
        int fimX = (blocoSelecionado.getX() + 1 > QTD_BLOCOS_LARGURA -1) ? blocoSelecionado.getX() : blocoSelecionado.getX()+1;
        int fimY = (blocoSelecionado.getY() + 1 > QTD_BLOCOS_ALTURA -1) ? blocoSelecionado.getY() : blocoSelecionado.getY()+1;



        if(!blocoSelecionado.isTemBandeira()){
            System.out.println(blocoSelecionado.getX() + " | " + blocoSelecionado.getY());
            blocoSelecionado.abrirBloco();

            if((blocoSelecionado instanceof BlocoVazio)) {
                for (int i = xInicial; i <= fimX; i++) {
                    for (int j = yInicial; j <= fimY; j++) {
                        Bloco blocoAdjacente = tabuleiro[j][i];
                        if (!blocoAdjacente.equals(blocoSelecionado) && blocoAdjacente.getEstadoBloco().equals(EstadoBloco.FECHADO) && !(blocoAdjacente instanceof  BlocoMinado)) {
                            abreCelulaSucessivamente(blocoAdjacente);
                        }
                    }
                }
            }
        }
    }

    //Bloco proximo a bombas
    //Calcula quantas bombas estão ao redor do bloco
    @Override
    public int qtdBombasAoRedor(Bloco blocoSelecionado){
        int qtdBombas = 0;
        int xInicial = (blocoSelecionado.getX() - 1 < 0) ? blocoSelecionado.getX() : blocoSelecionado.getX()-1;
        int yInicial = (blocoSelecionado.getY() - 1 < 0) ? blocoSelecionado.getY() : blocoSelecionado.getY()-1;
        int fimX = (blocoSelecionado.getX() + 1 > QTD_BLOCOS_LARGURA -1) ? blocoSelecionado.getX() : blocoSelecionado.getX()+1;
        int fimY = (blocoSelecionado.getY() + 1 > QTD_BLOCOS_ALTURA -1) ? blocoSelecionado.getY() : blocoSelecionado.getY()+1;

        for (int i = xInicial; i <= fimX; i++) {
            for (int j = yInicial; j <= fimY ; j++) {
                Bloco blocoAoRedor = tabuleiro[j][i];
                if((blocoAoRedor instanceof BlocoMinado) && !blocoAoRedor.equals(blocoSelecionado)) qtdBombas++;
            }
        }
        return qtdBombas;
    }
    //Jpanel---------------------------------------------------------

    private Color[] colors = {
            Color.BLUE,
            Color.CYAN.darker(),
            Color.GREEN.darker(),
            Color.YELLOW.darker(),
            Color.ORANGE.darker(),
            Color.PINK.darker(),
            Color.MAGENTA,
            Color.RED
    };
    public Color[] getColors() {
        return colors;
    }








    
}
