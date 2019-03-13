/**
 *
 *  @author Tomaszewska Justyna S15313
 *
 */

package changelistener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;

/*
 * wypisywanie na konsoli wszystkich zmian dat i cen,
 * kontrolę poprawności zmian: nie można zmienić ceny 
 * na liczbę mniejszą od 1000.
 */
public class Purchase implements Serializable {
	// dla wl związanej
	private PropertyChangeSupport chg = new PropertyChangeSupport(this);

	// dla wl ograniczonej
	private VetoableChangeSupport veto = new VetoableChangeSupport(this);

	// wlasciwosci klasy
	private String prod;// wlasciwosc prosta
	private String data;// bounded - O zmianie związanej właściwości
	// ziarna mogą być zawiadamiane inne komponenty i reagować
	// na tę zmianę.
	private Double price;// wl bounded i constrained
	// Ograniczana właściwość – to taka, o której zmianie powiadamiane
	// są zainteresowane inne komponenty i są pytane o zgodę
	// na tę zmianę. Jeśli którykolwiek z komponentów nie da
	// takiej zgody (zawetuje zmianę) – zmiana nie dochodzi do skutku.

	public Purchase() {

	}

	public Purchase(String prod, String data, Double price) {
		this.prod = prod;
		this.data = data;
		this.price = price;
	}

	/**
	 * @return the prod
	 */
	public String getProd() {
		return prod;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param prod
	 *            the prod to set
	 */
	public synchronized void setProd(String prod) {
		this.prod = prod;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public synchronized void setData(String newData) {
		String oldData = data;
		data = newData;
		chg.firePropertyChange("data", oldData, newData);
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public synchronized void setPrice(Double newPrice) throws PropertyVetoException {
		Double oldPrice = price;
		veto.fireVetoableChange("price", oldPrice, newPrice);
		chg.firePropertyChange("price", oldPrice, newPrice);
		price = newPrice;
	}

	// metody dodawania i usuwania słuchaczy
	public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
		chg.addPropertyChangeListener(l);
	}

	public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
		chg.removePropertyChangeListener(l);
	}
	
	// metody dodawania i usuwania słuchaczy
	public synchronized void addVetoableChangeListener(VetoableChangeListener l) {
		veto.addVetoableChangeListener(l);
	}

	public synchronized void removeVetoableChangeListener(VetoableChangeListener l) {
		veto.removeVetoableChangeListener(l);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Purchase [prod=" + prod + ", data=" + data + ", price=" + price + "]";
	}


}
