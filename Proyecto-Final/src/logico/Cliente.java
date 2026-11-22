package logico;

import java.util.ArrayList;

public class Cliente extends Persona
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5301228225327706307L;
	private ArrayList<Contrato> contratos;

	public Cliente(String codigo, String cedula, String nombre, String apellido, String telefono, String direccion,
			String correo, String userName, String clave, char sexo, ArrayList<Contrato> contratos)
	{
		super(codigo, cedula, nombre, apellido, telefono, direccion, correo, userName, clave, sexo);
		this.contratos = new ArrayList<>();
	}

	public ArrayList<Contrato> getContratos()
	{
		return contratos;
	}

	public void setContratos(ArrayList<Contrato> contratos)
	{
		this.contratos = contratos;
	}

}
