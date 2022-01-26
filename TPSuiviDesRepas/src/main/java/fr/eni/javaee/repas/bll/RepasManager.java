package fr.eni.javaee.repas.bll;

import fr.eni.javaee.repas.dal.RepasDAO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.repas.BusinessException;
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
	 * @throws BusinessException 
	 */
	public Repas ajouter(LocalDate date, LocalTime heure, List<String> aliments) throws BusinessException{
		Repas repas = new Repas();
		repas.setDate(date);
		repas.setHeure(heure);
		for(String aliment:aliments)
			//TODO à changer sans if mais dans exceptions!
		{if(aliment!=""){
			repas.getListeAliments().add(new Aliment(aliment.trim()));
			}
		}
		this.repasDAO.insert(repas);
		return repas;
	}
	public List<Repas> selectAll() throws BusinessException {
		return this.repasDAO.selectAll();
	}
	public List<Repas> selectByDate(LocalDate date) throws BusinessException {
		return this.repasDAO.selectByDate(date);
	}
}
