/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carsvm;

/**
 *
 * @author proteus
 */
public class Pearson {

    public void correlation(Car[] c) {

	Car avg = c[0].AvgCar(c);

	for (int a = 0; a < 6; ++a) {
	    double up = 0;
	    double down1 = 0;
	    double down2 = 0;

	    for (int i = 0; i < c.length; ++i) {
		up += (c[i].DoubleVals()[a] - avg.DoubleVals()[a]) * (c[i].DoubleVals()[6] - avg.DoubleVals()[6]);
		down1 += (c[i].DoubleVals()[a] - avg.DoubleVals()[a]) * (c[i].DoubleVals()[a] - avg.DoubleVals()[a]);
		down2 += (c[i].DoubleVals()[6] - avg.DoubleVals()[6]) * (c[i].DoubleVals()[6] - avg.DoubleVals()[6]);
	    }
//	    CarBuing b = null;
//	    CarMaint m = null;
//	    CarDoors d = null;
//	    CarPersons p = null;
//	    CarLug_boot l = null;
//	    CarSafety s = null;

	    switch (a) {
		case 0:
		    System.out.print("wspulczynnik korelacji dla " + "Buing" + " wynosi: ");
		    break;
		case 1:
		    System.out.print("wspulczynnik korelacji dla " + "Maint" + " wynosi: ");
		    break;
		case 2:
		    System.out.print("wspulczynnik korelacji dla " + "Doors" + " wynosi: ");
		    break;
		case 3:
		    System.out.print("wspulczynnik korelacji dla " + "Persons" + " wynosi: ");
		    break;
		case 4:
		    System.out.print("wspulczynnik korelacji dla " + "Lug_boot" + " wynosi: ");
		    break;
		case 5:
		    System.out.print("wspulczynnik korelacji dla " + "Safety" + " wynosi: ");
		    break;
	    }

	    double r = Math.abs(up / (Math.sqrt(down1)*Math.sqrt(down2)));
	    
	    System.out.print(r+"\n");

	    
	}


    }
}
