package jogo;

import jogo.interfaceGrafica.MenuGrafico;
import jogo.estruturaJogo.Arquivos;
import jogo.estruturaJogo.Dificuldade;
import jogo.estruturaJogo.Jogador;
import jogo.estruturaJogo.Jogo;
import jogo.estruturaTabuleiro.quadro.tipos.Quadro;
import jogo.estruturaTabuleiro.quadro.tipos.QuadroCampoMaluco;
import jogo.estruturaTabuleiro.bloco.*;
import exceptions.ValorDeAtributoInvalido;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Random;

public class MineSweeperGame{


    public MineSweeperGame() {

    }

    //METODOS INDEPENDENTES
    public Quadro criarQuadroNormal( int largura,int altura, int qtdMinas)
    {
        return new Quadro(largura,altura,new Bloco[altura][largura], qtdMinas);
    }

    public Quadro criarQuadroMaluco(int largura,int altura, int qtdMinas, int maluquice) throws ValorDeAtributoInvalido {
        return new QuadroCampoMaluco(largura,altura,new Bloco[altura][largura], qtdMinas, maluquice);
    }

    public void selecionarBloco(Jogo jogo,Bloco bloco){
        if(!bloco.isTemBandeira() || bloco.getEstadoBloco().equals(EstadoBloco.FECHADO)){
            jogo.getQuadro().abreCelulaSucessivamente(bloco);
        }else
            System.out.println("Celula está com bandeira");
    }




    private void printarTempo(Jogador jogador){
        System.out.println("Tempo: "+(double)jogador.getTempoMillis()/1000);
    }

    private void printarQtdBandeiras(Jogo jogo){
        System.out.println("Bandeiras: "+jogo.getQtdBandeiras());
    }



    public Quadro escolherQuadro(int largura, int altura, int qtdMinas) throws ValorDeAtributoInvalido {

        Quadro quadro = null;
        Object[] options1 = { "Campo normal ", "Campo minado maluco"};


        int result = JOptionPane.showOptionDialog(null, "Escolha um modo de jogo", "Modo de jogo",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                options1, null);
        if (result == JOptionPane.YES_OPTION) {
            quadro = criarQuadroNormal(largura,altura,qtdMinas);
        }
        else if(result == JOptionPane.NO_OPTION){
            Random random = new Random();
            int maluquice = random.nextInt(3) + 1;
            quadro = criarQuadroMaluco(largura,altura,qtdMinas,maluquice);
        }
        else if (result == JOptionPane.CLOSED_OPTION){
            new MenuGrafico();
        }

        return quadro;
    }

    public Jogo escolherDificuldade() throws ValorDeAtributoInvalido {

        Jogo jogo = null;
        Quadro quadro;
        Jogador jogador = new Jogador();
        Object[] options1 = { "Facil", "Medio", "Dificil" };


        //A qtd de minas é 10% dos blocos totais
        int result = JOptionPane.showOptionDialog(null, "Escolha uma dificuldade", "Escolha uma dificuldade",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                options1, null);
        if (result == JOptionPane.YES_OPTION) {
            quadro = escolherQuadro(10,10,10);
            jogo = new Jogo(jogador,quadro, Dificuldade.FACIL);
            jogo.getQuadro().gerarTabuleiro();
        }
        else if(result == JOptionPane.NO_OPTION){
            quadro = escolherQuadro(25,25, 63);
            jogo = new Jogo(jogador,quadro, Dificuldade.MEDIO);
            jogo.getQuadro().gerarTabuleiro();
        }
        else if(result == JOptionPane.CANCEL_OPTION){
            quadro = escolherQuadro(50,50,250);
            jogo = new Jogo(jogador,quadro, Dificuldade.DIFICIL);
            jogo.getQuadro().gerarTabuleiro();
        }
        else if (result == JOptionPane.CLOSED_OPTION){
            new MenuGrafico();
        }


        return jogo;
    }

    public Jogo resgatarOuCriarJogo() throws ValorDeAtributoInvalido {

        Jogo jogo = null;



        File file = new File(Arquivos.SAVEGAME.getEndereco());

        if(file.length() != 0){
            int escolha= JOptionPane.showConfirmDialog(null,
                    "Save de um jogo resgatado, você deseja usar esse save?");

            if(escolha== JOptionPane.YES_OPTION) {
                System.out.println("Resgatando um jogo antigo... \n");
                jogo = new Jogo();

                try {
                    jogo = jogo.resgatarJogo();
                }catch (NullPointerException e){
                    System.out.println("Fechando janela");
                }

            }
            else if(escolha== JOptionPane.NO_OPTION) {
                System.out.println("Criando um novo jogo e sobrescrevendo o save atual\n");
                try {
                    jogo = escolherDificuldade();
                }catch (NullPointerException e){
                    System.out.println("Fechando janela");
                }
            }
            else if (escolha == JOptionPane.CLOSED_OPTION){
                new MenuGrafico();
            }
        }
        else{
            System.out.println("Criando um novo jogo");

            try {
                jogo = escolherDificuldade();
            }catch (NullPointerException e){
                System.out.println("Fechando janela");
            }        }
        return jogo;
    }


    public static void main(String[] args) throws ValorDeAtributoInvalido {


        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                new MenuGrafico();
            }
        });








    }




}
