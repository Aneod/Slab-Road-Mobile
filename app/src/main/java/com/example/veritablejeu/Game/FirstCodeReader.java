package com.example.veritablejeu.Game;

import androidx.annotation.Nullable;
import androidx.core.util.Consumer;

import com.example.veritablejeu.BainDeSavon.BainDeSavon;
import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.MediaPlayerInstance.MediaPlayerInstance;
import com.example.veritablejeu.BackEnd.sequentialCode.Code;

public class FirstCodeReader {

    private static final char KEY_AESTHETIC = 'a';
    private static final char KEY_MUSIC = 'm';
    private static final char KEY_GAMEBOARD = 'p';

    public static char getKeyAesthetic() {
        return KEY_AESTHETIC;
    }

    public static char getKeyMusic() {
        return KEY_MUSIC;
    }

    public static char getKeyGameboard() {
        return KEY_GAMEBOARD;
    }

    private static final char KEY_BACKGROUND_COLORATION = 'b';
    private static final char KEY_BUBBLES_STYLE = 'p';
    private static final char KEY_CABLE_OUTLINE = 'o';

    public static char getKeyBackgroundColoration() {
        return KEY_BACKGROUND_COLORATION;
    }

    public static char getKeyBubblesStyle() {
        return KEY_BUBBLES_STYLE;
    }

    public static char getKeyCableOutline() {
        return KEY_CABLE_OUTLINE;
    }

    /**
     * Manages all data of the original code.
     * <p>
     *     a -> gameAesthetic
     *     <br>
     *     m -> music
     *     <br>
     *     p -> add a board
     * </p>
     */
    public static void read(Game game, String code) {
        Code.apply(code,
                KEY_AESTHETIC, (Consumer<String>) codex -> gameAesthetic(game, codex),
                KEY_MUSIC, (Consumer<String>) codex -> playMusic(game, codex),
                KEY_GAMEBOARD, (Consumer<String>) codex -> createGameboard(game, codex)
        );
    }

    /**
     * Manages the aesthetic of a Game.
     * <p>
     *     b -> background (colors)
     *     <br>
     *     p -> particles (colors and shape)
     * </p>
     */
    private static void gameAesthetic(Game game, String code) {
        if(game == null) return;
        Code.apply(code,
                KEY_BACKGROUND_COLORATION, (Consumer<String>) codex -> backgroundColoration(game, codex),
                KEY_BUBBLES_STYLE, (Consumer<String>) (string) -> BainDeSavon.getInstance(game).setDesigns(string),
                KEY_CABLE_OUTLINE, (Consumer<String>) game::setCableOutline
        );
    }

    private static void backgroundColoration(Game game, String code) {
        if(game == null) return;
        game.getBackgroundColors().setColors_byCode(code);
    }

    private static void playMusic(Game game, @Nullable String musicId) {
        if(game == null) return;
        boolean isNumeric = isNumeric(musicId);
        if(isNumeric) {
            int intId = Integer.parseInt(musicId);
            MediaPlayerInstance mediaPlayerInstance = MediaPlayerInstance.getInstance(game);
            mediaPlayerInstance.playNewMusic(intId);
        }
    }

    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private static void createGameboard(Game game, String code) {
        Board plateauModulaire = new Board(game, code);
        game.addBoard(plateauModulaire);
    }

}
