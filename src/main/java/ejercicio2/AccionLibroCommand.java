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
        // Implement the logic for the 'ejecutar' method here
    }
}



    class ConsultarStockCommand implements AccionLibroCommand {
        private ILibro libro;

        public ConsultarStockCommand(ILibro libro) {
            this.libro = libro;
        }

        @Override
        public void ejecutar() throws ExcepcionStockDesbordado {
            //TODO: Implementar
        }
    }
