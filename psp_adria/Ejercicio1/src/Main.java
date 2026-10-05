import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        // 1. Obtener el sistema operativo
        String sistemaOperativo = System.getProperty("os.name").toLowerCase();

        String comando;
        String directorio;

        // Comprobar si estamos en Windows o Linux
        if (sistemaOperativo.contains("windows")) {

            comando = "cmd /c dir";
            directorio = "c:/temp";

        } else if (sistemaOperativo.contains("linux")) {

            comando = "sh -c ls";
            directorio = "/tmp";

        } else {
            System.out.println("Sistema operativo no soportado");
            return;
        }

        // 2. Separar el comando usando split("\\s")
        String[] partesComando = comando.split("\\s");

        // Crear el proceso
        ProcessBuilder proceso = new ProcessBuilder(partesComando);

        // 3. Cambiar el directorio de trabajo
        proceso.directory(new File(directorio));

        // Mostrar la salida del proceso en nuestra consola
        proceso.inheritIO();

        // 4. Ejecutar el proceso
        try {
            proceso.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}