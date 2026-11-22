package logico;

import java.io.Serializable;

public class Usuario implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6571070610349808195L;

	private String id, userName, password;
	private Empleado empleado;

	public Usuario(String id, String tipo, String userName, String password, Empleado empleado)
	{
		this.id = id;
		this.userName = userName;
		this.password = password;
		if(tipo.equalsIgnoreCase("Comercial"))
		{
			this.empleado = (Comercial) empleado;
		}
		else 
		{
			this.empleado = (Administrador) empleado;
		}

	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Empleado getEmpleado()
	{
		return empleado;
	}

	public void setEmpleado(Empleado empleado)
	{
		this.empleado = empleado;
	}

}
