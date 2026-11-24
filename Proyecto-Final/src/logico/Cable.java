package logico;

public class Cable extends Servicio
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2889047717103561938L;
	private int cantCanales;
	private boolean hd;
	
	public Cable(String codigo, float precio, int cantCanales, boolean hd)
	{
		super(codigo, precio);
		this.cantCanales = cantCanales;
		this.hd = hd;
	}

	public int getCantCanales()
	{
		return cantCanales;
	}

	public void setCantCanales(int cantCanales)
	{
		this.cantCanales = cantCanales;
	}

	public boolean isHd()
	{
		return hd;
	}

	public void setHd(boolean hd)
	{
		this.hd = hd;
	}

	@Override
	public String toString()
	{
		return cantCanales + " canales disponibles " + (hd ? "Incluyendo la definicion HD" : "") + "(RD$ " + precio + ")";
	}
	
}
