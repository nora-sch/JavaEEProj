package fr.eni.javaee.repas.bll;
/**
 * 
 * @author n-sch
 * Les codes disponibles sont entre 20000 et 29999
 *
 */
public abstract class CodesResultatBLL {
	/**
	 * Echec quand la date et/ou heure ne respecte pas les régles définies dans validation (RepasManager)
	 */
public static final int REGLE_REPAS_DATE_ERREUR = 20000;
/**
 * Echec quand la liste des aliments ne respecte pas les régles définies dans validation (RepasManager)
 */
public static final int REGLE_REPAS_ALIMENTS_ERREUR = 20001;

}
