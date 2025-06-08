import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        LectorArchivo lector = new LectorArchivo();

        try {
            // 1. Cargar datos del archivo
            lector.cargarDesdeArchivo("src/configuracion.txt");

            System.out.println("Total de piezas: " + lector.getPiezasTotales());
            System.out.println("Máquinas cargadas:");
            for (Maquina m : lector.getMaquinas()) {
                System.out.println("- " + m.getNombre() + ": " + m.getPiezas());
            }

            // 2. BACKTRACKING
            System.out.println("\n=== BACKTRACKING ===");
            BacktrackingSolver backtracking = new BacktrackingSolver(
                    lector.getMaquinas(),
                    lector.getPiezasTotales()
            );
            Solucion solucionBT = backtracking.resolver();

            // 3. Mostrar resultados como pide el TP
            System.out.println("Solución obtenida: " + solucionBT.getSecuenciaMaquina());
            System.out.println("Piezas producidas: " + solucionBT.getTotalPiezasProducidas() +
                    ", Puestas en funcionamiento: " + solucionBT.getTotalPuestasFuncionamiento());
            System.out.println("Métrica (estados generados): " + solucionBT.getMetrica());






        } catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error en formato numérico: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error en formato del archivo: " + e.getMessage());
        }
    }
}