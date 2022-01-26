package fr.eni.javaee.repas.dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 * @author n-sch
 *
 */

public abstract class CodesResultatDAL {
	/*
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_OBJECT_NULL = 10000;
	/*
	 * Echec général quand erreur non gérée à l'insertion
	 */
	public static final int INSERT_OBJET_ECHEC = 10001;
	/*
	 * Echec de l'affichage de repas 
	 */
	public static final int LECTURE_REPAS_ECHEC = 10002;
}
