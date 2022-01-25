package fr.eni.javaee.repas.dal;

import java.util.List;

import fr.eni.javaee.repas.bo.Repas;

public interface RepasDAO {

	public void insert(Repas repas);

	List<Repas> selectAll();
	
}
