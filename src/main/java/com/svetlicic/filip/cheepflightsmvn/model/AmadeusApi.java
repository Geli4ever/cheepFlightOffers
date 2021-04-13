package com.svetlicic.filip.cheepflightsmvn.model;

import com.amadeus.Amadeus;

public class AmadeusApi {
    private static final AmadeusApi INSTANCE = new AmadeusApi();

    private final Amadeus amadeus = Amadeus
            .builder("dkVnrciocUVlAmuekIrXEy3y1fLKtALM", "3D0us6Szb7AShE3o")
            .build();

    private AmadeusApi(){}

    public static AmadeusApi getInstance(){
        return INSTANCE;
    }

    public Amadeus getAmadeus(){
        return this.amadeus;
    }
}
