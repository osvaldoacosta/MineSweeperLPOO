public class Quadro {
    private final int QTD_BLOCOS_LARGURA;
    private final int QTD_BLOCOS_ALTURA;
    private final int QTD_MINAS;

    public Quadro(int largura, int altura , int qtd_minas) {
        QTD_BLOCOS_LARGURA = largura;
        QTD_BLOCOS_ALTURA = altura;
        QTD_MINAS = qtd_minas;
    }

    public int getQTD_BLOCOS_LARGURA() {
        return QTD_BLOCOS_LARGURA;
    }

    public int getQTD_BLOCOS_ALTURA() {
        return QTD_BLOCOS_ALTURA;
    }

    public int getQTD_MINAS() {
        return QTD_MINAS;
    }

    public void gerarQuadro(){
        //Desenhar um quadro console (posteriormente em uma interface gráfica).
        //Gerar blocos com o estado de fechado baseados na altura e largura
        gerarMinas();

    }

    public void gerarMinas(){
        //Distribuir a qtd de bombas aleatoriamente nesses blocos

    }

    public void atualizarQuadro(){
        
    }

    //Blocos vazios
    public void verificarBlocosVazios(){
        //Ver se os blocos ao redor forem vazios, se sim deixa-los com o estado de exposto;

    }

    //Bloco proximo a bombas
    public int qtdBombasAoRedor(){
        int qtdBombas = 0;
        //Com as coordenadas ver quais são as bombas ao redor

        return qtdBombas;
    }
    
}
