package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.border.TitledBorder;

import logico.Cliente;
import logico.Orange;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;

public class RegPago extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JComboBox cbxContrato;
	private JComboBox cbxCliente;
	private DateTimeFormatter formato;
	private JTextField textField;

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
			
			JLabel lblEstado = new JLabel("Estado:");
			lblEstado.setBounds(12, 56, 95, 30);
			panel.add(lblEstado);
			
			JLabel lblPlan = new JLabel("Plan:");
			lblPlan.setBounds(12, 99, 95, 30);
			panel.add(lblPlan);
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
				JLabel lblCod = new JLabel("New label");
				lblCod.setBounds(119, 13, 95, 30);
				panel.add(lblCod);
			}
			{
				JLabel label = new JLabel("New label");
				label.setBounds(119, 56, 95, 30);
				panel.add(label);
			}
			{
				JLabel lblDesde = new JLabel("Desde:");
				lblDesde.setBounds(226, 13, 95, 30);
				panel.add(lblDesde);
			}
			{
				JLabel lblHasta = new JLabel("Hasta:");
				lblHasta.setBounds(226, 56, 95, 30);
				panel.add(lblHasta);
			}
			{
				JLabel lblDesde = new JLabel("New label");
				lblDesde.setBounds(332, 13, 95, 30);
				panel.add(lblDesde);
			}
			{
				JLabel lblHasta = new JLabel("New label");
				lblHasta.setBounds(333, 56, 95, 30);
				panel.add(lblHasta);
			}
			{
				JLabel label = new JLabel("New label");
				label.setBounds(119, 99, 95, 30);
				panel.add(label);
			}
			
			textField = new JTextField();
			textField.setText("0.00");
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setForeground(new Color(255, 102, 0));
			textField.setFont(new Font("SansSerif", Font.BOLD, 18));
			textField.setEditable(false);
			textField.setColumns(10);
			textField.setBackground(new Color(235, 237, 240));
			textField.setBounds(12, 228, 135, 45);
			panel.add(textField);
			
			JSpinner spinner = new JSpinner();
			spinner.setBounds(226, 142, 202, 30);
			panel.add(spinner);
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
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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
	}
	
	private void llenarClientes() 
	{
		for(Cliente c: Orange.getInstancia().getClientes()) 
		{
				cbxCliente.addItem(c); 
		}
		
	}
}
