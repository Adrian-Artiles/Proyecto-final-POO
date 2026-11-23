package logico;

import javax.swing.JOptionPane;

public class Validador {

	public static boolean campoNoVacio(String texto, String nombreCampo) {
		if (texto == null || texto.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, nombreCampo + " no puede estar vacio", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public static boolean comprobacionCed(String cedula, int tam, String mensaje) {
		boolean encontrado = true;

		if (cedula == null || cedula.trim().isEmpty() || cedula.length() != tam) {
			JOptionPane.showMessageDialog(null, mensaje + " debe tener " + tam + " digitos", "ERROR", JOptionPane.ERROR_MESSAGE);
			encontrado = false;
		}

		int i = 0;
		while (i < cedula.length() && encontrado) {
			char ch = cedula.charAt(i);

			if (!Character.isDigit(ch)) {
				JOptionPane.showMessageDialog(null, mensaje + " solo debe tener digitos (0-9)", "ERROR", JOptionPane.ERROR_MESSAGE);
				encontrado = false;
			}

			i++;
		}

		return encontrado;
	}

	public static boolean soloLetras(String texto, String nombreCampo) {
		if (!campoNoVacio(texto, nombreCampo)) {
			return false;
		}
		int i = 0;
		while (i < texto.length()) {
			char ch = texto.charAt(i);
			if (!Character.isLetter(ch) && !Character.isWhitespace(ch)) {
				JOptionPane.showMessageDialog(null, nombreCampo + " solo debe tener letras", "ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			i++;
		}
		return true;
	}

	public static boolean numeroFloatValido(String texto, String nombreCampo) {
		if (!campoNoVacio(texto, nombreCampo)) {
			return false;
		}
		try {
			float valor = Float.parseFloat(texto.trim());
			if (valor < 0) {
				JOptionPane.showMessageDialog(null, nombreCampo + " no puede ser negativo", "ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			return true;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, nombreCampo + " debe ser un numero valido", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public static boolean numeroIntValido(String texto, String nombreCampo) {
		if (!campoNoVacio(texto, nombreCampo)) {
			return false;
		}
		try {
			int valor = Integer.parseInt(texto.trim());
			if (valor < 0) {
				JOptionPane.showMessageDialog(null, nombreCampo + " no puede ser negativo", "ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			return true;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, nombreCampo + " debe ser un numero entero valido", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public static boolean correoValido(String correo) {
		if (!campoNoVacio(correo, "correo")) {
			return false;
		}
		String c = correo.trim();
		if (!c.contains("@") || !c.contains(".")) {
			JOptionPane.showMessageDialog(null, "correo no tiene un formato valido", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public static boolean passCorrect(String pass) {
		boolean valido = true;
		if (pass == null || pass.trim().isEmpty()) 
		{
			valido = false;
		} 
		else if (pass.length() < 8 || pass.length() > 12) 
		{
			valido = false;
		} 
		else 
		{
			int i = 0;
			boolean numero = false;
			boolean mayuscula = false;
			boolean minuscula = false;
			while (i < pass.length() && valido) 
			{
				char ch = pass.charAt(i);

				if (Character.isDigit(ch)) {
					numero = true;
				} else if (Character.isUpperCase(ch)) {
					mayuscula = true;
				} else if (Character.isLowerCase(ch)) {
					minuscula = true;
				} else if (Character.isWhitespace(ch)) {
					valido = false;
					break;
				}
				i++;
			}

			if (!numero || !mayuscula || !minuscula) 
			{
				valido = false;
			}
		}

		return valido;
	}
	
	public static boolean validar(String usuario, String pass, String passC) 
	{
		boolean valido = true;
		if (!Validador.campoNoVacio(usuario, "nombre de usuario")) 
		{
			valido = false;
		}
		else if (Orange.getInstancia().usuarioRepetido(usuario)) 
		{
			JOptionPane.showMessageDialog(null, "ese nombre de usuario ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
			valido = false;
		}
		else if (!Validador.passCorrect(pass)) 
		{
			JOptionPane.showMessageDialog(null, "contraseña debe tener entre 8 a 12 caracteres, sin espacios, con una mayuscula, minuscula y un numero", "ERROR", JOptionPane.ERROR_MESSAGE);
			valido = false;
		}
		else if (!pass.equals(passC)) 
		{
			JOptionPane.showMessageDialog(null, "las contraseñas no coinciden", "ERROR", JOptionPane.ERROR_MESSAGE);
			valido = false;
		}

		return valido;
	}
}