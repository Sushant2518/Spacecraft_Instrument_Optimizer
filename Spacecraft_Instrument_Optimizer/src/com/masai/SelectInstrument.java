package com.masai;

import java.util.ArrayList;
import java.util.List;

public class SelectInstrument {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// available instuments
		Instruments[] instruments = {
                new Instruments(3, 2, 10),
                new Instruments(4, 3, 15),
                new Instruments(2, 1, 8),
                new Instruments(5, 4, 20)
        };
		
		
		//Capacity
		int maxWeight = 10;
		int maxVolume = 7;
		
		//optimal combination of instruments
		List<Instruments> selectedInstruments = selectInstruments(instruments, maxWeight, maxVolume);
		
		
		System.out.println("Selected Instruments: ");
		
		for (Instruments instrument : selectedInstruments) {
            System.out.println("Weight: " + instrument.weight + " kg, Volume: " + instrument.volume + " m^3, Scientific Value: " + instrument.baseScintificValue);
        }
		
		int totalWeight = selectedInstruments.stream().mapToInt(instrument -> instrument.weight).sum();
        int totalVolume = selectedInstruments.stream().mapToInt(instrument -> instrument.volume).sum();
        int totalBaseScintificValue = selectedInstruments.stream().mapToInt(instrument -> instrument.baseScintificValue).sum();

        System.out.println("Total Weight: " + totalWeight + " kg");
        System.out.println("Total Volume: " + totalVolume + " m^3");
        System.out.println("Total Scientific Value: " + totalBaseScintificValue);
	}
	
	public static List<Instruments> selectInstruments(Instruments[] instruments, int maxWeight, int maxVolume){
		List<Instruments> bestCombination = new ArrayList<>();
		
		int bestValue = 0;
		
		for (int i = 0; i < (1 << instruments.length); i++) {
			List<Instruments> currentCombination = new ArrayList<>();
			
			int currentWeight = 0;
            int currentVolume = 0;
            int currentBaseScintificValue = 0;
            
            for (int j = 0; j < instruments.length; j++) {
                if ((i & (1 << j)) != 0) {
                    Instruments instrument = instruments[j];
                    if (currentWeight + instrument.weight <= maxWeight && currentVolume + instrument.volume <= maxVolume) {
                        currentCombination.add(instrument);
                        currentWeight += instrument.weight;
                        currentVolume += instrument.volume;
                        currentBaseScintificValue += instrument.baseScintificValue;
                    }
                }
            }
            if (currentBaseScintificValue > bestValue) {
                bestValue = currentBaseScintificValue;
                bestCombination = new ArrayList<>(currentCombination);
            }
            
		}
		return bestCombination;
	}

}


