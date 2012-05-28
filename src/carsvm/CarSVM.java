/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carsvm;

/**
 *
 * @author proteus
 */
public class CarSVM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	
	Model m = new Model();
	DataBilder data = new DataBilder();
	
	data.bildDataFromFile("data/car.data.txt", 0.5);
	
	m.setProblem(data.getProblem());
	m.setTestSVM(data.getTestSVM());
	
	m.buildModel();
//	try {
//	    m.loadModel("171on172.libsvm");
//	} catch (IOException ex) {
//	    Logger.getLogger(CarSVM.class.getName()).log(Level.SEVERE, null, ex);
//	}

	
	
	Car[] c = new Car[100];
	for (int a = 0; a <100 ; ++a) {
	    c[a] = new Car();
	}
	c[0].print();
	m.predictCar(c[0]);
	c[0].print();
	c[0].line();
	
	System.out.print("all cars\n");
	Car[] all = c[0].AllCars();
	for (int a = 0; a <all.length ; ++a) {
	    m.predictCar(all[a]);
	}
	System.out.print("all = "+all.length+"\n");
	
	
	System.out.print("avg car\n");
	
	Car avg = all[0].AvgCar(all);
	
	avg.print();
	
	Pearson p = new Pearson();
	
	p.correlation(all);


    }
}
