package estruturaJogo;

import javax.management.InvalidAttributeValueException;

import exceptions.ValorDeAtributoInvalido;

public class Jogador {
    private Integer pontuacao;
    private String nome;

    public Jogador() {
        this.pontuacao = 0;
        this.nome = "";
    }

    public void setNome(String nome) throws ValorDeAtributoInvalido {
		if(nome == null || nome == "")
			throw new ValorDeAtributoInvalido("Erro pois valor para nome é inválido");
		else
			this.nome = nome;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) throws ValorDeAtributoInvalido{
        if (pontuacao < 0)
        	throw new ValorDeAtributoInvalido("Erro pois foi passado um valor negativo para pontuação");
        
        else
        	this.pontuacao = pontuacao;
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
