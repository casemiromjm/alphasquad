package Game.view.elements.powerUps;

import Game.model.elements.powerUps.ExtraHealth;
import Game.view.elements.Drawable;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

// Classe responsável por desenhar o Power-Up de Saúde Extra no ecrã
public class ExtraHealthDraw implements Drawable {
    private final ExtraHealth extraHealth; // Instância do Power-Up de Saúde Extra

    /**
     * Construtor que inicializa o objeto de desenho para o Power-Up de Saúde Extra.
     *
     * @param extraHealth O Power-Up de Saúde Extra a ser desenhado.
     */
    public ExtraHealthDraw(ExtraHealth extraHealth) {
        this.extraHealth = extraHealth;
    }

    /**
     * Desenha o símbolo do Power-Up de Saúde Extra no ecrã.
     *
     * @param textGraphics O objeto TextGraphics utilizado para desenhar no ecrã.
     */
    @Override
    public void draw(TextGraphics textGraphics) {
        // Define a cor do texto para vermelho brilhante
        textGraphics.setForegroundColor(TextColor.ANSI.RED_BRIGHT);

        // Desenha o símbolo "+" na posição do Power-Up
        textGraphics.putString(
            new TerminalPosition(extraHealth.getPosition().getX(), extraHealth.getPosition().getY()), 
            "+"
        );
    }
}
