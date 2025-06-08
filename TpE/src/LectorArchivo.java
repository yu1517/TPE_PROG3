import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase se encargara de leer el archivo de texto y cargar los datos a memoria
 */
public class LectorArchivo {

    private List<Maquina> maquinas; // Lista para guardar las maquinas leidas
    private int piezasTotales;  // Total de piezas a producir

    public LectorArchivo() {
        this.maquinas = new ArrayList<>();
    }

    /**
     * Lee el archivo y carga los datos
     * @param ruta Ubicación del archivo (ej: "datos.txt")
     * @throws IOException Si hay error al leer el archivo
     * @throws NumberFormatException Si los números están mal formateados
     * @throws IllegalArgumentException Si el formato del archivo es incorrecto
     */
    public void cargarDesdeArchivo(String ruta) throws IOException, NumberFormatException, IllegalArgumentException {
        maquinas.clear();   // Limpiamos datos anteriores

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            // Leemos primera línea (total de piezas)
            String linea = br.readLine();
            if (linea == null) {
                throw new IllegalArgumentException("El archivo está vacío");
            }
            piezasTotales = Integer.parseInt(linea.trim());

            // Leemos las máquinas (líneas restantes)
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {      // Ignorar líneas vacías
                    String[] partes = linea.split(",");
                    if (partes.length != 2) {
                        throw new IllegalArgumentException("Formato de línea incorrecto: " + linea);
                    }
                    // Creamos y agregamos la máquina
                    String nombre = partes[0].trim();
                    int piezas = Integer.parseInt(partes[1].trim());
                    maquinas.add(new Maquina(nombre, piezas));
                }
            }
        }
    }

    // Métodos para acceder a los datos (getters)
    public List<Maquina> getMaquinas() {
        return new ArrayList<>(maquinas); // Devolvemos una copia para proteger el encapsulamiento
    }

    public int getPiezasTotales() {
        return piezasTotales;
    }
}
