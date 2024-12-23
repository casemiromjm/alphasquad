package Game.view.elements.obstacles;

import Game.model.elements.obstacles.SmallWoodenWall;
import Game.view.elements.Drawable;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

// Classe responsável por desenhar a pequena parede de madeira no ecrã
public class SmallWoodenWallDraw implements Drawable {
    private final SmallWoodenWall smallWoodenWall; // Instância da parede de madeira

    /**
     * Construtor que inicializa o objeto de desenho para a pequena parede de madeira.
     *
     * @param smallWoodenWall A parede de madeira a ser desenhada.
     */
    public SmallWoodenWallDraw(SmallWoodenWall smallWoodenWall) {
        this.smallWoodenWall = smallWoodenWall;
    }

    /**
     * Desenha a pequena parede de madeira no ecrã.
     *
     * @param textGraphics O objeto TextGraphics utilizado para desenhar no ecrã.
     */
    @Override
    public void draw(TextGraphics textGraphics) {
        // Define a cor do texto para uma tonalidade de madeira (#3F301D)
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#3F301D"));

        // Desenha o símbolo "W" na posição da parede de madeira
        textGraphics.putString(
            new TerminalPosition(smallWoodenWall.getPosition().getX(), smallWoodenWall.getPosition().getY()), 
            "W"
        );
    }
}
