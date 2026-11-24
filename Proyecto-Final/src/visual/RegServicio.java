package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;

import logico.Cable;
import logico.InternetHogar;
import logico.Orange;
import logico.Servicio;
import logico.Telefono;

import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class RegServicio extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtCod;
	private JRadioButton rdbtnTel;
	private JRadioButton rdbtnHogar;
	private JRadioButton rdbtn;
	private JFormattedTextField ftxtPrecio;
	private JSpinner spinner1;
	private JSpinner spinner2;
	private JLabel lblExtra1;
	private JLabel lblExtra2;
	private JRadioButton rdbtnHD;
	private JSpinner spinner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			RegServicio dialog = new RegServicio();
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
	public RegServicio()
	{
		setTitle("Registrar Servicio");
		setBounds(100, 100, 514, 341);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(null, "Tipo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel.add(panel_1, BorderLayout.NORTH);
				{
					rdbtnTel = new JRadioButton("Telefono");
					rdbtnTel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							actualizarDatos();
						}
					});
					rdbtnTel.setSelected(true);
					buttonGroup.add(rdbtnTel);
					panel_1.add(rdbtnTel);
				}
				{
					rdbtnHogar = new JRadioButton("Hogar");
					rdbtnHogar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							actualizarDatos();
						}
					});
					buttonGroup.add(rdbtnHogar);
					panel_1.add(rdbtnHogar);
				}
				{
					rdbtn = new JRadioButton("Cable");
					rdbtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							actualizarDatos();
						}
					});
					buttonGroup.add(rdbtn);
					panel_1.add(rdbtn);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel.add(panel_1, BorderLayout.CENTER);
				panel_1.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("codigo:");
				lblNewLabel.setBounds(12, 13, 160, 30);
				panel_1.add(lblNewLabel);
				
				JLabel lblPrecio = new JLabel("precio:");
				lblPrecio.setBounds(12, 56, 160, 30);
				panel_1.add(lblPrecio);
				
				lblExtra1 = new JLabel("minutos de llamada:");
				lblExtra1.setBounds(12, 99, 160, 30);
				panel_1.add(lblExtra1);
				
				lblExtra2 = new JLabel("datos (en GB):");
				lblExtra2.setBounds(12, 142, 160, 30);
				panel_1.add(lblExtra2);
				
				txtCod = new JTextField();
				txtCod.setBounds(205, 13, 225, 30);
				txtCod.setText("S-" + (Orange.generarCodServ < 10 ? "00" : Orange.generarCodServ < 100 ? "0" : "") + Orange.generarCodServ);
				panel_1.add(txtCod);
				txtCod.setColumns(10);
				
				DecimalFormat formato = new DecimalFormat("#0.00");
				formato.setParseBigDecimal(true);

				NumberFormatter formatear = new NumberFormatter(formato);
				formatear.setValueClass(Float.class);
				formatear.setMinimum(0.0f);
				formatear.setAllowsInvalid(false);
				
				ftxtPrecio = new JFormattedTextField(formatear);
				ftxtPrecio.setValue(0.0f);
				ftxtPrecio.setBounds(205, 60, 225, 30);
				panel_1.add(ftxtPrecio);
				
				spinner1 = new JSpinner();
				spinner1.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
				spinner1.setBounds(205, 103, 225, 30);
				panel_1.add(spinner1);
				
				spinner2 = new JSpinner();
				spinner2.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
				spinner2.setBounds(205, 146, 225, 30);
				panel_1.add(spinner2);
				
				rdbtnHD = new JRadioButton("incluir HD");
				rdbtnHD.setVisible(false);
				rdbtnHD.setBounds(179, 146, 127, 30);
				panel_1.add(rdbtnHD);
				
				spinner = new JSpinner();
				spinner.setBounds(205, 60, 225, 30);
				panel_1.add(spinner);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCrear = new JButton("Crear");
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Servicio s = null;
						if(rdbtnTel.isSelected()) 
						{
							s = new Telefono(txtCod.getText(), Float.parseFloat(ftxtPrecio.getValue().toString()), Integer.parseInt(spinner1.getValue().toString()), Integer.parseInt(spinner2.getValue().toString()));
						}
						else if(rdbtnHogar.isSelected()) 
						{
							s = new InternetHogar(txtCod.getText(), Float.parseFloat(ftxtPrecio.getValue().toString()), Integer.parseInt(spinner1.getValue().toString()), Integer.parseInt(spinner2.getValue().toString()));
						}
						else 
						{
							s = new Cable(txtCod.getText(), Float.parseFloat(ftxtPrecio.getValue().toString()), Integer.parseInt(spinner1.getValue().toString()), rdbtnHD.isSelected());
						}
						
						Orange.getInstancia().regServicio(s);
						JOptionPane.showMessageDialog(null, "servicio registrado correctamente", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
						txtCod.setText("S-" + (Orange.generarCodServ < 10 ? "00" : Orange.generarCodServ < 100 ? "0" : "") + Orange.generarCodServ);
						
					}
				});
				btnCrear.setActionCommand("OK");
				buttonPane.add(btnCrear);
				getRootPane().setDefaultButton(btnCrear);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}

	protected void actualizarDatos()
	{
		if(rdbtnTel.isSelected()) 
		{
			lblExtra2.setVisible(true);
			spinner2.setVisible(true);
			rdbtnHD.setVisible(false);
			lblExtra1.setText("minutos de llamada:");
			spinner1.setValue(0);
			lblExtra2.setText("datos (en GB):");
			spinner2.setValue(0);
		}
		else if(rdbtnHogar.isSelected()) 
		{
			lblExtra2.setVisible(true);
			spinner2.setVisible(true);
			rdbtnHD.setVisible(false);
			lblExtra1.setText("Velocidad de Subida (Mbps)");
			spinner1.setValue(0);
			lblExtra2.setText("Velocidad de Bajada (Mbps)");
			spinner2.setValue(0);
		}
		else 
		{
			lblExtra1.setText("Cantidad de Canales");
			spinner1.setValue(0);
			rdbtnHD.setVisible(true);
			lblExtra2.setVisible(false);
			spinner2.setVisible(false);
		}
		
	}
}
