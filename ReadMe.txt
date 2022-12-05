Make sure to follow these steps before you run the application.

You need to have tomcat downloaded and installed.
We are using Tomcat 9.0 so refer to https://tomcat.apache.org/download-90.cgi and choose the Windows version from the list below
Binary Distributions
Core:
zip (pgp, sha512)
tar.gz (pgp, sha512)
32-bit Windows zip (pgp, sha512)
64-bit Windows zip (pgp, sha512)
32-bit/64-bit Windows Service Installer (pgp, sha512)
Make sure it is configured to run under Port 8080

You need to know the API KEY in case you need to call additional API methods:
Ojg3OTZkOWQ0NTgxYzAyOTI0ZGZkY2ZkYzllM2E0YjIy

Download Jars API from
https://docs.intrinio.com/documentation/java

When you unzip the jar, make sure to add the extracted jar files to ..\WebContent\WEB-INF\lib folder of your project
Also make sure to add the path of the extracted jar files to Windows Path 

Click the Advanced system settings link.
Click Environment Variables.
In the Edit System Variable (or New System Variable) window, specify the value of the PATH environment variable.
(This may require you to reboot your machine)

Then right click on any html/jsp file and click Run As > Run On Server
and enjoy the application

You can also open the link directly from any browser, Chrome or Internet Explorer (Just make sure Tomcat on localhost 8080 is up and running)
http://localhost:8080/StockWebApp/mainpage.html

----------------------------------------------------------------------------

The Command pattern is a data driven design pattern and falls under behavioral pattern category:

[A request is wrapped under an object as command and passed to invoker object. Invoker object looks for the appropriate object which can handle this command 
and passes the command to the corresponding object which executes the command.]

We have implemented our project using the Command pattern as follows:

An interface Order which acts as a command.

A Stock class which acts as a request.

Concrete command classes BuyStock and SellStock implementing Order interface which will do actual command processing. 

A class Broker which acts as an invoker object. It can take and place orders.

Broker object uses command pattern to identify which object will execute which command based on the type of command in our case: BuyStock or SellStock

UML Summary:
Order.java is command interface
Stock.java is a request class
BuyStock.java and SellStock.java are concrete classes implementing the Order interface
Broker.java is the command invoker class

We have gone more advanced and made it a Web App.

Intinio API is the Data Marketplace used to offer a wide selection of live financial data feeds. It uses HTTPS verbs and responses are delivered in JSON format.

A trial account for 2 weeks has been created so we can use the API_KEY and access all features such as Real-Time Stock Prices.

We have gathered the top companies, and here is a snap list: 

Refer to: 
docs.intrinio.com/tutorial/web_api 
docs.intrinio.com/documentation/java/search_companies_v2 
docs.intrinio.com/documentation/java/get_security_realtime_price_v2 for more details.

The Web App is structured to have a mainpage.html with customised CSS and navigation menus for Home, Buy, Sell and Contact Us.
Main Servlets: BuyStock and SellStock to capture required fields to perfom Buy or Sell.

We have gone more advanced and introduced a Person Class where we captured First Name, Last Name and Balance.

We have used Session to pass the Balance across all pages and maintained it during the whole lifecylce of our application.

Enjoy our Demo