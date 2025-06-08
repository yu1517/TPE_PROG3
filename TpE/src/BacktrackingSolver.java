import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Estrategia de Backtracking para resolver el prolema de autopartes:
 *
 * - Genera un arbol de exploracion donde cada nodo representa una posile secuencia de maquinas.
 * - Estados finales: cuando la suma de piezas alcanza exantamente el total requerido (solucion) o lo supera (poda)
 * - Podas aplicadas:
 * 1. Cuando la suma parcial de piezas supera el total requerido.
 * 2. Cuando la cantidad de maquinas usadas ya es mayor que en la mejor solucion encontrada.
 * - Ordena las masquinas de mayor a menor capacidad para encontrar soluciones optimas mas rapido.
 * - Metrica: cuenta cada vez que se explora un nuevo estado (llamada recursiva)
 */
public class BacktrackingSolver {

    private List<Maquina> maquinas;
    private int totalRequerido;
    private List<Maquina> mejorSolucion;
    private int minMaquinas;
    private int estadosGenerados;

    public BacktrackingSolver(List<Maquina> maquinas, int totalRequerido) {
        // Copiamos y ordenamos las máquinas de mayor a menor capacidad
        this.maquinas = new ArrayList<>(maquinas);
        Collections.sort(this.maquinas, (a, b) -> Integer.compare(b.getPiezas(), a.getPiezas()));

        this.totalRequerido = totalRequerido;
        this.mejorSolucion = null;
        this.minMaquinas = Integer.MAX_VALUE;
        this.estadosGenerados = 0;
    }

    /**
     * Resuelve el problema y retorna la solución.
     * @return Objeto Solucion con todos los datos requeridos
     */
    public Solucion resolver(){
        backtrack(new ArrayList<>(), 0, 0, 0);
        return construirSolucion();
    }

    private void backtrack(List<Maquina>  secuenciaActual, int sumaActual, int maquinasUsadas, int indice){
        estadosGenerados++;  // Contador de estados explorados;

        // Poda 1: por optimalidad (si ya usamos mas maquinas que la mejor solucion)
        if (maquinasUsadas >= minMaquinas){
            return;
        }

        // Solucion encontrada
        if (sumaActual == totalRequerido){
            //if (maquinasUsadas < minMaquinas){
                mejorSolucion = new ArrayList<>(secuenciaActual);
                minMaquinas = maquinasUsadas;
           // }
            return;
        }

        // Poda 2: Si nos pasamos del total
        if (sumaActual > totalRequerido){
            return;
        }

        // Exploracion recursiva
        for (int i = indice; i < maquinas.size(); i++) {  // Evita permutaciones redundantes (ej: [M1,M2] y [M2,M1])
            Maquina maquina = maquinas.get(i);
            int nuevoTotal = sumaActual + maquina.getPiezas();

            // Poda por factibilidad
            if (nuevoTotal > totalRequerido) continue;

            secuenciaActual.add(maquina);
            backtrack(secuenciaActual, nuevoTotal, maquinasUsadas + 1, i);
            secuenciaActual.remove(secuenciaActual.size() - 1);

            // Corte temprano si encontramos solución mínima teórica
            if (minMaquinas == maquinasUsadas + 1) break;
        }
    }

    private Solucion construirSolucion(){
        if (mejorSolucion == null){
            return new Solucion(new ArrayList<>(),0 ,0, estadosGenerados);
        }
        int totalPiezas = 0;
        for (Maquina m: mejorSolucion){
            totalPiezas += m.getPiezas();
        }
        return new Solucion (mejorSolucion,totalPiezas, mejorSolucion.size(), estadosGenerados);
    }
}
