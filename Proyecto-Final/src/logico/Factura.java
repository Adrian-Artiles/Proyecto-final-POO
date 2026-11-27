package logico;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Factura implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6184703847509367981L;
	private String codigo;
    private float precio, itbis, total;
    private Plan plan;
    private LocalDate fecha;
    private boolean pagada;

    public Factura(String codigo, Plan plan, LocalDate fecha, float precio) {
        this.codigo = codigo;
        this.precio = precio;
        this.itbis = precio * Orange.ITBIS;
        this.total = precio + itbis;
        this.plan = plan;
        this.fecha = fecha;
    }

	public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getItbis()
	{
		return itbis;
	}

	public void setItbis(float itbis)
	{
		this.itbis = itbis;
	}

	public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

	public float getPrecio()
	{
		return precio;
	}

	public void setPrecio(float precio)
	{
		this.precio = precio;
	}

	public LocalDate getFecha()
	{
		return fecha;
	}

	public void setFecha(LocalDate fecha)
	{
		this.fecha = fecha;
	}

	public boolean isPagada()
	{
		return pagada;
	}

	public void setPagada(boolean pagada)
	{
		this.pagada = pagada;
	}
    
}