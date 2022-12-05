import java.util.ArrayList;
import java.util.List;

public class Broker 
{
   String s = "";	 
   
   private List<Order> orderList = new ArrayList<Order>(); 

   public void takeOrder(Order order){
      orderList.add(order);		
   }

   public String placeOrders()
   {
   
      for (Order order : orderList) 
      {
         s = s + order.execute();
      }
      orderList.clear();
      
      return s;
   }
}