package logico;

import java.util.ArrayList;

public class Cliente extends Persona
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5301228225327706307L;
	private ArrayList<Plan> planes;

	public Cliente(String codigo, String cedula, String nombre, String apellido, String telefono, String direccion,
			String correo, char sexo)
	{
		super(codigo, cedula, nombre, apellido, telefono, direccion, correo, sexo);
		planes = new ArrayList<>();
	}

	public ArrayList<Plan> getPlanes()
	{
		return planes;
	}

	public void setPlanes(ArrayList<Plan> planes)
	{
		this.planes = planes;
	}

}
