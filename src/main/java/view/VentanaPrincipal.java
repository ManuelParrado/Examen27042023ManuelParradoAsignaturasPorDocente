package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControladorValoracionMateria;
import controller.ControllerAsignatura;
import controller.ControllerAsignaturasPorDocente;
import controller.ControllerDocente;
import model.Asignatura;
import model.Asignaturaspordocente;
import model.Docente;
import model.Estudiante;
import model.Materia;
import model.Profesor;
import model.Valoracionmateria;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField jtfFiltrar;
	private DefaultListModel<Asignatura> listModelSelec = null;
	private DefaultListModel<Asignatura> listModelNoSelec = null;
	private int indiceProximaAsignturaParaAgregar = 0;
	private JList listSelec;
	private JList listNoSelec;
	private JComboBox<Docente> jcbDocente;
	private List<Asignatura> asignaturas = ControllerAsignatura.findAll();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNewLabel = new JLabel("Docentes y Asignaturas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		jtfFiltrar = new JTextField();
		GridBagConstraints gbc_jtfFiltrar = new GridBagConstraints();
		gbc_jtfFiltrar.insets = new Insets(0, 0, 5, 5);
		gbc_jtfFiltrar.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFiltrar.gridx = 0;
		gbc_jtfFiltrar.gridy = 1;
		contentPane.add(jtfFiltrar, gbc_jtfFiltrar);
		jtfFiltrar.setColumns(10);

		JButton btnNewButton = new JButton("Filtrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtraDocente();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		contentPane.add(btnNewButton, gbc_btnNewButton);

		jcbDocente = new JComboBox<Docente>();
		GridBagConstraints gbc_jcbDocente = new GridBagConstraints();
		gbc_jcbDocente.insets = new Insets(0, 0, 5, 5);
		gbc_jcbDocente.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbDocente.gridx = 0;
		gbc_jcbDocente.gridy = 2;
		contentPane.add(jcbDocente, gbc_jcbDocente);

		JButton btnNewButton_1 = new JButton("Cargar Materias");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarAsignaturas1();
//				agregarAsignaturas2();
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 2;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel_1 = new JLabel("Asignaturas no seleccionadas");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 5;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Asignaturas seleccionadas");
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 8;
		gbc_lblNewLabel_1_1.gridy = 0;
		panel.add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);

		listNoSelec = new JList(getDefaultListModelNoSelec());
		scrollPane.setViewportView(listNoSelec);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 5;
		gbc_panel_1.gridy = 1;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JButton bTodosNoSelec = new JButton("<<");
		bTodosNoSelec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pasarTodosANoSeleccionados();
			}
		});
		GridBagConstraints gbc_bTodosNoSelec = new GridBagConstraints();
		gbc_bTodosNoSelec.insets = new Insets(0, 0, 5, 0);
		gbc_bTodosNoSelec.gridx = 0;
		gbc_bTodosNoSelec.gridy = 0;
		panel_1.add(bTodosNoSelec, gbc_bTodosNoSelec);

		JButton bSelec = new JButton(">");
		bSelec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pasarASeleccionados();
			}
		});
		GridBagConstraints gbc_bSelec = new GridBagConstraints();
		gbc_bSelec.insets = new Insets(0, 0, 5, 0);
		gbc_bSelec.gridx = 0;
		gbc_bSelec.gridy = 1;
		panel_1.add(bSelec, gbc_bSelec);

		JButton bNoSelec = new JButton("<");
		bNoSelec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pasarANoSeleccionados();
			}
		});
		GridBagConstraints gbc_bNoSelec = new GridBagConstraints();
		gbc_bNoSelec.insets = new Insets(0, 0, 5, 0);
		gbc_bNoSelec.gridx = 0;
		gbc_bNoSelec.gridy = 2;
		panel_1.add(bNoSelec, gbc_bNoSelec);

		JButton bTodosSelec = new JButton(">>");
		bTodosSelec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pasarTodosASeleccionados();
			}
		});
		GridBagConstraints gbc_bTodosSelec = new GridBagConstraints();
		gbc_bTodosSelec.gridx = 0;
		gbc_bTodosSelec.gridy = 3;
		panel_1.add(bTodosSelec, gbc_bTodosSelec);

		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 4;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 7;
		gbc_scrollPane_1.gridy = 1;
		panel.add(scrollPane_1, gbc_scrollPane_1);

		listSelec = new JList(getDefaultListModelSelec());
		scrollPane_1.setViewportView(listSelec);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 2;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 4;
		contentPane.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		JButton bGuardar = new JButton("Guardar");
		bGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		GridBagConstraints gbc_bGuardar = new GridBagConstraints();
		gbc_bGuardar.gridx = 6;
		gbc_bGuardar.gridy = 0;
		panel_2.add(bGuardar, gbc_bGuardar);

	}

	/**
	 * 
	 * @return
	 */
	private DefaultListModel getDefaultListModelSelec() {
		this.listModelSelec = new DefaultListModel<Asignatura>();
		return this.listModelSelec;
	}

	/**
	 * 
	 * @return
	 */
	private DefaultListModel getDefaultListModelNoSelec() {
		this.listModelNoSelec = new DefaultListModel<Asignatura>();
		return this.listModelNoSelec;
	}

	/**
	 * 
	 */
	private void filtraDocente() {

		this.jcbDocente.removeAllItems();

		List<Docente> lista = ControllerDocente.findByLikeNombreCompleto(jtfFiltrar.getText());

		if (lista != null) {
			for (Docente d : lista) {
				this.jcbDocente.addItem(d);
			}
		}

	}

	/**
	 * 
	 */
	private void agregarAsignaturas1() {

		indiceProximaAsignturaParaAgregar = 0;

		if (asignaturas != null) {
			for (Asignatura a : asignaturas) {
				this.listModelNoSelec.add(indiceProximaAsignturaParaAgregar, a);
				indiceProximaAsignturaParaAgregar++;
			}
		}

	}

	/**
	 * 
	 */
	private void agregarAsignaturas2() {

//		CODIGO PARA AÑADIR A SELECCIONADOS LAS MATERIAS QUE DA EL PROFESOR SELECCIONADO 
//		LO HAGO DE LA OTRA FORMA AL FINAL PORQUE NO SOY CAPAZ DE HACER QUE SALGAN LOS 
//		NO SELECCIONAMOS SIN QUE SE REPITAN
//		

		listModelNoSelec.removeAllElements();
		listModelSelec.removeAllElements();

		Docente d = (Docente) jcbDocente.getSelectedItem();

		List<Asignaturaspordocente> asignaturasPD = ControllerAsignaturasPorDocente.findByIdDocente(d.getId());

		List<Asignatura> asignaturaSelec = new ArrayList<Asignatura>();

		for (Asignaturaspordocente ad : asignaturasPD) {
			asignaturaSelec.add(ad.getAsignatura());
		}

		if (asignaturaSelec != null) {
			for (Asignatura a : asignaturaSelec) {
				this.listModelSelec.add(indiceProximaAsignturaParaAgregar, a);
				indiceProximaAsignturaParaAgregar++;
			}
		}

//		CODIGO PARA AÑADIR A SELECCIONADOS LAS MATERIAS QUE DA EL PROFESOR SELECCIONADO

	}

	private void guardar() {

		Docente d = (Docente) jcbDocente.getSelectedItem();

		List<Asignatura> listaParaGuardar = new ArrayList<Asignatura>();

		for (int i = 0; i < listModelSelec.size(); i++) {
			listaParaGuardar.add((Asignatura) listModelSelec.getElementAt(i));
		}

		for (Asignatura e : listaParaGuardar) {
			Asignaturaspordocente ad = new Asignaturaspordocente();
			ad.setAsignatura(e);
			ad.setDocente(d);
			ControllerAsignaturasPorDocente.insertar(ad);
		}

	}

	/**
	 * 
	 */
	private void pasarASeleccionados() {
		for (int i = this.listNoSelec.getSelectedIndices().length - 1; i >= 0; i--) {

			this.listModelSelec.addElement(this.listModelNoSelec.get(this.listNoSelec.getSelectedIndices()[i]));
			this.listModelNoSelec.removeElementAt(this.listNoSelec.getSelectedIndices()[i]);
		}
	}

	/**
	 * 
	 */
	private void pasarANoSeleccionados() {
		for (int i = this.listSelec.getSelectedIndices().length - 1; i >= 0; i--) {

			this.listModelNoSelec.addElement(this.listModelSelec.get(this.listSelec.getSelectedIndices()[i]));
			this.listModelSelec.removeElementAt(this.listSelec.getSelectedIndices()[i]);
		}
	}

	/**
	 * 
	 */
	private void pasarTodosASeleccionados() {

		listModelNoSelec.removeAllElements();
		listModelSelec.removeAllElements();
		listModelSelec.addAll(asignaturas);
	}

	/**
	 * 
	 */
	private void pasarTodosANoSeleccionados() {

		listModelSelec.removeAllElements();
		listModelNoSelec.removeAllElements();
		listModelNoSelec.addAll(asignaturas);

	}

}
