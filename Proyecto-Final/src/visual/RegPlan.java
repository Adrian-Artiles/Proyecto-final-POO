package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Cable;
import logico.InternetHogar;
import logico.Orange;
import logico.Plan;
import logico.Servicio;
import logico.Telefono;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class RegPlan extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtCod;
	private JComboBox cbxTel;
	private JComboBox cbxHogar;
	private JComboBox cbxCable;
	private JTextField txtPrecio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			RegPlan dialog = new RegPlan();
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
	public RegPlan()
	{
		setTitle("Crear Plan");
		setBounds(100, 100, 713, 561);
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
				panel_1.setLayout(null);
				panel.add(panel_1, BorderLayout.CENTER);
				{
					JLabel label = new JLabel("Servicios de Telefono");
					label.setFont(new Font("SansSerif", Font.BOLD, 14));
					label.setBounds(12, 13, 460, 30);
					panel_1.add(label);
				}
				{
					cbxTel = new JComboBox();
					cbxTel.setFont(new Font("SansSerif", Font.BOLD, 14));
					cbxTel.setBounds(12, 56, 495, 30);
					panel_1.add(cbxTel);
				}
				{
					JLabel label = new JLabel("Servicios de Internet Hogar");
					label.setFont(new Font("SansSerif", Font.BOLD, 14));
					label.setBounds(12, 99, 460, 30);
					panel_1.add(label);
				}
				{
					cbxHogar = new JComboBox();
					cbxHogar.setFont(new Font("SansSerif", Font.BOLD, 14));
					cbxHogar.setBounds(12, 142, 495, 30);
					panel_1.add(cbxHogar);
				}
				{
					JLabel label = new JLabel("Servicios de Cable");
					label.setFont(new Font("SansSerif", Font.BOLD, 14));
					label.setBounds(12, 183, 460, 30);
					panel_1.add(label);
				}
				{
					cbxCable = new JComboBox();
					cbxCable.setFont(new Font("SansSerif", Font.BOLD, 14));
					cbxCable.setBounds(12, 226, 495, 30);
					panel_1.add(cbxCable);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("Suma de precios");
					lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
					lblNewLabel_2.setBounds(497, 13, 176, 57);
					panel_1.add(lblNewLabel_2);
				}
				{
					txtPrecio = new JTextField();
					txtPrecio.setText("0.0");
					txtPrecio.setForeground(Color.LIGHT_GRAY);
					txtPrecio.setEditable(false);
					txtPrecio.setBounds(584, 56, 89, 68);
					panel_1.add(txtPrecio);
					txtPrecio.setColumns(10);
				}
			}
		}
		
		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		panel.add(lblNewLabel);
		
		txtNombre = new JTextField();
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo:");
		panel.add(lblNewLabel_1);
		
		txtCod = new JTextField();
		txtCod.setEditable(false);
		panel.add(txtCod);
		txtCod.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCrear = new JButton("crear");
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if(validar()) 
						{
							Plan p = new Plan(txtNombre.getText(), txtCod.getText(), Float.parseFloat(txtPrecio.getText()), serviciosSeleccionados());
							Orange.getInstancia().regPlan(p);
						}
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
		
		llenarServicios();
	}

	protected ArrayList<Servicio> serviciosSeleccionados()
	{
		ArrayList<Servicio> ss = new ArrayList<>();
		if(cbxTel.getSelectedIndex() > 0) 
		{
			ss.add( (Telefono) (cbxTel.getSelectedItem()));
		}
		
		if(cbxHogar.getSelectedIndex() > 0) 
		{
			ss.add( (InternetHogar) (cbxHogar.getSelectedItem()));
		}
		
		if(cbxCable.getSelectedIndex() > 0) 
		{
			ss.add( (Cable) (cbxCable.getSelectedItem()));
		}
		
		return ss;
	}

	protected boolean validar()
	{
		
		return true;
	}

	private void llenarServicios()
	{
		cbxTel.addItem("<<SELECCIONE>>");
		cbxHogar.addItem("<<SELECCIONE>>");
		cbxCable.addItem("<<SELECCIONE>>");
		
		for(Servicio s: Orange.getInstancia().getServicios()) 
		{
			if(s instanceof Telefono) 
			{
				cbxTel.addItem((Telefono) s);
			}
			else if(s instanceof InternetHogar) 
			{
				cbxHogar.addItem((InternetHogar) s);
			}
			else 
			{
				cbxCable.addItem((Cable) s);
			}
		}
		
	}
}
