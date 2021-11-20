import estruturaTabuleiro.Quadro;
import estruturaTabuleiro.bloco.Bloco;

public class MineSweeperGame {

    //teste
    public static void main(String[] args) {
        Bloco tabuleiro [][]  = new  Bloco[10][10];

        Quadro quadro = new Quadro(10,10,tabuleiro,10);

        quadro.gerarTabuleiro();

        quadro.abrirAoRedor(tabuleiro[4][4]);

        quadro.debugBloco();
    }




}
