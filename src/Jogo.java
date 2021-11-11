public class Jogo {
    private Integer tempoSeg;
    private Integer qtdBandeiras;
    private Jogador jogador;
    private Quadro quadro;
    private Dificuldade dificuldade;
    public Jogo(Jogador jogador, Quadro quadro, Dificuldade dificuldade) {
        this.jogador = jogador;
        this.quadro = quadro;
        this.dificuldade = dificuldade;
        this.tempoSeg = 0;
        this.qtdBandeiras = 10;
    }

    //Como vou adicionar hardcoded não precisa de verficação
    public void setTempoSeg(Integer tempoSeg) {
        this.tempoSeg = tempoSeg;
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


        if (dificuldade.equals(Dificuldade.FACIL)){
            //Adicionar um valor para quantidade de bombas
            //quadro = new Quadro(largura,altura,10(Exemplo));
            //quadro.gerarQuadro();
        }
        else if (dificuldade.equals(Dificuldade.MEDIO)){

        }
        else if(dificuldade.equals(Dificuldade.DIFICIL)){

        }
    }


    public void checarFimDeJogo(){
    }

    public void reiniciarJogo(){
        //Resetar valores e gera um novo quadro no final
        //quadro.gerarQuadro();
    }

    //não se se será necessário, ver se vale a pena manter dps
    public void salvaRecordeEVoltaMenu(){
        jogador.gerarRecorde();
        reiniciarJogo();
    }

    /*
    public static void main(String[] args) {
        Jogador novoJogador = new Jogador(0,"");

    }
    */

}
