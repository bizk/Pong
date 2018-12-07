package objetos;

public interface NpcTDA {
    int obtenerPuntos();
    void spawn();
    void moverse(int k);

    int getX();
    int getY();
    int getAncho();
    int getAlto();

}