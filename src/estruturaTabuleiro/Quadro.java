package estruturaTabuleiro;

import estruturaTabuleiro.bloco.Bloco;
import estruturaTabuleiro.bloco.BlocoVazio;
import estruturaTabuleiro.bloco.EstadoBlocos;

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

        //Esse for gera blocos vazios para fins de teste, MUDAR DEPOIS
        for (int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
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

    //Blocos vazios
    public void abrirAoRedor(Bloco blocoCentro){
        int x = blocoCentro.getX();
        int y = blocoCentro.getY();

        //Desse jeito tá rodando o estrutura.bloco central também, talvez se fizer com 2 for fique melhor?
        for(int i = x-1;i <= x+1;i++){
            for (int j = y-1; j<= y+1;j++){
                if (tabuleiro[j][i].getClass().equals(BlocoVazio.class)){
                    tabuleiro[j][i].setEstadoBlocos(EstadoBlocos.EXPOSTO);
                    //System.out.println("Esse estrutura.bloco é: x: "+i+"y: "+j+ "Classe:"+tabuleiro[j][i].getClass().getCanonicalName());

                }
            }
        }
    }
    //Para teste
    public void debugBloco(){
        for (int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                Bloco bloco = tabuleiro[j][i];
                System.out.println("Esse estrutura.bloco é: : "+bloco.getClass().getCanonicalName()+"Coordenada: x: "+i+", y: " +j+ ", estado do bloco é: "+bloco.getEstadoBlocos());
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
