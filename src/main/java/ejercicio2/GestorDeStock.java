package ejercicio2;

import java.util.Collections;
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
                command = new ConsultarStockCommand(this); // pass the current GestorDeStock instance
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

    public Iterable<Object> getLibros() {
        return Collections.singleton(libros.entrySet());
    }

    public String consultarStock() {
        StringBuilder stock = new StringBuilder();
        for (HashMap.Entry<String, ILibro> entry : libros.entrySet()) {
            stock.append("ISBN: ").append(entry.getKey()).append(" Stock: ").append(entry.getValue().consultarStock()).append("\n");
        }
        return stock.toString();
    }
}