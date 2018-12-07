package objetos;

import java.util.Random;

public class Pelota {
    private int x, y, spawnX, spawnY;
    private int direcX, direcY;
    private int ancho = 20, alto = 20;
    private boolean derecha;
    private Random r;

    public Pelota(int k, int j) {
        this.spawnX = k;
        this.spawnY = j;
        this.r = new Random();
    }

    public void spawn() {
        this.x = this.spawnX;
        this.y = this.spawnY;
        this.derecha = generarDerecha();
        generarDestino();
    }

    public boolean generarDerecha() {
        return r.nextBoolean();
    }

    public void rebotar() {
        derecha = !derecha;
        generarDestino();
    }

    public void generarDestino() {
        if (derecha) {
            this.direcX = 600;
        } else {
            this.direcX = 0;
        }
        this.direcY = (int) (Math.random()*800-200);
    }

    public void mover() {
        if (derecha) {
            this.x += 2;
            if(this.y < direcY) this.y += 2;
            else this.y -= 2;
        } else {
            this.x-= 2;
            if(this.y < direcY) this.y+=2;
            else this.y-=2;
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getAncho() {
        return ancho;
    }
    public int getAlto() {
        return alto;
    }
    public boolean getDerecha() { return derecha; }
}
