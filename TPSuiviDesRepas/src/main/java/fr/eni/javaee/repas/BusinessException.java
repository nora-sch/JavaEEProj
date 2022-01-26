package fr.eni.javaee.repas;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author n-sch
 *
 * Cette classe permet de recenser l'ensemble des erreurs (par leur code) pouvant survenir
 * quel que soit la couche à l'origine pour les sauvegarder dasn une liste et pouvant afficher plus tard au niveau de JSP
 */

public class BusinessException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private List<Integer> listeCodesErreur;
	
	public BusinessException() {
		super();
		this.listeCodesErreur = new ArrayList<Integer>();
	}
	
	/**
	 * @param code Code de l'erreur. Doit avoir un message associé dans un fichier messages_erreur.properties
	 */
	public void ajouterErreur(int code) {
		if(!this.listeCodesErreur.contains(code)) {
			this.listeCodesErreur.add(code);
		}
	}
	
	public boolean hasErreurs() {
		return this.listeCodesErreur.size()>0;
	}
	public List<Integer> getListeCodesErreur(){
		return this.listeCodesErreur;
	}
	
}
