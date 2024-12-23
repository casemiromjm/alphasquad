package Game.view.elements.fighter;

import Game.model.elements.fighter.Enemy;
import Game.view.elements.Drawable;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

// Classe responsável por desenhar o inimigo no ecrã
public class EnemyDraw implements Drawable {
    private final Enemy enemy; // Instância do inimigo

    /**
     * Construtor que inicializa o objeto de desenho para o inimigo.
     *
     * @param enemy O inimigo a ser desenhado.
     */
    public EnemyDraw(Enemy enemy) {
        this.enemy = enemy;
    }

    /**
     * Desenha o inimigo no ecrã.
     *
     * @param textGraphics O objeto TextGraphics utilizado para desenhar no ecrã.
     */
    @Override
    public void draw(TextGraphics textGraphics) {
        // Define a cor do texto para vermelho
        textGraphics.setForegroundColor(TextColor.ANSI.RED);

        // Desenha o símbolo "E" na posição do inimigo
        textGraphics.putString(
            new TerminalPosition(enemy.getPosition().getX(), enemy.getPosition().getY()), 
            "E"
        );
    }
}

