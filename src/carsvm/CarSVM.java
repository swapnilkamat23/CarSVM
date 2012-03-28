/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carsvm;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	
	Car c = new Car();
	c.print();
	m.predictCar(c);
	c.print();
	c.line();



    }
}
