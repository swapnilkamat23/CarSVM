/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carsvm;

import java.util.*;
import libsvm.svm_node;

/**
 *
 * @author proteus
 */
class Car {
    //<editor-fold defaultstate="collapsed" desc="enums">

    public enum CarClass {

	unacc, acc, good, vgood, no;
	private static final List<CarClass> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static CarClass random() {
	    return VALUES.get(RANDOM.nextInt(SIZE));
	}
    }

    public enum CarBuing {

	vhigh, high, med, low;
	private static final List<CarBuing> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static CarBuing random() {
	    return VALUES.get(RANDOM.nextInt(SIZE));
	}
    }

    public enum CarMaint {

	vhigh, high, med, low;
	private static final List<CarMaint> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static CarMaint random() {
	    return VALUES.get(RANDOM.nextInt(SIZE));
	}
    }

    public enum CarDoors {

	_2, _3, _4, _5more;
	private static final List<CarDoors> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static CarDoors random() {
	    return VALUES.get(RANDOM.nextInt(SIZE));
	}
    }

    public enum CarPersons {

	_2, _4, _more;
	private static final List<CarPersons> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static CarPersons random() {
	    return VALUES.get(RANDOM.nextInt(SIZE));
	}
    }

    public enum CarLug_boot {

	small, med, big;
	private static final List<CarLug_boot> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static CarLug_boot random() {
	    return VALUES.get(RANDOM.nextInt(SIZE));
	}
    }

    public enum CarSafety {

	low, med, high;
	private static final List<CarSafety> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static CarSafety random() {
	    return VALUES.get(RANDOM.nextInt(SIZE));
	}
    }
    //</editor-fold>
    private CarClass cls;
    private CarBuing buing;
    private CarMaint maint;
    private CarDoors doors;
    private CarPersons persons;
    private CarLug_boot lug_boot;
    private CarSafety safety;
    private double dCls = 0;

    //<editor-fold defaultstate="collapsed" desc="geters&seters">
    public CarBuing getBuing() {
	return buing;
    }

    public CarClass getCls() {
	return cls;
    }

    public CarDoors getDoors() {
	return doors;
    }

    public CarLug_boot getLug_boot() {
	return lug_boot;
    }

    public CarMaint getMaint() {
	return maint;
    }

    public CarPersons getPersons() {
	return persons;
    }

    public CarSafety getSafety() {
	return safety;
    }

