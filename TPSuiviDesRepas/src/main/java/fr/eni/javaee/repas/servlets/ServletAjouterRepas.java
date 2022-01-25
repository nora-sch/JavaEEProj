package fr.eni.javaee.repas.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		LocalDate date;
		LocalTime heure;
		List<String> aliments;
		try {
			DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			date = LocalDate.parse(request.getParameter("date"),formatDate);
			DateTimeFormatter formatHeure = DateTimeFormatter.ofPattern("HH:mm");
			heure = LocalTime.parse(request.getParameter("heure"),formatHeure);
			aliments = Arrays.asList((request.getParameter("aliments")).split(","));

			RepasManager repasManager = new RepasManager();
			Repas repas = repasManager.ajouter(date, heure, aliments);
			//			request.setAttribute("repas", repas);
		}catch(Exception e) {
		e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ajoutRepas.jsp");
		rd.forward(request, response);
	}
}


