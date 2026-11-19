package logico;

import java.util.Date;

public class Factura {
    private String codigo;
    private float total, itbis;
    private Plan plan;
    private Date fecha;

    public Factura(String codigo, Plan plan, Date fecha) {
        this.codigo = codigo;
        this.itbis = calcularItbis();
        this.total = calcularTotal();
        this.plan = plan;
        this.fecha = fecha;
    }

    private float calcularTotal()
	{
		return plan.getPrecio() + itbis;
	}
    
    private float calcularItbis() 
    {
    	return (float) (plan.getPrecio() * Orange.ITBIS);
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}