package logico;

public class InternetHogar extends Servicio {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7723183833292069825L;
	private int velocidadSubida;
    private int velocidadBajada;
    private String direccion;

    public InternetHogar(String codigo, float precio, int velocidadSubida, int velocidadBajada, String direccion) {
        super(codigo, precio);
        this.velocidadSubida = velocidadSubida;
        this.velocidadBajada = velocidadBajada;
        this.direccion = direccion;
    }

    public int getVelocidadSubida() {
        return velocidadSubida;
    }

    public void setVelocidadSubida(int velocidadSubida) {
        this.velocidadSubida = velocidadSubida;
    }

    public int getVelocidadBajada() {
        return velocidadBajada;
    }

    public void setVelocidadBajada(int velocidadBajada) {
        this.velocidadBajada = velocidadBajada;
    }

	public String getDireccion()
	{
		return direccion;
	}

	public void setDireccion(String direccion)
	{
		this.direccion = direccion;
	}
    
}