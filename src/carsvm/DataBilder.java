/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carsvm;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import libsvm.svm_node;
import libsvm.svm_problem;

/**
 *
 * @author proteus
 */
public class DataBilder {

    private svm_problem problem;
    private svm_problem testSVM;

    //<editor-fold defaultstate="collapsed" desc="geters">
    public svm_problem getProblem() {
	return problem;
    }

    public svm_problem getTestSVM() {
	return testSVM;
    }
    //</editor-fold>

    public void bildDataFromFile(String fName, double pOfTestData) {
	try {
	    FileInputStream fstream = new FileInputStream(fName);

	    DataInputStream in = new DataInputStream(fstream);

	    ArrayList<String[]> dataFromFile = new ArrayList<String[]>();
	    while (in.available() != 0) {
		// Print file line to screen
		String line = in.readLine();
		String[] split = line.split(",");
		dataFromFile.add(split);
	    }

	    problem = new svm_problem();
	    problem.l = dataFromFile.size() - (int) (dataFromFile.size() * pOfTestData);
	    problem.y = new double[problem.l];
	    problem.x = new svm_node[problem.l][6];

	    testSVM = new svm_problem();
	    testSVM.l = dataFromFile.size() - problem.l;
	    testSVM.y = new double[testSVM.l];
	    testSVM.x = new svm_node[testSVM.l][6];

	    int p = 0;
	    int t = 0;
	    Random generator = new Random();
	    while (dataFromFile.size() > 0) {
		int a = generator.nextInt(dataFromFile.size());
		String[] line = dataFromFile.remove(a);


		if (generator.nextBoolean()) {
		    if (p < problem.l) {
			this.addToProblemAt(line, p);
			++p;
		    } else {
			this.addToTestAt(line, t);
			++t;
		    }
		} else {
		    if (t < testSVM.l) {
			this.addToTestAt(line, t);
			++t;
		    } else {

			this.addToProblemAt(line, p);
			++p;
		    }
		}


	    }

	    in.close();

	} catch (FileNotFoundException ex) {
	    Logger.getLogger(DataBilder.class.getName()).log(Level.SEVERE, null, ex);
	} catch (IOException ex) {
	    Logger.getLogger(DataBilder.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    //<editor-fold defaultstate="collapsed" desc="valOf…()">
    private double valOfClass(String str) {
	if (str.equalsIgnoreCase("unacc")) {
	    return 1;
	}
	if (str.equalsIgnoreCase("acc")) {
	    return 2;
	}
	if (str.equalsIgnoreCase("good")) {
	    return 3;
	}
	if (str.equalsIgnoreCase("vgood")) {
	    return 4;
	}

	throw new IllegalArgumentException("to nie jest class " + str);
    }

    private double valOfBuying(String str) {
	if (str.equalsIgnoreCase("vhigh")) {
	    return 1;
	}
	if (str.equalsIgnoreCase("high")) {
	    return 2;
	}
	if (str.equalsIgnoreCase("med")) {
	    return 3;
	}
	if (str.equalsIgnoreCase("low")) {
	    return 4;
	}
	throw new IllegalArgumentException("to nie jest buying " + str);

    }

    private double valOfMaint(String str) {
	if (str.equalsIgnoreCase("vhigh")) {
	    return 1;
	}
	if (str.equalsIgnoreCase("high")) {
	    return 2;
	}
	if (str.equalsIgnoreCase("med")) {
	    return 3;
	}
	if (str.equalsIgnoreCase("low")) {
	    return 4;
	}
	throw new IllegalArgumentException("to nie jest maint " + str);

    }

    private double valOfDoors(String str) {
	if (str.equalsIgnoreCase("2")) {
	    return 1;
	}
	if (str.equalsIgnoreCase("3")) {
	    return 2;
	}
	if (str.equalsIgnoreCase("4")) {
	    return 3;
	}
	if (str.equalsIgnoreCase("5more")) {
	    return 4;
	}
	throw new IllegalArgumentException("to nie jest doors " + str);

    }

    private double valOfPersons(String str) {
	if (str.equalsIgnoreCase("2")) {
	    return 1;
	}
	if (str.equalsIgnoreCase("4")) {
	    return 2;
	}
	if (str.equalsIgnoreCase("more")) {
	    return 3;
	}
	throw new IllegalArgumentException("to nie jest prtsons " + str);

    }

    private double valOfLug_boot(String str) {
	if (str.equalsIgnoreCase("small")) {
	    return 1;
	}
	if (str.equalsIgnoreCase("med")) {
	    return 2;
	}
	if (str.equalsIgnoreCase("big")) {
	    return 3;
	}
	throw new IllegalArgumentException("to nie jest lug_boot " + str);

    }

    private double valOfSafety(String str) {
	if (str.equalsIgnoreCase("low")) {
	    return 1;
	}
	if (str.equalsIgnoreCase("med")) {
	    return 2;
	}
	if (str.equalsIgnoreCase("high")) {
	    return 3;
	}
	throw new IllegalArgumentException("to nie jest safety " + str);

    }
    //</editor-fold>

    private svm_node nodeFrom(double val, int index) {
	svm_node r = new svm_node();
	r.index = index + 1;
	r.value = val;
	return r;
    }

    private svm_node[] boulfNodeForXFrom(String[] line) {
	svm_node[] r = new svm_node[6];

	int a = 0;
	r[a] = this.nodeFrom(this.valOfBuying(line[a]), a);
	++a;
	r[a] = this.nodeFrom(this.valOfMaint(line[a]), a);
	++a;
	r[a] = this.nodeFrom(this.valOfDoors(line[a]), a);
	++a;
	r[a] = this.nodeFrom(this.valOfPersons(line[a]), a);
	++a;
	r[a] = this.nodeFrom(this.valOfLug_boot(line[a]), a);
	++a;
	r[a] = this.nodeFrom(this.valOfSafety(line[a]), a);
	++a;

	return r;
    }

    private void addToProblemAt(String[] line, int p) {
	problem.y[p] = this.valOfClass(line[6]);
	problem.x[p] = this.boulfNodeForXFrom(line);
//	throw new UnsupportedOperationException("Not yet implemented");
    }

    private void addToTestAt(String[] line, int t) {
	testSVM.y[t] = this.valOfClass(line[6]);
	testSVM.x[t] = this.boulfNodeForXFrom(line);
//	throw new UnsupportedOperationException("Not yet implemented");
    }
}
