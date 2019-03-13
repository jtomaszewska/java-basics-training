/**
 *
 *  @author Tomaszewska Justyna S15313
 *
 */

package genericmethods;


import java.util.*;

public class Main {

  static List<String> getPricesInPLN(List<String> destinations, double xrate) {
    return ListCreator.collectFrom(destinations)
                       .when( 
                    		   fly -> fly.startsWith("WAW")
                        )
                       .mapEvery( val-> {	String priceEuro = val.split(" ")[2];
                    	   					Integer priceEuroInt = Integer.parseInt(priceEuro);
                    	   					int pricePLN = (int) ((int)priceEuroInt*xrate);
                    	   					String place = val.split(" ")[1];
                    	   					return "to "+ place + " - price in PLN: "+pricePLN; }
                        );
  }

  public static void main(String[] args) {
    // Lista destynacji: port_wylotu port_przylotu cena_EUR 
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = getPricesInPLN(dest, ratePLNvsEUR);
    for (String r : result) System.out.println(r);
  }
}
