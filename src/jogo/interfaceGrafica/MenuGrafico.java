package jogo.interfaceGrafica;

import exceptions.ValorDeAtributoInvalido;
import jogo.estruturaJogo.Jogador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class MenuGrafico extends JFrame {
    private JPanel jPanelMenu;

    private JButton[] botoesMenu;

    public final static String BOMB = new String(Character.toChars(128163));

    public MenuGrafico(){
        super();
        initMenu();
    }

    public void initMenu(){
        initMenuGrafico();
        initBotoesMenu();
        initWrapperMenu();
        initListenerBotoes();
    }
    private static Vector<Font> getCompatibleFonts() {
        Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        Vector<Font> fontVector = new Vector<>();

        for (Font font : fonts) {
            if (font.canDisplayUpTo("12345678" + BOMB) < 0) {
                fontVector.add(font);
            }
        }

        return fontVector;
    }
    private void initMenuGrafico(){
        //Cálculo totalmente árbitrário e na tentativa erro ._.
        setSize(20 + 25 * 35, 20 + 20 * 35);

        jPanelMenu = new JPanel(new BorderLayout(4, 4));
        jPanelMenu.setBorder(new EmptyBorder(4, 4, 4, 4));




        botoesMenu = new JButton[4];

        setContentPane(jPanelMenu);
        setTitle("Campo minado Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);



    }

    private void initBotoesMenu(){
        botoesMenu[0] = new JButton();
        botoesMenu[1] = new JButton();
        botoesMenu[2] = new JButton();
        botoesMenu[3] = new JButton();
        Font f = getCompatibleFonts().firstElement().deriveFont(20f);

        botoesMenu[0].setFont(f);
        botoesMenu[1].setFont(f);
        botoesMenu[2].setFont(f);

        botoesMenu[0].setFocusPainted(false);
        botoesMenu[1].setFocusPainted(false);
        botoesMenu[2].setFocusPainted(false);
        botoesMenu[3].setFocusPainted(false);

        botoesMenu[0].setText("JOGAR");
        botoesMenu[1].setText("RECORDES");
        botoesMenu[2].setText("SOBRE");

        Font fontBomb = getCompatibleFonts().firstElement().deriveFont(30f);

        botoesMenu[3].setForeground(Color.red);
        botoesMenu[3].setText(BOMB+"CAMPO MINADO");
        botoesMenu[3].setFont(fontBomb);


    }

    private void initListenerBotoes(){
        JFrame frameMenu = this;
        //Listener do botao jogar
        botoesMenu[0].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frameMenu.dispose();

                    new QuadroGrafico();


                } catch (ValorDeAtributoInvalido ex) {
                    ex.printStackTrace();
                }


            }


        });
        //Listener do botao recorde
        botoesMenu[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Jogador instanciaJogador = new Jogador();
                String recorde = instanciaJogador.StringRecorde();
                JOptionPane.showMessageDialog(jPanelMenu, recorde);

            }
        });


        //Listener do botao sobre
        botoesMenu[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(jPanelMenu, "Esse jogo foi desenvolvido por Osvaldo Costa para a cadeira de LPOO do curso de Engenharia da computação da Universidade de Pernambuco\n\n" +
                        "O jogo consiste em um campo minado tradicional com modos de jogo, esses modos serão selecionados no menu após clicarem em jogar\n" +
                                "\nModos:\nCampo minado: campo minado padrão, para começar o jogo você deve apertar no emojique está no centro-superior da tela, " +
                                "após clicado no emoji\no jogo começará, você pode clicar com o botão esquerdo(seleciona uma célula) e direito(coloca bandeira), "+

                        "\nCampo minado maluco: é o campo minado que quando o jogador marca uma célula com uma bandeira, a mesma tem uma chance de " +
                        "mudar de \nestado, por exemplo, uma célula vazio pode se tornar uma mina\n\nDificuldade\nFácil: Consiste de um tabuleiro 10x10 com 10 minas distribuidas nele\nMédio: Consiste de um tabuleiro 25x25 com 63 minas distribuidas nele" +
                                "\nDifícil: Consiste de um tabuleiro de 50x50 com 250 minas distribuidas nele\n\nRecordes\nO recorde é definido pelo menor tempo em que um jogador consegue ganhar um jogo(independente da dificuldade), ao ganhar será solicitado o nome para ser salvo no ranking\n" +
                                "\nJogo:\nSe você quiser salvar o seu jogo para jogar posteriormente, você pode!, ao clicar em fechar dentro do jogo, será pedido se você quer salvar o jogo atual, quando você voltar ao menu e clicar em jogar aparecerá se você quer começar de onde parou\n" +
                                "(obs: o save do jogo só será apagado se o jogador perder ou ganhar, será sobrescrito se ele salvar por cima fazendo o mesmo processo)  "
                        );


            }
        });







    }
    private void initWrapperMenu(){

        jPanelMenu.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.PAGE_START;

        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.insets = new Insets(2, 2, 2, 2);
        c.gridx = 0;
        c.gridy = 1;

        jPanelMenu.add(botoesMenu[3],c);

        c.gridy = 2;

        jPanelMenu.add(botoesMenu[0], c);
        c.gridy = 3;

        jPanelMenu.add(botoesMenu[1], c);


        c.gridy = 4;
        jPanelMenu.add(botoesMenu[2], c);

    }

}
