package logico;

public class Cliente extends Persona
{
	private ArrayList<Plan> planes;

	public Cliente(String codigo, String cedula, String nombre, String apellido, String telefono, String direccion,
			String correo, char sexo)
	{
		super(codigo, cedula, nombre, apellido, telefono, direccion, correo, sexo);
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
