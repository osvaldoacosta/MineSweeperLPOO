package estruturaTabuleiro.bloco;

public class Bloco {

    //Posicao refente a matriz(tabela) do quadro
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

    public void setEstadoBloco(EstadoBloco estadoBloco) {
        this.estadoBloco = estadoBloco;
    }

    public boolean isTemBandeira() {
        return temBandeira;
    }

    public void setTemBandeira(boolean temBandeira) {
        this.temBandeira = temBandeira;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //Método só será usado nessa classe
    private void marcarBloco(){
        if (estadoBloco.equals(EstadoBloco.FECHADO)){
            this.estadoBloco = EstadoBloco.EXPOSTO;
        }
    }

    //Principal a ser usado em outras classes
    public void marcarComBandeira(){
        if (!this.temBandeira){
            marcarBloco();
        }
    }











}
