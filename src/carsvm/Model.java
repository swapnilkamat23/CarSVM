/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carsvm;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import libsvm.*;

/**
 *
 * @author proteus
 */
public class Model {

    private svm_parameter param;
    private svm_problem problem;
    private svm_problem testSVM;
    private svm_model model;

    //<editor-fold defaultstate="collapsed" desc="geters&seters">
    public svm_model getModel() {
	return model;
    }

    public void setProblem(svm_problem problem) {
	this.problem = problem;
    }

    public void setTestSVM(svm_problem testSVM) {
	this.testSVM = testSVM;
    }

    public svm_parameter getParam() {
	return param;
    }

    public void setParam(svm_parameter param) {
	this.param = param;
    }
    //</editor-fold>

    public void buildModel() {
	long unixTime = System.currentTimeMillis() / 1000L;
	this.buildAndSaveModel("lastModel(" + unixTime + ").libsvm");
    }

    public void buildAndSaveModel(String saveTo) {

	// <editor-fold defaultstate="collapsed" desc="parametry svm">
	if (this.param == null) {
	    param = new svm_parameter();

	    param.svm_type = svm_parameter.C_SVC;
	    param.kernel_type = svm_parameter.RBF;
	    param.degree = 6;			//* for poly */
	    param.gamma = 1.0 / 6.0;			//* for poly/rbf/sigmoid */
	    param.coef0 = 1;			//* for poly/sigmoid */




	    //* these are for training only */
	    param.cache_size = 80;			//* in MB */
	    param.C = 10;				//* for C_SVC, EPSILON_SVR, and NU_SVR */
	    param.eps = 1e-3;				//* stopping criteria */
	    param.p = 0.1;				//* for EPSILON_SVR */
	    param.shrinking = 1;			//* use the shrinking heuristics */
	    param.probability = 0;			//* do probability estimates */
	    param.nr_weight = 0;			//* for C_SVC */
	    param.weight_label = new int[0];		//* for C_SVC */
	    param.weight = new double[0];		//* for C_SVC */
	    param.nu = 0.5;				//* for NU_SVC, ONE_CLASS, and NU_SVR */
	}



	// </editor-fold>

	String check = svm.svm_check_parameter(problem, param) == null ? "ok" : "nie ok";
	System.out.print("param test " + check + "\n");

	System.out.print("dane sa w problemie\n");

	System.out.print("buduje model\n");
	model = svm.svm_train(problem, param);
	try {
	    svm.svm_save_model(saveTo, model);
	} catch (IOException ex) {
	    Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
	    System.out.print(ex.getMessage() + "\n");
	    ex.printStackTrace();
	}
	System.out.print("mamy model\n");

	this.testModel();

    }

    private void testModel() {
	if (testSVM == null) {
	    throw new NullPointerException("nie mam testSVM");
	}

	int[] ok = new int[5];
	int[] nieOK = new int[5];

	for (int a = 0; a < 5; ++a) {
	    ok[a] = 0;
	    nieOK[a] = 0;
	}

	for (int a = 0; a < testSVM.x.length; ++a) {
	    double cls = svm.svm_predict(model, testSVM.x[a]);
	    int clsI = (int) cls;
	    if (cls == testSVM.y[a]) {
		++ok[clsI];
		System.out.print(cls + "==" + testSVM.y[a] + "\n");

	    } else {
		++nieOK[clsI];
		System.out.print("\t" + cls + "!=" + testSVM.y[a] + "\n");
	    }
	}

	System.out.print("\tcls:\tok\tnieOK\n");
	for (int a = 1; a < 5; ++a) {
	    System.out.print("\t" + a + ":\t" + ok[a] + "\t" + nieOK[a] + "\n");
	}
//	throw new UnsupportedOperationException("Not yet implemented Model.testModel()");
	
    }

    public void loadModel(String load) throws IOException {
	model = svm.svm_load_model(load);
    }

    public void predictCar(Car c) {
	c.setCls(svm.svm_predict(model, c.bildDataToPredict()));
    }
}
