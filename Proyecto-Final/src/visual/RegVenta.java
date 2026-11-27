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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import logico.Cliente;
import logico.Comercial;
import logico.Contrato;
import logico.Empleado;
import logico.Factura;
import logico.Orange;
import logico.Plan;
import logico.Validador;

public class RegVenta extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<Object> cbxCliente;
	private JComboBox<Object> cbxPlan;
	private JTextField txtCod;
	private JTextField txtFInicio;
	private JTextField txtFFinal;
	private JTextField txtMensual;
	private JTextField txtTotal;
	private JSpinner spnPlazo;
	private Empleado emp = null;
	private DateTimeFormatter formato;
	private JButton btnGen;

	private final Color naranjaOrange = new Color(255, 102, 0);
	private final Color fondoClaro = new Color(245, 246, 248);
	private final Color oscuroBarra = new Color(25, 25, 25);

	public static void main(String[] args) {
		try {
			RegVenta dialog = new RegVenta(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegVenta(Empleado aux) {
		emp = aux;
		setResizable(false);
		setTitle("Orange - Contratar un Plan");
		setBounds(100, 100, 660, 460);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(naranjaOrange);
		panelHeader.setPreferredSize(new Dimension(660, 64));
		panelHeader.setLayout(new BorderLayout());
		panelHeader.setBorder(new EmptyBorder(12, 20, 12, 20));
		getContentPane().add(panelHeader, BorderLayout.NORTH);

		JLabel lblTitulo = new JLabel("Orange - Realizar Venta");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
		panelHeader.add(lblTitulo, BorderLayout.WEST);

		JLabel lblSub = new JLabel("contratos y facturas");
		lblSub.setForeground(new Color(255, 235, 215));
		lblSub.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblSub.setHorizontalAlignment(SwingConstants.RIGHT);
		panelHeader.add(lblSub, BorderLayout.EAST);

		contentPanel.setBackground(fondoClaro);
		contentPanel.setBorder(new EmptyBorder(10, 14, 6, 14));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 10));

		JPanel panelSeleccion = new JPanel();
		panelSeleccion.setBackground(Color.WHITE);
		panelSeleccion.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 4, 0, 0, naranjaOrange),
				new TitledBorder(BorderFactory.createLineBorder(new Color(220, 223, 228)), "cliente y plan", TitledBorder.LEADING, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 13), oscuroBarra)
		));
		contentPanel.add(panelSeleccion, BorderLayout.NORTH);
		panelSeleccion.setLayout(new GridLayout(2, 2, 10, 8));

		JLabel lblCliente = new JLabel("cliente:");
		lblCliente.setFont(new Font("SansSerif", Font.BOLD, 13));
		panelSeleccion.add(lblCliente);

		cbxCliente = new JComboBox<>();
		cbxCliente.setBackground(Color.WHITE);
		cbxCliente.setFont(new Font("SansSerif", Font.PLAIN, 13));
		cbxCliente.addItem("<<SELECCIONE>>");
		cbxCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbxCliente.getSelectedIndex() > 0) {
					cbxPlan.setEnabled(true);
					llenarPlan((Cliente) (cbxCliente.getSelectedItem()));
				} else {
					cbxPlan.setEnabled(false);
					cbxPlan.removeAllItems();
					cbxPlan.addItem("<<SELECCIONE>>");
					resetearContrato();
				}
			}
		});
		panelSeleccion.add(cbxCliente);

		JLabel lblPlan = new JLabel("plan:");
		lblPlan.setFont(new Font("SansSerif", Font.BOLD, 13));
		panelSeleccion.add(lblPlan);

		cbxPlan = new JComboBox<>();
		cbxPlan.setBackground(Color.WHITE);
		cbxPlan.setFont(new Font("SansSerif", Font.PLAIN, 13));
		cbxPlan.addItem("<<SELECCIONE>>");
		cbxPlan.setEnabled(false);
		cbxPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbxPlan.getSelectedIndex() > 0) {
					btnGen.setEnabled(true);
					spnPlazo.setEnabled(true);
					calcularPrecio();
				} else {
					resetearContrato();
				}
			}
		});
		panelSeleccion.add(cbxPlan);

		JPanel panelContrato = new JPanel();
		panelContrato.setBackground(Color.WHITE);
		panelContrato.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 4, 0, 0, oscuroBarra),
				new TitledBorder(BorderFactory.createLineBorder(new Color(220, 223, 228)), "datos del contrato", TitledBorder.LEADING, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 13), oscuroBarra)
		));
		contentPanel.add(panelContrato, BorderLayout.CENTER);
		panelContrato.setLayout(null);

		JLabel lblCodigo = new JLabel("codigo:");
		lblCodigo.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblCodigo.setBounds(16, 26, 130, 28);
		panelContrato.add(lblCodigo);

		JLabel lblPlazo = new JLabel("plazo (meses):");
		lblPlazo.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblPlazo.setBounds(16, 68, 130, 28);
		panelContrato.add(lblPlazo);

		JLabel lblDesde = new JLabel("desde:");
		lblDesde.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblDesde.setBounds(280, 68, 55, 28);
		panelContrato.add(lblDesde);

		JLabel lblHasta = new JLabel("hasta:");
		lblHasta.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblHasta.setBounds(455, 68, 50, 28);
		panelContrato.add(lblHasta);

		JLabel lblCuotaMensual = new JLabel("factura mensual:");
		lblCuotaMensual.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblCuotaMensual.setBounds(16, 112, 130, 28);
		panelContrato.add(lblCuotaMensual);

		txtCod = new JTextField();
		txtCod.setText("CT-" + (Orange.generarCodCont < 10 ? "00" : Orange.generarCodCont < 100 ? "0" : "") + Orange.generarCodCont);
		txtCod.setEditable(false);
		txtCod.setBackground(new Color(235, 237, 240));
		txtCod.setFont(new Font("SansSerif", Font.BOLD, 13));
		txtCod.setBounds(150, 26, 116, 28);
		panelContrato.add(txtCod);
		txtCod.setColumns(10);

		spnPlazo = new JSpinner();
		spnPlazo.setFont(new Font("SansSerif", Font.PLAIN, 13));
		spnPlazo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				calcularPrecio();
			}
		});
		spnPlazo.setEnabled(false);
		spnPlazo.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnPlazo.setBounds(150, 68, 116, 28);
		panelContrato.add(spnPlazo);

		txtFInicio = new JTextField();
		txtFInicio.setEditable(false);
		txtFInicio.setBackground(new Color(235, 237, 240));
		txtFInicio.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtFInicio.setBounds(335, 68, 108, 28);
		panelContrato.add(txtFInicio);
		txtFInicio.setColumns(10);

		txtFFinal = new JTextField();
		txtFFinal.setEditable(false);
		txtFFinal.setBackground(new Color(235, 237, 240));
		txtFFinal.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtFFinal.setBounds(505, 68, 108, 28);
		panelContrato.add(txtFFinal);
		txtFFinal.setColumns(10);

		JLabel lblPrecioTotal = new JLabel("precio total (RD$):");
		lblPrecioTotal.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblPrecioTotal.setBounds(16, 156, 130, 28);
		panelContrato.add(lblPrecioTotal);

		txtMensual = new JTextField();
		txtMensual.setText("0.00");
		txtMensual.setEditable(false);
		txtMensual.setBackground(new Color(235, 237, 240));
		txtMensual.setFont(new Font("SansSerif", Font.BOLD, 13));
		txtMensual.setColumns(10);
		txtMensual.setBounds(150, 112, 116, 28);
		panelContrato.add(txtMensual);

		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setText("0.00");
		txtTotal.setBackground(new Color(235, 237, 240));
		txtTotal.setForeground(naranjaOrange);
		txtTotal.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtTotal.setColumns(10);
		txtTotal.setBounds(150, 156, 116, 32);
		panelContrato.add(txtTotal);

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(235, 237, 240));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 8));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnGen = new JButton("generar contrato y facturas");
		btnGen.setBackground(naranjaOrange);
		btnGen.setForeground(Color.WHITE);
		btnGen.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnGen.setFocusPainted(false);
		btnGen.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGen.setEnabled(false);
		btnGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!validarVenta()) {
					return;
				}

				Contrato nuevo = new Contrato(txtCod.getText(), (Cliente) (cbxCliente.getSelectedItem()),
						LocalDate.parse(txtFInicio.getText(), formato), LocalDate.parse(txtFFinal.getText(), formato),
						Float.parseFloat(txtMensual.getText()), (Plan) cbxPlan.getSelectedItem(), genFact());

				((Cliente) cbxCliente.getSelectedItem()).agregarContrato(nuevo);

				if (emp instanceof Comercial) {
					((Comercial) emp).setVentas(((Comercial) emp).getVentas() + 1);
				}

				Orange.getInstancia().regContrato(nuevo);

				JOptionPane.showMessageDialog(null, "Contrato y facturas generados con exito.", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
				limpiarCampos();
			}
		});
		btnGen.setActionCommand("OK");
		buttonPane.add(btnGen);
		getRootPane().setDefaultButton(btnGen);

		JButton cancelButton = new JButton("cancelar");
		cancelButton.setBackground(oscuroBarra);
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setFont(new Font("SansSerif", Font.BOLD, 13));
		cancelButton.setFocusPainted(false);
		cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		llenarCampos();
	}

	private boolean validarVenta() {
		if (cbxCliente.getSelectedIndex() <= 0) {
			JOptionPane.showMessageDialog(null, "debe seleccionar un cliente", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (cbxPlan.getSelectedIndex() <= 0) {
			JOptionPane.showMessageDialog(null, "debe seleccionar un plan", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!Validador.numeroIntValido(spnPlazo.getValue().toString(), "plazo")) {
			return false;
		}
		int meses = Integer.parseInt(spnPlazo.getValue().toString());
		if (meses <= 0) {
			JOptionPane.showMessageDialog(null, "el plazo debe ser al menos de 1 mes", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private void resetearContrato() {
		spnPlazo.setEnabled(false);
		spnPlazo.setValue(1);
		btnGen.setEnabled(false);
		txtMensual.setText("0.00");
		txtTotal.setText("0.00");
		txtFFinal.setText(LocalDate.now().plusMonths(1).format(formato));
	}

	protected void limpiarCampos() {
		cbxCliente.setSelectedIndex(0);
		spnPlazo.setValue(1);
		txtFInicio.setText(LocalDate.now().format(formato));
		txtFFinal.setText(LocalDate.now().plusMonths(1).format(formato));
		txtMensual.setText("0.00");
		txtTotal.setText("0.00");
		txtCod.setText("CT-" + (Orange.generarCodCont < 10 ? "00" : Orange.generarCodCont < 100 ? "0" : "") + Orange.generarCodCont);
	}

	protected void llenarPlan(Cliente c) {
		cbxPlan.removeAllItems();
		cbxPlan.addItem("<<SELECCIONE>>");

		for (Plan p : Orange.getInstancia().getPlanes()) {
			if (!c.planContratado(p)) {
				cbxPlan.addItem(p);
			}
		}

		if (cbxPlan.getItemCount() < 2) {
			cbxPlan.setEnabled(false);
			resetearContrato();
		}
	}

	protected ArrayList<Factura> genFact() {
		ArrayList<Factura> f = new ArrayList<>();
		int lim = Integer.parseInt(spnPlazo.getValue().toString());

		for (int i = 0; i < lim; i++) {
			Factura nueva = new Factura("FT-" + (Orange.generarCodFact < 10 ? "00" : Orange.generarCodFact < 100 ? "0" : "") + Orange.generarCodFact,
					((Plan) cbxPlan.getSelectedItem()),
					LocalDate.now().plusMonths(i + 1),
					((Plan) cbxPlan.getSelectedItem()).getPrecio());

			f.add(nueva);
			Orange.getInstancia().regFactura(nueva);
		}

		return f;
	}

	protected void calcularPrecio() {
		if (cbxPlan.getSelectedIndex() <= 0) {
			return;
		}

		float precio = ((Plan) cbxPlan.getSelectedItem()).getPrecio();
		float impuesto = precio * Orange.ITBIS;
		int meses = Integer.parseInt(spnPlazo.getValue().toString());

		txtMensual.setText(String.format(java.util.Locale.US, "%.2f", precio + impuesto));
		txtTotal.setText(String.format(java.util.Locale.US, "%.2f", (precio + impuesto) * meses));
		txtFFinal.setText(LocalDate.now().plusMonths(meses).format(formato).toString());
	}

	private void llenarCampos() {
		for (Cliente c : Orange.getInstancia().getClientes()) {
			if (!c.isDeudor()) {
				cbxCliente.addItem(c);
			}
		}

		txtFInicio.setText(LocalDate.now().format(formato));
		txtFFinal.setText(LocalDate.now().plusMonths(Integer.parseInt(spnPlazo.getValue().toString())).format(formato));
	}
}