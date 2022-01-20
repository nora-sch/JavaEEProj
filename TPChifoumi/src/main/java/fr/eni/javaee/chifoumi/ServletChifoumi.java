package fr.eni.javaee.chifoumi;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletChifoumi
 */
@WebServlet("/")
public class ServletChifoumi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int PIERRE = 0;
	private static final int CISEAUX = 1;
	private static final int PAPIER = 2;
	private static final String[] actions = {"PIERRE", "CISEAUX", "PAPIER"};
	int choixServeur;
	int choixUtilisateur;
	boolean win = false;
	private String message="";

	public void init() throws ServletException {

		this.choixServeur = new Random().nextInt(actions.length);
		this.win=false;
		System.out.println("Init Random : " + this.choixServeur);

	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("choix")!=null)
		{
			processRequest(request, response);
		}
		else
		{
			//Déléguer la réponse à la JSP jeu.jsp
			request.setAttribute("actions", (String[])actions);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jouer.jsp");
			rd.forward(request, response); 
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//		doGet(request, response);
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		choixUtilisateur = Integer.parseInt(request.getParameter("choix"));
		System.out.println("Vous avez choisi :"+choixUtilisateur);
		// égal
		if(this.choixUtilisateur==this.choixServeur) {
			this.message ="C'est égal - retentez!";
		}
		// papier->pierre
		if(this.choixUtilisateur==PAPIER && this.choixServeur==PIERRE) {
			win = true;
			this.message ="Vous avez gagné! Le papier recouvre la pierre!";
		}
		// pierre->papier
		if(this.choixUtilisateur==PIERRE && this.choixServeur==PAPIER) {
			win = false;
			this.message ="C'est perdu! Le papier recouvre la pierre!";
		}
		// ciseaux -> papier
		if(this.choixUtilisateur==CISEAUX && this.choixServeur==PAPIER) {
			win = true;
			this.message ="Vous avez gagné! Les ciseaux coupent le papier!";
		}
		// papier -> ciseaux
		if(this.choixUtilisateur==PAPIER && this.choixServeur==CISEAUX) {
			win = false;
			this.message ="C'est perdu! Les ciseaux coupent le papier!";
		}
		// pierre -> ciseaux
		if(this.choixUtilisateur==PIERRE && this.choixServeur==CISEAUX) {
			win = true;
			this.message ="Vous avez gagné! La pierre casse les ciseaux!";
		}
		// ciseaux -> pierre
		if(this.choixUtilisateur==CISEAUX && this.choixServeur==PIERRE) {
			win = false;
			this.message = "C'est perdu! La pierre casse les ciseaux!";
		}

		//response.sendRedirect("resultats.html");
		request.setAttribute("choixUtilisateur", actions[choixUtilisateur]);
		request.setAttribute("choixServeur", actions[choixServeur]);
		request.setAttribute("result", this.message);
	
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/resultats.jsp");
		rd.forward(request, response); 



	}
}
