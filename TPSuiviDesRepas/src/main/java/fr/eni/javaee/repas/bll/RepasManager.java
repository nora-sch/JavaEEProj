package fr.eni.javaee.repas.bll;

import fr.eni.javaee.repas.dal.RepasDAO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.repas.bo.Aliment;
import fr.eni.javaee.repas.bo.Repas;
import fr.eni.javaee.repas.dal.DAOFactory;

public class RepasManager {

	private RepasDAO repasDAO;

	/**
	 * Le consructeur permet d'initialiser la variable memebre avisDAO pour
	 * permettre une communication avec la bdd
	 */
	public RepasManager() {
		this.repasDAO = DAOFactory.getRepasDAO();
	}

	/**
	 * @param date
	 * @param heure
	 * @param List<Aliment> listeAliments
	 * @return un objet Repas en cas de succès
	 */
	public Repas ajouter(LocalDate date, LocalTime heure, List<String> aliments){
		Repas repas = new Repas();
		repas.setDate(date);
		repas.setHeure(heure);
		for(String aliment:aliments)
		{
			repas.getListeAliments().add(new Aliment(aliment.trim()));
		}
		this.repasDAO.insert(repas);
		return repas;
	}
	public List<Repas> selectAll() {
		return this.repasDAO.selectAll();
	}
}
