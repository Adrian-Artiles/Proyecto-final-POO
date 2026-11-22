package logico;

import java.time.LocalDate;

public class Administrador extends Empleado
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1753843119306340045L;
	private String departamento;

	public Administrador(String codigo, String cedula, String nombre, String apellido, String telefono,
			String direccion, String correo, String userName, String clave, char sexo, float salarioBase,
			LocalDate fechaIng, String departamento)
	{
		super(codigo, cedula, nombre, apellido, telefono, direccion, correo, userName, clave, sexo, salarioBase,
				fechaIng);
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

	@Override
	public float calcularSalario()
	{
		return salarioBase + calcularBono();
	}
	
	private float calcularBono() 
	{
		if(getFechaIng().plusYears(3).isBefore(LocalDate.now())) 
		{
			return getSalarioBase() * 2;
		}
		else 
		{
			return getSalarioBase() + (getSalarioBase()/2.0f);
		}
	}

	
}
