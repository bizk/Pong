package ventana;

import juego.Juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class Ventana extends JFrame {
    private Container c;
    private Vector<JLabel> paredes;
    private JPanel ventanaJuego;
    private JPanel ventanaPausa;
    private JLabel puntajeJugador;
    private JLabel puntajeEnemigo;
    private JLabel jugador;
    private JLabel enemigo;
    private JLabel pelota;
    private Timer timer;

    public Ventana() {
        c = this.getContentPane();
        c.setBackground(Color.BLACK);
        this.setSize(800,600);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new teclas());
        //
        construirMapa();
        jugar();
        menuPause();

        c.add(ventanaJuego);
        c.add(ventanaPausa);
        c.repaint();
    }

    private void construirMapa() {
        ventanaJuego = new JPanel();
        JLabel jl = new JLabel();
        ImageIcon ic = new ImageIcon("Linea.png");
        paredes = new Vector<JLabel>();

        ventanaJuego.setBounds(0,0,800,600);
        ventanaJuego.setBackground(Color.black);

        jl.setBounds(95,0,2,600);
        jl.setVisible(true);
        jl.setIcon(ic);
        ventanaJuego.add(jl);
        paredes.add(jl);

        jl = new JLabel();
        jl.setBounds(705,0,2,600);
        jl.setVisible(true);
        jl.setIcon(ic);
        ventanaJuego.add(jl);
        paredes.add(jl);

        ventanaJuego.setVisible(true);
        /*c.add(ventanaJuego);
        c.repaint();*/
    }

    private void jugar() {
        ImageIcon im = new ImageIcon("Jugador.png");
        Eventos refresco = new Eventos();

        timer = new Timer(10, refresco);

        puntajeJugador = new JLabel();
        puntajeJugador.setBounds(320, 10, 80, 80);
        puntajeJugador.setFont(new Font("Serif", Font.BOLD, 64));
        puntajeJugador.setForeground(Color.white);
        puntajeJugador.setText("0");
        puntajeJugador.setVisible(true);

        puntajeEnemigo = new JLabel();
        puntajeEnemigo.setBounds(400,10,80,80);
        puntajeEnemigo.setFont(new Font("Serif", Font.BOLD, 64));
        puntajeEnemigo.setForeground(Color.white);
        puntajeEnemigo.setText("0");
        puntajeEnemigo.setVisible(true);

        jugador = new JLabel();
        jugador.setBounds(Juego.getInstancia().getJugador().getX(), Juego.getInstancia().getJugador().getY(), Juego.getInstancia().getJugador().getAncho(), Juego.getInstancia().getJugador().getAlto());
        jugador.setIcon(im);
        jugador.setVisible(true);

        enemigo = new JLabel();
        enemigo.setBounds(Juego.getInstancia().getEnemigo().getX(), Juego.getInstancia().getEnemigo().getY(), Juego.getInstancia().getEnemigo().getAncho(), Juego.getInstancia().getEnemigo().getAlto());
        enemigo.setIcon(im);
        enemigo.setVisible(true);

        pelota = new JLabel();
        pelota.setBounds(Juego.getInstancia().getPelota().getX(), Juego.getInstancia().getPelota().getY(), Juego.getInstancia().getPelota().getAncho(), Juego.getInstancia().getPelota().getAlto());
        pelota.setIcon(im);
        pelota.setVisible(true);

        ventanaJuego.add(pelota);
        ventanaJuego.add(jugador);
        ventanaJuego.add(enemigo);
        ventanaJuego.add(puntajeJugador);
        ventanaJuego.add(puntajeEnemigo);
        timer.start();
    }

    private void menuPause() {
        ventanaPausa = new JPanel();
        ventanaPausa.setBounds(150,150, 50 ,50);
        ventanaPausa.setBackground(Color.green);
        ventanaPausa.setVisible(false);
    }

    class Eventos implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Juego.getInstancia().esGol(Juego.getInstancia().getPelota())) {
                if (Juego.getInstancia().getPelota().getDerecha()) {
                    Juego.getInstancia().getJugador().darPuntos();
                    ventanaJuego.remove(puntajeJugador);
                    puntajeJugador.setText(Integer.toString(Juego.getInstancia().getJugador().getPuntaje()));
                    ventanaJuego.add(puntajeJugador);
                    c.repaint();
                } else {
                    Juego.getInstancia().getEnemigo().darPuntos();
                    ventanaJuego.remove(puntajeEnemigo);
                    puntajeEnemigo.setText(Integer.toString(Juego.getInstancia().getEnemigo().getPuntaje()));
                    ventanaJuego.add(puntajeEnemigo);
                    c.repaint();
                }
                Juego.getInstancia().getPelota().spawn();
                pelota.setBounds(Juego.getInstancia().getPelota().getX(), Juego.getInstancia().getPelota().getY(), Juego.getInstancia().getPelota().getAncho(), Juego.getInstancia().getPelota().getAlto());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            if (Juego.getInstancia().chocaConNPC(Juego.getInstancia().getJugador(), Juego.getInstancia().getPelota()) || Juego.getInstancia().chocaConNPC(Juego.getInstancia().getEnemigo(), Juego.getInstancia().getPelota())) {
                Juego.getInstancia().getPelota().rebotar();
                Juego.getInstancia().getPelota().generarDestino();
            }
            if(!Juego.getInstancia().estaEnMarco(Juego.getInstancia().getPelota().getX(), Juego.getInstancia().getPelota().getY(), Juego.getInstancia().getPelota().getAlto())) {
                Juego.getInstancia().getPelota().generarDestino();
            }
            Juego.getInstancia().getPelota().mover();
            Juego.getInstancia().moverEnemigo();
            enemigo.setBounds(Juego.getInstancia().getEnemigo().getX(), Juego.getInstancia().getEnemigo().getY(), Juego.getInstancia().getEnemigo().getAncho(), Juego.getInstancia().getEnemigo().getAlto());
            jugador.setBounds(Juego.getInstancia().getJugador().getX(), Juego.getInstancia().getJugador().getY(), Juego.getInstancia().getJugador().getAncho(), Juego.getInstancia().getJugador().getAlto());
            pelota.setBounds(Juego.getInstancia().getPelota().getX(), Juego.getInstancia().getPelota().getY(), Juego.getInstancia().getPelota().getAncho(), Juego.getInstancia().getPelota().getAlto());
        }
    }

    class teclas implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            int tecla = e.getKeyCode();
            if (tecla == KeyEvent.VK_D) {
                Juego.getInstancia().moverJugadorDer();
            }
            else if (tecla == KeyEvent.VK_A) {
                Juego.getInstancia().moverJugadorIzq();
            }
            else if (tecla == KeyEvent.VK_ESCAPE) {
                if (timer.isRunning()) {
                    timer.stop();
                    ventanaPausa.setVisible(true);
                    c.repaint();
                } else {
                    timer.start();
                    ventanaPausa.setVisible(false);
                    ventanaJuego.repaint();
                    c.repaint();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
