package objetos;

import java.util.Random;

public class Pelota {
    private int x, y, spawnX, spawnY;
    private int direcX, direcY;
    private int ancho = 20, alto = 20, velocidad;
    private boolean derecha;
    private Random r;

    public Pelota(int k, int j, int vel) {
        this.spawnX = k;
        this.spawnY = j;
        this.velocidad = vel;
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
        do {
            this.direcY = (int) (Math.random()*1000-200);
        } while (direcY >= 0 && direcY <= 600);
    }

    public void mover() {
        if (derecha) {
            this.x += 3;
            if(this.y < direcY && y < 580-alto-velocidad) this.y += 3;
            else if (this.y > direcY && this.y > 0)this.y -= 3;
            else {
                this.generarDestino();
            }
        } else {
            this.x-= 3;
            if(this.y < direcY && y < 580-alto - velocidad) this.y+=3;
            else if (this.y > direcY && this.y > 0) this.y-=3;
            else {
                this.generarDestino();
            }
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
    public int getDirecY() {
        return direcY;
    }
}
