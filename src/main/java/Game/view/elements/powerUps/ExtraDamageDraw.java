package Game.view.elements.powerUps;

import Game.model.elements.powerUps.ExtraDamage;
import Game.view.elements.Drawable;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

// Classe responsável por desenhar o Power-Up de Dano Extra no ecrã
public class ExtraDamageDraw implements Drawable {
    private final ExtraDamage extraDamage; // Instância do Power-Up de Dano Extra

    /**
     * Construtor que inicializa o objeto de desenho para o Power-Up de Dano Extra.
     *
     * @param extraDamage O Power-Up de Dano Extra a ser desenhado.
     */
    public ExtraDamageDraw(ExtraDamage extraDamage) {
        this.extraDamage = extraDamage;
    }

    /**
     * Desenha o símbolo do Power-Up de Dano Extra no ecrã.
     *
     * @param textGraphics O objeto TextGraphics utilizado para desenhar no ecrã.
     */
    @Override
    public void draw(TextGraphics textGraphics) {
        // Define a cor do texto para amarelo brilhante
        textGraphics.setForegroundColor(TextColor.ANSI.YELLOW_BRIGHT);

        // Desenha o símbolo ">" na posição do Power-Up
        textGraphics.putString(
            new TerminalPosition(extraDamage.getPosition().getX(), extraDamage.getPosition().getY()), 
            ">"
        );
    }
}
