package com.example.veritablejeu.Game;

import androidx.annotation.Nullable;
import androidx.core.util.Consumer;

import com.example.veritablejeu.BainDeSavon.BainDeSavon;
import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.MediaPlayerInstance.MediaPlayerInstance;
import com.example.veritablejeu.BackEnd.sequentialCode.Code;

public class FirstCodeReader {

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
                'a', (Consumer<String>) codex -> gameAesthetic(game, codex),
                'm', (Consumer<String>) codex -> playMusic(game, codex),
                'p', (Consumer<String>) codex -> createGameboard(game, codex)
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
                'b', (Consumer<String>) game::backgroundColoration,
                'p', (Consumer<String>) (string) -> BainDeSavon.getInstance(game).setDesigns(string),
                'o', (Consumer<String>) game::setCableOutline
        );
    }

    private static void playMusic(Game game, @Nullable String musicId) {
        if(game == null) {
            return;
        }
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
