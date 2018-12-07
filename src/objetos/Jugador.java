package objetos;

public class Jugador implements NpcTDA {
    private int puntaje;
    private int x;
    private int y;

    private int ancho = 10;
    private int alto = 120;
    private int spawnX;

    public Jugador(int k, int j) {
        this.x = k;
        this.y = j;
        this.spawnX = this.y;
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
