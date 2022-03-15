package seaBattle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayDeque;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Esta clase se encarga de manejar la interfaz del juego
 * Author: Robert Fernando Gil robert.gil@correounivalle.edu.co - 2022985 - Esperanza Olivo esperanza.olivo@correounivalle.edu.co - 2025176
 * @version v.1.0.0 date: 05/03/2022
 */
public class GUI extends JFrame {
    private Container cp;
    private JPanel messagePanel = new JPanel(new BorderLayout());
    private JPanel buttonPanel = new JPanel();
    private JLabel message = new JLabel("Batalla Naval");
    private JLabel message1 = new JLabel("Message 1");
    private MyPanel leftBoard = new MyPanel();
    private MyPanel rightBoard = new MyPanel();
    private PanelInfo panelInfo;
    private State state;

    public static final String MESSAGE = "Bienvenido a Batalla Naval \n" +
            "\n Batalla Naval es un juego de estrategia que involucra dos participantes, en este caso un jugador humano y una máquina. " +
            "\n En el juego tendrás dos tableros: el de posición es aquel donde podrás ubicar tus barcos, el principal es donde realizarás tus disparos." +
            "\n Una vez los barcos han sido posicionados, inician las rondas. Cada jugador tendrá su turno. Si alguno toca o destruye un barco, " +
            "\n tendrá derecho a realizar otro disparo, de lo contrario el turno es del otro jugador. Hay 9 barcos disponibles: 4 fragatas, 3 destructores, " +
            "\n 2 submarinos y 1 portaaviones. El ganador será aquel que haya hundido más barcos. ";

    private PlayerAction handler;

    /**
     * Constructor de la clase GUI
     */
    public GUI() {
        cp = getContentPane();
        cp.setLayout(new BorderLayout());

        //Cambia el mensaje del panel
        messagePanel.setPreferredSize(new Dimension(GameConstant.DRAW_FIELD_DIMENSION*2+GameConstant.CELL_SIZE, 100));
        messagePanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //Maneja el panel del computador
        rightBoard.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (state == State.MAKE_MOVE)
                    handler.onMouseClicked(e.getX(), e.getY(),state);
                repaint();
            }
        });

        //Maneja el panel del jugador.
        leftBoard.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (state == State.BUILD_HORIZONTAL || state == State.BUILD_VERTICAL) {
                    handler.onMouseClicked(e.getX(), e.getY(), state);
                    repaint();
                    state = State.CHOOSE_ORIENT;
                    handler.onPassState(State.CHOOSE_ORIENT);
                }
            }
        });

        leftBoard.addDrawable(new PanelBoard());
        rightBoard.addDrawable(new PanelBoard());
        panelInfo = new PanelInfo();
        panelInfo.setBorder(BorderFactory.createTitledBorder("Información: "));


        cp.add(messagePanel, BorderLayout.NORTH);
        locateShips();
        cp.add(leftBoard, BorderLayout.WEST);
        cp.add(rightBoard, BorderLayout.EAST);
        cp.add(panelInfo, BorderLayout.SOUTH);
        setResizable(false);
        setTitle("Batalla Naval");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }


    public void setHandler(PlayerAction p) {
        handler = p;
        revalidate();
    }

    /**
     * Actualiza los mensajes de los paneles.
     * @param s
     * @param s1
     */

    public void updateMessage(String s, String s1) {
        if (!s.equals("")) message.setText(s);
        if (!s1.equals("")) message1.setText(s1);
        revalidate();
    }

    /**
     * Muestra las excepciones a la hora de ubicar los barcos
     */

    public void showException(){
        showMessageDialog(this, "No puedes ubicar los barcos aquí.");
    }

    public void updateState(State s) {
        state = s;
        revalidate();
    }

    public class MyPanel extends JPanel {
        public ArrayDeque<Drawable> objectsForDraw = new ArrayDeque<>();

        public MyPanel() {
            setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
            setBackground(new Color(0,191,255));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Drawable d : objectsForDraw) {
                d.draw(g);
            }
        }

        public void addDrawable(Drawable d) {
            objectsForDraw.add(d);
        }

        public void eraseAll() {
            objectsForDraw.clear();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(GameConstant.DRAW_FIELD_DIMENSION, GameConstant.DRAW_FIELD_DIMENSION);
        }
    }

    private void refresh() {
        leftBoard.eraseAll();
        leftBoard.addDrawable(new PanelBoard());
        rightBoard.eraseAll();
        rightBoard.addDrawable(new PanelBoard());
        locateShips();
        repaint();
        revalidate();
    }

    public void drawOnLeft(Drawable object) {
        leftBoard.addDrawable(object);
        revalidate();
    }

    public void drawOnRight(Drawable object) {
        rightBoard.addDrawable(object);
        revalidate();
    }

    /**
     * Ubica los barcos
     */

    private void locateShips() {
        messagePanel.removeAll();

        //Botón para realizar la ubicación de los barcos

        JButton buildButton = new JButton("Ubicar barcos");
        buildButton.setFocusPainted(false);
        buildButton.addActionListener(e -> {
            handler.onPassState(State.CHOOSE_ORIENT);
            revalidate();
            repaint();
        });

        //Botón de ayuda

        JButton help = new JButton("?");
        help.setFocusPainted(false);
        help.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, MESSAGE);
        });

        //Panel para ubicar los botones

        buttonPanel = new JPanel();
        buttonPanel.add(buildButton, BorderLayout.WEST);
        buttonPanel.add(help);

        //Mensajes que aparecerán en el tablero para informar sobre los movimientos realizados.

        message.setText("Tablero de posición: ");
        message.setFont(new Font("Dialog", Font.PLAIN, 16));
        message1.setText("Tablero principal: ");
        message1.setFont(new Font("Dialog", Font.PLAIN, 16));
        messagePanel.add(buttonPanel, BorderLayout.NORTH);
        messagePanel.add(message, BorderLayout.WEST);
        messagePanel.add(message1, BorderLayout.EAST);
    }

    /**
     * Elige la orientación del barco
     */

    public void chooseOrientation() {
        state = State.DO_NOTHING;
        Object[] options = {"Horizontal",
                "Vertical"};

        int n = JOptionPane.showOptionDialog(this,
                "Elige la orientación de los barcos",
                "",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);
        if (n == 0) {
            message1.setText("Horizontal");
            state = State.BUILD_HORIZONTAL;
        } else {message1.setText("Vertical");
            state = State.BUILD_VERTICAL;};
    }

    /**
     * Muestra un mensaje para comenzar a disparar
     */

    public void startShooting() {
        messagePanel.remove(buttonPanel);
        message.setText("¡Hecho!");
        message1.setText("Haz un disparo");
        state = State.MAKE_MOVE;
    }

    /**
     * Ejecuta el programa
     * @param args
     */

    public static void main(String[] args) {
        Control newGame = new Control();
    }

}
