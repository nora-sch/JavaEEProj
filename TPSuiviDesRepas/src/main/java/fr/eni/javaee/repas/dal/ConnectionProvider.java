package fr.eni.javaee.repas.dal;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

abstract class ConnectionProvider {

	private static DataSource dataSource;

	/**
	 * Au chargement de la classe, la DataSource est recherch�e dans l'arbre JNDI (une seule fois)
	 */
	static {
		Context context;
		try {
			context = new InitialContext();
			ConnectionProvider.dataSource = (DataSource)context.lookup("java:comp/env/jdbc/pool_cnx");
		}catch(NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("Impossible d'acc�der � la base de donn�es!");
		}
	}
	/**
	 * Cette m�thode retourne une connection op�rationnelle issu du pool de connexions vers la bdd
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return ConnectionProvider.dataSource.getConnection();
	}
	
}
