package application;

/**
 * Berechnet das Formelrad
 * 
 * @author Peter Rutschmann
 * @version 13.09.2018
 */
public class Calculator {
	private double leistung;
	private double spannung;
	private double strom;
	private double widerstand;

	public Calculator(double leistung, double spannung, double strom, double widerstand) {
		super();
		this.leistung = leistung;
		this.spannung = spannung;
		this.strom = strom;
		this.widerstand = widerstand;
	}

	public double getLeistung() {
		return leistung;
	}

	public double getSpannung() {
		return spannung;
	}

	public double getStrom() {
		return strom;
	}

	public double getWiderstand() {
		return widerstand;
	}

	@Override
	public String toString() {
		return "Calculator [leistung=" + leistung + ", spannung=" + spannung + ", strom=" + strom + ", widerstand="
				+ widerstand + "]";
	}

	public void calculate() {
		/*
		 * Hier auf Grund der vorhanden Werte entscheiden welche Methode unten
		 * aufgerufen werden muss.
		 */
	}

	/*
	 * Hier die Methoden mit den Formlen hinzufügen
	 */

	private double ampereOutOfOhmAndVolt(double ohm, double volt){
		return volt / ohm;
	}
	
	private double ampereOutOfOhmAndWatt(double ohm, double watt){
		return Math.sqrt(watt / ohm);
	}
	
	private double ampereOutOfVoltAndWatt(double volt, double watt){
		return watt / volt;
	}

	private double VoltfromOhmandAmpere(double ohm, double ampere) {
		return ohm * ampere;
	}

	private double VoltFromWattAndAmpere(double watt, double ampere) {
		return watt / ampere;
	}

	private double VoltFromWattAndOhm(double watt, double ohm) {
		return Math.sqrt(watt * ohm);
	}


}