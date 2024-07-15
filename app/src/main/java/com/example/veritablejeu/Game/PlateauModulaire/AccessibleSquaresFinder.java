package com.example.veritablejeu.Game.PlateauModulaire;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.PlateauModulaire.ModularBlob.BestItinerary;
import com.example.veritablejeu.Game.PlateauModulaire.ModularBlob.ModularBlob;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinates;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinatesManager;

import org.jetbrains.annotations.Contract;

import java.util.HashSet;
import java.util.Set;

public class AccessibleSquaresFinder {

    @NonNull
    public static Set<ZdecimalCoordinates> getAllAccessibleSquares_byMaster(@NonNull Board plateau) {
        Set<ZdecimalCoordinates> casesConnues = new HashSet<>();
        ModularBlob master = plateau.getMaster();
        if (master == null) {
            return casesConnues;
        }
        return redefinirCasesConnuesEnCasesAccessibles(plateau, master.getCurrentLocation().getCord(), casesConnues);
    }

    @NonNull
    @Contract("_, _, _ -> param3")
    private static Set<ZdecimalCoordinates> redefinirCasesConnuesEnCasesAccessibles(Board plateau, ZdecimalCoordinates numCaseDepart, @NonNull Set<ZdecimalCoordinates> casesConnues) {

        casesConnues.add(numCaseDepart);

        ZdecimalCoordinates caseDuHaut = ZdecimalCoordinatesManager.getTopOf(numCaseDepart);
        ZdecimalCoordinates caseDuBas = ZdecimalCoordinatesManager.getBottomOf(numCaseDepart);
        ZdecimalCoordinates caseDeGauche = ZdecimalCoordinatesManager.getLeftOf(numCaseDepart);
        ZdecimalCoordinates caseDeDroite = ZdecimalCoordinatesManager.getRightOf(numCaseDepart);
        ZdecimalCoordinates[] allNeighbors = new ZdecimalCoordinates[]{ caseDuHaut, caseDuBas, caseDeGauche, caseDeDroite };

        for(ZdecimalCoordinates coordinates : allNeighbors) {
            if(BestItinerary.isValidDirection(plateau, numCaseDepart, coordinates, casesConnues))
                casesConnues.addAll(redefinirCasesConnuesEnCasesAccessibles(plateau, coordinates, casesConnues));
        }

        return casesConnues;
    }
}
