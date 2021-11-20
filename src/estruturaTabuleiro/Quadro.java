package estruturaTabuleiro;

import estruturaTabuleiro.bloco.Bloco;
import estruturaTabuleiro.bloco.BlocoMinado;
import estruturaTabuleiro.bloco.BlocoVazio;
import estruturaTabuleiro.bloco.EstadoBloco;

import java.util.ArrayList;

public class Quadro {
    private final int LARGURA_ESPACO;
    private final int ALTURA_ESPACO;
    private final int QTD_MINAS;
    private Bloco [][] tabuleiro;

    public Quadro(int largura, int altura, Bloco[][] tabuleiro, int qtd_minas) {
        LARGURA_ESPACO = largura;
        ALTURA_ESPACO = altura;
        this.tabuleiro = tabuleiro;
        QTD_MINAS = qtd_minas;
    }

    public int getLARGURA_ESPACO() {
        return LARGURA_ESPACO;
    }

    public int getALTURA_ESPACO() {
        return ALTURA_ESPACO;
    }

    public int getQTD_MINAS() {
        return QTD_MINAS;
    }

    public Bloco[][] getTabuleiro() {
        return tabuleiro;
    }


    public void gerarTabuleiro(){
        //Desenhar um quadro console (posteriormente em uma interface gráfica).
        //Gerar blocos com o estado de fechado baseados na altura e largura

        //Esse for gera blocos vazios e com minas hard codado para fins de teste, TODO: MUDAR DEPOIS
        for (int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){

                if (i == 7){
                    tabuleiro[j][i] = new BlocoMinado(i,j);
                    tabuleiro[4][i-5] = new BlocoMinado(i-5,4);
                }
                else
                tabuleiro[j][i] = new BlocoVazio(i,j);

            }
        }
        //gerarMinas();

    }


    public void gerarMinas(){
        //Distribuir a qtd de bombas aleatoriamente nesses blocos

    }

    public void atualizarTabuleiro(){
        
    }

    //Não acho que será necessário depois, TODO: REMOVER CASO NÂO SEJA MAIS ÙTIL
    private ArrayList blocosLimitesDoTabuleiro(){
        ArrayList<Bloco> blocosLimites = new ArrayList<Bloco>();

        for (int i = 0; i < tabuleiro[0].length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {

                if (i == 0)
                    blocosLimites.add(tabuleiro[j][i]);
                else if (i ==  tabuleiro[0].length - 1)
                    blocosLimites.add(tabuleiro[j][i]);
                else if (j == 0)
                    blocosLimites.add(tabuleiro[j][i]);
                else if (j == tabuleiro.length - 1)
                    blocosLimites.add(tabuleiro[j][i]);
            }
        }
        return blocosLimites;
    }



    //Isso demandou bastante tempo por algum motivo ._.
    //Blocos vazios apenas
    public void abrirAoRedor(Bloco blocoSelecionado){
        int xInicial = (blocoSelecionado.getX() - 1 < 0) ? blocoSelecionado.getX() : blocoSelecionado.getX()-1;
        int yInicial = (blocoSelecionado.getY() - 1 < 0) ? blocoSelecionado.getY() : blocoSelecionado.getY()-1;
        int fimX = (blocoSelecionado.getX() + 1 > tabuleiro[0].length-1) ? blocoSelecionado.getX() : blocoSelecionado.getX()+1;
        int fimY = (blocoSelecionado.getY() + 1 > tabuleiro.length-1) ? blocoSelecionado.getY() : blocoSelecionado.getY()+1;

        //Só para observação
        System.out.println("x: " + blocoSelecionado.getX() + " | y: " + blocoSelecionado.getY());
        
        blocoSelecionado.setEstadoBloco(EstadoBloco.EXPOSTO);


        for (int i = xInicial; i <= fimX; i++) {
            for (int j = yInicial; j <= fimY ; j++) {
                Bloco blocoAdjacente = tabuleiro[j][i];



                if (blocoAdjacente.getClass().equals(BlocoVazio.class) && !blocoAdjacente.equals(blocoSelecionado) && blocoAdjacente.getEstadoBloco().equals(EstadoBloco.FECHADO) ) {
                    //System.out.println("X - "+x+"  |  Y - "+y);

                    abrirAoRedor(blocoAdjacente);

                }

            }
        }


    }

    //Para teste
    public void debugBloco(){
        for (int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                Bloco bloco = tabuleiro[j][i];
                if (bloco.getEstadoBloco().equals(EstadoBloco.FECHADO))
                System.out.println("A estrutura do bloco é: "+bloco.getClass().getCanonicalName()+" | Coordenada: x: "+i+", y: " +j+ " | Estado do bloco: : "+bloco.getEstadoBloco());
            }
        }
    }

    //base.Bloco proximo a bombas
    public int qtdBombasAoRedor(){
        int qtdBombas = 0;
        //Com as coordenadas ver quais são as bombas ao redor

        return qtdBombas;
    }

    public void abrirCelula(){

    }


}
