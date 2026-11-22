package logico;

public class Telefono extends Servicio {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1085834337029539445L;
	private String numero;
    private int minutosLlamada;
    private int datos;

    public Telefono(String codigo, float precio, String numero, int minutosLlamada, int datos) {
        super(codigo, precio);
        this.numero = numero;
        this.minutosLlamada = minutosLlamada;
        this.datos = datos;
    }

    public String getNumero()
	{
		return numero;
	}

	public void setNumero(String numero)
	{
		this.numero = numero;
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