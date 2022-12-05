import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BuyStock
 */
@WebServlet("/BuyStock")
public class BuyStock extends HttpServlet implements Order {
	private static final long serialVersionUID = 1L;
	private Stock aStock;
	    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyStock() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public BuyStock(Stock aStock){
        this.aStock = aStock;
     }

     public String execute() {
        return aStock.buy();
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
		// TODO Auto-generated method stub		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		boolean isEmpty = false;
        String s = "";
                		
        response.setContentType("text/html");		
		PrintWriter out = response.getWriter();
        
		String stockN=request.getParameter("stocks");
        String stockQ=request.getParameter("stockQuantity");  
        
        double dstockQ = 0;
        
        if (stockN == null || (stockN = htmlFilter(stockN.trim())).length() == 0) 
        {
             s = "Stock Name: MISSING";             
        }
        
        if (stockQ == null || (stockQ = htmlFilter(stockQ.trim())).length() == 0 || stockQ.indexOf("-") != -1) 
        {
             s = "Stock Quantity: MISSING";
             isEmpty = true;
        }
        else
        	dstockQ = Double.parseDouble(stockQ);
        
        double dstockP = 0;
        HttpSession session = request.getSession();
        double bc = Double.parseDouble(session.getAttribute("balance").toString());
        Stock aStock = new Stock(stockN, dstockP, dstockQ, bc);
        
        BuyStock buyStockOrder = new BuyStock(aStock);
               
        Broker broker = new Broker();
	    broker.takeOrder(buyStockOrder); //can take list of orders
	    
	    out.println("<HTML>");
	    out.println("<link rel=\"stylesheet\" href=\"mainpage.css\">");
	    out.println("<HEAD>");
	   /* out.println("<style>");
	    out.println(".myDiv");
	    out.println("{\r\n"
	    		+ "	      border: 5px outset red;\r\n"
	    		+ "	      background-color: lightblue;\r\n"
	    		+ "	      text-align: center;\r\n"
	    		+ "	    }\r\n"
	    		+ "	    ");
	    
	    out.println("</style>");*/
	    out.println("<meta charset=\"ISO-8859-1\">");
	    out.println("<title>Buy Stock</title>");
	    out.println("<link rel=\"stylesheet\" href=\"mainpage.css\">");
	    out.println("</head>");
	    out.println("<body text=\"white\">");
	  
	    
	    out.println("<div class=\"myDiv\" id=\"sdiv\"></div>");	
	    out.println("<a href=\"mainpage.html\" ><button class=\"back\"><span>Back</span></button></a>");
	    out.println("</body>");
	    out.println("</html>");
	    
	    try
        {
	    	if (!isEmpty)
	    	{
	     		out.println("<h2 id=\"line1\">"+broker.placeOrders()+"</h2>");	    		
	    	}
	    	else
	    		out.println("<h2 id=\"line1\">"+s+"</h2>");
        }
        catch (Exception e2) 
        {
        	System.out.println(e2);
        }       
        out.close();   
        session.setAttribute("balance", Stock.balance);
	}
}