package fr.eni.javaee.repas.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.repas.bo.Aliment;
import fr.eni.javaee.repas.bo.Repas;

public class RepasDAOJdbcImpl implements RepasDAO{
	private static final String INSERT_REPAS = "INSERT INTO REPAS(date_repas, heure_repas) VALUES(?,?);";
	private static final String INSERT_ALIMENTS = "INSERT INTO ALIMENTS(nom, id_repas) VALUES(?,?);";
	private static final String SELECT_ALL = "SELECT r.id as id_repas, r.date_repas, r.heure_repas, a.id as id_aliment, a.nom FROM REPAS r INNER JOIN ALIMENTS a ON r.id=a.id_repas ORDER BY r.date_repas DESC, r.heure_repas DESC";
	private static final String SELECT_BY_DATE = "SELECT r.id as id_repas, r.date_repas, r.heure_repas, a.id as id_aliment, a.nom FROM REPAS r INNER JOIN ALIMENTS a ON r.id=a.id_repas WHERE r.date_repas = ? ORDER BY r.date_repas DESC, r.heure_repas DESC";
	public void insert(Repas repas) {
		Connection cnx = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			cnx = ConnectionProvider.getConnection();
			cnx.setAutoCommit(false);
			pstmt = cnx.prepareStatement(INSERT_REPAS, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setDate(1, java.sql.Date.valueOf(repas.getDate()));
			pstmt.setTime(2, java.sql.Time.valueOf(repas.getHeure()));
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				repas.setIdRepas(rs.getInt(1));
			}
			rs.close();
			pstmt.close();

			pstmt = cnx.prepareStatement(INSERT_ALIMENTS, PreparedStatement.RETURN_GENERATED_KEYS);
			for(Aliment aliment : repas.getListeAliments()) {
				pstmt.setString(1, aliment.getNom());
				pstmt.setInt(2, repas.getIdRepas());
				pstmt.executeUpdate();
				rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					aliment.setIdAliment(rs.getInt(1));
				}
			}
			rs.close();
			pstmt.close();
			cnx.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			try {
				cnx.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				cnx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
	}

	@Override
	public List<Repas> selectAll() {
		List<Repas> listeRepas  = new ArrayList<Repas>();
		Connection cnx = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_ALL);
			rs = pstmt.executeQuery();
			Repas repas = new Repas();
			while(rs.next()) {
				if(rs.getInt("id_repas")!=repas.getIdRepas()) {
					repas = new Repas();
					repas.setIdRepas(rs.getInt("id_repas")); 
					repas.setDate(rs.getDate("date_repas").toLocalDate());
					repas.setHeure(rs.getTime("heure_repas").toLocalTime());
					listeRepas.add(repas);
				}
				Aliment aliment = new Aliment();
				aliment.setIdAliment(rs.getInt("id_aliment"));
				aliment.setNom(rs.getString("nom"));
				repas.getListeAliments().add(aliment);
			}
			rs.close();
			pstmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				cnx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listeRepas;
	}
	
	@Override
	public List<Repas> selectByDate(LocalDate dateRecherchee) {
		List<Repas> listeRepas  = new ArrayList<Repas>();
		Connection cnx = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_DATE);
			pstmt.setDate(1, java.sql.Date.valueOf(dateRecherchee));
			rs = pstmt.executeQuery();
			Repas repas = new Repas();
			while(rs.next()) {
				if(rs.getInt("id_repas")!=repas.getIdRepas()) {
					repas = new Repas();
					repas.setIdRepas(rs.getInt("id_repas")); 
					repas.setDate(rs.getDate("date_repas").toLocalDate());
					repas.setHeure(rs.getTime("heure_repas").toLocalTime());
					listeRepas.add(repas);
				}
				Aliment aliment = new Aliment();
				aliment.setIdAliment(rs.getInt("id_aliment"));
				aliment.setNom(rs.getString("nom"));
				repas.getListeAliments().add(aliment);
			}
			rs.close();
			pstmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				cnx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listeRepas;
	}
}
