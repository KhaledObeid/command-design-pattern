<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="ISO-8859-1">
        <link rel="stylesheet" href="mainpage.css">
        <title>Sell Stock</title>
        <style>
            .myDiv {
                border: 5px outset red;
                background-color: lightblue;
                text-align: center;
            }
        </style>
    </head>

    <body text="white">
        <header>
            <nav>
                <ul>
                    <li><a href="mainpage.html">Home</a></li>
                    <li><a href="SellStock.jsp">Sell Stock</a></li>
                    <li><a href="BuyStock.jsp">Buy Stock</a></li>
                    <li><a href="contact.html">Contact Us</a></li>
                </ul>
            </nav>
        </header>
        <form action="SellStock" method="post">
            <label for="stocks">Choose a Stock</label><br>
            <select name="stocks" id="stocks" class="custom-select">
  <option value="AAPL">Apple Inc</option>
  <option value="XOM">Exxon Mobil Corp</option>
  <option value="ORCL">Oracle Corp</option>
  <option value="KO">Coca-Cola Co</option>
  <option value="INTC">Intel Corp</option>
  <option value="WMT">Walmart Inc</option>
  <option value="MSFT">Microsoft Corp</option>
  <option value="IBM">International Business Machines Corp</option>
  <option value="PEP">PepsiCo Inc</option>
  <option value="PFE">Pfizer Inc</option>
  <option value="META">Meta Platforms, Inc.</option>
  <option value="AMZN">Amazon.com Inc</option>
  <option value="BKNG">Booking Holdings Inc</option>
  <option value="SBUX">Starbucks Corp</option>        
</select><br/><br/> Stock Quantity<br><input type="number" name="stockQuantity" /><br/><br/>
            <input type="submit" value="Sell Stock" />
        </form>
    </body>

    </html>