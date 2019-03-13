/**
 *
 *  @author Tomaszewska Justyna S15313
 *
 */

package changelistener;

import java.beans.PropertyVetoException;

public class Main {
  public static void main(String[] args) {

    Purchase purch = new Purchase("komputer", "nie ma promocji", 3000.00);
    System.out.println(purch);

    PurchaseChangeListener purchaseChangeListener = new PurchaseChangeListener();
    purch.addPropertyChangeListener(purchaseChangeListener);
    purch.addVetoableChangeListener(purchaseChangeListener);
    
    try {
      purch.setData("w promocji");
      purch.setPrice(2000.00);
      System.out.println(purch);

      purch.setPrice(500.00);

    } catch (PropertyVetoException exc) {
      System.out.println(exc.getMessage());
    }
    System.out.println(purch);

  }
}