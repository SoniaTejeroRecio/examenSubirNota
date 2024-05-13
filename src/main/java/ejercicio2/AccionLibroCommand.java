package ejercicio2;

public interface AccionLibroCommand {
    void ejecutar() throws ExcepcionStockDesbordado;
}

class AñadirStockCommand implements AccionLibroCommand {
    private ILibro libro;
    private int cantidad;

    public AñadirStockCommand(ILibro libro, int cantidad) {
        this.libro = libro;
        this.cantidad = cantidad;
    }

    @Override
    public void ejecutar() {
        try {
            libro.añadirStock(cantidad);
            System.out.println("El libro se ha añadido correctamente.");
        } catch (NullPointerException e) {
            System.out.println("Error: Intento de añadir stock a un libro que no existe.");
        } catch (ExcepcionStockDesbordado e) {
            System.out.println(e.getMessage());
        }
    }
}




class QuitarStockCommand implements AccionLibroCommand {
    private ILibro libro;
    private int cantidad;

    public QuitarStockCommand(ILibro libro, int cantidad) {
        this.libro = libro;
        this.cantidad = cantidad;
    }

    @Override
    public void ejecutar() throws ExcepcionStockDesbordado {
        try {
            libro.quitarStock(cantidad);
            System.out.println("El libro se ha quitado correctamente.");
        } catch (NullPointerException e) {
            System.out.println("Error: Intento de quitar stock a un libro que no existe.");
        } catch (ExcepcionStockDesbordado e) {
            System.out.println(e.getMessage());
        }
    }
}



class ConsultarStockCommand implements AccionLibroCommand {
    private GestorDeStock gestorDeStock;

    public ConsultarStockCommand(GestorDeStock gestorDeStock) {
        this.gestorDeStock = gestorDeStock;
    }

    @Override
    public void ejecutar() {
        try {
            System.out.println("Stock actual: " + gestorDeStock.consultarStock());
        } catch (NullPointerException e) {
            System.out.println("Error: Intento de consultar stock de un libro que no existe.");
        }
    }
}
