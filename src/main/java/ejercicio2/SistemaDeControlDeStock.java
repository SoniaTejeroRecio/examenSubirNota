package ejercicio2;
import java.util.Scanner;

public class SistemaDeControlDeStock {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ItemFactory factory = new ItemFactory() {
            @Override
            public ILibro crearLibro() {
                return null;
            }

            @Override
            public IRevista crearRevista() {
                return null;
            }

            @Override
            public IPeriodico crearPeriodico() {
                return null;
            }
        };
        GestorDeStock gestorDeStock = new GestorDeStock(factory);

        while (true) {
            System.out.println("\nIntroducir el ISBN del libro o 'salir' para finalizar:");
            String isbn = scanner.nextLine();

            if ("salir".equalsIgnoreCase(isbn)) {
                break;
            }

            System.out.println("ISBN " + isbn);
            System.out.println("¿Acción? (añadir, quitar, consultar, salir):");
            String accion = scanner.nextLine();

            if ("salir".equalsIgnoreCase(accion)) {
                break;
            }

            try {
                if (!accion.equals("consultar")) {
                    System.out.println("Introducir cantidad a añadir:");
                    long cantidad = Long.parseLong(scanner.nextLine());

                    if (cantidad > Integer.MAX_VALUE || cantidad < 0) { // Prevenir entrada negativa y desbordamiento
                        throw new ExcepcionStockDesbordado("El stock del libro excede la capacidad de este sistema.");
                    }

                    gestorDeStock.gestionarAccion(isbn, accion, (int) cantidad);
                } else {
                    gestorDeStock.gestionarAccion(isbn, accion, 0); // Consultar stock actual
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduzca un número válido.");
            } catch (ExcepcionStockDesbordado e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}
