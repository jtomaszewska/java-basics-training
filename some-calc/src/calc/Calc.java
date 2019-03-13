/**
 *
 *  @author Tomaszewska Justyna S15313
 *
 */
/**
 *
 *  @author Tomaszewska Justyna S15313
 *
 */

package calc;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;

public class Calc {

	private HashMap<Character, String> operations = new HashMap<>();
	private DecimalFormat formatter = new DecimalFormat("0.#######");

	public Calc() {
		operations.put('+', "dodaj");
		operations.put('-', "odejmij");
		operations.put('*', "pomnoz");
		operations.put('/', "podziel");
	}

	String doCalc(String arg) {
		
		try {
			
			Class<ActionSet> actionClass = ActionSet.class;			
			ActionSet actionSet = new ActionSet();
			
			String[] skladniki = arg.split("\\s+");
			BigDecimal liczba1 = new BigDecimal(skladniki[0]);
			char operator = skladniki[1].charAt(0);
			BigDecimal liczba2 = new BigDecimal(skladniki[2]);

			String name = operations.get(operator);
			Method currAction = actionClass.getMethod(name, BigDecimal.class, BigDecimal.class);
			Object result = currAction.invoke(actionSet, liczba1, liczba2);
			return formatter.format(result);
		} catch (Throwable ex) {
			return "Invalid command to calc";
		}

	}
}
