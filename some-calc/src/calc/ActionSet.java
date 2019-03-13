package calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ActionSet {

	public BigDecimal dodaj(BigDecimal a, BigDecimal b){
		return a.add(b);
	}

	public BigDecimal odejmij(BigDecimal a, BigDecimal b){
		return a.subtract(b);
	}
	
	public BigDecimal pomnoz(BigDecimal a, BigDecimal b){
		return a.multiply(b);
	}
	
	public BigDecimal podziel(BigDecimal a, BigDecimal b){
		return a.divide(b, 7, RoundingMode.HALF_UP);
	}
}
