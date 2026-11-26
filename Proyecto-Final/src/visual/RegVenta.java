package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import logico.Cliente;
import logico.Contrato;
import logico.Empleado;
import logico.Factura;
import logico.Orange;
import logico.Plan;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class RegVenta extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JComboBox cbxCliente;
	private JComboBox cbxPlan;
	private JTextField txtCod;
	private JTextField txtFInicio;
	private JTextField txtFFinal;
	private JTextField txtMensual;
	private JTextField txtTotal;
	private JSpinner spnPlazo;
	private Empleado e;
	private DateTimeFormatter formato;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			RegVenta dialog = new RegVenta(null);
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
	public RegVenta(Empleado aux)
	{
		e = aux;
		setTitle("Contratar un Plan");
		setBounds(100, 100, 648, 408);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Contrato", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);

			JLabel lblNewLabel_2 = new JLabel("Codigo:");
			lblNewLabel_2.setBounds(12, 13, 109, 30);
			panel.add(lblNewLabel_2);

			JLabel lblPlazo = new JLabel("Plazo (en meses):");
			lblPlazo.setBounds(12, 56, 109, 30);
			panel.add(lblPlazo);

			JLabel lblDesde = new JLabel("Desde:");
			lblDesde.setBounds(261, 56, 46, 30);
			panel.add(lblDesde);

			JLabel lblHasta = new JLabel("Hasta:");
			lblHasta.setBounds(447, 56, 46, 30);
			panel.add(lblHasta);

			JLabel lblCuotaMensual = new JLabel("Factura Mensual:");
			lblCuotaMensual.setBounds(12, 99, 109, 30);
			panel.add(lblCuotaMensual);

			txtCod =  new JTextField();
			txtCod.setText("CT-" + (Orange.generarCodCont < 10 ? "00" :  Orange.generarCodCont < 100 ? "0" : "") + Orange.generarCodCont);
			txtCod.setEditable(false);
			txtCod.setBounds(133, 13, 116, 30);
			panel.add(txtCod);
			txtCod.setColumns(10);

			spnPlazo = new JSpinner();
			spnPlazo.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					calcularPrecio();
				}
			});
			spnPlazo.setEnabled(false);
			spnPlazo.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnPlazo.setBounds(133, 56, 116, 30);
			panel.add(spnPlazo);

			txtFInicio = new JTextField();
			txtFInicio.setEditable(false);
			txtFInicio.setBounds(319, 56, 116, 30);
			panel.add(txtFInicio);
			txtFInicio.setColumns(10);

			txtFFinal = new JTextField();
			txtFFinal.setEditable(false);
			txtFFinal.setBounds(492, 56, 116, 30);
			panel.add(txtFFinal);
			txtFFinal.setColumns(10);

			JLabel lblNewLabel_3 = new JLabel("Precio Total:");
			lblNewLabel_3.setBounds(12, 146, 109, 30);
			panel.add(lblNewLabel_3);

			txtMensual = new JTextField();
			txtMensual.setEditable(false);
			txtMensual.setColumns(10);
			txtMensual.setBounds(133, 103, 116, 30);
			panel.add(txtMensual);

			txtTotal = new JTextField();
			txtTotal.setEditable(false);
			txtTotal.setColumns(10);
			txtTotal.setBounds(133, 146, 116, 30);
			panel.add(txtTotal);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Cliente y Plan", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new GridLayout(3, 2, 0, 3));
			{
				JLabel lblNewLabel = new JLabel("CLIENTE:");
				panel.add(lblNewLabel);
			}
			{
				cbxCliente = new JComboBox();
				cbxCliente.addItem("<<SELECCIONE>>"); 
				cbxCliente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(cbxCliente.getSelectedIndex() > 0) 
						{
							cbxPlan.setEnabled(true);
							llenarPlan((Cliente)(cbxCliente.getSelectedItem()));
						}
						else 
						{
							cbxPlan.setEnabled(false);
							cbxPlan.removeAllItems();
							cbxPlan.addItem("<<SELECCIONE>>");
						}
					}
				});
				panel.add(cbxCliente);
			}

			JLabel lblNewLabel_6 = new JLabel("");
			panel.add(lblNewLabel_6);

			JLabel lblNewLabel_7 = new JLabel("");
			panel.add(lblNewLabel_7);
			{
				JLabel lblNewLabel_1 = new JLabel("PLAN:");
				panel.add(lblNewLabel_1);
			}
			{
				cbxPlan = new JComboBox();
				cbxPlan.addItem("<<SELECCIONE>>"); 
				cbxPlan.setEnabled(false);
				cbxPlan.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(cbxPlan.getSelectedIndex() > 0) 
						{
							spnPlazo.setEnabled(true);
							calcularPrecio();
						}
						else 
						{
							spnPlazo.setEnabled(false);
						}
					}
				});
				panel.add(cbxPlan);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("generar contrato y facturas");
				okButton.setEnabled(false);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						Contrato nuevo = new Contrato(txtCod.getText(),(Cliente) (cbxCliente.getSelectedItem()), 
								LocalDate.parse(txtFInicio.getText(), formato), LocalDate.parse(txtFFinal.getText(), formato), 
								Float.parseFloat(txtMensual.getText()), (Plan) cbxPlan.getSelectedItem(), genFact());

						((Cliente) cbxCliente.getSelectedItem()).agregarContrato(nuevo);

						Orange.getInstancia().regContrato(nuevo);

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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
		llenarCampos();
	}

	protected void llenarPlan(Cliente c)
	{
		cbxPlan.removeAllItems();
		cbxPlan.addItem("<<SELECCIONADO>>"); 


		for(Plan p: Orange.getInstancia().getPlanes()) 
		{
			boolean encontrado = false;
			int i = 0;

			while (i < c.getContratos().size() && !encontrado) 
			{
				if (c.getContratos().get(i).getPlan().getCodigo().equals(p.getCodigo())) 
				{
					encontrado = true;
				}
				i++;
			}

			if(!encontrado) 
			{
				cbxPlan.addItem(p);
			}
		}

		if(cbxPlan.getItemCount() < 2) 
		{
			cbxPlan.setEnabled(false);
		}
	}

	protected ArrayList<Factura> genFact()
	{
		ArrayList<Factura> f = new ArrayList<>();
		int lim = Integer.parseInt(spnPlazo.getValue().toString());

		for(int i = 0; i < lim; i++) 
		{
			Factura nueva = new Factura("F", ((Plan) cbxPlan.getSelectedItem()), 
					LocalDate.now().plusMonths(i + 1), ((Plan) cbxPlan.getSelectedItem()).getPrecio());

			f.add(nueva);
		}

		return f;
	}

	protected void calcularPrecio()
	{
		float precio = ((Plan) cbxPlan.getSelectedItem()).getPrecio();
		float impuesto = precio * Orange.ITBIS;
		int meses = Integer.parseInt(spnPlazo.getValue().toString());

		txtMensual.setText(String.format(java.util.Locale.US, "%.2f", precio + impuesto));
		txtTotal.setText(String.format(java.util.Locale.US, "%.2f", (precio + impuesto) * meses));
		txtFFinal.setText(LocalDate.now().plusMonths(meses).toString());
	}

	private void llenarCampos() 
	{
		for(Cliente c: Orange.getInstancia().getClientes()) 
		{
			if(!c.isDeudor()) 
			{
				cbxCliente.addItem(c); 
			}
		}

		txtFInicio.setText(LocalDate.now().format(formato));
		txtFFinal.setText(LocalDate.now().plusMonths(Integer.parseInt(spnPlazo.getValue().toString())).format(formato));
	}
}
