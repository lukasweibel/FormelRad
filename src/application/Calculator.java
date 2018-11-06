package application;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * Berechnet das Formelrad
 * 
 * @author Peter Rutschmann
 * @version 13.09.2018
 */
public class Calculator {
	private Double leistung;
	private Double spannung;
	private Double strom;
	private Double widerstand;

	public Calculator(Double leistung, Double spannung, Double strom, Double widerstand) {
		super();
		this.leistung = leistung;
		this.spannung = spannung;
		this.strom = strom;
		this.widerstand = widerstand;
	}

	public Double getLeistung() {
		return leistung;
	}

	public Double getSpannung() {
		return spannung;
	}

	public Double getStrom() {
		return strom;
	}

	public Double getWiderstand() {
		return widerstand;
	}

	@Override
	public String toString() {
		return "Calculator [leistung=" + leistung + ", spannung=" + spannung + ", strom=" + strom + ", widerstand="
				+ widerstand + "]";
	}

	public boolean calculate() {
        long filledFieldsAmount = Stream.of(leistung, spannung, strom, widerstand)
                .filter(Objects::nonNull)
                .count();

		if (leistung != null && spannung != null) {
			strom = ampereOutOfVoltAndWatt(spannung, leistung);
			widerstand = OhmFromVoltAndWatt(spannung, leistung);
		} else if (leistung != null && strom != null) {
			spannung = VoltFromWattAndAmpere(leistung, strom);
			widerstand = OhmFromWattAndAmpere(leistung, strom);
		} else if (leistung != null && widerstand != null) {
			spannung = VoltFromWattAndOhm(leistung, widerstand);
			strom = ampereOutOfOhmAndWatt(widerstand, leistung);
		} else if (spannung != null && strom != null) {
			leistung = wattOutOfVoltAndAmpere(spannung, strom);
			widerstand = OhmFromVoltAndAmpere(spannung, strom);
		} else if (spannung != null && widerstand != null) {
			leistung = wattOutOfVoltAndOhm(spannung, widerstand);
			strom = ampereOutOfOhmAndVolt(widerstand, spannung);
		} else if (strom != null && widerstand != null) {
			leistung = wattOutOfAmpereAndOhm(strom, widerstand);
			spannung = VoltfromOhmandAmpere(widerstand, strom);
		} else {
			throw new RuntimeException("Not enough args");
		}

        return filledFieldsAmount < 3;
    }

	/*
	 * Hier die Methoden mit den Formlen hinzufügen
	 */
	private double VoltfromOhmandAmpere(double ohm, double ampere) {
		return ohm * ampere;
	}

	private double VoltFromWattAndAmpere(double watt, double ampere) {
		return watt / ampere;
	}

	private double VoltFromWattAndOhm(double watt, double ohm) {
		return Math.sqrt(watt * ohm);
	}

	private double OhmFromVoltAndAmpere(double volt, double ampere) {
		return volt/ampere;
	}

	private double ampereOutOfOhmAndVolt(double ohm, double volt){
		return volt / ohm;
	}
	
	private double ampereOutOfOhmAndWatt(double ohm, double watt) {
		return Math.sqrt(watt / ohm);
	}

	private double wattOutOfAmpereAndOhm(double ampere, double ohm) {
		return ohm * Math.pow(ampere, 2);
	}

	private double wattOutOfVoltAndAmpere(double volt, double ampere) {
		return volt * ampere;
	}

    private double wattOutOfVoltAndOhm(double volt, double ohm) {
        return Math.pow(volt, 2) / ohm;
    }

    private double ampereOutOfVoltAndWatt(double volt, double watt){
		return watt / volt;
	}

	private double OhmFromWattAndAmpere(double watt, double ampere) {
		return watt/Math.pow(ampere, 2);
	}

	private double OhmFromVoltAndWatt(double volt, double watt) {
		return Math.pow(volt, 2)/watt;
	}
	
}
