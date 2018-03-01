package com.example.android.morejokes;

import java.util.Random;

public class Jokes {

    private final static String[] JOKES = {
            "Era uma vez um pintinho que chamava Ralam. Toda vez que chovia, Relam Piava",
            "Por que a galinha atravessou a faixa de Mobius? Para chegar do mesmo lado",
            "O que o fisico fazia quando queria sair para brincar e nao tinha tempo? Usava Torricelli",
            "Como o papai noel otimiza a entrega de presentes? Usando calculo Renal"
    };

    public String getJoke(){
        return JOKES[new Random().nextInt(JOKES.length)];
    }
}
