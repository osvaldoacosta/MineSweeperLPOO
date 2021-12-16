package jogo.interfaceGrafica;

import jogo.MineSweeperGame;
import jogo.estruturaJogo.Jogo;
import jogo.estruturaTabuleiro.bloco.Bloco;
import exceptions.ValorDeAtributoInvalido;
import jogo.estruturaTabuleiro.bloco.EstadoBloco;
import jogo.estruturaTabuleiro.bloco.tipos.BlocoMinado;
import jogo.estruturaTabuleiro.bloco.tipos.BlocoProxAMinas;
import jogo.estruturaTabuleiro.bloco.tipos.BlocoVazio;
import jogo.estruturaTabuleiro.quadro.tipos.QuadroCampoMaluco;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class QuadroGrafico extends JFrame implements MouseListener {

    private Jogo jogo;

    private long tempoInicial;
    private long tempoInGame;

    private JPanel jPanelQuadro;

    private JButton[] botoesTopBar;

    private JButton[][] botoesTabuleiro;


    private boolean tabuleiroBloqueado = true;
    private boolean isFimDeJogo = false;

    //Characteres unicode com os respectivos emojis link codePoints https://codepoints.net/
    public final static String BOMB = new String(Character.toChars(128163));
    public final static String FLAG = new String(Character.toChars(128681));

    //Emojis do topbar
    private String SMILE;
    private String SAD;


    public QuadroGrafico() throws ValorDeAtributoInvalido {
        super();
        initInterfaceQuadro();



    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    private Color[] colors = {
            Color.BLUE,
            Color.CYAN.darker(),
            Color.GREEN.darker(),
            Color.YELLOW.darker(),
            Color.ORANGE.darker(),
            Color.PINK.darker(),
            Color.MAGENTA,
            Color.RED
    };

    public JButton[][] getBotoesTabuleiro() {
        return botoesTabuleiro;
    }


    public Color[] getColors() {
        return colors;
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

    //Thread da parte de cima do quadrojogo
    private void topBarRun() {
        tempoInGame = 0;
        //Thread responsável pela barra de cima
        Thread threadTopBar = new Thread() {
            public void run() {
                while (tempoInGame < 999 && !isFimDeJogo) {
                    try {
                        salvarTempoAtual();
                    } catch (ValorDeAtributoInvalido e) {
                        e.printStackTrace();
                    }
                    tempoInGame = (int) getJogo().getJogador().getTempoMillis() / 1000;
                    botoesTopBar[2].setText(tempoInGame + "");
                    botoesTopBar[0].setText(String.valueOf(jogo.getQtdBandeiras()));

                }
            }
        };
        threadTopBar.start();
    }


    private void initInterfaceQuadro() {

        try {
            iniciarJogo();
            initQuadroGrafico();
            initTopBar();
            initWrapperQuadro();
            addListenerNosBotoes();
        }
        catch (NullPointerException | ValorDeAtributoInvalido e){
            System.out.println("Voltando ao menu");
        }

    }
    private void iniciarJogo() throws ValorDeAtributoInvalido {
        MineSweeperGame mineSweeperGame = new MineSweeperGame();

        setJogo(mineSweeperGame.resgatarOuCriarJogo());
        setTitle(jogo.getQuadro().getClass().getSimpleName());

        //Seta os emojis
        SMILE = jogo.getQuadro() instanceof QuadroCampoMaluco ? new String(Character.toChars(128523)) : new String(Character.toChars(128512));
        SAD = jogo.getQuadro() instanceof QuadroCampoMaluco ? new String(Character.toChars(128579)) : new String(Character.toChars(9785));





    }

    private void initTopBar(){
        JButton botaoQtdBandeiras = new JButton();
        JButton botaoSmile = new JButton();
        JButton botaoTempo = new JButton();

        botoesTopBar[0] = botaoQtdBandeiras;
        botoesTopBar[1] = botaoSmile;
        botoesTopBar[2] = botaoTempo;

        botoesTopBar[0].setFocusPainted(false);
        botoesTopBar[1].setFocusPainted(false);
        botoesTopBar[2].setFocusPainted(false);

        Font f = getCompatibleFonts().firstElement().deriveFont(20f);
        Font smileFont = getCompatibleFonts().firstElement().deriveFont(30f);


        botaoQtdBandeiras.setFont(f);
        botaoTempo.setFont(f);

        botaoSmile.setFont(smileFont);
        botaoSmile.setText(SMILE);

        botaoQtdBandeiras.setText(String.valueOf(jogo.getQtdBandeiras()));
    }

    private void initQuadroGrafico(){
        jPanelQuadro = new JPanel(new BorderLayout(4, 4));
        jPanelQuadro.setBorder(new EmptyBorder(4, 4, 4, 4));

        int altura = jogo.getQuadro().getQTD_BLOCOS_ALTURA();
        int largura = jogo.getQuadro().getQTD_BLOCOS_LARGURA();

        //Cálculo totalmente árbitrário feito na tentativa e erro ._.
        setSize(20 + largura * 35, 20 + altura * 55);



        botoesTopBar = new JButton[3];
        setTitle("Campo minado game");
        setContentPane(jPanelQuadro);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);




    }

    private void initWrapperQuadro(){
        int altura = jogo.getQuadro().getQTD_BLOCOS_ALTURA();
        int largura = jogo.getQuadro().getQTD_BLOCOS_LARGURA();

        JPanel topGridWrapper = new JPanel(new GridLayout(1, largura, 7, 5));
        JPanel tabuleiroGridWrapper = new JPanel(new GridLayout(altura, largura));


        topGridWrapper.add(botoesTopBar[0]);
        topGridWrapper.add(botoesTopBar[1]);
        topGridWrapper.add(botoesTopBar[2]);


        jPanelQuadro.add(tabuleiroGridWrapper, BorderLayout.CENTER);
        jPanelQuadro.add(topGridWrapper, BorderLayout.NORTH);

        populaOsBotoes(tabuleiroGridWrapper);

    }

    private void addListenerNosBotoes(){
        //Listener no botao smile
        botoesTopBar[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabuleiroBloqueado = false;
                tempoInicial = System.currentTimeMillis();
                topBarRun();
            }
        });

        //Listener no botao de fechar janela
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(jPanelQuadro,
                        "Você tem certeza que quer encerrar o jogo e voltar para o menu?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    isFimDeJogo = true;
                    try {
                        salvarTempoAtual();
                    } catch (ValorDeAtributoInvalido e) {
                        e.printStackTrace();
                    }
                    if(tempoInGame > 0) {
                        if (JOptionPane.showConfirmDialog(jPanelQuadro, "Você deseja salvar a sessão atual?", "Salvar jogo",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                            jogo.salvarJogo();
                        }
                    }
                    new MenuGrafico();
                }
            }
        });
    }


    private void populaOsBotoes(JPanel gridWrapper) {

        int altura = jogo.getQuadro().getQTD_BLOCOS_ALTURA();
        int largura = jogo.getQuadro().getQTD_BLOCOS_LARGURA();

        int in = 5;
        Insets insets = new Insets(in, in, in, in);
        Font f = getCompatibleFonts().firstElement().deriveFont(16f);
        botoesTabuleiro = new JButton[altura][largura];
        for (int ii = 0; ii < altura; ii++) {
            for (int jj = 0; jj < largura; jj++) {
                Bloco bloco = jogo.getQuadro().getTabuleiro()[ii][jj];


                //Inicializando os botões do grid
                JButton b = new JButton();
                b.setMargin(insets);
                b.setFont(f);
                b.setText("?");
                b.setFocusPainted(false);

                b.putClientProperty("coluna", jj);
                b.putClientProperty("linha", ii);
                b.addMouseListener(this);
                //Botando cores para o botão selecionado
                if (bloco.isTemBandeira()) {
                    b.setForeground(Color.red);
                    b.setText(FLAG);
                } else if (bloco.getEstadoBloco().equals(EstadoBloco.EXPOSTO)) {

                    if (bloco instanceof BlocoVazio) {
                        b.setText("");
                    }
                    if (bloco instanceof BlocoMinado) {
                        b.setForeground(Color.red);
                        b.setText(BOMB);
                    } else if (bloco instanceof BlocoProxAMinas) {
                        b.setForeground(getColors()[((BlocoProxAMinas) bloco).getMinasAoRedor() - 1]);

                        b.setText(String.valueOf(((BlocoProxAMinas) bloco).getMinasAoRedor()));
                    }

                } else {
                    b.setForeground(Color.black);
                    b.setText("?");
                }
                gridWrapper.add(b);
                botoesTabuleiro[ii][jj] = b;
            }
        }
    }

    private void salvarTempoAtual() throws ValorDeAtributoInvalido {
        jogo.getJogador().setTempoMillis(System.currentTimeMillis() - tempoInicial);
    }


    private void atualizarBlocosEBotoes(Bloco blocoSelecionado) throws ValorDeAtributoInvalido {


        for (int i = 0; i < jogo.getQuadro().getQTD_BLOCOS_ALTURA(); i++) {
            for (int j = 0; j < jogo.getQuadro().getQTD_BLOCOS_LARGURA(); j++) {
                Bloco bloco = jogo.getQuadro().getTabuleiro()[i][j];
                JButton b = botoesTabuleiro[i][j];

                if (bloco.isTemBandeira()) {
                    b.setForeground(Color.red);
                    b.setText(FLAG);
                } else if (!bloco.isFechado()) {
                    b.setText("?");

                    if (bloco instanceof BlocoVazio) {
                        b.setText("");
                    }
                    if (bloco instanceof BlocoMinado) {
                        b.setForeground(Color.red);
                        b.setText(BOMB);
                    } else if (bloco instanceof BlocoProxAMinas) {
                        b.setForeground(getColors()[((BlocoProxAMinas) bloco).getMinasAoRedor() - 1]);

                        b.setText(String.valueOf(((BlocoProxAMinas) bloco).getMinasAoRedor()));
                    }

                } else {
                    b.setForeground(Color.black);
                    b.setText("?");
                }


            }
        }

        boolean [] arrayFimDeJogo = jogo.isFimDeJogo(blocoSelecionado);
        if (arrayFimDeJogo[0]){
            isFimDeJogo = true;
            if(!arrayFimDeJogo[1]){
                botoesTopBar[1].setText(SAD);
                JOptionPane.showMessageDialog(jPanelQuadro, "Infelizmente você perdeu =(");
            }


            else {
                JOptionPane.showMessageDialog(jPanelQuadro, "Parabéns você ganhou =)");

                String nome = JOptionPane.showInputDialog(jPanelQuadro,
                        "Digite o seu nome para salvar o recorde?", null);

                jogo.getJogador().setNome(nome);
                jogo.getJogador().salvarRecorde();

            }



            this.dispose();
            new MenuGrafico();
        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {


        JButton btn = (JButton) e.getSource();
        Bloco[][] tabuleiro = getJogo().getQuadro().getTabuleiro();
        Bloco bloco = tabuleiro[(int) btn.getClientProperty("linha")][(int) btn.getClientProperty("coluna")];
        if (tabuleiroBloqueado == false) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                try {
                    jogo.getQuadro().marcarBandeira(bloco, jogo);
                } catch (ValorDeAtributoInvalido ex) {
                    ex.printStackTrace();
                }
                System.out.println(bloco.getX() + " | " + bloco.getY() + " | bandeira: " + bloco.isTemBandeira());

            } else if (e.getButton() == MouseEvent.BUTTON1) {
                MineSweeperGame mineSweeperGame = new MineSweeperGame();

                mineSweeperGame.selecionarBloco(jogo, bloco);

            }

            try {
                atualizarBlocosEBotoes(bloco);
            } catch (ValorDeAtributoInvalido ex) {
                ex.printStackTrace();
            }
        }


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
