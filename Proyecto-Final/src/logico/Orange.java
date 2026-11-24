package logico;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
	private ArrayList<Persona> usuarios;
	private ArrayList<Contrato> contratos;
	ArrayList<Servicio> servicios;
	private ArrayList<Plan> planes;
	private ArrayList<Factura> ventas;
	private static Orange o = null;

	public static final float ITBIS = 0.18f;
	public static int generarCodCliente = 1;
	public static int generarCodEmp = 1;
	public static int generarCodCont = 1;
	public static int generarCodServ = 1;
	public static int generarCodPlan = 1;
	public static int generarCodFact = 1;

	private Orange()
	{
		this.empleados = new ArrayList<>();
		this.clientes = new ArrayList<>();
		this.contratos = new ArrayList<>();
		this.planes = new ArrayList<>();
		this.ventas = new ArrayList<>();
		this.usuarios = new ArrayList<>();
		this.servicios = new ArrayList<>();
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
		generarCodCliente = Orange.getInstancia().clientes.size() + 1;
		generarCodEmp = Orange.getInstancia().empleados.size() + 1;
		generarCodCont = Orange.getInstancia().contratos.size() + 1;
		generarCodServ = Orange.getInstancia().servicios.size() + 1;
		generarCodPlan = Orange.getInstancia().planes.size() + 1;
		generarCodFact = Orange.getInstancia().ventas.size() + 1;
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

	public ArrayList<Cliente> getClientes()
	{
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes)
	{
		this.clientes = clientes;
	}

	public ArrayList<Empleado> getEmpleados()
	{
		return empleados;
	}

	public void setEmpleados(ArrayList<Empleado> empleados)
	{
		this.empleados = empleados;
	}

	public ArrayList<Persona> getUsuarios()
	{
		return usuarios;
	}

	public void setUsuarios(ArrayList<Persona> usuarios)
	{
		this.usuarios = usuarios;
	}

	public ArrayList<Servicio> getServicios()
	{
		return servicios;
	}

	public void setServicios(ArrayList<Servicio> servicios)
	{
		this.servicios = servicios;
	}

	public void guardarArchivo() 
	{
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Orange.dat")))
		{
			oos.writeObject(Orange.getInstancia());
			oos.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
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

	public Persona buscarUserByNameC(String user)
	{
		for (Cliente c: clientes)
		{
			if(c.getCodigo().equalsIgnoreCase(user)) 
			{
				return c;
			}
		}

		return null;
	}
	
	public boolean usuarioRepetido(String user) 
	{
		for (Persona p : usuarios) 
		{
			if (p.getUserName().equalsIgnoreCase(user)) 
			{
				return true;
			}
		}

		return false;
	}

	public Persona loginCorrecto(String user, String clave)
	{
		Persona aux = null;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < usuarios.size()) 
		{
			Persona u = usuarios.get(i);
			if(u.getUserName().equals(user) && u.getClave().equals(clave)) 
			{
				encontrado = true;
				aux = u;
			}
			i++;
		}

		return aux;
	}

	public void regEmpleado(Empleado aux)
	{
		if(aux instanceof Comercial) 
		{
			empleados.add((Comercial) aux);
			usuarios.add((Comercial) aux);
		}
		else 
		{
			empleados.add((Administrador) aux);
			usuarios.add((Administrador) aux);
		}

		generarCodEmp++;

		guardarArchivo();
	}

	public void regCliente(Cliente nuevo)
	{
		clientes.add(nuevo);
		usuarios.add(nuevo);

		generarCodCliente++;

		guardarArchivo();
		
	}

	public void regServicio(Servicio s)
	{
		servicios.add(s);

		generarCodServ++;

		guardarArchivo();
		
	}

	public void regPlan(Plan p)
	{
		planes.add(p);
		
		generarCodPlan++;

		guardarArchivo();
	}

}
