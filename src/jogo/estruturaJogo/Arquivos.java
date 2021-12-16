package jogo.estruturaJogo;

import java.io.File;

public enum Arquivos {
    SAVEGAME,RANKING;

    private String endereco;

    static{
        SAVEGAME.endereco = SAVEGAME +".txt";
        RANKING.endereco = RANKING +".txt";
    }

    public String getEndereco(){
        return endereco;
    }

    public void apagarArquivo(){
        new File(endereco).delete();
    }

}
