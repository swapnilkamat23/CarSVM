/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carsvm;

/**
 *
 * @author proteus
 */
public class Morris {
    //TODO inna metoda morisa 
    //TODO mapa punktow wyjsciowych -> pdf

    public void correlation(Car[] c, Model m) {

	double[] fr = new double[6];
	
	for (int n = 0; n < 6; ++n) {
	    fr[n] = 0;
	    double p = 1;
	    if (n < 3) {
		p = 4;
	    } else {
		p = 3;
	    }
//	    System.out.print(p+"\n");
	    double Δ = 1 / (p - 1);

	    for (int r = 0; r < c.length; ++r) {
		if (c[r].doubleVals4Morris()[n] < 0 || c[r].doubleVals4Morris()[n] > 1) {
		    System.out.print(c[r].doubleVals4Morris()[n] + "\n");
		}
		double[] dataR = c[r].doubleVals4Morris();
		dataR[n] += Δ;
		if (dataR[n] > 1) {
		    dataR[n] = 1;
		}
		//System.out.print("-\n");
		//c[r].line();
		Car crΔ = c[0].CarFromMorrisData(dataR);
		m.predictCar(crΔ);

		double di = (c[r].DoubleVals()[6] - crΔ.DoubleVals()[6]) / Δ;
		fr[n] += di;

	    }

	fr[n] /= c.length;
	fr[n] = Math.abs(fr[n]);
	switch (n) {
		case 0:
		    System.out.print("wspulczynnik Morrisa dla " + "Buing" + " wynosi: ");
		    break;
		case 1:
		    System.out.print("wspulczynnik Morrisa dla " + "Maint" + " wynosi: ");
		    break;
		case 2:
		    System.out.print("wspulczynnik Morrisa dla " + "Doors" + " wynosi: ");
		    break;
		case 3:
		    System.out.print("wspulczynnik Morrisa dla " + "Persons" + " wynosi: ");
		    break;
		case 4:
		    System.out.print("wspulczynnik Morrisa dla " + "Lug_boot" + " wynosi: ");
		    break;
		case 5:
		    System.out.print("wspulczynnik Morrisa dla " + "Safety" + " wynosi: ");
		    break;
	    }

	System.out.print(fr[n]+"\n");
	}
	
    }
}
