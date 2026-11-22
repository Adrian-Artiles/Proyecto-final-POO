package logico;

import java.io.Serializable;

public abstract class Servicio implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1114372327598340154L;
	protected String codigo;
    protected float precio;

    public Servicio(String codigo, float precio) {
        this.codigo = codigo;
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}