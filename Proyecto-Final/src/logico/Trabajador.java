package logico;

public class Trabajador extends Persona
{
	private float salarioBase;

	public Trabajador(String codigo, String cedula, String nombre, String apellido, String telefono, String direccion,
			String correo, char sexo, float salarioBase)
	{
		super(codigo, cedula, nombre, apellido, telefono, direccion, correo, sexo);
		this.salarioBase = salarioBase;
	}

	public float getSalarioBase()
	{
		return salarioBase;
	}

	public void setSalarioBase(float salarioBase)
	{
		this.salarioBase = salarioBase;
	}

}
