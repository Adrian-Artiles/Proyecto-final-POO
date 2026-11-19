package logico;

import java.time.LocalDate;

public class Comercial extends Empleado
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6321178053883048755L;
	private float comision;
	private int ventas;

	public Comercial(String codigo, String cedula, String nombre, String apellido, String telefono, String direccion,
			String correo, char sexo, float salarioBase, LocalDate fechaIng, float comision, int ventas)
	{
		super(codigo, cedula, nombre, apellido, telefono, direccion, correo, sexo, salarioBase, fechaIng);
		this.comision = comision;
		this.ventas = ventas;
	}

	public float getComision()
	{
		return comision;
	}

	public void setComision(float comision)
	{
		this.comision = comision;
	}

	public int getVentas()
	{
		return ventas;
	}

	public void setVentas(int ventas)
	{
		this.ventas = ventas;
	}

	@Override
	public float calcularSalario()
	{
		return salarioBase + (comision * ventas);
	}

}
