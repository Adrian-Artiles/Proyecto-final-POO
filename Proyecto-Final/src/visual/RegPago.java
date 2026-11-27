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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import logico.Cliente;
import logico.Contrato;
import logico.Factura;
import logico.Orange;
import logico.Validador;

public class RegPago extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<Object> cbxContrato;
	private JComboBox<Object> cbxCliente;
	private DateTimeFormatter formato;
	private JTextField txtTotal;
	private JSpinner spnPagar;
	private JLabel lblCod;
	private JLabel lblEstado;
	private JLabel lblDesde;
	private JLabel lblHasta;
	private JLabel lblPlan;
	private DefaultListModel<String> modeloFacturas;
	private JList<String> listFacturas;
	private JButton btnPagar;

	private final Color naranjaOrange = new Color(255, 102, 0);
	private final Color fondoClaro = new Color(245, 246, 248);
	private final Color oscuroBarra = new Color(25, 25, 25);

	public static void main(String[] args) {
		try {
			RegPago dialog = new RegPago();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegPago() {
		setResizable(false);
		setTitle("Orange - Pagar Facturas");
		setBounds(100, 100, 560, 620);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(naranjaOrange);
		panelHeader.setPreferredSize(new Dimension(560, 64));
		panelHeader.setLayout(new BorderLayout());
		panelHeader.setBorder(new EmptyBorder(12, 20, 12, 20));
		getContentPane().add(panelHeader, BorderLayout.NORTH);

		JLabel lblTitulo = new JLabel("Orange - Pagar Facturas");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
		panelHeader.add(lblTitulo, BorderLayout.WEST);

		JLabel lblSub = new JLabel("cobro de cuotas");
		lblSub.setForeground(new Color(255, 235, 215));
		lblSub.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblSub.setHorizontalAlignment(SwingConstants.RIGHT);
		panelHeader.add(lblSub, BorderLayout.EAST);

		contentPanel.setBackground(fondoClaro);
		contentPanel.setBorder(new EmptyBorder(10, 14, 6, 14));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 8));

		JPanel panelSeleccion = new JPanel();
		panelSeleccion.setBackground(Color.WHITE);
		panelSeleccion.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 4, 0, 0, naranjaOrange),
				new TitledBorder(BorderFactory.createLineBorder(new Color(220, 223, 228)), "cliente y contrato", TitledBorder.LEADING, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 13), oscuroBarra)
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
					cbxContrato.setEnabled(true);
					llenarContrato((Cliente) (cbxCliente.getSelectedItem()));
				} else {
					if (cbxContrato != null) {
						cbxContrato.setEnabled(false);
						cbxContrato.removeAllItems();
						cbxContrato.addItem("<<SELECCIONE>>");
					}
					LimpiarDatos();
				}
			}
		});
		panelSeleccion.add(cbxCliente);

		JLabel lblContrato = new JLabel("contrato:");
		lblContrato.setFont(new Font("SansSerif", Font.BOLD, 13));
		panelSeleccion.add(lblContrato);

		cbxContrato = new JComboBox<>();
		cbxContrato.setBackground(Color.WHITE);
		cbxContrato.setFont(new Font("SansSerif", Font.PLAIN, 13));
		cbxContrato.setEnabled(false);
		cbxContrato.addItem("<<SELECCIONE>>");
		cbxContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbxContrato.getSelectedIndex() > 0) {
					llenarDatos((Contrato) (cbxContrato.getSelectedItem()));
				} else {
					LimpiarDatos();
				}
			}
		});
		panelSeleccion.add(cbxContrato);

		JPanel panelCentro = new JPanel();
		panelCentro.setBackground(fondoClaro);
		panelCentro.setLayout(new BorderLayout(0, 8));
		contentPanel.add(panelCentro, BorderLayout.CENTER);

		JPanel panelContrato = new JPanel();
		panelContrato.setBackground(Color.WHITE);
		panelContrato.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 4, 0, 0, oscuroBarra),
				new TitledBorder(BorderFactory.createLineBorder(new Color(220, 223, 228)), "detalle del contrato y cobro", TitledBorder.LEADING, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 13), oscuroBarra)
		));
		panelContrato.setPreferredSize(new Dimension(520, 225));
		panelCentro.add(panelContrato, BorderLayout.NORTH);
		panelContrato.setLayout(null);

		JLabel lblCodigoTitulo = new JLabel("codigo:");
		lblCodigoTitulo.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblCodigoTitulo.setBounds(14, 20, 85, 24);
		panelContrato.add(lblCodigoTitulo);

		lblCod = new JLabel("-");
		lblCod.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblCod.setBounds(105, 20, 120, 24);
		panelContrato.add(lblCod);

		JLabel lblDesdeTitulo = new JLabel("desde:");
		lblDesdeTitulo.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblDesdeTitulo.setBounds(245, 20, 65, 24);
		panelContrato.add(lblDesdeTitulo);

		lblDesde = new JLabel("-");
		lblDesde.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblDesde.setBounds(315, 20, 175, 24);
		panelContrato.add(lblDesde);

		JLabel lblEstadoTitulo = new JLabel("estado:");
		lblEstadoTitulo.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblEstadoTitulo.setBounds(14, 48, 85, 24);
		panelContrato.add(lblEstadoTitulo);

		lblEstado = new JLabel("-");
		lblEstado.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblEstado.setBounds(105, 48, 120, 24);
		panelContrato.add(lblEstado);

		JLabel lblHastaTitulo = new JLabel("hasta:");
		lblHastaTitulo.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblHastaTitulo.setBounds(245, 48, 65, 24);
		panelContrato.add(lblHastaTitulo);

		lblHasta = new JLabel("-");
		lblHasta.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblHasta.setBounds(315, 48, 175, 24);
		panelContrato.add(lblHasta);

		JLabel lblPlanTitulo = new JLabel("plan:");
		lblPlanTitulo.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblPlanTitulo.setBounds(14, 78, 85, 24);
		panelContrato.add(lblPlanTitulo);

		lblPlan = new JLabel("-");
		lblPlan.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblPlan.setBounds(105, 78, 390, 24);
		panelContrato.add(lblPlan);

		JLabel lblFacturasAPagar = new JLabel("facturas a pagar (meses):");
		lblFacturasAPagar.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblFacturasAPagar.setBounds(14, 114, 195, 28);
		panelContrato.add(lblFacturasAPagar);

		spnPagar = new JSpinner();
		spnPagar.setFont(new Font("SansSerif", Font.PLAIN, 13));
		spnPagar.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (cbxContrato != null && cbxContrato.getSelectedIndex() > 0) {
					actualizarListaYTotal((Contrato) (cbxContrato.getSelectedItem()));
				}
			}
		});
		spnPagar.setEnabled(false);
		spnPagar.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1)));
		spnPagar.setBounds(215, 114, 110, 28);
		panelContrato.add(spnPagar);

		JLabel lblTotalAPagar = new JLabel("total a pagar (RD$):");
		lblTotalAPagar.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblTotalAPagar.setBounds(14, 154, 195, 34);
		panelContrato.add(lblTotalAPagar);

		txtTotal = new JTextField();
		txtTotal.setText("0.00");
		txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotal.setForeground(naranjaOrange);
		txtTotal.setFont(new Font("SansSerif", Font.BOLD, 18));
		txtTotal.setEditable(false);
		txtTotal.setColumns(10);
		txtTotal.setBackground(new Color(235, 237, 240));
		txtTotal.setBounds(215, 152, 165, 40);
		panelContrato.add(txtTotal);

		JPanel panelListaFacturas = new JPanel();
		panelListaFacturas.setBackground(Color.WHITE);
		panelListaFacturas.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 4, 0, 0, naranjaOrange),
				new TitledBorder(BorderFactory.createLineBorder(new Color(220, 223, 228)), "facturas pendientes seleccionadas", TitledBorder.LEADING, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 13), oscuroBarra)
		));
		panelListaFacturas.setLayout(new BorderLayout());
		panelCentro.add(panelListaFacturas, BorderLayout.CENTER);

		modeloFacturas = new DefaultListModel<>();
		listFacturas = new JList<>(modeloFacturas);
		listFacturas.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JScrollPane scrollFacturas = new JScrollPane(listFacturas);
		panelListaFacturas.add(scrollFacturas, BorderLayout.CENTER);

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(235, 237, 240));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 8));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnPagar = new JButton("pagar");
		btnPagar.setBackground(naranjaOrange);
		btnPagar.setForeground(Color.WHITE);
		btnPagar.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnPagar.setFocusPainted(false);
		btnPagar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnPagar.setEnabled(false);
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!validarPago()) {
					return;
				}

				Cliente clienteSel = (Cliente) cbxCliente.getSelectedItem();
				Contrato contratoSel = (Contrato) cbxContrato.getSelectedItem();
				ArrayList<Factura> pendientes = obtenerFacturasPendientes(contratoSel);
				int cantidad = Integer.parseInt(spnPagar.getValue().toString());
				int limite = Math.min(cantidad, pendientes.size());

				for (int i = 0; i < limite; i++) {
					pendientes.get(i).setPagada(true);
				}

				contratoSel.verificarContrato();
				clienteSel.setDeudor(!clienteSel.clientePago());
				Orange.getInstancia().guardarArchivo();

				JOptionPane.showMessageDialog(null, "Pago registrado correctamente (" + limite + " factura(s)).", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);

				if (clienteSel.clientePago()) {
					recargarClientesPendientes();
				} else {
					llenarContrato(clienteSel);
				}
			}
		});
		btnPagar.setActionCommand("OK");
		buttonPane.add(btnPagar);
		getRootPane().setDefaultButton(btnPagar);

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

		llenarClientes();
	}

	private boolean validarPago() {
		if (cbxCliente.getSelectedIndex() <= 0) {
			JOptionPane.showMessageDialog(null, "debe seleccionar un cliente", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (cbxContrato.getSelectedIndex() <= 0) {
			JOptionPane.showMessageDialog(null, "debe seleccionar un contrato", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!Validador.numeroIntValido(spnPagar.getValue().toString(), "facturas a pagar")) {
			return false;
		}
		Contrato ct = (Contrato) cbxContrato.getSelectedItem();
		ArrayList<Factura> pendientes = obtenerFacturasPendientes(ct);
		int cantidad = Integer.parseInt(spnPagar.getValue().toString());
		if (pendientes.isEmpty() || cantidad <= 0 || cantidad > pendientes.size()) {
			JOptionPane.showMessageDialog(null, "cantidad de facturas a pagar no valida", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private void recargarClientesPendientes() {
		cbxCliente.removeAllItems();
		cbxCliente.addItem("<<SELECCIONE>>");
		llenarClientes();
		cbxContrato.setEnabled(false);
		cbxContrato.removeAllItems();
		cbxContrato.addItem("<<SELECCIONE>>");
		LimpiarDatos();
	}

	private ArrayList<Factura> obtenerFacturasPendientes(Contrato ct) {
		ArrayList<Factura> pendientes = new ArrayList<>();
		if (ct != null && ct.getFacturas() != null) {
			for (Factura f : ct.getFacturas()) {
				if (!f.isPagada()) {
					pendientes.add(f);
				}
			}
		}
		return pendientes;
	}

	protected void actualizarListaYTotal(Contrato ct) {
		if (modeloFacturas == null || spnPagar == null) {
			return;
		}
		modeloFacturas.clear();
		ArrayList<Factura> pendientes = obtenerFacturasPendientes(ct);
		int cantidad = Integer.parseInt(spnPagar.getValue().toString());
		if (cantidad > pendientes.size()) {
			cantidad = pendientes.size();
		}

		for (int i = 0; i < cantidad; i++) {
			Factura f = pendientes.get(i);
			String fechaTxt = (f.getFecha() != null) ? f.getFecha().format(formato) : "-";
			String estadoVenc = (f.getFecha() != null && f.getFecha().isBefore(LocalDate.now())) ? " [VENCIDA]" : "";
			modeloFacturas.addElement("Mes " + (i + 1) + " | " + f.getCodigo() + " | Vence: " + fechaTxt + " | RD$ " + String.format(java.util.Locale.US, "%.2f", f.getTotal()) + estadoVenc);
		}

		calcularTotal(ct);
	}

	protected void calcularTotal(Contrato ct) {
		if (txtTotal == null || spnPagar == null) {
			return;
		}
		float total = 0.0f;
		ArrayList<Factura> pendientes = obtenerFacturasPendientes(ct);
		int cantidad = Integer.parseInt(spnPagar.getValue().toString());
		int limite = Math.min(cantidad, pendientes.size());

		for (int i = 0; i < limite; i++) {
			total += pendientes.get(i).getTotal();
		}

		txtTotal.setText(String.format(java.util.Locale.US, "%.2f", total));
	}

	protected void llenarDatos(Contrato ct) {
		lblCod.setText(ct.getCodigo());
		lblEstado.setText(ct.isActivo() ? "ACTIVO" : "FINALIZADO");
		lblDesde.setText(ct.getFechaIng().format(formato).toString());
		lblHasta.setText(ct.getFechaFin().format(formato).toString());
		lblPlan.setText(ct.getPlan().toString());

		int pendientes = obtenerFacturasPendientes(ct).size();
		int maxMeses = Math.max(1, pendientes);
		spnPagar.setModel(new SpinnerNumberModel(1, 1, maxMeses, 1));
		spnPagar.setEnabled(pendientes > 0);
		btnPagar.setEnabled(pendientes > 0);
		actualizarListaYTotal(ct);
	}

	protected void LimpiarDatos() {
		if (lblCod == null || lblEstado == null || lblDesde == null || lblHasta == null || lblPlan == null || spnPagar == null || btnPagar == null || modeloFacturas == null || txtTotal == null) {
			return;
		}
		lblCod.setText("-");
		lblEstado.setText("-");
		lblDesde.setText("-");
		lblHasta.setText("-");
		lblPlan.setText("-");
		spnPagar.setModel(new SpinnerNumberModel(1, 1, 1, 1));
		spnPagar.setEnabled(false);
		btnPagar.setEnabled(false);
		modeloFacturas.clear();
		txtTotal.setText("0.00");
	}

	protected void llenarContrato(Cliente c) {
		cbxContrato.removeAllItems();
		cbxContrato.addItem("<<SELECCIONE>>");

		for (Contrato ct : c.getContratos()) {
			if (!ct.pagoCompleto()) {
				cbxContrato.addItem(ct);
			}
		}
		LimpiarDatos();
	}

	private void llenarClientes() {
		for (Cliente c : Orange.getInstancia().getClientes()) {
			if (!c.clientePago()) {
				cbxCliente.addItem(c);
			}
		}
	}

	public boolean hayClientesPorPagar() {
		return cbxCliente.getItemCount() >= 2;
	}
}