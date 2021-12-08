import estruturaJogo.Dificuldade;
import estruturaJogo.Jogador;
import estruturaJogo.Jogo;
import estruturaTabuleiro.Quadro;
import estruturaTabuleiro.QuadroCampoMaluco;
import estruturaTabuleiro.bloco.*;
import exceptions.ValorDeAtributoInvalido;
import java.util.Scanner;

public class MineSweeperGame {




    public Quadro criarQuadroNormal( int largura,int altura, int qtdMinas)
    {
        return new Quadro(largura,altura,new Bloco[altura][largura], qtdMinas);
    }

    public Quadro criarQuadroMaluco(int largura,int altura, int qtdMinas, int maluquice) throws ValorDeAtributoInvalido {
        return new QuadroCampoMaluco(largura,altura,new Bloco[altura][largura], qtdMinas, maluquice);
    }




    public void printarQuadro(Quadro quadro){
        Scanner scanner = new Scanner(System.in);



        for (int i = 0; i < quadro.getALTURA_ESPACO(); i++) {
            System.out.printf(i+"- | "); //Fru fru para printar as coordenadas do y do quadro

            for (int j = 0; j < quadro.getLARGURA_ESPACO();j++) {
                Bloco bloco = quadro.getTabuleiro()[i][j];

                if(bloco.isTemBandeira()) System.out.printf("B");

                else if(bloco.getEstadoBloco().equals(EstadoBloco.EXPOSTO)){
                    if(bloco instanceof BlocoVazio) System.out.printf("V");
                    else if(bloco instanceof BlocoMinado) System.out.printf("M");
                    else if(bloco instanceof BlocoProxAMinas) System.out.printf(String.valueOf(((BlocoProxAMinas) bloco).getMinasAoRedor()));
                }
                else System.out.printf("?");


                System.out.printf(" | ");
            }
            System.out.printf("\n");

        }
    }


    private void printarPontuacao(Jogador jogador){
        System.out.println("Pontuacao: "+jogador.getPontuacao());
    }
    //Vai ser usado no fim do jogo
    private void printarNome(Jogador jogador){
        System.out.println("Nome: "+jogador.getNome());
    }
    private void printarQtdBandeiras(Jogo jogo){
        System.out.println("Bandeiras: "+jogo.getQtdBandeiras());
    }
    //Não sei se vou usar
    private void printarTempo(Jogo jogo){
        System.out.println("Tempo: "+jogo.getTempoSeg());
    }


    public void printarJogo(Jogo jogo){
        printarQuadro(jogo.getQuadro());
        printarPontuacao(jogo.getJogador());
        printarQtdBandeiras(jogo);
    }

    public Quadro escolherQuadro() throws ValorDeAtributoInvalido {
        Scanner scanner = new Scanner(System.in);
        Quadro quadro;
        int escolhaTipoDeQuadro;
        System.out.println("Escolha um modo de jogo\n1)Campo Minado\n2)Campo Minado Maluco");
        escolhaTipoDeQuadro = scanner.nextInt();

        switch (escolhaTipoDeQuadro){
            case 1:
                quadro = criarQuadroNormal(10,10,10);
                break;
            case 2:
                quadro = criarQuadroMaluco(10,10,10,2);

                break;
            default:
                throw new IllegalStateException("Ocorreu algum erro na escolha do tipo de jogo, valor selecionado: " + escolhaTipoDeQuadro);
        }
        System.out.println("O tipo de campo selecionado foi o: "+quadro.getClass().getSimpleName());
        return quadro;
    }

    public void selecionarBloco(Jogo jogo,int x,int y) throws ValorDeAtributoInvalido {
        Bloco bloco = jogo.getQuadro().getTabuleiro()[y][x];
        if(!bloco.isTemBandeira() || bloco.getEstadoBloco().equals(EstadoBloco.FECHADO)){
            jogo.getQuadro().abrirCelulaEAoRedor(bloco);
            jogo.calcularPontuacao();
        }else
            System.out.println("Celula está com bandeira");

    }





    public static void main(String[] args) throws ValorDeAtributoInvalido {
        Scanner scanner = new Scanner(System.in);

        MineSweeperGame mineSweeperGame = new MineSweeperGame();
        Jogador jogador = new Jogador();
        Quadro quadro = mineSweeperGame.escolherQuadro();
        Jogo jogo = new Jogo(jogador,quadro, Dificuldade.FACIL);



        jogo.getQuadro().gerarTabuleiro();

        mineSweeperGame.printarJogo(jogo);

        //Vou botar o tempo nesse while ? talvez
        while (true){
            int escolha;


            System.out.println("Digite 1 para marcar um bloco ou digite 2 para colocar a bandeira nele ");
            escolha = scanner.nextInt();
            System.out.println("Digite um X: ");
            int x = scanner.nextInt();
            System.out.println("Digite um Y: ");
            int y = scanner.nextInt();
            switch (escolha){
                case 1:
                    mineSweeperGame.selecionarBloco(jogo,x,y);
                    break;
                case 2:
                    jogo.getQuadro().marcarBandeira(jogo.getQuadro().getTabuleiro()[y][x], jogo);

                    break;
            }
            mineSweeperGame.printarJogo(jogo);

            if(jogo.isFimDeJogo(quadro.getTabuleiro()[y][x])) break;

        }



    }





}
