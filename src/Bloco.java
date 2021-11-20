public class Bloco {

    //Posicao refente a matriz(tabela) do quadro
    private int x;
    private int y;

    private EstadoBlocos estadoBlocos;
    private boolean temBandeira;

    public Bloco(int x, int y) {
        this.x = x;
        this.y = y;
        this.estadoBlocos = EstadoBlocos.FECHADO;
        this.temBandeira = false;
    }

    public EstadoBlocos getEstadoBlocos() {
        return estadoBlocos;
    }

    public void setEstadoBlocos(EstadoBlocos estadoBlocos) {
        this.estadoBlocos = estadoBlocos;
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
        if (estadoBlocos.equals(EstadoBlocos.FECHADO)){
            this.estadoBlocos = EstadoBlocos.EXPOSTO;
        }
    }
    //Principal a ser usado em outras classes
    public void marcarComBandeira(){
        if (!this.temBandeira){
            marcarBloco();
        }
    }











}