    public void setCls(double d) {
	dCls = d;
	if (d % 1 > 0) {
	    throw new IllegalAccessError("cls ma być calkowite a jest " + d + "\n");
	}
	int i = (int) d;
	switch (i) {
	    case 1:
		this.cls = CarClass.unacc;
		break;
	    case 2:
		this.cls = CarClass.acc;
		break;
	    case 3:
		this.cls = CarClass.good;
		break;
	    case 4:
		this.cls = CarClass.vgood;
		break;
	    default:
		throw new IllegalAccessError("cls ma być inne a jest " + d + "\n");


	}
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public Car(CarClass cls, CarBuing buing, CarMaint maint, CarDoors doors, CarPersons persons, CarLug_boot lug_boot, CarSafety safety) {
	this.cls = cls;
	this.buing = buing;
	this.maint = maint;
	this.doors = doors;
	this.persons = persons;
	this.lug_boot = lug_boot;
	this.safety = safety;
    }

    public Car(CarBuing buing, CarMaint maint, CarDoors doors, CarPersons persons, CarLug_boot lug_boot, CarSafety safety) {
	this.cls = CarClass.no;
	this.buing = buing;
	this.maint = maint;
	this.doors = doors;
	this.persons = persons;
	this.lug_boot = lug_boot;
	this.safety = safety;
    }

    public Car() {
	this.cls = CarClass.no;
	this.dCls = 0;
	this.buing = CarBuing.random();
	this.maint = CarMaint.random();
	this.doors = CarDoors.random();
	this.persons = CarPersons.random();
	this.lug_boot = CarLug_boot.random();
	this.safety = CarSafety.random();

    }
    //</editor-fold>

    public svm_node[] bildDataToPredict() {
	svm_node[] r = new svm_node[6];

	r[0] = this.nodeFrom(buing, 1);
	r[1] = this.nodeFrom(maint, 2);
	r[2] = this.nodeFrom(doors, 3);
	r[3] = this.nodeFrom(persons, 4);
	r[4] = this.nodeFrom(lug_boot, 5);
	r[5] = this.nodeFrom(safety, 6);


	return r;
    }

    private svm_node nodeFrom(Enum val, int index) {
	svm_node r = new svm_node();
	r.index = index + 1;
	double doubleVal = 0;

	//<editor-fold defaultstate="collapsed" desc="enum to double">
	if (val.getClass().equals(CarBuing.class)) {
	    switch ((CarBuing) val) {
		case vhigh:
		    doubleVal = 1;
		    break;
		case high:
		    doubleVal = 2;
		    break;
		case med:
		    doubleVal = 3;
		    break;
		case low:
		    doubleVal = 4;
	    }
	}

	if (val.getClass().equals(CarMaint.class)) {
	    switch ((CarMaint) val) {
		case vhigh:
		    doubleVal = 1;
		    break;
		case high:
		    doubleVal = 2;
		    break;
		case med:
		    doubleVal = 3;
		    break;
		case low:
		    doubleVal = 4;
	    }
	}
	if (val.getClass().equals(CarDoors.class)) {
	    switch ((CarDoors) val) {
		case _2:
		    doubleVal = 1;
		    break;
		case _3:
		    doubleVal = 2;
		    break;
		case _4:
		    doubleVal = 3;
		    break;
		case _5more:
		    doubleVal = 4;
		    break;
	    }
	}
	if (val.getClass().equals(CarPersons.class)) {
	    switch ((CarPersons) val) {
		case _2:
		    doubleVal = 1;
		    break;
		case _4:
		    doubleVal = 2;
		    break;
		case _more:
		    doubleVal = 3;
		    break;
	    }
	}
	if (val.getClass().equals(CarLug_boot.class)) {
	    switch ((CarLug_boot) val) {
		case small:
		    doubleVal = 1;
		    break;
		case med:
		    doubleVal = 2;
		    break;
		case big:
		    doubleVal = 3;
		    break;
	    }
	}
	if (val.getClass().equals(CarSafety.class)) {
	    switch ((CarSafety) val) {
		case low:
		    doubleVal = 1;
		    break;
		case med:
		    doubleVal = 2;
		    break;
		case high:
		    doubleVal = 3;
		    break;

	    }
	}
//</editor-fold>

	if (doubleVal == 0) {
	    throw new IllegalArgumentException("to niejest nasz enum");
	}

	r.value = doubleVal;
	return r;
    }

    public void print() {
	System.out.print("-----------------\n");
	System.out.print("Car:\n");
	System.out.print("\tclass\t\t" + this.cls + " " + dCls + "\n");
	System.out.print("\tbuying\t\t" + this.buing + "\n");
	System.out.print("\tmaint\t\t" + this.maint + "\n");
	System.out.print("\tdoors\t\t" + this.doors + "\n");
	System.out.print("\tpersons\t\t" + this.persons + "\n");
	System.out.print("\tlug_boot\t" + this.lug_boot + "\n");
	System.out.print("\tsafety\t\t" + this.safety + "\n");
    }

    public void line() {
	System.out.print(this.buing + "," + this.maint + "," + this.doors.toString().replace("_", "") + "," + this.persons.toString().replace("_", "") + "," + this.lug_boot + "," + this.safety + "\n");
    }

    public Car[] AllCars() {
	ArrayList<Car> allTmp = new ArrayList<Car>();

	for (CarBuing b : CarBuing.values()) {
	    for (CarMaint m : CarMaint.values()) {
		for (CarDoors d : CarDoors.values()) {
		    for (CarPersons p : CarPersons.values()) {
			for (CarLug_boot l : CarLug_boot.values()) {
			    for (CarSafety s : CarSafety.values()) {
				allTmp.add(new Car(b, m, d, p, l, s));
			    }
			}
		    }
		}
	    }
	}

	Car[] all;
	all = new Car[allTmp.size()];
	for (int a = 0; a < allTmp.size(); ++a) {
	    all[a] = allTmp.get(a);
	}
	return all;
    }

    public Car AvgCar(Car[] all) {

	double[] sum = new double[7];
	for (int a = 0; a < sum.length; ++a) {
	    sum[a] = 0;
	}

	for (Car c : all) {
	    svm_node[] stat = c.bildDataToPredict();
	    for (int a = 0; a < stat.length; ++a) {
		sum[a] += stat[a].value;
	    }


	    switch (c.getCls()) {
		case unacc:
		    sum[6] += 1;
		    break;
		case acc:
		    sum[6] += 2;
		    break;
		case good:
		    sum[6] += 3;
		    break;
		case vgood:
		    sum[6] += 4;
		    break;
		case no:
		    throw new EnumConstantNotPresentException(null, "nie może nie miec klasy");
	    }
	}

	for (int a = 0; a < sum.length; ++a) {
	    sum[a] = sum[a] / all.length;
	    System.out.print(a + " " + sum[a] + "\n");
	}

	CarBuing b = null;
	CarMaint m = null;
	CarDoors d = null;
	CarPersons p = null;
	CarLug_boot l = null;
	CarSafety s = null;

	CarClass c = null;

	//<editor-fold defaultstate="collapsed" desc="double2Enum">
	int[] avg = new int[7];
	for (int a = 0; a < avg.length; ++a) {
	    avg[a] = (int) (sum[a] + 0.5);
	}



	switch (avg[0]) {
	    case 1:
		b = CarBuing.vhigh;
		break;
	    case 2:
		b = CarBuing.high;
		break;
	    case 3:
		b = CarBuing.med;
		break;
	    case 4:
		b = CarBuing.low;
	}



	switch (avg[1]) {
	    case 1:
		m = CarMaint.vhigh;
		break;
	    case 2:
		m = CarMaint.high;
		break;
	    case 3:
		m = CarMaint.med;
		break;
	    case 4:
		m = CarMaint.low;
	}

	switch (avg[2]) {
	    case 1:
		d = CarDoors._2;
		break;
	    case 2:
		d = CarDoors._3;
		break;
	    case 3:
		d = CarDoors._4;
		break;
	    case 4:
		d = CarDoors._5more;
		break;
	}

	switch (avg[3]) {
	    case 1:
		p = CarPersons._2;
		break;
	    case 2:
		p = CarPersons._4;
		break;
	    case 3:
		p = CarPersons._more;
		break;
	}

	switch (avg[4]) {
	    case 1:
		l = CarLug_boot.small;
		break;
	    case 2:
		l = CarLug_boot.med;
		break;
	    case 3:
		l = CarLug_boot.big;
		break;
	}


	switch (avg[5]) {
	    case 1:
		s = CarSafety.low;
		break;
	    case 2:
		s = CarSafety.med;
		break;
	    case 3:
		s = CarSafety.high;
		break;

	}


	switch (avg[6]) {

	    case 1:
		c = CarClass.unacc;
		break;
	    case 2:
		c = CarClass.acc;
		break;
	    case 3:
		c = CarClass.good;
		break;
	    case 4:
		c = CarClass.vgood;
		break;


	}



	//</editor-fold>

	return new Car(c, b, m, d, p, l, s);
    }
}
