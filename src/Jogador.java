public class Jogador {
    private Integer pontuacao;
    private String nome;

    public Jogador(Integer pontuacao, String nome) {
        this.pontuacao = pontuacao;
        this.nome = nome;
    }

    public void setNome(String nome) {
        if(nome != null){
            this.nome = nome;
        }
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        if (pontuacao >= 0) {
            this.pontuacao = pontuacao;
        }
    }

    public String getNome() {
        return nome;
    }

    //Ver lógica posteriormente
    public void gerarRecorde(){
        //Ver como vou salvar e resgatar os recordes
        //Pegar o recorde e comparar/ordenar para fazer os 10 melhores recordes
        //Ordenar com o stream talvez?
    }

}
