package logico;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Contrato implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5027188198966103904L;
	private String codigo;
	private Cliente cliente;
	private LocalDate fechaIng;
	private LocalDate fechaFin;
	private float precioMensual;
	private boolean activo;
	private Plan plan;
	private ArrayList<Factura> facturas;

	public Contrato(String codigo, Cliente cliente, LocalDate fechaIng, LocalDate fechaFin, float precioMensual,
			Plan plan, ArrayList<Factura> pagos)
	{
		this.codigo = codigo;
		this.cliente = cliente;
		this.fechaIng = fechaIng;
		this.fechaFin = fechaFin;
		this.precioMensual = precioMensual;
		this.activo = true;
		this.plan = plan;
		this.facturas = pagos;
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

	public LocalDate getFechaIng()
	{
		return fechaIng;
	}

	public void setFechaIng(LocalDate fechaIng)
	{
		this.fechaIng = fechaIng;
	}

	public LocalDate getFechaFin()
	{
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin)
	{
		this.fechaFin = fechaFin;
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

	public ArrayList<Factura> getFacturas()
	{
		return facturas;
	}

	public void setFacturas(ArrayList<Factura> facturas)
	{
		this.facturas = facturas;
	}
	
	public boolean pagoCompleto() 
	{
		boolean encontrado = false;
		int i = 0;
		while(i < facturas.size() && !encontrado) 
		{
			if(!facturas.get(i).isPagada()) 
			{
				encontrado = true;
			}
			i++;
		}
		
		return !encontrado;
	}
	
	public int facturasVencidas() 
	{
		int vencidas = 0;
		LocalDate hoy = LocalDate.now();

		for (Factura f : facturas) 
		{
			if (!f.isPagada() && f.getFecha().isBefore(hoy)) 
			{
				vencidas++;
			}
		}

		return vencidas;
	}
	
	public boolean verificarContrato() 
	{
		activo = facturasVencidas() >= 3 ? false : activo;

		return activo;
	}

}
