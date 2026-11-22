package logico;

import java.io.Serializable;

import java.util.ArrayList;

public class Orange implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3036088393140392770L;
	private ArrayList<Cliente> clientes;
	private ArrayList<Empleado> empleados;
	private ArrayList<Contrato> contratos;
	private ArrayList<Plan> planes;
	private ArrayList<Factura> ventas;
	private static Orange o = null;
	public static final float ITBIS = 0.18f;

	private Orange()
	{
		this.empleados = new ArrayList<>();
		this.clientes = new ArrayList<>();
		this.contratos = new ArrayList<>();
		this.planes = new ArrayList<>();
		this.ventas = new ArrayList<>();
	}

	public static Orange getInstancia() 
	{
		if(o == null) 
		{
			o = new Orange();
		}

		return o;
	}
	
	public static void setOrange(Orange r) 
	{
		o = r;
	}

	public ArrayList<Contrato> getContratos()
	{
		return contratos;
	}

	public void setContratos(ArrayList<Contrato> contratos)
	{
		this.contratos = contratos;
	}

	public ArrayList<Plan> getPlanes()
	{
		return planes;
	}

	public void setPlanes(ArrayList<Plan> planes)
	{
		this.planes = planes;
	}

	public ArrayList<Factura> getVentas()
	{
		return ventas;
	}

	public void setVentas(ArrayList<Factura> ventas)
	{
		this.ventas = ventas;
	}

	public Cliente buscarClienteByEmail(String correo)
	{
		for (Cliente c : clientes)
		{
			if(c.getCorreo().equalsIgnoreCase(correo)) 
			{
				return c;
			}
		}

		return null;
	}

	public Empleado buscarTrabajadorByEmail(String correo)
	{
		for (Empleado t: empleados)
		{
			if(t.getCorreo().equalsIgnoreCase(correo)) 
			{
				return t;
			}
		}

		return null;
	}
	
	public Plan buscarPlanByCod(String cod)
	{
		for (Plan p: planes)
		{
			if(p.getCodigo().equalsIgnoreCase(cod)) 
			{
				return p;
			}
		}

		return null;
	}
	
	public Factura buscarFacturaByCod(String cod)
	{
		for (Factura f: ventas)
		{
			if(f.getCodigo().equalsIgnoreCase(cod)) 
			{
				return f;
			}
		}

		return null;
	}

	public Persona buscarUserByNameE(String user)
	{
		for (Empleado e: empleados)
		{
			if(e.getCodigo().equalsIgnoreCase(user)) 
			{
				return e;
			}
		}
		
		return null;
	}

	public void regAdmin(Administrador aux)
	{
		empleados.add(aux);
		
	}

}
