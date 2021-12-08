package estruturaTabuleiro;


import estruturaJogo.Jogo;
import estruturaTabuleiro.bloco.Bloco;
import estruturaTabuleiro.bloco.EstadoBloco;
import estruturaTabuleiro.bloco.*;

import exceptions.ValorDeAtributoInvalido;

import java.util.Random;

public class Quadro implements InterfaceQuadro{
    private final int LARGURA_ESPACO;
    private final int ALTURA_ESPACO;
    private final int QTD_MINAS;
    private Bloco[][] tabuleiro;

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

    public void setTabuleiro(Bloco[][] tabuleiro) throws ValorDeAtributoInvalido {
        if(tabuleiro == null)
            throw new ValorDeAtributoInvalido("Erro pois esse tabuleiro é nulo");
        else
            this.tabuleiro = tabuleiro;
    }

    //TODO: ARRUMAR OS METODOS DE GERAR TABULEIRO, GERAR MINAS, E ATUALIZAR O TABULEIRO.
    
    @Override
    public void gerarTabuleiro(){
        gerarTabuleiroVazio();
        gerarMinas();
        gerarBlocoProxAMinas();
    }

    //Gera um bloco prox a minas se um bloco vazio estiver perto de uma mina
    private void gerarBlocoProxAMinas(){
        for (int i = 0; i < LARGURA_ESPACO; i++){
            for(int j = 0; j < ALTURA_ESPACO; j++){
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
        for (int i = 0; i < LARGURA_ESPACO; i++){
            for(int j = 0; j < ALTURA_ESPACO; j++){
                tabuleiro[j][i] = new BlocoVazio(i,j);
            }
        }
    }

    //Gera minas para o quadro
    private void gerarMinas(){
        //Distribuir a qtd de bombas aleatoriamente nesses blocos
        System.out.println("Gerando minas:\nX | Y");
        for(int i = 0;i<this.QTD_MINAS;i++){
            Random random = new Random();
            int x = random.nextInt( LARGURA_ESPACO);
            int y = random.nextInt( ALTURA_ESPACO);
            System.out.println(x+" | "+y);

            if(tabuleiro[y][x] instanceof BlocoMinado) i--;
            tabuleiro[y][x] = new BlocoMinado(x,y);
        }
    }
   
    @Override
    public void atualizarTabuleiro(){
        //Substitui todos os blocos com blocos novos, deve-se fazer isso após o fim de jogo.
        gerarTabuleiro();
    }
    
    //Marca ou desmarca um bloco com bandeira
    @Override
    public void marcarBandeira(Bloco bloco, Jogo jogo) throws ValorDeAtributoInvalido {
	    System.out.println("Bloco x: "+bloco.getX()+"| y:"+bloco.getY()+" | Marcado com bandeira: "+bloco.isTemBandeira()+" | É campo normal!");
        if (!bloco.isTemBandeira()){
            bloco.marcarBlocoComBandeira();
            jogo.setQtdBandeiras(jogo.getQtdBandeiras()-1);
        }
        else{
            bloco.desmarcarBlocoComBandeira();
            jogo.setQtdBandeiras(jogo.getQtdBandeiras()+1);
        }

    }





    //Metodo abre independente de ter bandeira ou não
    //Abre sucessivamente as células
    @Override
    public void abrirCelulaEAoRedor(Bloco blocoSelecionado){
        int xInicial = (blocoSelecionado.getX() - 1 < 0) ? blocoSelecionado.getX() : blocoSelecionado.getX()-1;
        int yInicial = (blocoSelecionado.getY() - 1 < 0) ? blocoSelecionado.getY() : blocoSelecionado.getY()-1;
        int fimX = (blocoSelecionado.getX() + 1 > LARGURA_ESPACO-1) ? blocoSelecionado.getX() : blocoSelecionado.getX()+1;
        int fimY = (blocoSelecionado.getY() + 1 > ALTURA_ESPACO-1) ? blocoSelecionado.getY() : blocoSelecionado.getY()+1;
        System.out.println("x: " + blocoSelecionado.getX() + " | y: " + blocoSelecionado.getY());

        if(!blocoSelecionado.isTemBandeira()){
            blocoSelecionado.abrirBloco();

            if((blocoSelecionado instanceof BlocoVazio)) {
                for (int i = xInicial; i <= fimX; i++) {
                    for (int j = yInicial; j <= fimY; j++) {
                        Bloco blocoAdjacente = tabuleiro[j][i];
                        if (!blocoAdjacente.equals(blocoSelecionado) && blocoAdjacente.getEstadoBloco().equals(EstadoBloco.FECHADO) && !(blocoAdjacente instanceof  BlocoMinado)) {
                            abrirCelulaEAoRedor(blocoAdjacente);
                        }
                    }
                }
            }
        }





    }

    //Para teste TODO:Remover depois
    public void debugBloco(){
        for (int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                Bloco bloco = tabuleiro[j][i];

                System.out.println("A estrutura do bloco é: "+bloco.getClass().getCanonicalName()+" | Coordenada: x: "+i+", y: " +j+ " | Estado do bloco: : "+bloco.getEstadoBloco());
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
        int fimX = (blocoSelecionado.getX() + 1 > LARGURA_ESPACO-1) ? blocoSelecionado.getX() : blocoSelecionado.getX()+1;
        int fimY = (blocoSelecionado.getY() + 1 > ALTURA_ESPACO-1) ? blocoSelecionado.getY() : blocoSelecionado.getY()+1;


        for (int i = xInicial; i <= fimX; i++) {
            for (int j = yInicial; j <= fimY ; j++) {
                Bloco blocoAoRedor = tabuleiro[j][i];
                if((blocoAoRedor instanceof BlocoMinado) && !blocoAoRedor.equals(blocoSelecionado)) qtdBombas++;
            }
        }
        return qtdBombas;
    }



    
}
