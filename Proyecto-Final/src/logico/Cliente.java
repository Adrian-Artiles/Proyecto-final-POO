package logico;

import java.util.ArrayList;

public class Cliente extends Persona
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5301228225327706307L;
	private ArrayList<Contrato> contratos;
	private boolean deudor;

	public Cliente(String codigo, String cedula, String nombre, String apellido, String telefono, String direccion,
			String correo, String userName, String clave, char sexo, ArrayList<Contrato> contratos)
	{
		super(codigo, cedula, nombre, apellido, telefono, direccion, correo, userName, clave, sexo);
		this.contratos = new ArrayList<>();
		this.deudor = false;
	}

	public boolean isDeudor()
	{
		return deudor;
	}

	public void setDeudor(boolean deudor)
	{
		this.deudor = deudor;
	}

	public ArrayList<Contrato> getContratos()
	{
		return contratos;
	}

	public void setContratos(ArrayList<Contrato> contratos)
	{
		this.contratos = contratos;
	}
	
	public boolean planContratado(Plan p) 
	{
		boolean encontrado = false;
		int i = 0;

		while (i < contratos.size() && !encontrado) 
		{
			if (contratos.get(i).getPlan().getCodigo().equals(p.getCodigo())) 
			{
				encontrado = true;
			}
			i++;
		}

		return encontrado;
	}

	public void agregarContrato(Contrato nuevo)
	{
		contratos.add(nuevo);
	}

	@Override
	public String toString()
	{
		return codigo + " - " + nombre + " " + apellido + " (" + cedula + ")";
	}
	
	public boolean clientePago() 
	{
		for(Contrato ct: contratos) 
		{
			if(!ct.pagoCompleto()) 
			{
				return false;
			}
		}
		
		return true;
	}

}
