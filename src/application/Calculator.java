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
		double result = ohm * ampere;
		System.out.println("Result of volt from ohm (" + ohm + ") and ampere (" + ampere + ") is " + result);
		return result;
	}

	private double VoltFromWattAndAmpere(double watt, double ampere) {
		double result = watt / ampere;
		System.out.println("Result of volt from watt (" + watt + ") and ampere (" + ampere + ") is " + result);
		return result;
	}

	private double VoltFromWattAndOhm(double watt, double ohm) {
		double result = Math.sqrt(watt * ohm);
		System.out.println("Result of volt from watt (" + watt + ") and ohm (" + ohm + ") is " + result);
		return result;
	}

	private double OhmFromVoltAndAmpere(double volt, double ampere) {
		double result = volt/ampere;
        System.out.println("Result of ohm from volt (" + volt + ") and ampere (" + ampere + ") is " + result);
        return result;
	}

	private double ampereOutOfOhmAndVolt(double ohm, double volt){
		double result = volt / ohm;
        System.out.println("Result of ampere from ohm (" + ohm + ") and volt (" + volt + ") is " + result);
        return result;
    }
	
	private double ampereOutOfOhmAndWatt(double ohm, double watt) {
		double result = Math.sqrt(watt / ohm);
        System.out.println("Result of ampere from ohm (" + ohm + ") and watt (" + watt + ") is " + result);
        return result;
	}

	private double wattOutOfAmpereAndOhm(double ampere, double ohm) {
		double result = ohm * Math.pow(ampere, 2);
        System.out.println("Result of watt from ampere (" + ampere + ") and ohm (" + ohm + ") is " + result);
        return result;
	}

	private double wattOutOfVoltAndAmpere(double volt, double ampere) {
		double result = volt * ampere;
        System.out.println("Result of watt from volt (" + volt + ") and ampere (" + ampere + ") is " + result);
        return result;
    }

    private double wattOutOfVoltAndOhm(double volt, double ohm) {
        double result = Math.pow(volt, 2) / ohm;
        System.out.println("Result of watt from volt (" + volt + ") and ohm (" + ohm + ") is " + result);
        return result;
    }

    private double ampereOutOfVoltAndWatt(double volt, double watt){
		double result = watt / volt;
        System.out.println("Result of ampere from volt (" + volt + ") and watt (" + watt + ") is " + result);
        return result;
    }

	private double OhmFromWattAndAmpere(double watt, double ampere) {
		double result =  watt/Math.pow(ampere, 2);
        System.out.println("Result of ohm from watt (" + watt + ") and ampere (" + ampere + ") is " + result);
        return result;
    }

	private double OhmFromVoltAndWatt(double volt, double watt) {
		double result = Math.pow(volt, 2)/watt;
        System.out.println("Result of ohm from volt (" + volt + ") and watt (" + watt + ") is " + result);
        return result;
    }
	
}
