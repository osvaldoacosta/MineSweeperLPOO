package estruturaTabuleiro.bloco;

import estruturaTabuleiro.bloco.BlocoMinado;
import estruturaTabuleiro.bloco.EstadoBloco;
import exceptions.ValorDeAtributoInvalido;

public abstract class Bloco {

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

    public void setEstadoBloco(EstadoBloco estadoBloco) throws ValorDeAtributoInvalido{
    	//Não acho que precisava, pois é um enum, mas como a tarefa manda...
        if(estadoBloco.equals(EstadoBloco.EXPOSTO) || estadoBloco.equals(EstadoBloco.FECHADO))
        	this.estadoBloco = estadoBloco;
        else 
    		throw new ValorDeAtributoInvalido("Erro pois foi passado algum valor que não faz parte desse enumeration");

    }

    public boolean isTemBandeira() {
        return temBandeira;
    }

    public void setTemBandeira(boolean temBandeira) throws ValorDeAtributoInvalido {
    	//Também não acho que será necessário
    	if(temBandeira == true || temBandeira == false) 
    		this.temBandeira = temBandeira;
    	else
    		throw new ValorDeAtributoInvalido("Erro pois foi passado algum valor inválido para o atributo temBandeira");

        
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //Consertando as funções desse método
    public void abrirBloco(){
        if (estadoBloco.equals(EstadoBloco.FECHADO) && this.temBandeira == false){
            this.estadoBloco = EstadoBloco.EXPOSTO;
        }
    }

    //Principal a ser usado em outras classes
    public void marcarBlocoComBandeira(){
        this.temBandeira = true;
    }

    public void desmarcarBlocoComBandeira(){
        this.temBandeira = false;
    }










}
