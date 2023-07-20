package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.ControladorExamenCurso;
import Modelo.Jugador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;

public class VistaExamenCurso extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Nombre;
	private JTextField textField_Id;
	private JTextField textField_Dorsal;
	private JTextField textField_Altura;
	private ControladorExamenCurso controlador= new ControladorExamenCurso();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaExamenCurso frame = new VistaExamenCurso();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}//main

	/**
	 * Create the frame.
	 */
	public VistaExamenCurso() {
		setTitle("CRUD Jugadores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 220);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(new Color(255, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_Nombre = new JTextField();
		textField_Nombre.setBounds(82, 64, 136, 21);
		contentPane.add(textField_Nombre);
		textField_Nombre.setColumns(10);
		
		JLabel lbl_ID = new JLabel("ID");
		lbl_ID.setForeground(Color.WHITE);
		lbl_ID.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_ID.setBounds(16, 41, 36, 13);
		contentPane.add(lbl_ID);
		
		JLabel lblNewLabel_Nombre = new JLabel("Nombre");
		lblNewLabel_Nombre.setForeground(Color.WHITE);
		lblNewLabel_Nombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_Nombre.setBounds(82, 41, 68, 13);
		contentPane.add(lblNewLabel_Nombre);
		
		textField_Id = new JTextField();
		textField_Id.setColumns(10);
		textField_Id.setBounds(10, 64, 54, 21);
		contentPane.add(textField_Id);
		
		JLabel lblNewLabel_Dorsal = new JLabel("Dorsal");
		lblNewLabel_Dorsal.setForeground(Color.WHITE);
		lblNewLabel_Dorsal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_Dorsal.setBounds(229, 41, 68, 13);
		contentPane.add(lblNewLabel_Dorsal);
		
		textField_Dorsal = new JTextField();
		textField_Dorsal.setColumns(10);
		textField_Dorsal.setBounds(228, 64, 54, 21);
		contentPane.add(textField_Dorsal);
		
		JLabel lblNewLabel_Altura = new JLabel("Altura");
		lblNewLabel_Altura.setForeground(Color.WHITE);
		lblNewLabel_Altura.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_Altura.setBounds(307, 41, 68, 13);
		contentPane.add(lblNewLabel_Altura);
		
		textField_Altura = new JTextField();
		textField_Altura.setColumns(10);
		textField_Altura.setBounds(297, 64, 78, 21);
		contentPane.add(textField_Altura);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.PINK);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (controlador.existeIDEnBaseDeDatos(Integer.parseInt(textField_Id.getText()))) {				
					controlador.EliminarJugador(Integer.parseInt(textField_Id.getText()));
					JOptionPane.showMessageDialog(null,"id "+ textField_Id.getText()+" eliminado");
					textField_Id.setText(" ");
					
				
				}
				else {					
					JOptionPane.showMessageDialog(null,"No podemos eliminar el id "+ textField_Id.getText()+" porque no existe");
				}
			
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEliminar.setBounds(150, 95, 85, 21);
		contentPane.add(btnEliminar);
		
		//Añadir registros en tabla Jugadores
		JButton btnAnadir = new JButton("Añadir");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Si no existe en base de datos ese ID					
				if (!controlador.existeIDEnBaseDeDatos(Integer.parseInt(textField_Id.getText()))){
					//Llamo al metodo para que me diga el id más alto
					int idCalculado=controlador.calculaId();
					JOptionPane.showMessageDialog(null, idCalculado);
					//Grabamos el registro del jugador tal cual en la base de datos	porque no existe en la base de datos
					JOptionPane.showMessageDialog(null, "No existe en la base de datos");				
					controlador.guardarEnBaseDatos(Integer.parseInt(textField_Id.getText()),							
					textField_Nombre.getText(),
					Integer.parseInt(textField_Dorsal.getText()),
					Double.parseDouble(textField_Altura.getText()));	
					int idCambio=Integer.parseInt(textField_Id.getText());
					//Llamamos al modificar id para que lo busque y le ponga el IdCalculado al IdCambio
					controlador.modificarIDBaseDeDatos(idCalculado+1,idCambio);								
			
						}			
				else {			
					//Existe el ID en la base de datos 
					  JOptionPane.showMessageDialog(null, "Existe en la base de datos");
					  controlador.modificarRegistroDuplicado(Integer.parseInt(textField_Id.getText()),						
					  textField_Nombre.getText(),
					  Integer.parseInt(textField_Dorsal.getText()),
					  Double.parseDouble(textField_Altura.getText()));					  										
				
			}				
				
						
			}
			
		});
		
		btnAnadir.setForeground(Color.PINK);
		btnAnadir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAnadir.setBounds(38, 95, 85, 21);
		contentPane.add(btnAnadir);
		
		JButton btnListar = new JButton("Listar");
		btnListar.setForeground(Color.PINK);
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, controlador.ListaJugadores());		
			
			}
		});
	
		btnListar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnListar.setBounds(262, 95, 85, 21);
		contentPane.add(btnListar);
	
	}
}

	
