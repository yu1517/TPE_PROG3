import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class LectorArchivo {
    public static List<Maquina> maquinas = new ArrayList<Maquina>();
    public static int piezasTotales;

    public static void cargarDesdeArchivo(String ruta) throws Exception {
        maquinas.clear();
        BufferedReader br = new BufferedReader(new FileRider(ruta));
        piezasTotales = Integer.parseInt(br.readLine());

        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            
        }

    }
}
