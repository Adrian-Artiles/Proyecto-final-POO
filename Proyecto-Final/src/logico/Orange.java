package logico;

import java.util.ArrayList;
import java.util.HashMap;


public class Orange
{
	private HashMap<String, Cliente> usuariosCliente;
	private HashMap<String, Empleado> usuariosEmpleados;
	private ArrayList<Contrato> contratos;
	private ArrayList<Plan> planes;
	private ArrayList<Factura> ventas;
	private static Orange o = null;

	private Orange()
	{
		this.usuariosEmpleados = new HashMap<>();
		this.usuariosCliente = new HashMap<>();
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

	public HashMap<String, Empleado> getUsuariosEmpleados()
	{
		return usuariosEmpleados;
	}

	public void setUsuariosEmpleados(HashMap<String, Empleado> usuariosTrabajadores)
	{
		this.usuariosEmpleados = usuariosTrabajadores;
	}

	public HashMap<String, Cliente> getUsuariosCliente()
	{
		return usuariosCliente;
	}

	public void setUsuariosCliente(HashMap<String, Cliente> usuariosCliente)
	{
		this.usuariosCliente = usuariosCliente;
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
		for (Cliente c : usuariosCliente.values())
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
		for (Empleado t: usuariosEmpleados.values())
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

}
