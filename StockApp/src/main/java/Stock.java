import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.intrinio.api.SecurityApi;
import com.intrinio.invoker.ApiClient;
import com.intrinio.invoker.Configuration;
import com.intrinio.invoker.auth.ApiKeyAuth;
import com.intrinio.models.RealtimeStockPrice;

public class Stock
{
	NumberFormat nf = NumberFormat.getCurrencyInstance();
	DecimalFormat df = new DecimalFormat();
	
	public String stockName;
	public double stockPrice;
	public double stockQuantity;
	public static double balance;

	public Stock(String sName, double sPrice, double sQuantity, double b)
	{
		this.stockName = sName;
		this.stockPrice = sPrice;
		this.stockQuantity = sQuantity;
		
		Stock.balance = b;
	}

	public String buy()
	{
		String output = new String();
				
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		ApiKeyAuth auth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
        auth.setApiKey("Ojg3OTZkOWQ0NTgxYzAyOTI0ZGZkY2ZkYzllM2E0YjIy");
        defaultClient.setAllowRetries(true);

        SecurityApi securityApi = new SecurityApi();
              
        String source = null;
        
        try
        {
        	RealtimeStockPrice result = securityApi.getSecurityRealtimePrice(stockName, source);
        	stockPrice = result.getLastPrice().doubleValue();
        	
        	Stock.balance -= (stockPrice * stockQuantity);
        	     	
         	if (Stock.balance > 0)        	
         	{
         		df.applyPattern("#.###");
        		output = "You have successfully bought " + stockQuantity + " stocks of  " + stockName +
   				     " for " + stockPrice  + "</br>" + "Your new Balance is " + nf.format(Stock.balance);        		
        	}
        	else
        		output = "You have exceeded your Balance!";
        	
        } catch (Exception e)
        {
        	output = "Request Stock: " + stockName + " " + e.getCause().getMessage();
        }        
        return output;
    }
	
	public String sell()
	{
		String output = new String();
		
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		ApiKeyAuth auth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
        auth.setApiKey("Ojg3OTZkOWQ0NTgxYzAyOTI0ZGZkY2ZkYzllM2E0YjIy");
        defaultClient.setAllowRetries(true);

        SecurityApi securityApi = new SecurityApi();
              
        String source = null;
        
        try
        {
        	RealtimeStockPrice result = securityApi.getSecurityRealtimePrice(stockName, source);
        	stockPrice = result.getLastPrice().doubleValue();
        	
        
        
        Stock.balance += (stockPrice * stockQuantity);
     	
     	if (Stock.balance > 0)        	
     	{
     		df.applyPattern("#.###");
    		output = "You have successfully sold " + stockQuantity + " stocks of " + stockName +
				     " for " + stockPrice  + "</br>" + "Your new Balance is " + nf.format(Stock.balance);        		
    	}
    	else
    		output = "You have exceeded your Balance";
        } catch (Exception e)
        {
        	output = "Request Stock: " + stockName + " " + e.getCause().getMessage();
        }        
        return output;
        
	}
	
	public String theStock()
	{
		return "[" + this.stockName + "," + this.stockPrice + "," + this.stockQuantity + "]";
	}
}