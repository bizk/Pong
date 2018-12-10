package objetos;

public class Jugador implements NpcTDA {
    private int puntaje;
    private int x;
    private int y;
    private int velocidad;

    private int ancho = 10;
    private int alto = 120;
    private int spawnX;

    public Jugador(int k, int j, int vel) {
        this.x = k;
        this.y = j;
        this.velocidad = vel;
        this.spawnX = this.x;
        this.puntaje = 0;
    }

    @Override
    public int obtenerPuntos() {
        return puntaje;
    }

    @Override
    public void spawn() {
        this.x = spawnX;
    }

    @Override
    public void moverse(int k) {
        this.y += k;
    }

    public void moverEnemigo(int destino) {
        if (y > destino-velocidad && y >= 0) this.y -= velocidad;
        else if(y < destino + velocidad && y < 600 - alto) this.y += velocidad;
    }

    public void darPuntos() {
        this.puntaje++;
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return y;
    }
    public int getAncho() {
        return ancho;
    }
    public int getAlto() {
        return alto;
    }
    public int getPuntaje() { return puntaje; }

    public void setY(int y) {
        this.y = y;
    }
    public void setX(int x) {
        this.x = x;
    }
}
