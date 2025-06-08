import javax.xml.transform.Source;
import java.util.List;

/**
 * Representa el resultado de un algoritmo de solucion
 *
 * - Secuencia de maquinas utilizadas
 * - Cantidad de piezas producidas
 * - Cantidad de puestas en funcionamiento (maquinas usadas)
 * - Metricas de costo del algoritmo
 */
public class Solucion {
    private final List<Maquina> secuenciaMaquinas ;
    private final int totalPiezasProducidas;
    private final int totalPuestasFuncionamiento;
    private final int metrica;

    /**
     * Constructor completo
     * @param secuenciaMaquinas Lista ordenada de máquinas utilizadas
     * @param totalPiezasProducidas Suma total de piezas producidas
     * @param totalPuestasFuncionamiento Cantidad de máquinas usadas
     * @param metrica Métrica del algoritmo (estados generados o candidatos considerados)
     */
    public Solucion(List<Maquina> secuenciaMaquinas,int totalPiezasProducidas,int totalPuestasFuncionamiento,int metrica){
        this.secuenciaMaquinas  = secuenciaMaquinas;
        this.totalPiezasProducidas = totalPiezasProducidas;
        this.totalPuestasFuncionamiento = totalPuestasFuncionamiento;
        this.metrica = metrica;
    }

    // Getters
    public List<Maquina> getSecuenciaMaquina() {
        return secuenciaMaquinas ;
    }

    public int getTotalPiezasProducidas() {
        return totalPiezasProducidas;
    }

    public int getTotalPuestasFuncionamiento() {
        return totalPuestasFuncionamiento;
    }

    public int getMetrica() {
        return metrica;
    }

    @Override
    public String toString() {
        return "Secuencia: " + secuenciaMaquinas + "\n" +
                "Piezas producidas: " + totalPiezasProducidas + "\n" +
                "Puestas en funcionamiento: " + totalPuestasFuncionamiento + "\n" +
                "Métrica: " + metrica;
    }
}
