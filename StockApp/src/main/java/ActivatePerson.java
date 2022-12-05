import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ActivatePerson
 */
@WebServlet("/ActivatePerson")
public class ActivatePerson extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Person aPerson;
	
	NumberFormat nf = NumberFormat.getCurrencyInstance();
	DecimalFormat df = new DecimalFormat();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivatePerson() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private String htmlFilter(String message) {
        if (message == null) return null;
        int len = message.length();
        StringBuffer result = new StringBuffer(len + 20);
        char aChar;
   
        for (int i = 0; i < len; ++i) {
           aChar = message.charAt(i);
           switch (aChar) {
               case '<': result.append("&lt;"); break;
               case '>': result.append("&gt;"); break;
               case '&': result.append("&amp;"); break;
               case '"': result.append("&quot;"); break;
               default: result.append(aChar);
           }
        }
        return (result.toString());
     }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean isEmpty = false;
        String s = "";
        
		response.setContentType("text/html");		
		HttpSession session = request.getSession();
		
		PrintWriter out = response.getWriter();
        
		String firstN=request.getParameter("firstName");
        String lastN=request.getParameter("lastName");  
        String balanceN=request.getParameter("balance");
        
        if (firstN == null || (firstN = htmlFilter(firstN.trim())).length() == 0) 
        {
             s ="First Name: MISSING";             
        }
        
        if (lastN == null || (lastN = htmlFilter(lastN.trim())).length() == 0) 
        {
             s =s + " " + "Last Name: MISSING";             
        }
        
        if (balanceN == null || (balanceN = htmlFilter(balanceN.trim())).length() == 0 || balanceN.indexOf("-") != -1) 
        {
             s =s + " "+ "Balance: MISSING";
             isEmpty = true;
        }
        
        out.println("<HTML>");
	    out.println("<HEAD>");
	    out.println("<link rel=\"stylesheet\" href=\"mainpage.css\">");
	  /*  out.println("<style>");
	    out.println(".myDiv");
	    out.println("{\r\n"
	    		+ "	      border: 5px outset red;\r\n"
	    		+ "	      background-color: lightblue;\r\n"
	    		+ "	      text-align: center;\r\n"
	    		+ "	    }\r\n"
	    		+ "	    ");
	    		*/
	    out.println("</style>");
	    out.println("<meta charset=\"ISO-8859-1\">");
	    out.println("<title>Activate Person</title>");
	  
	    out.println("</head>");
	    out.println("<body text=\"white\">");
	    
	    out.println("<div class=\"myDiv\" id=\"sdiv\">");	 
	    out.println("<a href=\"mainpage.html\" ><button class=\"back\"><span>Back</span></button></a>");
	    out.println("</body>");
	    out.println("</html>");
	    
        if (!isEmpty)
        {
        	double dBalance = Double.parseDouble(balanceN);
        	aPerson = new Person(firstN, lastN, dBalance);
        	df.applyPattern("#.###");
        	s ="User "+ aPerson.firstName + " " + aPerson.lastName + " with " + nf.format(aPerson.balance) + " has been activated successfully.";
        	session.setAttribute("balance", dBalance);
        }
        
        out.println("<h2 id=\"line1\">"+s+"</h2>");
        out.close();
        
        // set response content type
        response.setContentType("text/html");	}
}