package logico;

import java.util.ArrayList;
import java.util.Date;

public class Contrato
{
	private String codigo;
	private Cliente cliente;
	private Date fecha;
	private float precioMensual;
	private boolean activo;
	private Plan plan;
	private ArrayList<Factura> pagos;
	
	public Contrato(String codigo, Cliente cliente, Date fecha, float precioMensual, boolean activo, Plan plan,
			ArrayList<Factura> pagos)
	{
		this.codigo = codigo;
		this.cliente = cliente;
		this.fecha = fecha;
		this.precioMensual = precioMensual;
		this.activo = activo;
		this.plan = plan;
		this.pagos = pagos;
	}

	public String getCodigo()
	{
		return codigo;
	}

	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	public void setCliente(Cliente cliente)
	{
		this.cliente = cliente;
	}

	public Date getFecha()
	{
		return fecha;
	}

	public void setFecha(Date fecha)
	{
		this.fecha = fecha;
	}

	public float getPrecioMensual()
	{
		return precioMensual;
	}

	public void setPrecioMensual(float precioMensual)
	{
		this.precioMensual = precioMensual;
	}

	public boolean isActivo()
	{
		return activo;
	}

	public void setActivo(boolean activo)
	{
		this.activo = activo;
	}

	public Plan getPlan()
	{
		return plan;
	}

	public void setPlan(Plan plan)
	{
		this.plan = plan;
	}

	public ArrayList<Factura> getPagos()
	{
		return pagos;
	}

	public void setPagos(ArrayList<Factura> pagos)
	{
		this.pagos = pagos;
	}

}
