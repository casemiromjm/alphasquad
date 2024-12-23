package Game.view.elements.obstacles;

import Game.model.elements.obstacles.SmallStoneWall;
import Game.view.elements.Drawable;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

// Classe responsável por desenhar a pequena parede de pedra no ecrã
public class SmallStoneWallDraw implements Drawable {
    private final SmallStoneWall smallStoneWall; // Instância da parede de pedra

    /**
     * Construtor que inicializa o objeto de desenho para a pequena parede de pedra.
     *
     * @param smallStoneWall A parede de pedra a ser desenhada.
     */
    public SmallStoneWallDraw(SmallStoneWall smallStoneWall) {
        this.smallStoneWall = smallStoneWall;
    }

    /**
     * Desenha a pequena parede de pedra no ecrã.
     *
     * @param textGraphics O objeto TextGraphics utilizado para desenhar no ecrã.
     */
    @Override
    public void draw(TextGraphics textGraphics) {
        // Define a cor do texto para cinza (#808080)
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#808080"));

        // Desenha o símbolo "S" na posição da parede de pedra
        textGraphics.putString(
            new TerminalPosition(smallStoneWall.getPosition().getX(), smallStoneWall.getPosition().getY()), 
            "S"
        );
    }
}
