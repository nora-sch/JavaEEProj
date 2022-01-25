package fr.eni.javaee.repas.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.repas.bll.RepasManager;
import fr.eni.javaee.repas.bo.Repas;

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
		
		RepasManager repasManager = new RepasManager();
		List<Repas> listeRepas =  repasManager.selectAll();
		request.setAttribute("listeRepas", listeRepas);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/afficherRepas.jsp");
		rd.forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
