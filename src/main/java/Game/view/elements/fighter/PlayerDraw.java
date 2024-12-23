package Game.view.elements.fighter;

import Game.model.elements.fighter.Player;
import Game.view.elements.Drawable;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

// Classe responsável por desenhar o jogador no ecrã
public class PlayerDraw implements Drawable {
    private final Player player; // Instância do jogador

    /**
     * Construtor que inicializa o objeto de desenho para o jogador.
     *
     * @param player O jogador a ser desenhado.
     */
    public PlayerDraw(Player player) {
        this.player = player;
    }

    /**
     * Desenha o jogador no ecrã.
     *
     * @param textGraphics O objeto TextGraphics utilizado para desenhar no ecrã.
     */
    @Override
    public void draw(TextGraphics textGraphics) {
        // Define a cor do texto para azul
        textGraphics.setForegroundColor(TextColor.ANSI.BLUE);

        // Desenha o símbolo "P" na posição do jogador
        textGraphics.putString(
            new TerminalPosition(player.getPosition().getX(), player.getPosition().getY()), 
            "P"
        );
    }
}

