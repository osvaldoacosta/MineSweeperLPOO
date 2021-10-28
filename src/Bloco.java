public class Bloco {

    //Acredito que falte mais coisa
    //Posicao refente a matriz(tabela) do quadro[Não acho que manterei desse jeito
    private int x;
    private int y;
    private Estado estado;
    private TipoDeBloco tipoDeBloco;

    public Bloco(int x, int y, TipoDeBloco tipoDeBloco) {
        this.x = x;
        this.y = y;
        this.estado = Estado.FECHADO;
        this.tipoDeBloco = tipoDeBloco;
    }

    public TipoDeBloco getTipoDeBloco() {
        return tipoDeBloco;
    }

    public void setTipoDeBloco(TipoDeBloco tipoDeBloco) {
        this.tipoDeBloco = tipoDeBloco;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }


    public void onPress(){
        this.estado = Estado.EXPOSTO;
        //Adicionar logica do que acontece depois de pressionar
    }










}
