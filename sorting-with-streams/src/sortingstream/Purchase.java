/**
 *
 *  @author Tomaszewska Justyna S15313
 *
 */

package sortingstream;

public class Purchase {

	String idKlienta;
	String nazwiskoImie;
	String nazwaTowaru;
	double cena;
	double zakupionaIlosc;

	public Purchase(String[] line) {
		this.idKlienta = line[0];
		this.nazwiskoImie = line[1];
		this.nazwaTowaru = line[2];
		this.cena = Double.parseDouble(line[3]);
		this.zakupionaIlosc = Double.parseDouble(line[4]);
	}

	/**
	 * @return the idKlienta
	 */
	public String getIdKlienta() {
		return idKlienta;
	}

	public double getKoszty() {
		return cena*zakupionaIlosc;
	}
	
	/**
	 * @return the nazwiskoImie
	 */
	public String getNazwiskoImie() {
		return nazwiskoImie;
	}

	/**
	 * @return the nazwaTowaru
	 */
	public String getNazwaTowaru() {
		return nazwaTowaru;
	}

	/**
	 * @return the cena
	 */
	public double getCena() {
		return cena;
	}

	/**
	 * @return the zakupionaIlosc
	 */
	public double getZakupionaIlosc() {
		return zakupionaIlosc;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return idKlienta + ";" + nazwiskoImie + ";" + nazwaTowaru
				+ ";" + cena + ";" + zakupionaIlosc;
	}

	
	
}
