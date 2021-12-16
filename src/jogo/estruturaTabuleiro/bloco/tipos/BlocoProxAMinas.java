package jogo.estruturaTabuleiro.bloco.tipos;

import jogo.estruturaTabuleiro.bloco.Bloco;

public class BlocoProxAMinas extends Bloco {
    private int minasAoRedor;
    public BlocoProxAMinas(int x, int y,int minasAoRedor) {
        super(x, y);
        this.minasAoRedor = minasAoRedor;
    }

    public int getMinasAoRedor() {
        return minasAoRedor;
    }

    public void setMinasAoRedor(int minasAoRedor) {
        this.minasAoRedor = minasAoRedor;
    }
}
