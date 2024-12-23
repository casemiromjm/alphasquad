package Game.view.elements.obstacles;

import Game.model.elements.obstacles.Bush;
import Game.view.elements.Drawable;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

// Classe responsável por desenhar um arbusto no ecrã
public class BushDraw implements Drawable {
    private final Bush bush; // Instância do arbusto

    /**
     * Construtor que inicializa o objeto de desenho para o arbusto.
     *
     * @param bush O arbusto a ser desenhado.
     */
    public BushDraw(Bush bush) {
        this.bush = bush;
    }

    /**
     * Desenha o arbusto no ecrã.
     *
     * @param textGraphics O objeto TextGraphics utilizado para desenhar no ecrã.
     */
    @Override
    public void draw(TextGraphics textGraphics) {
        // Define a cor do texto para verde brilhante
        textGraphics.setForegroundColor(TextColor.ANSI.GREEN_BRIGHT);

        // Desenha o símbolo "B" na posição do arbusto
        textGraphics.putString(
            new TerminalPosition(bush.getPosition().getX(), bush.getPosition().getY()), 
            "B"
        );
    }
}

