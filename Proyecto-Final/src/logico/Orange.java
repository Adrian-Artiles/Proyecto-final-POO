package logico;

import java.util.ArrayList;
import java.util.HashMap;


public class Orange
{
	private HashMap<String, Cliente> usuariosCliente;
	private HashMap<String, Trabajador> usuariosTrabajadores;
	private ArrayList<Plan> planes;
	private ArrayList<Factura> ventas;
	private static Orange o = null;

	private Orange()
	{
		this.usuariosTrabajadores = new HashMap<>();
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

	public HashMap<String, Trabajador> getUsuariosTrabajadores()
	{
		return usuariosTrabajadores;
	}

	public void setUsuariosTrabajadores(HashMap<String, Trabajador> usuariosTrabajadores)
	{
		this.usuariosTrabajadores = usuariosTrabajadores;
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

	public Trabajador buscarTrabajadorByEmail(String correo)
	{
		for (Trabajador t: usuariosTrabajadores.values())
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
