package Modelo;

import java.util.Objects;

public class Jugador {
	
//Atributos	
	
private int ID;
private  String nombre;
private  int dorsal;
private double altura;
public int getID() {
	return ID;
}


public Jugador() {

}


public Jugador(int iD, String nombre, int dorsal, double altura) {
	super();
	ID = iD;
	this.nombre = nombre;
	this.dorsal = dorsal;
	this.altura = altura;
}






//Mejora el rendimiento de java y devuelve un nº que Java lo reutiliza internamente

@Override
public int hashCode() {
	return Objects.hash(ID, altura, dorsal, nombre);
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Jugador other = (Jugador) obj;
	return ID == other.ID && Double.doubleToLongBits(altura) == Double.doubleToLongBits(other.altura)
			&& dorsal == other.dorsal && Objects.equals(nombre, other.nombre);
}


//Getter & Setter
public void setID(int iD) {
	ID = iD;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public int getDorsal() {
	return dorsal;
}
public void setDorsal(int dorsal) {
	this.dorsal = dorsal;
}
public double getAltura() {
	return altura;
}
public void setAltura(double altura) {
	this.altura = altura;
}


@Override
public String toString() {
	return "Jugador [ID=" + ID + ", nombre=" + nombre + ", dorsal=" + dorsal + ", altura=" + altura + "]";
}

















	
}
