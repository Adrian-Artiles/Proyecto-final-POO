package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.border.TitledBorder;

import logico.Cliente;
import logico.Contrato;
import logico.Factura;
import logico.Orange;
import logico.Servicio;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class RegPago extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JComboBox cbxContrato;
	private JComboBox cbxCliente;
	private DateTimeFormatter formato;
	private JTextField txtTotal;
	private JSpinner spnPagar;
	private JLabel lblCod;
	private JLabel lblEstado;
	private JLabel lblDesde;
	private JLabel lblHasta;
	private JLabel lblPlan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			RegPago dialog = new RegPago();
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
	public RegPago()
	{
		setTitle("Pagar Facturas");
		setBounds(100, 100, 471, 510);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null );
		formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);

			JLabel lblNewLabel_4 = new JLabel("Codigo:");
			lblNewLabel_4.setBounds(12, 13, 95, 30);
			panel.add(lblNewLabel_4);

			JLabel label2 = new JLabel("Estado:");
			label2.setBounds(12, 56, 95, 30);
			panel.add(label2);

			JLabel lblPlan_1 = new JLabel("Plan:");
			lblPlan_1.setBounds(12, 99, 95, 30);
			panel.add(lblPlan_1);
			{
				JLabel lblFacturasAPagar = new JLabel("Facturas a Pagar (en meses):");
				lblFacturasAPagar.setHorizontalAlignment(SwingConstants.CENTER);
				lblFacturasAPagar.setBounds(12, 142, 202, 30);
				panel.add(lblFacturasAPagar);
			}
			{
				JLabel lblTotalAPagar = new JLabel("Total a Pagar:");
				lblTotalAPagar.setHorizontalAlignment(SwingConstants.CENTER);
				lblTotalAPagar.setBounds(12, 185, 135, 30);
				panel.add(lblTotalAPagar);
			}
			{
				lblCod = new JLabel("New label");
				lblCod.setBounds(119, 13, 95, 30);
				panel.add(lblCod);
			}
			{
				JLabel lblDesde_1 = new JLabel("Desde:");
				lblDesde_1.setBounds(226, 13, 95, 30);
				panel.add(lblDesde_1);
			}
			{
				JLabel lblHasta_1 = new JLabel("Hasta:");
				lblHasta_1.setBounds(226, 56, 95, 30);
				panel.add(lblHasta_1);
			}
			{
				lblDesde = new JLabel("New label");
				lblDesde.setBounds(332, 13, 95, 30);
				panel.add(lblDesde);
			}
			{
				lblHasta = new JLabel("New label");
				lblHasta.setBounds(333, 56, 95, 30);
				panel.add(lblHasta);
			}
			{
				lblPlan = new JLabel("New label");
				lblPlan.setBounds(119, 99, 308, 30);
				panel.add(lblPlan);
			}

			txtTotal = new JTextField();
			txtTotal.setText("0.00");
			txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
			txtTotal.setForeground(new Color(255, 102, 0));
			txtTotal.setFont(new Font("SansSerif", Font.BOLD, 18));
			txtTotal.setEditable(false);
			txtTotal.setColumns(10);
			txtTotal.setBackground(new Color(235, 237, 240));
			txtTotal.setBounds(12, 227, 135, 45);
			panel.add(txtTotal);

			spnPagar = new JSpinner();
			spnPagar.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					calcularTotal((Contrato) (cbxContrato.getSelectedItem()));
				}
			});
			spnPagar.setEnabled(false);
			spnPagar.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnPagar.setBounds(226, 142, 202, 30);
			panel.add(spnPagar);
			{
				lblEstado = new JLabel("New label");
				lblEstado.setBounds(119, 56, 95, 30);
				panel.add(lblEstado);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Cliente y Contrato", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new GridLayout(3, 2, 0, 0));
			{
				JLabel lblNewLabel = new JLabel("Cliente:");
				panel.add(lblNewLabel);
			}
			{
				cbxCliente = new JComboBox();
				cbxCliente.addItem("<<SELECCIONE>>");
				cbxCliente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(cbxCliente.getSelectedIndex() > 0) 
						{
							cbxContrato.setEnabled(true);
							llenarContrato((Cliente)(cbxCliente.getSelectedItem()));
						}
						else 
						{
							cbxContrato.setEnabled(false);
							cbxContrato.removeAllItems();
							cbxContrato.addItem("<<SELECCIONE>>");
						}
					}
				});
				panel.add(cbxCliente);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("");
				panel.add(lblNewLabel_3);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("");
				panel.add(lblNewLabel_2);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Contrato:");
				panel.add(lblNewLabel_1);
			}
			{
				cbxContrato = new JComboBox();
				cbxContrato.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(cbxContrato.getSelectedIndex() > 0) 
						{
							spnPagar.setEnabled(true);
							llenarDatos((Contrato) (cbxContrato.getSelectedItem()));
						}
						else 
						{
							LimpiarDatos();
						}
					}
				});
				cbxContrato.setEnabled(false);
				cbxContrato.addItem("<<SELECCIONE>>");
				panel.add(cbxContrato);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnPagar = new JButton("Pagar");
				btnPagar.setEnabled(false);
				btnPagar.setActionCommand("OK");
				buttonPane.add(btnPagar);
				getRootPane().setDefaultButton(btnPagar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		llenarClientes();
	}

	protected void calcularTotal(Contrato ct)
	{
			float total = 0.0f;
			for (Factura f: ct.getFacturas()) 
			{
				//total += s.getPrecio();
			}
			
			txtTotal.setText(String.format(java.util.Locale.US, "%.2f", total));
	}

	protected void llenarDatos(Contrato ct)
	{
		lblCod.setText(ct.getCodigo());
		lblEstado.setText(ct.isActivo() ? "ACTIVO" : "FINALIZADO" );
		lblDesde.setText(ct.getFechaIng().format(formato).toString());
		lblHasta.setText(ct.getFechaFin().format(formato).toString());
		lblPlan.setText(ct.getPlan().toString());
	}

	protected void LimpiarDatos()
	{
		lblCod.setText("");
		lblEstado.setText("");
		lblDesde.setText("");
		lblHasta.setText("");
		lblPlan.setText("");
	}

	protected void llenarContrato(Cliente c)
	{
		cbxContrato.removeAllItems();
		cbxContrato.addItem("<<SELECCIONE>>");

		for(Contrato ct: c.getContratos()) 
		{
			if(!ct.pagoCompleto()) 
			{
				cbxContrato.addItem(ct);
			}
		}

	}

	private void llenarClientes() 
	{
		for(Cliente c: Orange.getInstancia().getClientes()) 
		{
			if(!c.clientePago()) 
			{
				cbxCliente.addItem(c); 
			}
		}
	}
	
	public boolean hayClientesPorPagar() 
	{
		return cbxCliente.getItemCount() >= 2;
	}
}
