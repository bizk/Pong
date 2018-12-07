package juego;

import objetos.Jugador;
import objetos.NpcTDA;
import objetos.Pelota;
import ventana.Ventana;

public class Juego {
    private Jugador jugador;
    private Jugador enemigo;
    private Pelota pelota;

    private int velocidad = 5;

    private static Juego instancia;

    public static Juego getInstancia() {
        if (instancia == null) instancia = new Juego();
        return instancia;
    }

    private Juego() {
        System.out.println("x");
    }

    public void iniciarJuego() {
        jugador = new Jugador(100, 100);
        enemigo = new Jugador(100, 100);
        pelota = new Pelota(380,250);
        jugador.spawn();
        pelota.spawn();
        pelota.generarDerecha();
        pelota.generarDestino();
        Ventana v = new Ventana();
    }

    public boolean estaEnMarco(NpcTDA npc) {
        if (npc.getX() >= 100 && npc.getX() <= 700) {
            if (npc.getY() >= 0 && npc.getY() < 600) {
                return true;
            }
        }
        return false;
    }

    public boolean estaEnMarco(int x, int y) {
        if (x >= 100 && x <= 700) {
            if (y >= 0 && y < 600) {
                return true;
            }
        }
        return false;
    }

    public boolean esGol(Pelota p) {
        if (p.getX() > 100 && p.getX() < 700) return false;
        return true;
    }

    public boolean chocaConNPC(NpcTDA npc, Pelota p) {
        if (p.getX() <= npc.getX() + npc.getAncho() && p.getX() >= npc.getX()) {
            if (p.getY() >= npc.getY() && p.getY() <= npc.getY() + npc.getAlto()) return true;
        }
        return false;
    }

    public void moverJugadorDer() {
        if (estaEnMarco(jugador.getX(), jugador.getY() + velocidad + jugador.getAlto() + 20)) jugador.moverse(velocidad);
    }
    public void moverJugadorIzq() {
        if (estaEnMarco(jugador.getX(), jugador.getY() - velocidad)) jugador.moverse(-velocidad);
    }

    public Pelota getPelota() { return this.pelota;}
    public Jugador getJugador() {
        return this.jugador;
    }
    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
}
