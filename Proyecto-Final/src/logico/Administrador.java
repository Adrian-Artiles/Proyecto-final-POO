package logico;

import java.time.LocalDate;

public class Administrador extends Empleado
{
	private String departamento;

	public Administrador(String codigo, String cedula, String nombre, String apellido, String telefono,
			String direccion, String correo, char sexo, float salarioBase, LocalDate fechaIng, String departamento)
	{
		super(codigo, cedula, nombre, apellido, telefono, direccion, correo, sexo, salarioBase, fechaIng);
		this.departamento = departamento;
	}

	public String getDepartamento()
	{
		return departamento;
	}

	public void setDepartamento(String departamento)
	{
		this.departamento = departamento;
	}
	
	
}
