package logico;

import java.time.LocalDate;

public abstract class Empleado extends Persona
{
	protected float salarioBase;
	protected LocalDate fechaIng;

	public Empleado(String codigo, String cedula, String nombre, String apellido, String telefono, String direccion,
			String correo, char sexo, float salarioBase, LocalDate fechaIng)
	{
		super(codigo, cedula, nombre, apellido, telefono, direccion, correo, sexo);
		this.salarioBase = salarioBase;
		this.fechaIng = fechaIng;
	}

	public float getSalarioBase()
	{
		return salarioBase;
	}

	public void setSalarioBase(float salarioBase)
	{
		this.salarioBase = salarioBase;
	}

	public LocalDate getFechaIng()
	{
		return fechaIng;
	}

	public void setFechaIng(LocalDate fechaIng)
	{
		this.fechaIng = fechaIng;
	}
	
}
