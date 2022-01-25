package fr.eni.javaee.repas.bo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Repas {
	private int idRepas;
	private LocalDate date;
	private LocalTime heure;
	private List<Aliment> listeAliments = new ArrayList<Aliment>();

	public Repas() {
	}
	public Repas(LocalDate date, LocalTime heure, List<Aliment> listeAliments) {
		super();
		this.date = date;
		this.heure = heure;
		this.listeAliments = listeAliments;
	}
	public int getIdRepas() {
		return idRepas;
	}
	public void setIdRepas(int idRepas) {
		this.idRepas = idRepas;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getHeure() {
		return heure;
	}
	public void setHeure(LocalTime heure) {
		this.heure = heure;
	}
	public List<Aliment> getListeAliments() {
		return listeAliments;
	}
	public void setListeAliments(List<Aliment> listeAliments) {
		this.listeAliments = listeAliments;
	}

	public String toString() {
		return "Repas du "+date+" à "+heure+" : "+listeAliments;
	}
}
