package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Cable;
import logico.InternetHogar;
import logico.Orange;
import logico.Plan;
import logico.Servicio;
import logico.Telefono;
import logico.Validador;

public class RegPlan extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtCod;
	private JComboBox<Object> cbxTel;
	private JComboBox<Object> cbxHogar;
	private JComboBox<Object> cbxCable;
	private JTextField txtPrecio;
	private DefaultListModel<String> modeloLista;
	private JList<String> listPlanes;

	private final Color naranjaOrange = new Color(255, 102, 0);
	private final Color fondoClaro = new Color(245, 246, 248);
	private final Color oscuroBarra = new Color(25, 25, 25);

	public static void main(String[] args) {
		try {
			RegPlan dialog = new RegPlan();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegPlan() {
		setResizable(false);
		setTitle("Orange - Crear Plan");
		setBounds(100, 100, 740, 620);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(naranjaOrange);
		panelHeader.setPreferredSize(new Dimension(740, 62));
		panelHeader.setLayout(new BorderLayout());
		panelHeader.setBorder(new EmptyBorder(12, 20, 12, 20));
		getContentPane().add(panelHeader, BorderLayout.NORTH);

		JLabel lblTitulo = new JLabel("Orange - Planes");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
		panelHeader.add(lblTitulo, BorderLayout.WEST);

		JLabel lblSub = new JLabel("configuracion y consulta");
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
		panelSuperior.setPreferredSize(new Dimension(700, 325));
		contentPanel.add(panelSuperior, BorderLayout.NORTH);

		JPanel panelEncabezadoPlan = new JPanel();
		panelEncabezadoPlan.setBackground(Color.WHITE);
		panelEncabezadoPlan.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 4, 0, 0, naranjaOrange),
				new TitledBorder(BorderFactory.createLineBorder(new Color(220, 223, 228)), "datos del plan", TitledBorder.LEADING, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 13), oscuroBarra)
		));
		panelEncabezadoPlan.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 8));
		panelSuperior.add(panelEncabezadoPlan, BorderLayout.NORTH);

		JLabel lblNombre = new JLabel("nombre:");
		lblNombre.setFont(new Font("SansSerif", Font.BOLD, 13));
		panelEncabezadoPlan.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("SansSerif", Font.PLAIN, 13));
		panelEncabezadoPlan.add(txtNombre);
		txtNombre.setColumns(18);

		JLabel lblCodigo = new JLabel("codigo:");
		lblCodigo.setFont(new Font("SansSerif", Font.BOLD, 13));
		panelEncabezadoPlan.add(lblCodigo);

		txtCod = new JTextField();
		txtCod.setEditable(false);
		txtCod.setBackground(new Color(235, 237, 240));
		txtCod.setFont(new Font("SansSerif", Font.BOLD, 13));
		panelEncabezadoPlan.add(txtCod);
		txtCod.setColumns(12);

		JPanel panelServicios = new JPanel();
		panelServicios.setBackground(Color.WHITE);
		panelServicios.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 4, 0, 0, oscuroBarra),
				new TitledBorder(BorderFactory.createLineBorder(new Color(220, 223, 228)), "seleccion de servicios", TitledBorder.LEADING, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 13), oscuroBarra)
		));
		panelServicios.setLayout(null);
		panelSuperior.add(panelServicios, BorderLayout.CENTER);

		JLabel lblTel = new JLabel("servicios de telefono:");
		lblTel.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblTel.setBounds(12, 36, 460, 22);
		panelServicios.add(lblTel);

		cbxTel = new JComboBox<>();
		cbxTel.setBackground(Color.WHITE);
		cbxTel.setFont(new Font("SansSerif", Font.PLAIN, 13));
		cbxTel.setBounds(12, 60, 485, 28);
		panelServicios.add(cbxTel);

		JLabel lblHogar = new JLabel("servicios de internet hogar:");
		lblHogar.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblHogar.setBounds(12, 96, 460, 22);
		panelServicios.add(lblHogar);

		cbxHogar = new JComboBox<>();
		cbxHogar.setBackground(Color.WHITE);
		cbxHogar.setFont(new Font("SansSerif", Font.PLAIN, 13));
		cbxHogar.setBounds(12, 120, 485, 28);
		panelServicios.add(cbxHogar);

		JLabel lblCable = new JLabel("servicios de cable:");
		lblCable.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblCable.setBounds(12, 156, 460, 22);
		panelServicios.add(lblCable);

		cbxCable = new JComboBox<>();
		cbxCable.setBackground(Color.WHITE);
		cbxCable.setFont(new Font("SansSerif", Font.PLAIN, 13));
		cbxCable.setBounds(12, 180, 485, 28);
		panelServicios.add(cbxCable);

		JLabel lblSuma = new JLabel("total plan (RD$):");
		lblSuma.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblSuma.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuma.setBounds(515, 36, 175, 24);
		panelServicios.add(lblSuma);

		txtPrecio = new JTextField();
		txtPrecio.setText("0.00");
		txtPrecio.setBackground(new Color(235, 237, 240));
		txtPrecio.setForeground(naranjaOrange);
		txtPrecio.setFont(new Font("SansSerif", Font.BOLD, 18));
		txtPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrecio.setEditable(false);
		txtPrecio.setBounds(515, 64, 175, 45);
		panelServicios.add(txtPrecio);
		txtPrecio.setColumns(10);

		JPanel panelLista = new JPanel();
		panelLista.setBackground(Color.WHITE);
		panelLista.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 4, 0, 0, naranjaOrange),
				new TitledBorder(BorderFactory.createLineBorder(new Color(220, 223, 228)), "planes registrados", TitledBorder.LEADING, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 13), oscuroBarra)
		));
		panelLista.setLayout(new BorderLayout());
		contentPanel.add(panelLista, BorderLayout.CENTER);

		modeloLista = new DefaultListModel<>();
		listPlanes = new JList<>(modeloLista);
		listPlanes.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JScrollPane scrollPane = new JScrollPane(listPlanes);
		panelLista.add(scrollPane, BorderLayout.CENTER);

		ActionListener recalcularListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recalcularPrecio();
			}
		};
		cbxTel.addActionListener(recalcularListener);
		cbxHogar.addActionListener(recalcularListener);
		cbxCable.addActionListener(recalcularListener);

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
			public void actionPerformed(ActionEvent e) {
				recalcularPrecio();
				if (validar()) {
					Plan p = new Plan(txtNombre.getText().trim(), txtCod.getText(), Float.parseFloat(txtPrecio.getText()), serviciosSeleccionados());
					Orange.getInstancia().regPlan(p);
					JOptionPane.showMessageDialog(null, "plan creado correctamente", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
					actualizarCodigo();
					cargarListaPlanes();
					limpiarCampos();
				}
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
		llenarServicios();
		cargarListaPlanes();
	}

	private void actualizarCodigo() {
		txtCod.setText("P-" + (Orange.generarCodPlan < 10 ? "00" : Orange.generarCodPlan < 100 ? "0" : "") + Orange.generarCodPlan);
	}

	private void recalcularPrecio() {
		float total = 0.0f;
		for (Servicio s : serviciosSeleccionados()) {
			total += s.getPrecio();
		}
		txtPrecio.setText(String.format(java.util.Locale.US, "%.2f", total));
	}

	private void limpiarCampos() {
		txtNombre.setText("");
		cbxTel.setSelectedIndex(0);
		cbxHogar.setSelectedIndex(0);
		cbxCable.setSelectedIndex(0);
		txtPrecio.setText("0.00");
	}

	private void cargarListaPlanes() {
		modeloLista.clear();
		for (Plan p : Orange.getInstancia().getPlanes()) {
			int cantServ = (p.getServicios() != null) ? p.getServicios().size() : 0;
			modeloLista.addElement(p.getCodigo() + " - " + p.getNombre() + " | RD$ " + p.getPrecio() + " (" + cantServ + " servicios)");
		}
	}

	protected ArrayList<Servicio> serviciosSeleccionados() {
		ArrayList<Servicio> ss = new ArrayList<>();
		if (cbxTel.getSelectedIndex() > 0) {
			ss.add((Telefono) cbxTel.getSelectedItem());
		}
		if (cbxHogar.getSelectedIndex() > 0) {
			ss.add((InternetHogar) cbxHogar.getSelectedItem());
		}
		if (cbxCable.getSelectedIndex() > 0) {
			ss.add((Cable) cbxCable.getSelectedItem());
		}
		return ss;
	}

	protected boolean validar() {
		if (!Validador.campoNoVacio(txtNombre.getText().trim(), "nombre del plan")) {
			return false;
		}
		if (serviciosSeleccionados().isEmpty()) {
			JOptionPane.showMessageDialog(null, "debe seleccionar al menos un servicio para el plan", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private void llenarServicios() {
		cbxTel.addItem("<<SELECCIONE>>");
		cbxHogar.addItem("<<SELECCIONE>>");
		cbxCable.addItem("<<SELECCIONE>>");

		for (Servicio s : Orange.getInstancia().getServicios()) {
			if (s instanceof Telefono) {
				cbxTel.addItem((Telefono) s);
			} else if (s instanceof InternetHogar) {
				cbxHogar.addItem((InternetHogar) s);
			} else {
				cbxCable.addItem((Cable) s);
			}
		}
	}
}