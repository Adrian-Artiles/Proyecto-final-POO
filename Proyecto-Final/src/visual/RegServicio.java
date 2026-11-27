package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;

import logico.Cable;
import logico.InternetHogar;
import logico.Orange;
import logico.Servicio;
import logico.Telefono;
import logico.Validador;

public class RegServicio extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtCod;
	private JRadioButton rdbtnTel;
	private JRadioButton rdbtnHogar;
	private JRadioButton rdbtnCable;
	private JFormattedTextField ftxtPrecio;
	private JSpinner spinner1;
	private JSpinner spinner2;
	private JLabel lblExtra1;
	private JLabel lblExtra2;
	private JRadioButton rdbtnHD;
	private DefaultListModel<String> modeloLista;
	private JList<String> listServicios;

	private final Color naranjaOrange = new Color(255, 102, 0);
	private final Color fondoClaro = new Color(245, 246, 248);
	private final Color oscuroBarra = new Color(25, 25, 25);

	public static void main(String[] args) {
		try {
			RegServicio dialog = new RegServicio();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegServicio() {
		setResizable(false);
		setTitle("Orange - Registrar Servicio");
		setBounds(100, 100, 560, 540);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(naranjaOrange);
		panelHeader.setPreferredSize(new Dimension(560, 62));
		panelHeader.setLayout(new BorderLayout());
		panelHeader.setBorder(new EmptyBorder(12, 20, 12, 20));
		getContentPane().add(panelHeader, BorderLayout.NORTH);

		JLabel lblTitulo = new JLabel("Orange - Servicios");
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

		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(fondoClaro);
		panelSuperior.setLayout(new BorderLayout(0, 8));
		panelSuperior.setPreferredSize(new Dimension(520, 255));
		contentPanel.add(panelSuperior, BorderLayout.NORTH);

		JPanel panelTipo = new JPanel();
		panelTipo.setBackground(Color.WHITE);
		panelTipo.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 4, 0, 0, naranjaOrange),
				new TitledBorder(BorderFactory.createLineBorder(new Color(220, 223, 228)), "tipo de servicio", TitledBorder.LEADING, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 13), oscuroBarra)
		));
		panelSuperior.add(panelTipo, BorderLayout.NORTH);

		rdbtnTel = new JRadioButton("Telefono");
		rdbtnTel.setBackground(Color.WHITE);
		rdbtnTel.setFont(new Font("SansSerif", Font.PLAIN, 13));
		rdbtnTel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDatos();
				
			}
		});
		rdbtnTel.setSelected(true);
		buttonGroup.add(rdbtnTel);
		panelTipo.add(rdbtnTel);

		rdbtnHogar = new JRadioButton("Hogar");
		rdbtnHogar.setBackground(Color.WHITE);
		rdbtnHogar.setFont(new Font("SansSerif", Font.PLAIN, 13));
		rdbtnHogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDatos();
			}
		});
		buttonGroup.add(rdbtnHogar);
		panelTipo.add(rdbtnHogar);

		rdbtnCable = new JRadioButton("Cable");
		rdbtnCable.setBackground(Color.WHITE);
		rdbtnCable.setFont(new Font("SansSerif", Font.PLAIN, 13));
		rdbtnCable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDatos();
			}
		});
		buttonGroup.add(rdbtnCable);
		panelTipo.add(rdbtnCable);

		JPanel panelDatos = new JPanel();
		panelDatos.setBackground(Color.WHITE);
		panelDatos.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 4, 0, 0, oscuroBarra),
				new TitledBorder(BorderFactory.createLineBorder(new Color(220, 223, 228)), "datos del servicio", TitledBorder.LEADING, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 13), oscuroBarra)
		));
		panelSuperior.add(panelDatos, BorderLayout.CENTER);
		panelDatos.setLayout(null);

		JLabel lblCodigo = new JLabel("codigo:");
		lblCodigo.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblCodigo.setBounds(16, 16, 175, 25);
		panelDatos.add(lblCodigo);

		JLabel lblPrecio = new JLabel("precio (RD$):");
		lblPrecio.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblPrecio.setBounds(16, 48, 175, 25);
		panelDatos.add(lblPrecio);

		lblExtra1 = new JLabel("minutos de llamada:");
		lblExtra1.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblExtra1.setBounds(16, 80, 175, 25);
		panelDatos.add(lblExtra1);

		lblExtra2 = new JLabel("datos (en GB):");
		lblExtra2.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblExtra2.setBounds(16, 112, 175, 25);
		panelDatos.add(lblExtra2);

		txtCod = new JTextField();
		txtCod.setEditable(false);
		txtCod.setBackground(new Color(235, 237, 240));
		txtCod.setFont(new Font("SansSerif", Font.BOLD, 13));
		txtCod.setBounds(205, 16, 240, 25);
		panelDatos.add(txtCod);
		txtCod.setColumns(10);

		DecimalFormat formato = new DecimalFormat("#0.00");
		formato.setParseBigDecimal(true);

		NumberFormatter formatear = new NumberFormatter(formato);
		formatear.setValueClass(Float.class);
		formatear.setMinimum(0.0f);
		formatear.setAllowsInvalid(false);

		ftxtPrecio = new JFormattedTextField(formatear);
		ftxtPrecio.setFont(new Font("SansSerif", Font.PLAIN, 13));
		ftxtPrecio.setValue(0.0f);
		ftxtPrecio.setBounds(205, 48, 240, 25);
		panelDatos.add(ftxtPrecio);

		spinner1 = new JSpinner();
		spinner1.setFont(new Font("SansSerif", Font.PLAIN, 13));
		spinner1.setModel(new SpinnerNumberModel(0, 0, null, 1));
		spinner1.setBounds(205, 80, 240, 25);
		panelDatos.add(spinner1);

		spinner2 = new JSpinner();
		spinner2.setFont(new Font("SansSerif", Font.PLAIN, 13));
		spinner2.setModel(new SpinnerNumberModel(0, 0, null, 1));
		spinner2.setBounds(205, 112, 240, 25);
		panelDatos.add(spinner2);

		rdbtnHD = new JRadioButton("incluir HD");
		rdbtnHD.setBackground(Color.WHITE);
		rdbtnHD.setFont(new Font("SansSerif", Font.PLAIN, 13));
		rdbtnHD.setVisible(false);
		rdbtnHD.setBounds(205, 112, 150, 25);
		panelDatos.add(rdbtnHD);

		JPanel panelLista = new JPanel();
		panelLista.setBackground(Color.WHITE);
		panelLista.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 4, 0, 0, naranjaOrange),
				new TitledBorder(BorderFactory.createLineBorder(new Color(220, 223, 228)), "servicios registrados", TitledBorder.LEADING, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 13), oscuroBarra)
		));
		panelLista.setLayout(new BorderLayout());
		contentPanel.add(panelLista, BorderLayout.CENTER);

		modeloLista = new DefaultListModel<>();
		listServicios = new JList<>(modeloLista);
		listServicios.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JScrollPane scrollPane = new JScrollPane(listServicios);
		panelLista.add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(235, 237, 240));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 8));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnCrear = new JButton("crear");
		btnCrear.setBackground(naranjaOrange);
		btnCrear.setForeground(Color.WHITE);
		btnCrear.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnCrear.setFocusPainted(false);
		btnCrear.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!Validador.numeroFloatValido(ftxtPrecio.getValue().toString(), "precio")) {
					return;
				}
				if (!Validador.numeroIntValido(spinner1.getValue().toString(), lblExtra1.getText().replace(":", ""))) {
					return;
				}
				if (!rdbtnCable.isSelected() && !Validador.numeroIntValido(spinner2.getValue().toString(), lblExtra2.getText().replace(":", ""))) {
					return;
				}

				float precio = Float.parseFloat(ftxtPrecio.getValue().toString());
				int val1 = Integer.parseInt(spinner1.getValue().toString());
				int val2 = Integer.parseInt(spinner2.getValue().toString());

				Servicio s = null;
				if (rdbtnTel.isSelected()) {
					s = new Telefono(txtCod.getText(), precio, val1, val2);
				} else if (rdbtnHogar.isSelected()) {
					s = new InternetHogar(txtCod.getText(), precio, val1, val2);
				} else {
					s = new Cable(txtCod.getText(), precio, val1, rdbtnHD.isSelected());
				}

				Orange.getInstancia().regServicio(s);
				JOptionPane.showMessageDialog(null, "servicio registrado correctamente", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
				actualizarCodigo();
				cargarListaServicios();
				limpiarCampos();
			}
		});
		btnCrear.setActionCommand("OK");
		buttonPane.add(btnCrear);
		getRootPane().setDefaultButton(btnCrear);

		JButton btnCancelar = new JButton("cancelar");
		btnCancelar.setBackground(oscuroBarra);
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnCancelar.setFocusPainted(false);
		btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setActionCommand("Cancel");
		buttonPane.add(btnCancelar);

		actualizarCodigo();
		actualizarDatos();
		cargarListaServicios();
	}

	private void actualizarCodigo() {
		txtCod.setText("S-" + (Orange.generarCodServ < 10 ? "00" : Orange.generarCodServ < 100 ? "0" : "") + Orange.generarCodServ);
	}

	private void limpiarCampos() {
		ftxtPrecio.setValue(0.0f);
		spinner1.setValue(0);
		spinner2.setValue(0);
		rdbtnHD.setSelected(false);
	}

	private void cargarListaServicios() {
		modeloLista.clear();
		for (Servicio s : Orange.getInstancia().getServicios()) {
			String tipo = "Servicio";
			if (s instanceof Telefono) {
				tipo = "Telefono";
			} else if (s instanceof InternetHogar) {
				tipo = "Hogar";
			} else if (s instanceof Cable) {
				tipo = "Cable";
			}
			modeloLista.addElement(s.getCodigo() + " [" + tipo + "] - " + s.toString());
		}
	}

	protected void actualizarDatos() {
		if (rdbtnTel.isSelected()) {
			lblExtra2.setVisible(true);
			spinner2.setVisible(true);
			rdbtnHD.setVisible(false);
			lblExtra1.setText("minutos de llamada:");
			spinner1.setValue(0);
			lblExtra2.setText("datos (en GB):");
			spinner2.setValue(0);
		} else if (rdbtnHogar.isSelected()) {
			lblExtra2.setVisible(true);
			spinner2.setVisible(true);
			rdbtnHD.setVisible(false);
			lblExtra1.setText("velocidad de subida (Mbps):");
			spinner1.setValue(0);
			lblExtra2.setText("velocidad de bajada (Mbps):");
			spinner2.setValue(0);
		} else {
			lblExtra1.setText("cantidad de canales:");
			spinner1.setValue(0);
			rdbtnHD.setVisible(true);
			lblExtra2.setVisible(false);
			spinner2.setVisible(false);
		}
	}
}