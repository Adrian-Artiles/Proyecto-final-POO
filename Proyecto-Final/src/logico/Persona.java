package logico;

import java.io.Serializable;

public abstract class Persona implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6627593761722309894L;
	protected String codigo, cedula, nombre, apellido, telefono, direccion, correo, userName, clave;
	protected char sexo;
	
	public Persona(String codigo, String cedula, String nombre, String apellido, String telefono, String direccion,
			String correo, String userName, String clave, char sexo)
	{
		this.codigo = codigo;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.direccion = direccion;
		this.correo = correo;
		this.userName = userName;
		this.clave = clave;
		this.sexo = sexo;
	}
	
	public String getCodigo()
	{
		return codigo;
	}
	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}
	public String getCedula()
	{
		return cedula;
	}
	public void setCedula(String cedula)
	{
		this.cedula = cedula;
	}
	public String getNombre()
	{
		return nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	public String getApellido()
	{
		return apellido;
	}
	public void setApellido(String apellido)
	{
		this.apellido = apellido;
	}
	public String getTelefono()
	{
		return telefono;
	}
	public void setTelefono(String telefono)
	{
		this.telefono = telefono;
	}
	public String getDireccion()
	{
		return direccion;
	}
	public void setDireccion(String direccion)
	{
		this.direccion = direccion;
	}
	public String getCorreo()
	{
		return correo;
	}
	public void setCorreo(String correo)
	{
		this.correo = correo;
	}
	public char getSexo()
	{
		return sexo;
	}
	public void setSexo(char sexo)
	{
		this.sexo = sexo;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getClave()
	{
		return clave;
	}

	public void setClave(String clave)
	{
		this.clave = clave;
	}
	
}
