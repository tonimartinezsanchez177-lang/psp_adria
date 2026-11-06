
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        ProcessBuilder processBuilder = new ProcessBuilder("ping", "-c", "4", "localhost");

        try {
            Process proceso = processBuilder.start();

            System.out.println("¿El proceso está vivo al inicio?: " + proceso.isAlive());

            System.out.println("PID: " + proceso.pid());
            System.out.println("Comando: " + processBuilder.command());
            System.out.println("Argumentos: " + processBuilder.command().subList(1, processBuilder.command().size()));
            System.out.println("Usuario: " + proceso.info().user().orElse("No disponible"));

            System.out.println("\nResultado del ping:");

            try (BufferedReader lector = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream()))) {

                String linea;

                while ((linea = lector.readLine()) != null) {
                    System.out.println(linea);
                }
            }

            int resultado = proceso.waitFor();

            System.out.println("\nCódigo de resultado: " + resultado);

            System.out.println("¿El proceso está vivo al final?: " + proceso.isAlive());

        } catch (IOException e) {
            System.out.println("Error al ejecutar o leer el proceso: " + e.getMessage());

        } catch (InterruptedException e) {
            System.out.println("La espera del proceso fue interrumpida: " + e.getMessage());

            Thread.currentThread().interrupt();
        }
    }
}
