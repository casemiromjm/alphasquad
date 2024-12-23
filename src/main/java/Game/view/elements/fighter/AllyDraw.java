package Game.view.elements.fighter;

import Game.model.elements.fighter.Ally;
import Game.view.elements.Drawable;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

// Classe responsável por desenhar os aliados no ecrã
public class AllyDraw implements Drawable {
    private final Ally ally; // Instância do aliado

    /**
     * Construtor que inicializa o objeto de desenho para o aliado.
     *
     * @param ally O aliado a ser desenhado.
     */
    public AllyDraw(Ally ally) {
        this.ally = ally;
    }

    /**
     * Desenha o aliado no ecrã.
     *
     * @param textGraphics O objeto TextGraphics utilizado para desenhar no ecrã.
     */
    @Override
    public void draw(TextGraphics textGraphics) {
        // Define a cor do texto para ciano
        textGraphics.setForegroundColor(TextColor.ANSI.CYAN);

        // Desenha o símbolo "A" na posição do aliado
        textGraphics.putString(
            new TerminalPosition(ally.getPosition().getX(), ally.getPosition().getY()), 
            "A"
        );
    }
}

