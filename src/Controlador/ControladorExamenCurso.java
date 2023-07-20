package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Modelo.Jugador;

public class ControladorExamenCurso {
	
	//public void guardarEnBaseDatos(String ciudad, int codigoPostal) {
		
public void guardarEnBaseDatos(int ID, String nombre, int dorsal, double altura) {		
				//Sabemos si podemos acceder a la clase para la conexión con la base de datos
		
				try {
					Class.forName("org.sqlite.JDBC");				
				
				//url: protocolo:tipo de base de datos: direccion de nuestra base de datos
				String url="jdbc:sqlite:G:\\Curso Java Accion Laboral\\BD\\examen.txt";
				
				//Creamos objeto conexion con el se realiza la conexion y nos trae el objeto conexion
				Connection conexion=DriverManager.getConnection(url);
				
				//Comprobamos si la conexión ha ido bien
				if (conexion!=null) {
					System.out.println("Hay conexión\n");			
				}else {
					System.out.println("No hay conexión\n");
				}												
					
				//Ejecutamos INSERT INTO
				var pt=conexion.prepareStatement("INSERT INTO JUGADORES values(?,?,?,?)");
				pt.setInt(1, ID);
				pt.setString(2, nombre);
				pt.setInt(3,dorsal);
				pt.setDouble(4, altura);
				
				pt.executeUpdate();//Devuelve numero de sentencias afectadas	
				
				//creamos el objeto Jugador
				Jugador jugador=new Jugador(ID,nombre,dorsal,altura);
				
				JOptionPane.showMessageDialog(null, jugador.toString());
								
				//Cerramos la conexión
				if (conexion!=null) {
					conexion.close();
				}
				else {
					System.out.println("No hay conexión");
				}
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"Error capturado: "+e);
				
			}
	}//guardarEnBaseDatos


public boolean existeIDEnBaseDeDatos(int ID) {
	
	try {
		Class.forName("org.sqlite.JDBC");				
	
	//url: protocolo:tipo de base de datos: direccion de nuestra base de datos
	String url="jdbc:sqlite:G:\\Curso Java Accion Laboral\\BD\\examen.txt";
	
	//Se realiza la conexion y nos trae el objeto conexion
	Connection conexion=DriverManager.getConnection(url);
	
	//Comprobamos si la conexión ha ido bien
	if (conexion!=null) {
		System.out.println("Hay conexión\n");			
	}else {
		System.out.println("No hay conexión");
	}
											
	/*Ejecutamos la consulta SELECT*/
	  
	var pt = conexion.prepareStatement("SELECT * from JUGADORES WHERE ID=?");
	pt.setInt(1,ID);
	//Me devuelve todas las lineas de la select
	ResultSet rs= pt.executeQuery();			
	//Cerramos la conexión
	if (conexion!=null) {
		conexion.close();
	}
	return rs.next();
		
} catch (Exception e) {
	JOptionPane.showMessageDialog(null,"Error capturado: "+e);					
}
	return false;

}


public int calculaId() {	
	int idMax=0;
	try {
		Class.forName("org.sqlite.JDBC");				
	
	//url: protocolo:tipo de base de datos: direccion de nuestra base de datos
	String url="jdbc:sqlite:G:\\Curso Java Accion Laboral\\BD\\examen.txt";
	
	//Se realiza la conexion y nos trae el objeto conexion
	Connection conexion=DriverManager.getConnection(url);
	
	//Comprobamos si la conexión ha ido bien
	if (conexion!=null) {
		System.out.println("Hay conexión\n");			
	}else {
		System.out.println("No hay conexión");
	}
											
	/*Ejecutamos la consulta SELECT*/
	  
	var pt = conexion.prepareStatement("SELECT MAX(ID) from JUGADORES");
	//Me devuelve todas las lineas de la select
	ResultSet rs= pt.executeQuery();			

	
	//Se ejecuta mientras devuelva true
	while(rs.next()) {
		idMax=rs.getInt(1);
	}
	//Cerramos la conexión
	if (conexion!=null) {
		conexion.close();
	}

	
} catch (Exception e) {
	JOptionPane.showMessageDialog(null,"Error capturado: "+e);					
}
	return idMax;
	
	
}//Calcula ID

public void modificarIDBaseDeDatos(int idCambio,int idCalculado) {
	
	try {
		Class.forName("org.sqlite.JDBC");				
	
	//url: protocolo:tipo de base de datos: direccion de nuestra base de datos
	String url="jdbc:sqlite:G:\\Curso Java Accion Laboral\\BD\\examen.txt";
	
	//Se realiza la conexion y nos trae el objeto conexion
	Connection conexion=DriverManager.getConnection(url);
	
	//Comprobamos si la conexión ha ido bien
	if (conexion!=null) {
		System.out.println("Hay conexión\n");			
	}else {
		System.out.println("No hay conexión");
	}
											
  	//Ejecutamos UPDATE

	var pt=conexion.prepareStatement("UPDATE JUGADORES set ID=? WHERE ID=?");
	pt.setInt(2, idCalculado);
	pt.setInt(1, idCambio);
	pt.executeUpdate();		
					
	
	//Cerramos la conexión
	if (conexion!=null) {
		conexion.close();
	}
	else {
		System.out.println("No hay conexión");
	}
	
} catch (Exception e) {
	JOptionPane.showMessageDialog(null,"Error capturado: "+e);
	
}	
	
	
}

