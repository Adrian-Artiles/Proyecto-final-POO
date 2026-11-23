package visual;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Empleado;
import logico.Orange;
import logico.Persona;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;
import java.awt.Color;

public class RegUser extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3091369970725956372L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JPasswordField txtPassC;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private Empleado e;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			RegUser dialog = new RegUser(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegUser(Empleado aux)
	{
		e = aux;
		setTitle(e != null ? "Modificar Usuario" : "Registrar Usuario");
		setBounds(100, 100, 585, 660);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Empleado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new GridLayout(0, 3, 0, 10));
			{
				JLabel lblNewLabel = new JLabel("Tipo:");
				lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
				panel.add(lblNewLabel);
			}
			
			JRadioButton rdbtnComercial = new JRadioButton("Comercial");
			buttonGroup.add(rdbtnComercial);
			rdbtnComercial.setSelected(true);
			rdbtnComercial.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
			panel.add(rdbtnComercial);
			
			JRadioButton rdbtnAdmin = new JRadioButton("Administrador");
			buttonGroup.add(rdbtnAdmin);
			rdbtnAdmin.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
			panel.add(rdbtnAdmin);
			{
				JLabel lblContrasea = new JLabel("Nombre de Usuario:");
				lblContrasea.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
				panel.add(lblContrasea);
			}
			{
				txtUser = new JTextField();
				txtUser.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
				txtUser.setColumns(10);
				panel.add(txtUser);
			}
			
			JLabel label_4 = new JLabel("");
			label_4.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
			panel.add(label_4);
			{
				JLabel lblConfirmarContrasea = new JLabel("Contrase\u00F1a:");
				lblConfirmarContrasea.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
				panel.add(lblConfirmarContrasea);
			}
			
						txtPass = new JPasswordField();
						txtPass.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
						panel.add(txtPass);
			
			JLabel lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
			panel.add(lblNewLabel_1);
			{
				JLabel lblContrasea_1 = new JLabel("Confirmar Contrase\u00F1a:");
				lblContrasea_1.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
				panel.add(lblContrasea_1);
			}
			
						txtPassC = new JPasswordField();
						txtPassC.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
						panel.add(txtPassC);
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "TITULO", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("CODIGO:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(12, 60, 130, 34);
		panel.add(lblNewLabel_2);
		
		JLabel lblCedula = new JLabel("CEDULA:");
		lblCedula.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblCedula.setBounds(12, 107, 130, 34);
		panel.add(lblCedula);
		
		JLabel lblNombre = new JLabel("NOMBRE:");
		lblNombre.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNombre.setBounds(12, 154, 130, 34);
		panel.add(lblNombre);
		
		JLabel lblApellidos = new JLabel("APELLIDOS:");
		lblApellidos.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblApellidos.setBounds(12, 201, 130, 34);
		panel.add(lblApellidos);
		
		JLabel lblTelefono = new JLabel("TELEFONO:");
		lblTelefono.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblTelefono.setBounds(12, 248, 130, 34);
		panel.add(lblTelefono);
		
		JLabel lblDireccion = new JLabel("DIRECCION:");
		lblDireccion.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblDireccion.setBounds(12, 295, 130, 34);
		panel.add(lblDireccion);
		
		JLabel lblCorreo = new JLabel("CORREO:");
		lblCorreo.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblCorreo.setBounds(12, 342, 130, 34);
		panel.add(lblCorreo);
		
		JLabel lblSexo = new JLabel("SEXO:");
		lblSexo.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblSexo.setBounds(12, 13, 130, 34);
		panel.add(lblSexo);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Masculino");
		buttonGroup_1.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setBounds(144, 13, 109, 34);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnFemenino = new JRadioButton("Femenino");
		buttonGroup_1.add(rdbtnFemenino);
		rdbtnFemenino.setBounds(270, 14, 114, 34);
		panel.add(rdbtnFemenino);
		
		textField = new JTextField();
		textField.setForeground(Color.WHITE);
		textField.setEditable(false);
		textField.setBounds(144, 61, 280, 34);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(144, 114, 280, 34);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(144, 161, 280, 34);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(144, 208, 280, 34);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(144, 255, 280, 34);
		panel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(144, 302, 280, 34);
		panel.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(144, 349, 280, 34);
		panel.add(textField_6);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCrear = new JButton("Crear");
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(usuarioRepetido(txtUser.getText()) && passCorrect(String.valueOf(txtPass.getPassword().toString()))) 
						{
							if(String.valueOf(txtPass.getPassword().toString()).equals(String.valueOf(txtPassC.getPassword().toString()))) 
							{
								if(Validador()) 
								{
									
								}
								
							}
							else 
							{
								
							}
						}
					}

					private boolean Validador() {
						// TODO Auto-generated method stub
						return false;
					}

					private boolean usuarioRepetido(String text) {
						// TODO Auto-generated method stub
						return false;
					}
				});
				btnCrear.setActionCommand("OK");
				buttonPane.add(btnCrear);
				getRootPane().setDefaultButton(btnCrear);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public boolean passCorrect(String pass) {
		boolean valido = true;
		if (pass == null || pass.trim().isEmpty()) {
			valido = false;
		} else if (pass.length() < 8 || pass.length() > 12) {
			valido = false;
		} else {
			int i = 0;
			boolean numero = false;
			boolean mayuscula = false;
			boolean minuscula = false;
			while (i < pass.length() && valido) {
				char ch = pass.charAt(i);

				if (Character.isDigit(ch)) {
					numero = true;
				} else if (Character.isUpperCase(ch)) {
					mayuscula = true;
				} else if (Character.isLowerCase(ch)) {
					minuscula = true;
				} else if (Character.isWhitespace(ch)) {
					valido = false;
				}
				i++;
			}

			if (!numero || !mayuscula || !minuscula) {
				valido = false;
			}
		}

		return valido;
	}
}
