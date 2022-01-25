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
		
		List<Aliment> listeAliments = new ArrayList<Aliment>();
		for(String aliment : aliments) {
			listeAliments.add(new Aliment(aliment));
		}
		
		Repas repas = new Repas(date, heure, listeAliments);
		this.repasDAO.insert(repas);
		return repas;
	}
}