public void modificarRegistroDuplicado(int id, String nombre, int dorsal, double altura) {
	JOptionPane.showMessageDialog(null, id+nombre+dorsal+altura);
	try {
		Class.forName("org.sqlite.JDBC");				
	
	//url: protocolo:tipo de base de datos: direccion de nuestra base de datos
	String url="jdbc:sqlite:G:\\Curso Java Accion Laboral\\BD\\examen.txt";
	
	//Se realiza la conexion y nos trae el objeto conexion
	Connection conexion=DriverManager.getConnection(url);
	
	//Comprobamos si la conexión ha ido bien
	if (conexion!=null) {
		System.out.println("Hay conexión\n");			
	}else {
		System.out.println("No hay conexión");
	}
											
	/*Ejecutamos la consulta SELECT*/
	  
	//Ejecutamos UPDATE

	var pt=conexion.prepareStatement("UPDATE JUGADORES set NOMBRE=?,DORSAL=?,ALTURA=? WHERE ID=?");
	pt.setInt(4, id);/*where*/	;
	pt.setDouble(3, altura);
	pt.setInt(2, dorsal);
	pt.setString(1, nombre);
	pt.executeUpdate();			
					
	//Cerramos la conexión
	if (conexion!=null) {
		conexion.close();
	}
	else {
		System.out.println("No hay conexión");
	}
	
} catch (Exception e) {
	JOptionPane.showMessageDialog(null,"Error capturado: "+e);
	
}	
	
}

//Listar Base de datos

public String ListaJugadores() {
	
	try {
		Class.forName("org.sqlite.JDBC");				
	
	//url: protocolo:tipo de base de datos: direccion de nuestra base de datos
	String url="jdbc:sqlite:G:\\Curso Java Accion Laboral\\BD\\examen.txt";
	
	//Se realiza la conexion y nos trae el objeto conexion
	Connection conexion=DriverManager.getConnection(url);
	
	//Comprobamos si la conexión ha ido bien
	if (conexion!=null) {
		System.out.println("Hay conexión\n");			
	}else {
		System.out.println("No hay conexión");
	}
											
	/*Ejecutamos la consulta SELECT*/
	  
	var pt = conexion.prepareStatement("SELECT * from JUGADORES");
		
	//Me devuelve todas las lineas de la select
	ResultSet rs= pt.executeQuery();
	
	//Creamos variable mensaje
	String mensaje="";
	
	//Se ejecuta mientras devuelva true
	mensaje+="LISTADO DE JUGADORES";
	mensaje+='\n';	
	mensaje+="Nº  "+'\t'+"Nombre    "+"\t"+"Dorsal     "+'\t'+"Altura    ";
	while(rs.next()) {	
		mensaje+='\n';
		mensaje+=rs.getInt(1) + "    "+rs.getString(2)+"         "+rs.getInt(3)+"           "+rs.getDouble(4);											
		mensaje+='\n';						
	}						
			
	//Cerramos la conexión
	if (conexion!=null) {
		conexion.close();
	}

	return mensaje;
	
	
} catch (Exception e) {
	JOptionPane.showMessageDialog(null,"Error capturado: "+e);					
}
	return null;

}

//Eliminar Jugador
public void EliminarJugador(int id) {
	
	try {
		Class.forName("org.sqlite.JDBC");				
	
	//url: protocolo:tipo de base de datos: direccion de nuestra base de datos
	String url="jdbc:sqlite:G:\\Curso Java Accion Laboral\\BD\\examen.txt";
	
	//Se realiza la conexion y nos trae el objeto conexion
	Connection conexion=DriverManager.getConnection(url);
	
	//Comprobamos si la conexión ha ido bien
	if (conexion!=null) {
		System.out.println("Hay conexión\n");			
	}else {
		System.out.println("No hay conexión");
	}
											
	/*Ejecutamos la consulta SELECT*/
	  
	var pt = conexion.prepareStatement("DELETE FROM JUGADORES WHERE ID=?");
	pt.setInt(1,id);
	pt.executeUpdate();
			
	//Cerramos la conexión
	if (conexion!=null) {
		conexion.close();
	}
						
	
} catch (Exception e) {
	JOptionPane.showMessageDialog(null,"Error capturado: "+e);					
}
	

}
	
	
}	


		

