package jogo.estruturaTabuleiro.bloco;

import jogo.estruturaTabuleiro.bloco.tipos.BlocoMinado;

import java.io.Serializable;

public abstract class Bloco implements Serializable {


    //Posicao refente a matriz(tabuleiro) do quadro
    private int x;
    private int y;
    private EstadoBloco estadoBloco;
    private boolean temBandeira;


    public Bloco(int x, int y) {
        this.x = x;
        this.y = y;
        this.estadoBloco = EstadoBloco.FECHADO;
        this.temBandeira = false;
    }

    public EstadoBloco getEstadoBloco() {
        return estadoBloco;
    }

    public boolean isTemBandeira() {
        return temBandeira;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void marcarBlocoComBandeira(){
        this.temBandeira = true;
    }

    public void desmarcarBlocoComBandeira(){
        this.temBandeira = false;
    }

    public void abrirBloco(){
        if (estadoBloco.equals(EstadoBloco.FECHADO) && temBandeira == false){
            this.estadoBloco = EstadoBloco.EXPOSTO;
        }
    }

    public boolean isFechado(){
        return this.getEstadoBloco().equals(EstadoBloco.FECHADO) ? true : false;
    }













}
