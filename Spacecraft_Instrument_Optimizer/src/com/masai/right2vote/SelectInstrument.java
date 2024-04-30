package com.masai.right2vote;

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
		
		int n = instruments.length;
		
        int[][][] dp = new int[n + 1][maxWeight + 1][maxVolume + 1];
        
        for (int i = 1; i <= n; i++) {
            Instruments current = instruments[i - 1];
            for (int w = 1; w <= maxWeight; w++) {
                for (int v = 1; v <= maxVolume; v++) {
                    if (current.weight <= w && current.volume <= v) {
                        dp[i][w][v] = Math.max(dp[i - 1][w][v], dp[i - 1][w - current.weight][v - current.volume] + current.baseScintificValue);
                    } else {
                        dp[i][w][v] = dp[i - 1][w][v];
                    }
                }
            }
        }
        
        List<Instruments> selectedInstruments = new ArrayList<>();
        int w = maxWeight;
        int v = maxVolume;
        for (int i = n; i > 0 && dp[i][w][v] > 0; i--) {
            if (dp[i][w][v] != dp[i - 1][w][v]) {
                selectedInstruments.add(instruments[i - 1]);
                w -= instruments[i - 1].weight;
                v -= instruments[i - 1].volume;
            }
        }
        
        return selectedInstruments;
	}

}


