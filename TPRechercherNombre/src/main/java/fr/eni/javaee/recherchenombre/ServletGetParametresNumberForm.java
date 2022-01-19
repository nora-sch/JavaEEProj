package fr.eni.javaee.recherchenombre;

import fr.eni.javaee.recherchenombre.Functions;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ServletGetParametresNumberForm
 */
@WebServlet(
		urlPatterns = "/ServletGetParametresNumberForm",
		initParams = {
				@WebInitParam(description="value min", name="NB_MIN",value="0"),
				@WebInitParam(description="value max", name="NB_MAX",value="10")
		}
		)
public class ServletGetParametresNumberForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int randomNb;
	private int min = 5;
	private int max = 15;
	private boolean win;

	@Override
	public void init() throws ServletException {
		//
		if(this.getInitParameter("NB_MIN")!=null && !this.getInitParameter("NB_MIN").equals("") && Functions.isNumeric(this.getInitParameter("NB_MIN"))) {
			System.out.println("min:"+Functions.isNumeric(this.getInitParameter("NB_MIN")));
		}
		if(this.getInitParameter("NB_MAX")!=null && !this.getInitParameter("NB_MAX").equals("") && Functions.isNumeric(this.getInitParameter("NB_MAX"))) {
			System.out.println("max:"+Functions.isNumeric(this.getInitParameter("NB_MAX")));
		}

		//On recherche entre 0 inclus et la différence max-min+1 (car valeur max exclue)
		this.randomNb=new Random().nextInt(this.max-this.min+1)+this.min;
		this.win=false;
		System.out.println("Init Random : " + this.randomNb);
		System.out.println("Min :" + this.min);
		System.out.println("Max :" + this.max);
	}

	/*
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nombre = request.getParameter("nombre");
		if(nombre.equals(String.valueOf(this.randomNb)) && win==false) {
			response.sendRedirect("redirection/succes.html");
			win = true;
		}else {
			response.sendRedirect("redirection/echec.html");
		}
	}

}
