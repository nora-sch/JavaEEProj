package fr.eni.javaee.couleurssession;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletCouleurChoix
 */
@WebServlet("/")
public class ServletCouleurChoix extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		String couleurs = this.getServletContext().getInitParameter("COULEURS");
		String[] couleurArray=couleurs.split(",");
		this.getServletContext().setAttribute("listeCouleurs", couleurArray); // sauvegarder la liste dans le context d'application
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		Cookie nbAcces = null;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		if(nbAcces==null) {
			nbAcces = new Cookie("nbAcces", "1");
		}
		if(cookies!=null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("nbAcces")) {
					nbAcces = cookie;
					int count = Integer.parseInt(cookie.getValue())+1;
					nbAcces.setValue(String.valueOf(count));
				}
			}
		}
		
		nbAcces.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(nbAcces);
		request.setAttribute("cookieNbAcces", nbAcces);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/couleurChoix.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("couleurSession", request.getParameter("couleurSelectionne"));
		doGet(request, response);
	}

}
