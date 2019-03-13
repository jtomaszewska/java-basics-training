package changelistener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class PurchaseChangeListener implements VetoableChangeListener, PropertyChangeListener{

	@Override
	public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
		Double newPrive = (Double) evt.getNewValue();
		double price = newPrive.intValue();
		if( newPrive < 1000)
			throw new PropertyVetoException("Price change to: "+evt.getNewValue()+" not allowed", evt);
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("Change value of: "+evt.getPropertyName() +" from: "+evt.getOldValue()+" to: "+evt.getNewValue());
		
	}

}
//Purchase [prod=komputer, data=nie ma promocji, price=3000.0]
//Change value of: data from: nie ma promocji to: w promocji
//Change value of: price from: 3000.0 to: 2000.0
//Purchase [prod=komputer, data=w promocji, price=2000.0]
//Price change to: 500.0 not allowed
//Purchase [prod=komputer, data=w promocji, price=2000.0]