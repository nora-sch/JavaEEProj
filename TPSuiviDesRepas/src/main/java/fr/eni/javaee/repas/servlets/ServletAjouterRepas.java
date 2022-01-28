package fr.eni.javaee.repas.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.repas.BusinessException;
import fr.eni.javaee.repas.bll.RepasManager;
import fr.eni.javaee.repas.bo.Aliment;
import fr.eni.javaee.repas.bo.Repas;

/**
 * Servlet implementation class ServletAjoutRepas
 */
@WebServlet("/ajoutrepas")
public class ServletAjouterRepas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAjouterRepas() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ajoutRepas.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		LocalDate date = null;
		LocalTime heure = null;
		String aliments = null;
		List<Integer> listeErreurs = new ArrayList<Integer>();
		try {
			DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			date = LocalDate.parse(request.getParameter("date"),formatDate);
		}catch(DateTimeParseException e){
			listeErreurs.add(CodesResultatServlets.FORMAT_REPAS_DATE_ERREUR);
		}
		try {
			DateTimeFormatter formatHeure = DateTimeFormatter.ofPattern("HH:mm");
			heure = LocalTime.parse(request.getParameter("heure"),formatHeure);
		}catch(DateTimeParseException e) {
			listeErreurs.add(CodesResultatServlets.FORMAT_REPAS_HEURE_ERREUR);
		}

		aliments = (String) request.getParameter("aliments");
		if(aliments==null || aliments.trim().isEmpty()){
			listeErreurs.add(CodesResultatServlets.FORMAT_REPAS_ALIMENTS_ERREUR);
		}
		if(aliments!=null) {
			System.out.println(aliments);
			if(!aliments.matches(("^[\\pL\\pM\\p{Zs}, ]+$"))) {
				listeErreurs.add(CodesResultatServlets.FORMAT_REPAS_ALIMENTS_SYNTAXE_ERREUR);
			}
		}
		if(listeErreurs.size()>0) {
			request.setAttribute("listeCodesErreur", listeErreurs);
			System.out.println(listeErreurs);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ajoutRepas.jsp");
			rd.forward(request, response);
		}
		else {
			try {
				RepasManager repasManager = new RepasManager();
				Repas repas = repasManager.ajouter(date, heure, Arrays.asList(aliments.split(",")));
				request.setAttribute("repas", repas);
			}catch(BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				System.out.println(e.getListeCodesErreur());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ajoutRepas.jsp");
				rd.forward(request, response);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ajoutRepas.jsp");
			rd.forward(request, response);
		}
	}
}


