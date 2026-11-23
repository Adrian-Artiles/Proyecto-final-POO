package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Administrador;
import logico.Cliente;
import logico.Comercial;
import logico.Orange;
import logico.Persona;
import logico.Validador;

public class Login extends JFrame {

	private static final long serialVersionUID = 6498829247915543098L;
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPass;

	private final Color naranjaOrange = new Color(255, 102, 0);
	private final Color fondoClaro = new Color(245, 246, 248);
	private final Color oscuroBarra = new Color(25, 25, 25);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream empresa;
				FileOutputStream empresa2;
				ObjectInputStream empresaRead;
				ObjectOutputStream empresaWrite;
				try {
					empresa = new FileInputStream("Orange.dat");
					empresaRead = new ObjectInputStream(empresa);
					Orange temp = (Orange) empresaRead.readObject();
					Orange.setOrange(temp);
					empresa.close();
					empresaRead.close();
				} catch (FileNotFoundException e) {
					try {
						empresa2 = new FileOutputStream("Orange.dat");
						empresaWrite = new ObjectOutputStream(empresa2);
						Administrador aux = new Administrador("A-0", "00000000000", "", "", "", "", "", "Administrador", "admin", 'M', 0, LocalDate.now(), "");
						Orange.getInstancia().regEmpleado(aux);
						Orange.generarCodEmp--;
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

	public Login() {
		setTitle("orange - inicio de sesion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 420);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(fondoClaro);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(naranjaOrange);
		panelHeader.setPreferredSize(new Dimension(620, 82));
		panelHeader.setLayout(new BorderLayout());
		panelHeader.setBorder(new EmptyBorder(14, 24, 14, 24));
		contentPane.add(panelHeader, BorderLayout.NORTH);

		JLabel lblTitulo = new JLabel("Orange");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 26));
		panelHeader.add(lblTitulo, BorderLayout.WEST);

		JLabel lblSubtitulo = new JLabel("acceso al sistema");
		lblSubtitulo.setForeground(new Color(255, 235, 215));
		lblSubtitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblSubtitulo.setHorizontalAlignment(SwingConstants.RIGHT);
		panelHeader.add(lblSubtitulo, BorderLayout.EAST);

		JPanel panelCentro = new JPanel();
		panelCentro.setBackground(fondoClaro);
		panelCentro.setBorder(new EmptyBorder(24, 60, 24, 60));
		panelCentro.setLayout(new BorderLayout());
		contentPane.add(panelCentro, BorderLayout.CENTER);

		JPanel panelCard = new JPanel();
		panelCard.setBackground(Color.WHITE);
		panelCard.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 5, 0, 0, naranjaOrange),
				BorderFactory.createCompoundBorder(
						new TitledBorder(BorderFactory.createLineBorder(new Color(220, 223, 228)), "credenciales", TitledBorder.LEADING, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 13), oscuroBarra),
						new EmptyBorder(16, 20, 16, 20)
				)
		));
		panelCard.setLayout(null);
		panelCentro.add(panelCard, BorderLayout.CENTER);

		JLabel lblUsuario = new JLabel("usuario:");
		lblUsuario.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblUsuario.setForeground(oscuroBarra);
		lblUsuario.setBounds(20, 32, 110, 28);
		panelCard.add(lblUsuario);

		txtUser = new JTextField();
		txtUser.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtUser.setBounds(135, 32, 260, 28);
		panelCard.add(txtUser);
		txtUser.setColumns(10);

		JLabel lblClave = new JLabel("contrase\u00F1a:");
		lblClave.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblClave.setForeground(oscuroBarra);
		lblClave.setBounds(20, 80, 110, 28);
		panelCard.add(lblClave);

		txtPass = new JPasswordField();
		txtPass.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtPass.setBounds(135, 80, 260, 28);
		panelCard.add(txtPass);

		JButton btnLogin = new JButton("iniciar sesion");
		btnLogin.setBackground(naranjaOrange);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnLogin.setFocusPainted(false);
		btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usuario = txtUser.getText().trim();
				String clave = new String(txtPass.getPassword());

				if (!Validador.campoNoVacio(usuario, "usuario") || !Validador.campoNoVacio(clave, "contraseña")) {
					return;
				}

				Persona u = Orange.getInstancia().loginCorrecto(usuario, clave);
				if (u != null) {
					if (u instanceof Cliente) {
						PCliente pc = new PCliente((Cliente) u);
						pc.setVisible(true);
					} else if (u instanceof Comercial) {
						Principal p = new Principal((Comercial) u);
						p.setVisible(true);
					} else {
						Principal p = new Principal((Administrador) u);
						p.setVisible(true);
					}
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "usuario o contraseña incorrectos", "ERROR", JOptionPane.ERROR_MESSAGE);
					txtPass.setText("");
				}
			}
		});
		btnLogin.setBounds(135, 132, 150, 32);
		panelCard.add(btnLogin);

		JPanel panelFooter = new JPanel();
		panelFooter.setBackground(new Color(235, 237, 240));
		panelFooter.setPreferredSize(new Dimension(620, 32));
		panelFooter.setBorder(new EmptyBorder(6, 20, 6, 20));
		panelFooter.setLayout(new BorderLayout());
		contentPane.add(panelFooter, BorderLayout.SOUTH);


		JLabel lblFooterRight = new JLabel("proyecto final beta isael & adrian");
		lblFooterRight.setForeground(new Color(90, 90, 90));
		lblFooterRight.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblFooterRight.setHorizontalAlignment(SwingConstants.RIGHT);
		panelFooter.add(lblFooterRight, BorderLayout.EAST);

		getRootPane().setDefaultButton(btnLogin);
	}
}