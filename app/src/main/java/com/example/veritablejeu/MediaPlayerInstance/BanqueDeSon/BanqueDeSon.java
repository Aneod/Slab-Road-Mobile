package com.example.veritablejeu.MediaPlayerInstance.BanqueDeSon;

import com.example.veritablejeu.R;

import java.util.ArrayList;
import java.util.List;

public class BanqueDeSon implements IBanqueDeSon {

    private static BanqueDeSon instance;
    private final int musiqueDuMenu = R.raw.son_trois;
    private final List<Integer> pistesAudio = new ArrayList<>();

    private BanqueDeSon(){
        pistesAudio.add(R.raw.son_un);
        pistesAudio.add(R.raw.son_deux);
        pistesAudio.add(R.raw.son_trois);
        pistesAudio.add(R.raw.son_quatre);
        pistesAudio.add(R.raw.son_cinq);
        pistesAudio.add(R.raw.son_six);
        pistesAudio.add(R.raw.son_sept);
        pistesAudio.add(R.raw.son_huit);
        pistesAudio.add(R.raw.son_neuf);
        pistesAudio.add(R.raw.son_dix);
        pistesAudio.add(R.raw.son_onze);
        pistesAudio.add(R.raw.son_douze);
        pistesAudio.add(R.raw.son_treize);
        pistesAudio.add(R.raw.son_quatorze);
        pistesAudio.add(R.raw.son_quize);
    }

    public static BanqueDeSon getInstance() {
        if(instance == null) instance = new BanqueDeSon();
        return instance;
    }

    @Override
    public int getMusiqueDuNumero(int numero) {
        if(numero < 0 || numero >= pistesAudio.size()) {
            return musiqueDuMenu;
        }
        return pistesAudio.get(numero);
    }

    @Override
    public int getMusiqueDuMenu() {
        return musiqueDuMenu;
    }

    @Override
    public int getNombreTotalDePistes() {
        return pistesAudio.size();
    }
}
