package com.masai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SelectInstrument {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Instruments> instruments = new ArrayList<>();
        instruments.add(new Instruments(3, 2, 10));
        instruments.add(new Instruments(4, 3, 15));
        instruments.add(new Instruments(2, 1, 8));
        instruments.add(new Instruments(5, 4, 20));

        int payloadCapacity = 10;
        int volumeCapacity = 7;

        List<Instruments> selectedInstruments = selectInstruments(instruments, payloadCapacity, volumeCapacity);
        System.out.println("Selected Instruments:");
        for (Instruments instrument : selectedInstruments) {
            System.out.println("Weight: " + instrument.weight + ", Volume: " + instrument.volume +
                    ", Base Scientific Value: " + instrument.baseScintificValue);
        }

        int totalWeight = selectedInstruments.stream().mapToInt(instrument -> instrument.weight).sum();
        int totalVolume = selectedInstruments.stream().mapToInt(instrument -> instrument.volume).sum();
        int totalScientificValue = selectedInstruments.stream().mapToInt(instrument -> instrument.baseScintificValue).sum();
        
        System.out.println("Total Weight: " + totalWeight);
        System.out.println("Total Volume: " + totalVolume);
        System.out.println("Total Scientific Value: " + totalScientificValue);
	
	}
	
	 private static List<Instruments> selectInstruments(List<Instruments> instruments, int payloadCapacity, int volumeCapacity) {
	        Collections.sort(instruments, Comparator.comparingDouble(instrument ->
	                (double) instrument.baseScintificValue / instrument.weight));

	        List<Instruments> selectedInstruments = new ArrayList<>();
	        int currentPayload = 0;
	        int currentVolume = 0;

	        for (Instruments instrument : instruments) {
	            if (currentPayload + instrument.weight <= payloadCapacity &&
	                    currentVolume + instrument.volume <= volumeCapacity) {
	                selectedInstruments.add(instrument);
	                currentPayload += instrument.weight;
	                currentVolume += instrument.volume;
	            }
	        }

	        return selectedInstruments;
	    }

}


