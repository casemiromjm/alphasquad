package Game.view.elements;

import com.googlecode.lanterna.graphics.TextGraphics;

// Interface que define o contrato para elementos desenháveis no jogo
public interface Drawable {

    /**
     * Método responsável por desenhar o elemento no ecrã.
     *
     * @param textGraphics O objeto TextGraphics usado para desenhar o elemento.
     */
    void draw(TextGraphics textGraphics);
}
