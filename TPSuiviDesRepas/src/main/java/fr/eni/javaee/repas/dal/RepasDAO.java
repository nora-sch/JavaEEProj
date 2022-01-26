package fr.eni.javaee.repas.dal;

import java.time.LocalDate;
import fr.eni.javaee.repas.BusinessException;
import java.util.List;

import fr.eni.javaee.repas.bo.Repas;

public interface RepasDAO {

	public void insert(Repas repas) throws BusinessException;

	List<Repas> selectAll() throws BusinessException;

	List<Repas> selectByDate(LocalDate dateRecherchee) throws BusinessException;
	
}
