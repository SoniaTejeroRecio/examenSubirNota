package ejercicio2;

import java.util.HashMap;

public class GestorDeStock {
    private HashMap<String, ILibro> libros = new HashMap<>();
    private ItemFactory factory;

    public GestorDeStock(ItemFactory factory) {
        this.factory = factory;
    }

    public void gestionarAccion(String isbn, String accion, int cantidad) {
        ILibro libro = libros.get(isbn);
         if (libro == null) {
            libro = factory.crearLibro();
            libros.put(isbn, libro);
        }
        AccionLibroCommand command;


        switch (accion) {
            case "añadir":
                command = new AñadirStockCommand(libro, cantidad);
                break;
            case "quitar":
                command = new QuitarStockCommand(libro, cantidad);
                break;
            case "consultar":
                command = new ConsultarStockCommand(libro);
                break;
            default:
                System.out.println("Acción no reconocida.");
                return;
        }

        try {
            command.ejecutar();
        } catch (ExcepcionStockDesbordado e) {
            System.out.println(e.getMessage());
        }
    }
}