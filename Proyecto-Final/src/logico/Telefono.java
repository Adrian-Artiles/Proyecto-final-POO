package logico;

public class Telefono extends Servicio {
    private int minutosLlamada;
    private int datos;

    public Telefono(String codigo, float precio, int minutosLlamada, int datos) {
        super(codigo, precio);
        this.minutosLlamada = minutosLlamada;
        this.datos = datos;
    }

    public int getMinutosLlamada() {
        return minutosLlamada;
    }

    public void setMinutosLlamada(int minutosLlamada) {
        this.minutosLlamada = minutosLlamada;
    }

    public int getDatos() {
        return datos;
    }

    public void setDatos(int datos) {
        this.datos = datos;
        
    }
}