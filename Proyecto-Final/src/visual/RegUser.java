package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Orange;
import logico.Usuario;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;

public class RegUser extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JPasswordField txtPassC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			RegUser dialog = new RegUser();
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
	public RegUser()
	{
		setTitle("Registar Usuario");
		setBounds(100, 100, 578, 283);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Tipo:");
				lblNewLabel.setBounds(12, 13, 145, 30);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblContrasea = new JLabel("Nombre de Usuario:");
				lblContrasea.setBounds(12, 56, 145, 30);
				panel.add(lblContrasea);
			}
			{
				JLabel lblConfirmarContrasea = new JLabel("Contrase\u00F1a:");
				lblConfirmarContrasea.setBounds(12, 99, 145, 30);
				panel.add(lblConfirmarContrasea);
			}
			{
				JLabel lblContrasea_1 = new JLabel("Confirmar Contrase\u00F1a:");
				lblContrasea_1.setBounds(12, 142, 145, 30);
				panel.add(lblContrasea_1);
			}
			{
				txtUser = new JTextField();
				txtUser.setColumns(10);
				txtUser.setBounds(151, 56, 285, 30);
				panel.add(txtUser);
			}

			JComboBox cbxTipo = new JComboBox();
			cbxTipo.setBounds(151, 13, 285, 30);
			panel.add(cbxTipo);

			txtPass = new JPasswordField();
			txtPass.setBounds(151, 99, 285, 30);
			panel.add(txtPass);

			txtPassC = new JPasswordField();
			txtPassC.setBounds(151, 142, 285, 30);
			panel.add(txtPassC);
		}
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
								
							}
							else 
							{
								
							}
						}
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
	
	public boolean usuarioRepetido(String user) 
	{
		Usuario u = Orange.getInstancia().buscarUserByName(user);
		
		return (u != null);
	}

	public boolean passCorrect(String pass) 
	{
		boolean valido = true;
		if(pass.trim().isEmpty()) 
		{
			valido = false;
		}
		else if(pass.length() != 8) 
		{
			valido = false;
		}
		else if(pass.contains(" "))
		{
			valido = false;
		}
		else 
		{
			int i = 0;
			boolean numero = false;
			boolean mayuscula = false;
			boolean minuscula = false;
			while(i < pass.length()) 
			{
				char ch = pass.charAt(i);

				if(Character.isDigit(ch)) 
				{
					numero = true;
				}
				else if(Character.isUpperCase(ch))
				{
					mayuscula = true;
				}
				else if(Character.isLowerCase(ch)) 
				{
					minuscula = true;
				}
				else if(Character.isWhitespace(ch))
				{
					valido = false;
					break;
				}
			}

			if(!numero || !mayuscula || !minuscula) 
			{
				valido = false;
			}
		}

		return valido;
	}
}
