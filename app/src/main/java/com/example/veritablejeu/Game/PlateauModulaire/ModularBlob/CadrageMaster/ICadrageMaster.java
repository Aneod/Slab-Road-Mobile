package com.example.veritablejeu.Game.PlateauModulaire.ModularBlob.CadrageMaster;

public interface ICadrageMaster {

    void show_and_resume();

    void hide_and_pause();

    void resume_animation();

    void pause_animation();

    void show(int duration);

    void hide(int duration);
}
