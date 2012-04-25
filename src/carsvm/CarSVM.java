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
	
	Pearson p = new Pearson();
	
	p.correlation(c);


    }
}
