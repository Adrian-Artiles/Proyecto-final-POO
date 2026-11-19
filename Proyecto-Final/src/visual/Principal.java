package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Principal extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal frame = new Principal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Principal() {
        setTitle("orange - sistema de control");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 960, 620);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(25, 25, 25));
        menuBar.setBorder(BorderFactory.createEmptyBorder(4, 6, 4, 6));
        setJMenuBar(menuBar);

        JMenu mnPersonal = new JMenu("personal");
        mnPersonal.setForeground(Color.WHITE);
        mnPersonal.setFont(new Font("SansSerif", Font.PLAIN, 13));
        menuBar.add(mnPersonal);

        JMenuItem mntmRegPersonal = new JMenuItem("registrar personal");
        mntmRegPersonal.setFont(new Font("SansSerif", Font.PLAIN, 12));
        mnPersonal.add(mntmRegPersonal);

        JMenuItem mntmListPersonal = new JMenuItem("listado de personal");
        mntmListPersonal.setFont(new Font("SansSerif", Font.PLAIN, 12));
        mnPersonal.add(mntmListPersonal);

        JMenu mnClientes = new JMenu("clientes");
        mnClientes.setForeground(Color.WHITE);
        mnClientes.setFont(new Font("SansSerif", Font.PLAIN, 13));
        menuBar.add(mnClientes);

        JMenuItem mntmRegCliente = new JMenuItem("registrar cliente");
        mntmRegCliente.setFont(new Font("SansSerif", Font.PLAIN, 12));
        mnClientes.add(mntmRegCliente);

        JMenuItem mntmListClientes = new JMenuItem("consultar clientes");
        mntmListClientes.setFont(new Font("SansSerif", Font.PLAIN, 12));
        mnClientes.add(mntmListClientes);

        JMenu mnServicios = new JMenu("planes y servicios");
        mnServicios.setForeground(Color.WHITE);
        mnServicios.setFont(new Font("SansSerif", Font.PLAIN, 13));
        menuBar.add(mnServicios);

        JMenuItem mntmRegServicio = new JMenuItem("nuevo servicio");
        mntmRegServicio.setFont(new Font("SansSerif", Font.PLAIN, 12));
        mnServicios.add(mntmRegServicio);

        JMenuItem mntmRegPlan = new JMenuItem("crear plan");
        mntmRegPlan.setFont(new Font("SansSerif", Font.PLAIN, 12));
        mnServicios.add(mntmRegPlan);

        JMenu mnFacturacion = new JMenu("facturacion");
        mnFacturacion.setForeground(Color.WHITE);
        mnFacturacion.setFont(new Font("SansSerif", Font.PLAIN, 13));
        menuBar.add(mnFacturacion);

        JMenuItem mntmNuevaFactura = new JMenuItem("emitir factura");
        mntmNuevaFactura.setFont(new Font("SansSerif", Font.PLAIN, 12));
        mnFacturacion.add(mntmNuevaFactura);

        JMenu mnReportes = new JMenu("reportes");
        mnReportes.setForeground(Color.WHITE);
        mnReportes.setFont(new Font("SansSerif", Font.PLAIN, 13));
        menuBar.add(mnReportes);

        JMenuItem mntmRepPeriodo = new JMenuItem("reporte por periodo");
        mntmRepPeriodo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        mnReportes.add(mntmRepPeriodo);

        JMenuItem mntmRepLocal = new JMenuItem("reporte por local");
        mntmRepLocal.setFont(new Font("SansSerif", Font.PLAIN, 12));
        mnReportes.add(mntmRepLocal);

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

        JLabel lblTitulo = new JLabel("orange");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 32));
        panelHeader.add(lblTitulo, BorderLayout.WEST);

        JLabel lblSubtitulo = new JLabel("panel de gestion y facturacion");
        lblSubtitulo.setForeground(new Color(255, 235, 215));
        lblSubtitulo.setFont(new Font("SansSerif", Font.PLAIN, 15));
        lblSubtitulo.setHorizontalAlignment(SwingConstants.RIGHT);
        panelHeader.add(lblSubtitulo, BorderLayout.EAST);

        JPanel panelCentro = new JPanel();
        panelCentro.setBackground(new Color(245, 246, 248));
        panelCentro.setBorder(new EmptyBorder(35, 45, 35, 45));
        panelCentro.setLayout(new GridLayout(2, 3, 22, 22));
        contentPane.add(panelCentro, BorderLayout.CENTER);

        JButton btnPersonal = crearBotonMenu("personal", "trabajador, comercial y admin", new Color(255, 102, 0));
        JButton btnClientes = crearBotonMenu("clientes", "registro y control de cuentas", new Color(35, 35, 35));
        JButton btnPlanes = crearBotonMenu("planes y servicios", "catalogo de ofertas y lineas", new Color(35, 35, 35));
        JButton btnFacturar = crearBotonMenu("facturacion", "cobros y emision de facturas", new Color(255, 102, 0));
        JButton btnConsultas = crearBotonMenu("consultas", "busqueda rapida y estados", new Color(35, 35, 35));
        JButton btnReportes = crearBotonMenu("reportes", "estadisticas por periodo y local", new Color(35, 35, 35));

        panelCentro.add(btnPersonal);
        panelCentro.add(btnClientes);
        panelCentro.add(btnPlanes);
        panelCentro.add(btnFacturar);
        panelCentro.add(btnConsultas);
        panelCentro.add(btnReportes);

        JPanel panelFooter = new JPanel();
        panelFooter.setBackground(new Color(235, 237, 240));
        panelFooter.setPreferredSize(new Dimension(960, 34));
        panelFooter.setBorder(new EmptyBorder(6, 20, 6, 20));
        panelFooter.setLayout(new BorderLayout());
        contentPane.add(panelFooter, BorderLayout.SOUTH);

        JLabel lblEstado = new JLabel("estado: sistema activo");
        lblEstado.setForeground(new Color(90, 90, 90));
        lblEstado.setFont(new Font("SansSerif", Font.PLAIN, 12));
        panelFooter.add(lblEstado, BorderLayout.WEST);

        JLabel lblVersion = new JLabel("proyecto 7 - orange");
        lblVersion.setForeground(new Color(90, 90, 90));
        lblVersion.setFont(new Font("SansSerif", Font.PLAIN, 12));
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

        btnFacturar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mntmNuevaFactura.doClick();
            }
        });

        btnConsultas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mntmListClientes.doClick();
            }
        });

        btnReportes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mntmRepPeriodo.doClick();
            }
        });
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