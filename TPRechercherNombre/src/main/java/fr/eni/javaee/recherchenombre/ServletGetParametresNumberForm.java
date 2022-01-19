package fr.eni.javaee.recherchenombre;

import java.io.IOException;
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
				@WebInitParam(description="des paramètres d'initialisation",
						name="NOM_PARAMETRE",
						value="VALEUR_PARAMETRE")
		}
		)
public class ServletGetParametresNumberForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int randomNb;
	private int min = 0;
	private int max = 10;
	private boolean win;
	
	private String valeurParametre;

	@Override
    public void init() throws ServletException {
//		this.valeurParametre = this.getInitParameter("NOM_PARAMETRE");
//		super.init();
		  	//On recherche entre 0 inclus et la différence max-min+1 (car valeur max exclue)
    	this.randomNb=new Random().nextInt(this.max-this.min+1)+this.min;
    	this.win=false;
    	System.out.println("Init Random : " + this.randomNb);
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
		
		//System.out.println(this.valeurParametre);
  
		String nombre = request.getParameter("nombre");
		if(nombre.equals(String.valueOf(this.randomNb)) && win==false) {
			response.sendRedirect("redirection/succes.html");
			
			win = true;
		}else {
			response.sendRedirect("redirection/echec.html");
		}
	}

}
