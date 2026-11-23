package visual;

import java.awt.BorderLayout;


import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Administrador;
import logico.Cliente;
import logico.Comercial;
import logico.Orange;
import logico.Persona;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6498829247915543098L;
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				FileInputStream empresa;
				FileOutputStream empresa2;
				ObjectInputStream empresaRead;
				ObjectOutputStream empresaWrite;
				try {
					empresa = new FileInputStream ("Orange.dat");
					empresaRead = new ObjectInputStream(empresa);
					Orange temp = (Orange)empresaRead.readObject();
					Orange.setOrange(temp);
					empresa.close();
					empresaRead.close();
				} catch (FileNotFoundException e) {
					try {
						empresa2 = new  FileOutputStream("Orange.dat");
						empresaWrite = new ObjectOutputStream(empresa2);
						Administrador aux = new Administrador("A-0", "00000000000", "", "", "", "", "", "Administrador", "admin", 'M', 0, LocalDate.now(), "");
						Orange.getInstancia().regAdmin(aux);
						empresaWrite.writeObject(Orange.getInstancia());
						empresa2.close();
						empresaWrite.close();
					} catch (FileNotFoundException e1) {
					} catch (IOException e1) {
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login()
	{
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 425);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("USUARIO:");
		lblNewLabel.setBounds(25, 86, 93, 30);
		panel.add(lblNewLabel);

		txtUser = new JTextField();
		txtUser.setBounds(119, 86, 200, 30);
		panel.add(txtUser);
		txtUser.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("CONTRASE\u00D1A:");
		lblNewLabel_1.setBounds(25, 146, 93, 30);
		panel.add(lblNewLabel_1);

		txtPass = new JPasswordField();
		txtPass.setBounds(119, 146, 200, 30);
		panel.add(txtPass);

		JButton btnLogin = new JButton("INICIAR SESION");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Persona u = Orange.getInstancia().loginCorrecto(txtUser.getText(), String.valueOf(txtPass.getPassword()));
				if(u != null)
				{
					if(u instanceof Cliente) 
					{
						PCliente pc = new PCliente((Cliente) u);
						pc.setVisible(true);
					}
					else if(u instanceof Comercial) 
					{
						Principal p = new Principal((Comercial) u);
						p.setVisible(true);
					}
					else 
					{
						Principal p = new Principal((Administrador) u);
						p.setVisible(true);
					}
					
					dispose();
				}
				else 
				{
					txtPass.setText("");
				}
			}
		});
		btnLogin.setBounds(25, 206, 125, 25);
		panel.add(btnLogin);
	}
}
