package Game.view.elements.powerUps;

import Game.model.elements.powerUps.ExtraAim;
import Game.view.elements.Drawable;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

// Classe responsável por desenhar o Power-Up de Precisão Extra no ecrã
public class ExtraAimDraw implements Drawable {
    private final ExtraAim extraAim; // Instância do Power-Up de Precisão Extra

    /**
     * Construtor que inicializa o objeto de desenho para o Power-Up de Precisão Extra.
     *
     * @param extraAim O Power-Up de Precisão Extra a ser desenhado.
     */
    public ExtraAimDraw(ExtraAim extraAim) {
        this.extraAim = extraAim;
    }

    /**
     * Desenha o símbolo do Power-Up de Precisão Extra no ecrã.
     *
     * @param textGraphics O objeto TextGraphics utilizado para desenhar no ecrã.
     */
    @Override
    public void draw(TextGraphics textGraphics) {
        // Define a cor do texto para magenta brilhante
        textGraphics.setForegroundColor(TextColor.ANSI.MAGENTA_BRIGHT);

        // Desenha o símbolo "o" na posição do Power-Up
        textGraphics.putString(
            new TerminalPosition(extraAim.getPosition().getX(), extraAim.getPosition().getY()), 
            "o"
        );
    }
}
