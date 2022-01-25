package fr.eni.javaee.repas.bo;

public class Aliment {
private int idAliment;
private String nom;

public Aliment() {
}
public Aliment(String nom) {
	super();
	this.nom = nom;
}
public Aliment(int idAliment, String nom) {
	this.idAliment = idAliment;
	this.nom = nom;
}
public int getIdAliment() {
	return idAliment;
}
// ????
public void setIdAliment(int idAliment) {
	this.idAliment = idAliment;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
@Override
public String toString() {
	return idAliment + " - " + nom;
}



}
