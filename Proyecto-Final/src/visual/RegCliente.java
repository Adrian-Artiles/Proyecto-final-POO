package visual;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Cliente;
import logico.Contrato;
import logico.Orange;
import logico.Validador;

public class RegCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup grupoSexo = new ButtonGroup();

	private JTextField txtUser;
	private JPasswordField txtPass;
	private JPasswordField txtPassC;
	private JTextField txtCodigo;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtCorreo;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFemenino;
	private DefaultListModel<String> modeloLista;
	private JList<String> listClientes;

	private final Color naranjaOrange = new Color(255, 102, 0);
	private final Color fondoClaro = new Color(245, 246, 248);
	private final Color oscuroBarra = new Color(25, 25, 25);

	public static void main(String[] args) {
		try {
			RegCliente dialog = new RegCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegCliente() {
		setResizable(false);
		setTitle("Orange - Registro de Clientes");
		setBounds(100, 100, 640, 740);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(naranjaOrange);
		panelHeader.setPreferredSize(new Dimension(640, 64));
		panelHeader.setLayout(new BorderLayout());
		panelHeader.setBorder(new EmptyBorder(12, 20, 12, 20));
		getContentPane().add(panelHeader, BorderLayout.NORTH);

		JLabel lblTitulo = new JLabel("Orange - Control de Clientes");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
		panelHeader.add(lblTitulo, BorderLayout.WEST);

		JLabel lblSub = new JLabel("registro y consulta");
		lblSub.setForeground(new Color(255, 235, 215));
		lblSub.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblSub.setHorizontalAlignment(SwingConstants.RIGHT);
		panelHeader.add(lblSub, BorderLayout.EAST);

		contentPanel.setBackground(fondoClaro);
		contentPanel.setBorder(new EmptyBorder(10, 14, 6, 14));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 8));

		JPanel panelAcceso = new JPanel();
		panelAcceso.setBackground(Color.WHITE);
		panelAcceso.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 4, 0, 0, naranjaOrange),
				new TitledBorder(BorderFactory.createLineBorder(new Color(220, 223, 228)), "acceso de cliente", TitledBorder.LEADING, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 13), oscuroBarra)
				));
		contentPanel.add(panelAcceso, BorderLayout.NORTH);
		panelAcceso.setLayout(new GridLayout(0, 2, 10, 8));

		JLabel lblUsuario = new JLabel("nombre de usuario:");
		lblUsuario.setFont(new Font("SansSerif", Font.BOLD, 13));
		panelAcceso.add(lblUsuario);

		txtUser = new JTextField();
		txtUser.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtUser.setColumns(10);
		panelAcceso.add(txtUser);

		JLabel lblClave = new JLabel("contrase\u00F1a:");
		lblClave.setFont(new Font("SansSerif", Font.BOLD, 13));
		panelAcceso.add(lblClave);

		txtPass = new JPasswordField();
		txtPass.setFont(new Font("SansSerif", Font.PLAIN, 13));
		panelAcceso.add(txtPass);

		JLabel lblClaveC = new JLabel("confirmar contrase\u00F1a:");
		lblClaveC.setFont(new Font("SansSerif", Font.BOLD, 13));
		panelAcceso.add(lblClaveC);

		txtPassC = new JPasswordField();
		txtPassC.setFont(new Font("SansSerif", Font.PLAIN, 13));
		panelAcceso.add(txtPassC);

		JPanel panelCentro = new JPanel();
		panelCentro.setBackground(fondoClaro);
		panelCentro.setLayout(new BorderLayout(0, 8));
		contentPanel.add(panelCentro, BorderLayout.CENTER);

		JPanel panelDatos = new JPanel();
		panelDatos.setBackground(Color.WHITE);
		panelDatos.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 4, 0, 0, oscuroBarra),
				new TitledBorder(BorderFactory.createLineBorder(new Color(220, 223, 228)), "datos del cliente", TitledBorder.LEADING, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 13), oscuroBarra)
				));
		panelDatos.setPreferredSize(new Dimension(580, 315));
		panelCentro.add(panelDatos, BorderLayout.NORTH);
		panelDatos.setLayout(null);

		JLabel lblSexo = new JLabel("sexo:");
		lblSexo.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblSexo.setBounds(16, 20, 120, 24);
		panelDatos.add(lblSexo);

		rdbtnMasculino = new JRadioButton("masculino");
		rdbtnMasculino.setBackground(Color.WHITE);
		grupoSexo.add(rdbtnMasculino);
		rdbtnMasculino.setSelected(true);
		rdbtnMasculino.setFont(new Font("SansSerif", Font.PLAIN, 13));
		rdbtnMasculino.setBounds(150, 20, 105, 24);
		panelDatos.add(rdbtnMasculino);

		rdbtnFemenino = new JRadioButton("femenino");
		rdbtnFemenino.setBackground(Color.WHITE);
		grupoSexo.add(rdbtnFemenino);
		rdbtnFemenino.setFont(new Font("SansSerif", Font.PLAIN, 13));
		rdbtnFemenino.setBounds(265, 20, 105, 24);
		panelDatos.add(rdbtnFemenino);

		JLabel lblCodigo = new JLabel("codigo:");
		lblCodigo.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblCodigo.setBounds(16, 52, 120, 24);
		panelDatos.add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBackground(new Color(235, 237, 240));
		txtCodigo.setFont(new Font("SansSerif", Font.BOLD, 13));
		txtCodigo.setBounds(150, 52, 270, 24);
		txtCodigo.setText("CL-" + (Orange.generarCodCliente < 10 ? "00" :
			Orange.generarCodCliente < 100 ? "0": "") + Orange.generarCodCliente);
		panelDatos.add(txtCodigo);

		JLabel lblCedula = new JLabel("cedula:");
		lblCedula.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblCedula.setBounds(16, 84, 120, 24);
		panelDatos.add(lblCedula);

		txtCedula = new JTextField();
		txtCedula.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtCedula.setBounds(150, 84, 270, 24);
		panelDatos.add(txtCedula);

		JLabel lblNombre = new JLabel("nombre:");
		lblNombre.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblNombre.setBounds(16, 116, 120, 24);
		panelDatos.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtNombre.setBounds(150, 116, 270, 24);
		panelDatos.add(txtNombre);

		JLabel lblApellido = new JLabel("apellidos:");
		lblApellido.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblApellido.setBounds(16, 148, 120, 24);
		panelDatos.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtApellido.setBounds(150, 148, 270, 24);
		panelDatos.add(txtApellido);

		JLabel lblTelefono = new JLabel("telefono:");
		lblTelefono.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblTelefono.setBounds(16, 180, 120, 24);
		panelDatos.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtTelefono.setBounds(150, 180, 270, 24);
		panelDatos.add(txtTelefono);

		JLabel lblDireccion = new JLabel("direccion:");
		lblDireccion.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblDireccion.setBounds(16, 212, 120, 24);
		panelDatos.add(lblDireccion);

		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtDireccion.setBounds(150, 212, 270, 24);
		panelDatos.add(txtDireccion);

		JLabel lblCorreo = new JLabel("correo:");
		lblCorreo.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblCorreo.setBounds(16, 244, 120, 24);
		panelDatos.add(lblCorreo);

		txtCorreo = new JTextField();
		txtCorreo.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtCorreo.setBounds(150, 244, 270, 24);
		panelDatos.add(txtCorreo);

		JPanel panelLista = new JPanel();
		panelLista.setBackground(Color.WHITE);
		panelLista.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 4, 0, 0, naranjaOrange),
				new TitledBorder(BorderFactory.createLineBorder(new Color(220, 223, 228)), "clientes registrados", TitledBorder.LEADING, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 13), oscuroBarra)
				));
		panelLista.setLayout(new BorderLayout());
		panelCentro.add(panelLista, BorderLayout.CENTER);

		modeloLista = new DefaultListModel<>();
		listClientes = new JList<>(modeloLista);
		listClientes.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JScrollPane scrollPane = new JScrollPane(listClientes);
		panelLista.add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(235, 237, 240));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 8));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnRegistrar = new JButton("registrar");
		btnRegistrar.setBackground(naranjaOrange);
		btnRegistrar.setForeground(Color.WHITE);
		btnRegistrar.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnRegistrar.setFocusPainted(false);
		btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usuario = txtUser.getText().trim();
				String pass = new String(txtPass.getPassword());
				String passC = new String(txtPassC.getPassword());

				if(Validador.validar(usuario, pass, passC) && validarDatosCliente()) 
				{
					char sexo = rdbtnMasculino.isSelected() ? 'M' : 'F';
					String codigo = txtCodigo.getText();
					String cedula = txtCedula.getText().trim();
					String nombre = txtNombre.getText().trim();
					String apellido = txtApellido.getText().trim();
					String telefono = txtTelefono.getText().trim();
					String direccion = txtDireccion.getText().trim();
					String correo = txtCorreo.getText().trim();

					Cliente nuevo = new Cliente(codigo, cedula, nombre, apellido, telefono, direccion, correo, usuario, pass, sexo, new ArrayList<Contrato>());
					Orange.getInstancia().regCliente(nuevo);

					JOptionPane.showMessageDialog(null, "cliente registrado correctamente", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
					cargarListaClientes();
					limpiarCampos();
				}

			}
		});
		btnRegistrar.setActionCommand("OK");
		buttonPane.add(btnRegistrar);
		getRootPane().setDefaultButton(btnRegistrar);

		JButton btnCancelar = new JButton("cancelar");
		btnCancelar.setBackground(oscuroBarra);
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnCancelar.setFocusPainted(false);
		btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setActionCommand("Cancel");
		buttonPane.add(btnCancelar);

		cargarListaClientes();
	}

	private boolean validarDatosCliente() {
		if (!Validador.comprobacionCed(txtCedula.getText().trim(), 11, "cedula") ||
				!Validador.soloLetras(txtNombre.getText().trim(), "nombre") || 
				!Validador.soloLetras(txtApellido.getText().trim(), "apellidos") || 
				!Validador.comprobacionCed(txtTelefono.getText().trim(), 10, "telefono") ||
				!Validador.campoNoVacio(txtDireccion.getText().trim(), "direccion") || 
				!Validador.correoValido(txtCorreo.getText().trim())) 
		{
			return false;
		}

		return true;
	}

	private void cargarListaClientes() 
	{
		modeloLista.clear();

		for (Cliente c : Orange.getInstancia().getClientes()) 
		{
			modeloLista.addElement(c.getCodigo() + " - " + c.getNombre() + " " + c.getApellido() + " (" + c.getCedula() + ")");
		}

	}

	private void limpiarCampos() {
		txtUser.setText("");
		txtPass.setText("");
		txtPassC.setText("");
		txtCedula.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtCorreo.setText("");
		txtCodigo.setText("CL-" + (Orange.generarCodCliente < 10 ? "00" :
			Orange.generarCodCliente < 100 ? "0": "") + Orange.generarCodCliente);
	}

	private boolean passValida(String pass) {
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