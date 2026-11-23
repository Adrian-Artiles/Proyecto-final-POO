package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
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
import javax.swing.text.NumberFormatter;

import logico.Administrador;
import logico.Comercial;
import logico.Empleado;
import logico.Orange;
import logico.Persona;
import logico.Validador;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegUser extends JDialog {

	private static final long serialVersionUID = 3091369970725956372L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JPasswordField txtPassC;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JTextField txtCodigo;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtCorreo;
	private JLabel lblExtra1;
	private JLabel lblExtra2;
	private JRadioButton rdbtnComercial;
	private JRadioButton rdbtnAdmin;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFemenino;
	private DefaultListModel<String> modeloLista;
	private JList<String> listEmpleados;
	private Empleado e;

	private final Color naranjaOrange = new Color(255, 102, 0);
	private final Color fondoClaro = new Color(245, 246, 248);
	private final Color oscuroBarra = new Color(25, 25, 25);
	private JFormattedTextField ftxtSalario;
	private JComboBox cbxDepartamento;
	private JFormattedTextField ftxtComision;

	public static void main(String[] args) {
		try {
			RegUser dialog = new RegUser(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegUser(Empleado aux) {
		setResizable(false);
		e = aux;
		setTitle(e != null ? "modificar usuario" : "registrar usuario");
		setBounds(100, 100, 640, 770);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		DecimalFormat formato = new DecimalFormat("#0.00");
		formato.setParseBigDecimal(true);

		NumberFormatter formatear = new NumberFormatter(formato);
		formatear.setValueClass(Float.class);
		formatear.setMinimum(0.0f);
		formatear.setAllowsInvalid(false);

		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(naranjaOrange);
		panelHeader.setPreferredSize(new Dimension(640, 64));
		panelHeader.setLayout(new BorderLayout());
		panelHeader.setBorder(new EmptyBorder(12, 20, 12, 20));
		getContentPane().add(panelHeader, BorderLayout.NORTH);

		JLabel lblHeaderTitulo = new JLabel("orange - gestion de personal");
		lblHeaderTitulo.setForeground(Color.WHITE);
		lblHeaderTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
		panelHeader.add(lblHeaderTitulo, BorderLayout.WEST);

		JLabel lblHeaderSub = new JLabel(e != null ? "edicion" : "registro");
		lblHeaderSub.setForeground(new Color(255, 235, 215));
		lblHeaderSub.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblHeaderSub.setHorizontalAlignment(SwingConstants.RIGHT);
		panelHeader.add(lblHeaderSub, BorderLayout.EAST);

		contentPanel.setBackground(fondoClaro);
		contentPanel.setBorder(new EmptyBorder(10, 14, 6, 14));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 8));

		JPanel panelTop = new JPanel();
		panelTop.setBackground(Color.WHITE);
		panelTop.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 4, 0, 0, naranjaOrange),
				new TitledBorder(BorderFactory.createLineBorder(new Color(220, 223, 228)), "acceso y rol", TitledBorder.LEADING, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 13), oscuroBarra)
				));
		contentPanel.add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new GridLayout(0, 3, 8, 8));

		JLabel lblTipo = new JLabel("tipo:");
		lblTipo.setFont(new Font("SansSerif", Font.BOLD, 13));
		panelTop.add(lblTipo);

		rdbtnComercial = new JRadioButton("comercial");
		rdbtnComercial.setBackground(Color.WHITE);
		buttonGroup.add(rdbtnComercial);
		rdbtnComercial.setSelected(true);
		rdbtnComercial.setFont(new Font("SansSerif", Font.PLAIN, 13));
		panelTop.add(rdbtnComercial);

		rdbtnAdmin = new JRadioButton("administrador");
		rdbtnAdmin.setBackground(Color.WHITE);
		buttonGroup.add(rdbtnAdmin);
		rdbtnAdmin.setFont(new Font("SansSerif", Font.PLAIN, 13));
		panelTop.add(rdbtnAdmin);

		JLabel lblUsuario = new JLabel("nombre de usuario:");
		lblUsuario.setFont(new Font("SansSerif", Font.BOLD, 13));
		panelTop.add(lblUsuario);

		txtUser = new JTextField();
		txtUser.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtUser.setColumns(10);
		panelTop.add(txtUser);

		panelTop.add(new JLabel(""));

		JLabel lblClave = new JLabel("contrase\u00F1a:");
		lblClave.setFont(new Font("SansSerif", Font.BOLD, 13));
		panelTop.add(lblClave);

		txtPass = new JPasswordField();
		txtPass.setFont(new Font("SansSerif", Font.PLAIN, 13));
		panelTop.add(txtPass);

		panelTop.add(new JLabel(""));

		JLabel lblConfirmarClave = new JLabel("confirmar contrase\u00F1a:");
		lblConfirmarClave.setFont(new Font("SansSerif", Font.BOLD, 13));
		panelTop.add(lblConfirmarClave);

		txtPassC = new JPasswordField();
		txtPassC.setFont(new Font("SansSerif", Font.PLAIN, 13));
		panelTop.add(txtPassC);

		panelTop.add(new JLabel(""));

		JPanel panelCentro = new JPanel();
		panelCentro.setBackground(fondoClaro);
		panelCentro.setLayout(new BorderLayout(0, 8));
		contentPanel.add(panelCentro, BorderLayout.CENTER);

		JPanel panelDatos = new JPanel();
		panelDatos.setBackground(Color.WHITE);
		panelDatos.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 4, 0, 0, oscuroBarra),
				new TitledBorder(BorderFactory.createLineBorder(new Color(220, 223, 228)), "datos personales", TitledBorder.LEADING, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 13), oscuroBarra)
				));
		panelDatos.setPreferredSize(new Dimension(580, 380));
		panelCentro.add(panelDatos, BorderLayout.NORTH);
		panelDatos.setLayout(null);

		JLabel lblSexo = new JLabel("sexo:");
		lblSexo.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblSexo.setBounds(16, 22, 130, 24);
		panelDatos.add(lblSexo);

		rdbtnMasculino = new JRadioButton("masculino");
		rdbtnMasculino.setBackground(Color.WHITE);
		buttonGroup_1.add(rdbtnMasculino);
		rdbtnMasculino.setSelected(true);
		rdbtnMasculino.setFont(new Font("SansSerif", Font.PLAIN, 13));
		rdbtnMasculino.setBounds(155, 22, 110, 24);
		panelDatos.add(rdbtnMasculino);

		rdbtnFemenino = new JRadioButton("femenino");
		rdbtnFemenino.setBackground(Color.WHITE);
		buttonGroup_1.add(rdbtnFemenino);
		rdbtnFemenino.setFont(new Font("SansSerif", Font.PLAIN, 13));
		rdbtnFemenino.setBounds(275, 22, 110, 24);
		panelDatos.add(rdbtnFemenino);

		JLabel lblCodigo = new JLabel("codigo:");
		lblCodigo.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblCodigo.setBounds(16, 54, 130, 24);
		panelDatos.add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBackground(new Color(235, 237, 240));
		txtCodigo.setFont(new Font("SansSerif", Font.BOLD, 13));
		txtCodigo.setBounds(155, 54, 270, 24);
		panelDatos.add(txtCodigo);

		JLabel lblCedula = new JLabel("cedula:");
		lblCedula.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblCedula.setBounds(16, 86, 130, 24);
		panelDatos.add(lblCedula);

		txtCedula = new JTextField();
		txtCedula.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtCedula.setBounds(155, 86, 270, 24);
		panelDatos.add(txtCedula);

		JLabel lblNombre = new JLabel("nombre:");
		lblNombre.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblNombre.setBounds(16, 118, 130, 24);
		panelDatos.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtNombre.setBounds(155, 118, 270, 24);
		panelDatos.add(txtNombre);

		JLabel lblApellidos = new JLabel("apellidos:");
		lblApellidos.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblApellidos.setBounds(16, 150, 130, 24);
		panelDatos.add(lblApellidos);

		txtApellido = new JTextField();
		txtApellido.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtApellido.setBounds(155, 150, 270, 24);
		panelDatos.add(txtApellido);

		JLabel lblTelefono = new JLabel("telefono:");
		lblTelefono.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblTelefono.setBounds(16, 182, 130, 24);
		panelDatos.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtTelefono.setBounds(155, 182, 270, 24);
		panelDatos.add(txtTelefono);

		JLabel lblDireccion = new JLabel("direccion:");
		lblDireccion.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblDireccion.setBounds(16, 214, 130, 24);
		panelDatos.add(lblDireccion);

		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtDireccion.setBounds(155, 214, 270, 24);
		panelDatos.add(txtDireccion);

		JLabel lblCorreo = new JLabel("correo:");
		lblCorreo.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblCorreo.setBounds(16, 246, 130, 24);
		panelDatos.add(lblCorreo);

		txtCorreo = new JTextField();
		txtCorreo.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtCorreo.setBounds(155, 246, 270, 24);
		panelDatos.add(txtCorreo);

		JLabel lblSalario = new JLabel("salario base:");
		lblSalario.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblSalario.setBounds(16, 278, 130, 24);
		panelDatos.add(lblSalario);

		lblExtra1 = new JLabel("departamento:");
		lblExtra1.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblExtra1.setBounds(16, 310, 130, 24);
		panelDatos.add(lblExtra1);

		lblExtra2 = new JLabel("comision:");
		lblExtra2.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblExtra2.setBounds(16, 342, 130, 24);
		panelDatos.add(lblExtra2);

		ftxtSalario = new JFormattedTextField(formatear);
		ftxtSalario.setFont(new Font("SansSerif", Font.PLAIN, 13));
		ftxtSalario.setValue(0.0f);
		ftxtSalario.setBounds(155, 279, 270, 24);
		panelDatos.add(ftxtSalario);

		cbxDepartamento = new JComboBox();
		cbxDepartamento.setFont(new Font("SansSerif", Font.BOLD, 13));
		cbxDepartamento.setModel(new DefaultComboBoxModel(new String[] {"Gerencia General", "Tecnolog\u00EDa y Sistemas (TI)", "Administraci\u00F3n y Finanzas", "Recursos Humanos", "Planificaci\u00F3n y Control"}));
		cbxDepartamento.setEnabled(false);
		cbxDepartamento.setBounds(155, 311, 270, 24);
		panelDatos.add(cbxDepartamento);

		ftxtComision = new JFormattedTextField(formatear);
		ftxtComision.setFont(new Font("SansSerif", Font.PLAIN, 13));
		ftxtComision.setValue(0.0f);
		ftxtComision.setBounds(155, 343, 270, 24);
		panelDatos.add(ftxtComision);

		JPanel panelLista = new JPanel();
		panelLista.setBackground(Color.WHITE);
		panelLista.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 4, 0, 0, naranjaOrange),
				new TitledBorder(BorderFactory.createLineBorder(new Color(220, 223, 228)), "empleados registrados", TitledBorder.LEADING, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 13), oscuroBarra)
				));
		panelLista.setLayout(new BorderLayout());
		panelCentro.add(panelLista, BorderLayout.CENTER);

		modeloLista = new DefaultListModel<>();
		listEmpleados = new JList<>(modeloLista);
		listEmpleados.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JScrollPane scrollPane = new JScrollPane(listEmpleados);
		panelLista.add(scrollPane, BorderLayout.CENTER);

		rdbtnComercial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizarCamposRol();
			}
		});

		rdbtnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizarCamposRol();
			}
		});

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(235, 237, 240));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 8));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnCrear = new JButton(e != null ? "guardar" : "crear");
		btnCrear.setBackground(naranjaOrange);
		btnCrear.setForeground(Color.WHITE);
		btnCrear.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnCrear.setFocusPainted(false);
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usuario = txtUser.getText().trim();
				String pass = new String(txtPass.getPassword());
				String passC = new String(txtPassC.getPassword());

				if(Validador.validar(usuario, pass, passC) && validarFormularioEmpleado()) 
				{
					char sexo = rdbtnMasculino.isSelected() ? 'M' : 'F';
					String codigo = txtCodigo.getText();
					String cedula = txtCedula.getText().trim();
					String nombre = txtNombre.getText().trim();
					String apellido = txtApellido.getText().trim();
					String telefono = txtTelefono.getText().trim();
					String direccion = txtDireccion.getText().trim();
					String correo = txtCorreo.getText().trim();
					float salarioBase = Float.parseFloat(ftxtSalario.getValue().toString());

					Empleado nuevo = null;
					if (rdbtnComercial.isSelected()) {
						float comision = Float.parseFloat(ftxtComision.getValue().toString());
						nuevo = new Comercial(codigo, cedula, nombre, apellido, telefono, direccion, correo, usuario, pass, sexo, salarioBase, LocalDate.now(), comision);
					} else {
						String departamento = cbxDepartamento.getSelectedItem().toString();
						nuevo = new Administrador(codigo, cedula, nombre, apellido, telefono, direccion, correo, usuario, pass, sexo, salarioBase, LocalDate.now(), departamento);
					}

					if(!Orange.getInstancia().getEmpleados().contains(nuevo)) 
					{
						Orange.getInstancia().regEmpleado(nuevo);
						JOptionPane.showMessageDialog(null, "empleado registrado correctamente", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
						cargarListaEmpleados();
						limpiarCampos();
					}

				}
			}
		});
		btnCrear.setActionCommand("OK");
		buttonPane.add(btnCrear);
		getRootPane().setDefaultButton(btnCrear);

		JButton cancelButton = new JButton("cancelar");
		cancelButton.setBackground(oscuroBarra);
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setFont(new Font("SansSerif", Font.BOLD, 13));
		cancelButton.setFocusPainted(false);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		actualizarCamposRol();
		cargarListaEmpleados();
	}

	private void actualizarCamposRol() 
	{
		if (rdbtnComercial.isSelected()) 
		{
			txtCodigo.setText("C-" + (Orange.generarCodEmp < 10 ? "00" :  
				Orange.generarCodEmp < 100 ? "0" : "") + Orange.generarCodEmp);

			ftxtComision.setEnabled(true);
			cbxDepartamento.setEnabled(false);
		} 
		else 
		{
			txtCodigo.setText("A-" + (Orange.generarCodEmp < 10 ? "00" :  
				Orange.generarCodEmp < 100 ? "0" : "") + Orange.generarCodEmp);

			ftxtComision.setEnabled(false);
			cbxDepartamento.setEnabled(true);
		}
	}

	private boolean validarFormularioEmpleado() 
	{
		boolean valido = true;
		if (!Validador.comprobacionCed(txtCedula.getText().trim(), 11, "cedula") || 
				!Validador.soloLetras(txtNombre.getText().trim(), "nombre") || 
				!Validador.soloLetras(txtApellido.getText().trim(), "apellidos") || 
				!Validador.comprobacionCed(txtTelefono.getText().trim(), 10, "telefono") ||
				!Validador.campoNoVacio(txtDireccion.getText().trim(), "direccion") ||
				!Validador.correoValido(txtCorreo.getText().trim()) ||
				!Validador.numeroFloatValido(ftxtSalario.getValue().toString(), "salario base")) 
		{
			valido = false;
		}
		else 
		{
			if (rdbtnComercial.isSelected()) 
			{
				if (!Validador.numeroFloatValido(ftxtComision.getValue().toString(), "comision")) 
				{
					valido = false;
				}
			} 
			else 
			{
				if (!Validador.campoNoVacio(cbxDepartamento.getSelectedItem().toString(), "departamento")) 
				{
					valido = false;
				}
			}
		}

		return valido;
	}

	private void cargarListaEmpleados() {
		modeloLista.clear();

		for (Empleado emp : Orange.getInstancia().getEmpleados()) {
			String tipo = (emp instanceof Administrador) ? "Administrador" : "Comercial";
			modeloLista.addElement(emp.getCodigo() + " - " + emp.getNombre() + " " + emp.getApellido() + " (" + tipo + ")");
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
		ftxtSalario.setValue(0.0f);
		cbxDepartamento.setSelectedIndex(0);
		ftxtComision.setValue(0.0f);
		actualizarCamposRol();
	}

	
}