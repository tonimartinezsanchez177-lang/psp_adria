
public class Impresora implements Runnable {

    // Atributos de la impresora.
    private String nombre;
    private int numeroPaginas;

    // Constructor que recibe el nombre y el número de páginas.
    public Impresora(String nombre, int numeroPaginas) {
        this.nombre = nombre;
        this.numeroPaginas = numeroPaginas;
    }

    // Método que ejecutará el hilo.
    @Override
    public void run() {

        // Recorremos todas las páginas que debe imprimir la impresora.
        for (int pagina = 1; pagina <= numeroPaginas; pagina++) {

            System.out.println(nombre + " está imprimiendo la página "
                    + pagina + " de " + numeroPaginas);

            try {
                // Simulamos el tiempo necesario para imprimir una página.
                Thread.sleep(1000);

            } catch (InterruptedException e) {

                // Restauramos el estado de interrupción del hilo.
                Thread.currentThread().interrupt();

                // Terminamos la tarea si el hilo ha sido interrumpido.
                System.out.println(nombre + " ha sido interrumpida.");
                return;
            }
        }
        System.out.println(nombre + " ha terminado de imprimir.");
    }
}
