package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logico.Comercial;
import logico.Empleado;
import logico.Orange;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class Principal extends JFrame {

	private static final long serialVersionUID = -2338171001859749383L;
	private JPanel contentPane;
	private Empleado emp;
	private JMenu mnPersonal;
	private JMenu mnServicios;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal(Empleado aux) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Orange.getInstancia().guardarArchivo();
			}
		});
		emp = aux;

		setTitle("Orange - Sistema de Control");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 620);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(25, 25, 25));
		menuBar.setBorder(BorderFactory.createEmptyBorder(4, 6, 4, 6));
		setJMenuBar(menuBar);

		mnPersonal = new JMenu("personal");
		mnPersonal.setForeground(Color.WHITE);
		mnPersonal.setFont(new Font("SansSerif", Font.PLAIN, 13));
		menuBar.add(mnPersonal);

		JMenuItem mntmRegPersonal = new JMenuItem("registrar personal");
		mntmRegPersonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegUser ru = new RegUser(null);
				ru.setModal(true);

				ru.setVisible(true);
			}
		});
		mntmRegPersonal.setFont(new Font("SansSerif", Font.PLAIN, 12));
		mnPersonal.add(mntmRegPersonal);

		JMenuItem mntmListPersonal = new JMenuItem("listado de personal");
		mntmListPersonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegUser ru = new RegUser(null);
				ru.setModal(true);
				ru.setVisible(true);
			}
		});
		mntmListPersonal.setFont(new Font("SansSerif", Font.PLAIN, 12));
		mnPersonal.add(mntmListPersonal);

		JMenu mnClientes = new JMenu("clientes");
		mnClientes.setForeground(Color.WHITE);
		mnClientes.setFont(new Font("SansSerif", Font.PLAIN, 13));
		menuBar.add(mnClientes);

		JMenuItem mntmRegCliente = new JMenuItem("registrar cliente");
		mntmRegCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegCliente rcl = new RegCliente();
				rcl.setModal(true);
				rcl.setVisible(true);
			}
		});
		mntmRegCliente.setFont(new Font("SansSerif", Font.PLAIN, 12));
		mnClientes.add(mntmRegCliente);

		JMenuItem mntmListClientes = new JMenuItem("consultar clientes");
		mntmListClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegCliente rcl = new RegCliente();
				rcl.setModal(true);
				rcl.setVisible(true);
			}
		});
		mntmListClientes.setFont(new Font("SansSerif", Font.PLAIN, 12));
		mnClientes.add(mntmListClientes);

		mnServicios = new JMenu("planes y servicios");
		mnServicios.setForeground(Color.WHITE);
		mnServicios.setFont(new Font("SansSerif", Font.PLAIN, 13));
		menuBar.add(mnServicios);

		JMenuItem mntmRegServicio = new JMenuItem("nuevo servicio");
		mntmRegServicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegServicio rs = new RegServicio();
				rs.setModal(true);
				rs.setVisible(true);
			}
		});
		mntmRegServicio.setFont(new Font("SansSerif", Font.PLAIN, 12));
		mnServicios.add(mntmRegServicio);

		JMenuItem mntmListServicios = new JMenuItem("listado de servicios");
		mntmListServicios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegServicio rs = new RegServicio();
				rs.setModal(true);
				rs.setVisible(true);
			}
		});
		mntmListServicios.setFont(new Font("SansSerif", Font.PLAIN, 12));
		mnServicios.add(mntmListServicios);

		JMenuItem mntmRegPlan = new JMenuItem("crear plan");
		mntmRegPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!Orange.getInstancia().getServicios().isEmpty()) 
				{
					RegPlan rp = new RegPlan();
					rp.setModal(true);
					rp.setVisible(true);
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "Debe de crear algun servicio primero", "Falta Los servicios", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		mntmRegPlan.setFont(new Font("SansSerif", Font.PLAIN, 12));
		mnServicios.add(mntmRegPlan);

		JMenuItem mntmListPlanes = new JMenuItem("listado de planes");
		mntmListPlanes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegPlan rp = new RegPlan();
				rp.setModal(true);
				rp.setVisible(true);
			}
		});
		mntmListPlanes.setFont(new Font("SansSerif", Font.PLAIN, 12));
		mnServicios.add(mntmListPlanes);

		JMenu mnVentas = new JMenu("ventas");
		mnVentas.setForeground(Color.WHITE);
		mnVentas.setFont(new Font("SansSerif", Font.PLAIN, 13));
		menuBar.add(mnVentas);

		JMenuItem mntmNuevaVenta = new JMenuItem("realizar venta");
		mntmNuevaVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegVenta rg = new RegVenta(emp);
				rg.setModal(true);
				rg.setVisible(true);
			}
		});
		mntmNuevaVenta.setFont(new Font("SansSerif", Font.PLAIN, 12));
		mnVentas.add(mntmNuevaVenta);

		JMenu mnReportes = new JMenu("reportes");
		mnReportes.setForeground(Color.WHITE);
		mnReportes.setFont(new Font("SansSerif", Font.PLAIN, 13));
		menuBar.add(mnReportes);

		JMenuItem mntmGraficas = new JMenuItem("graficas y estadisticas");
		mntmGraficas.setFont(new Font("SansSerif", Font.PLAIN, 12));
		mnReportes.add(mntmGraficas);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 246, 248));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(new Color(255, 102, 0));
		panelHeader.setPreferredSize(new Dimension(960, 110));
		panelHeader.setLayout(new BorderLayout());
		panelHeader.setBorder(new EmptyBorder(18, 28, 18, 28));
		contentPane.add(panelHeader, BorderLayout.NORTH);

		JLabel lblTitulo = new JLabel("Orange");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 32));
		panelHeader.add(lblTitulo, BorderLayout.WEST);

		JLabel lblSubtitulo = new JLabel("panel de gestion y ventas");
		lblSubtitulo.setForeground(new Color(255, 235, 215));
		lblSubtitulo.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblSubtitulo.setHorizontalAlignment(SwingConstants.RIGHT);
		panelHeader.add(lblSubtitulo, BorderLayout.EAST);

		JPanel panelCentro = new JPanel();
		panelCentro.setBackground(new Color(245, 246, 248));
		panelCentro.setBorder(new EmptyBorder(35, 45, 35, 45));
		panelCentro.setLayout(new GridLayout(2, 3, 22, 22));
		contentPane.add(panelCentro, BorderLayout.CENTER);

		JButton btnPersonal = crearBotonMenu("personal", "Comercial y Administrador", new Color(255, 102, 0));
		JButton btnClientes = crearBotonMenu("clientes", "registro y control de cuentas", new Color(35, 35, 35));
		JButton btnPlanes = crearBotonMenu("planes y servicios", "catalogo de ofertas y lineas", new Color(35, 35, 35));
		JButton btnVentas = crearBotonMenu("realizar venta", "contratos y emision de facturas", new Color(255, 102, 0));
		JButton btnConsultas = crearBotonMenu("consultas", "busqueda rapida y estados", new Color(35, 35, 35));
		JButton btnReportes = crearBotonMenu("reportes", "graficas y estadisticas", new Color(35, 35, 35));

		panelCentro.add(btnPersonal);
		panelCentro.add(btnClientes);
		panelCentro.add(btnPlanes);
		panelCentro.add(btnVentas);
		panelCentro.add(btnConsultas);
		panelCentro.add(btnReportes);

		JPanel panelFooter = new JPanel();
		panelFooter.setBackground(new Color(235, 237, 240));
		panelFooter.setPreferredSize(new Dimension(960, 34));
		panelFooter.setBorder(new EmptyBorder(6, 20, 6, 20));
		panelFooter.setLayout(new BorderLayout());
		contentPane.add(panelFooter, BorderLayout.SOUTH);

		JLabel lblEstado = new JLabel("Usuario: -");
		if (emp != null) {
			lblEstado.setText("Usuario: " + emp.getUserName());
		}
		lblEstado.setForeground(new Color(90, 90, 90));
		lblEstado.setFont(new Font("SansSerif", Font.PLAIN, 12));
		panelFooter.add(lblEstado, BorderLayout.WEST);

		JLabel lblVersion = new JLabel("CERRAR SESION");
		lblVersion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblVersion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Login l = new Login();
				l.setVisible(true);
				dispose();
			}
		});
		lblVersion.setForeground(new Color(90, 90, 90));
		lblVersion.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblVersion.setHorizontalAlignment(SwingConstants.RIGHT);
		panelFooter.add(lblVersion, BorderLayout.EAST);

		btnPersonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmRegPersonal.doClick();
			}
		});

		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmRegCliente.doClick();
			}
		});

		btnPlanes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmRegPlan.doClick();
			}
		});

		btnVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmNuevaVenta.doClick();
			}
		});

		btnConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmListClientes.doClick();
			}
		});

		btnReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmGraficas.doClick();
			}
		});

		if (emp instanceof Comercial) {
			mnPersonal.setEnabled(false);
			mnServicios.setEnabled(false);
			btnPersonal.setEnabled(false);
			btnPlanes.setEnabled(false);
		}
	}

	private JButton crearBotonMenu(String titulo, String descripcion, Color colorAcento) {
		JButton btn = new JButton("<html><div style='text-align:left;'><span style='font-size:15px;font-weight:bold;'>" + titulo + "</span><br><span style='font-size:11px;color:#666666;'>" + descripcion + "</span></div></html>");
		btn.setBackground(Color.WHITE);
		btn.setForeground(new Color(30, 30, 30));
		btn.setFocusPainted(false);
		btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 5, 0, 0, colorAcento),
				BorderFactory.createCompoundBorder(
						BorderFactory.createLineBorder(new Color(220, 223, 228), 1),
						new EmptyBorder(14, 18, 14, 18)
						)
				));
		return btn;
	}
}