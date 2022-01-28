package fr.eni.javaee.repas.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.repas.bll.RepasManager;
import fr.eni.javaee.repas.bo.Repas;
import fr.eni.javaee.repas.BusinessException;

/**
 * Servlet implementation class ServletAfficherRepas
 */
@WebServlet("/repas")
public class ServletAfficherRepas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAfficherRepas() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Integer> listeErreurs = new ArrayList<Integer>();
		LocalDate date = null;

		try {
			RepasManager repasManager = new RepasManager();
			List<Repas> listeRepas =  repasManager.selectAll();
			request.setAttribute("listeRepas", listeRepas);
		}catch(BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
		}


		if(request.getParameter("date")!=null && !request.getParameter("date").equals("")) {
			request.setCharacterEncoding("UTF-8");

			try {
				DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				date = LocalDate.parse(request.getParameter("date"),formatDate);
			}catch(DateTimeParseException e) {
				e.printStackTrace();
				listeErreurs.add(CodesResultatServlets.FORMAT_REPAS_DATE_ERREUR);
			}


			if(listeErreurs.size()>0) {
				request.setAttribute("listeCodesErreur", listeErreurs);
			}else {
				try {
					RepasManager repasManager = new RepasManager();
					List<Repas> listeRepasParDate = repasManager.selectByDate(date);
					if(listeRepasParDate!=null && listeRepasParDate.size()>0) {
						if (request.getParameter("iddate")!=null){
							System.out.println("Date : " + request.getParameter("iddate"));
							//request.setAttribute("iddate",request.getParameter("iddate"));
									}
						request.setAttribute("repasParDate", "true");
						request.setAttribute("listeRepasParDate", listeRepasParDate);}
					else {
						request.setAttribute("repasParDate", "false");
					}
				}catch(BusinessException e) {
					e.printStackTrace();
					request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				}

			}
		}
		System.out.println(listeErreurs);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/afficherRepas.jsp");
		rd.forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

