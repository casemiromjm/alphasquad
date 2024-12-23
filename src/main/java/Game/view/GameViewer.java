package Game.view;

import Game.model.gameModel.GameModel;
import Game.model.elements.Position;
import Game.model.elements.fighter.Ally;
import Game.model.elements.fighter.Enemy;
import Game.model.elements.fighter.Fighter;
import Game.model.elements.obstacles.*;
import Game.model.elements.powerUps.*;
import Game.view.elements.Drawable;
import Game.view.elements.fighter.AllyDraw;
import Game.view.elements.fighter.EnemyDraw;
import Game.view.elements.fighter.PlayerDraw;
import Game.view.elements.obstacles.BushDraw;
import Game.view.elements.obstacles.SmallStoneWallDraw;
import Game.view.elements.obstacles.SmallWoodenWallDraw;
import Game.view.elements.powerUps.ExtraAimDraw;
import Game.view.elements.powerUps.ExtraDamageDraw;
import Game.view.elements.powerUps.ExtraHealthDraw;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Classe responsável por renderizar o estado principal do jogo no ecrã
public class GameViewer extends Viewer {

    /**
     * Construtor que inicializa o visualizador do jogo com o modelo fornecido.
     *
     * @param gameModel O modelo que contém os dados do jogo.
     */
    public GameViewer(GameModel gameModel) {
        super(gameModel);
    }

    /**
     * Desenha o estado atual do jogo no ecrã.
     *
     * @param screen O ecrã onde os elementos do jogo serão desenhados.
     * @throws IOException Caso ocorra um erro ao desenhar no ecrã.
     */
    @Override
    public void draw(Screen screen) throws IOException {
        screen.clear();

        TextGraphics textGraphics = screen.newTextGraphics();
        int width = screen.getTerminalSize().getColumns();
        int height = screen.getTerminalSize().getRows();

        drawBackground(textGraphics, width, height);
        drawElements(textGraphics);

        screen.refresh();
    }

    /**
     * Desenha o ecrã da fase de combate, mostrando informações adicionais sobre o lutador ativo e o alvo.
     *
     * @param screen O ecrã onde os elementos do combate serão desenhados.
     * @param fighter O lutador ativo.
     * @param target O alvo do lutador ativo.
     * @throws IOException Caso ocorra um erro ao desenhar no ecrã.
     */
    public void drawFighterCombatPhase(Screen screen, Fighter fighter, Fighter target) throws IOException {
        screen.clear();

        TextGraphics textGraphics = screen.newTextGraphics();
        int width = screen.getTerminalSize().getColumns();
        int height = screen.getTerminalSize().getRows();

        drawBackground(textGraphics, width, height);
        drawElements(textGraphics);
        drawSideInfo(textGraphics, fighter, target);
        drawTargetSelection(textGraphics, target.getPosition());

        screen.refresh();
    }

    /**
     * Cria uma lista de elementos desenháveis com base no estado atual do jogo.
     *
     * @return Uma lista de elementos que podem ser desenhados.
     */
    private List<Drawable> createElements() {
        List<Drawable> elements = new ArrayList<>();
        GameModel gameModel = (GameModel) super.getModel();

        elements.add(new PlayerDraw(gameModel.getPlayer()));

        for (Enemy enemy : gameModel.getEnemyList()) {
            elements.add(new EnemyDraw(enemy));
        }

        for (Ally al : gameModel.getAllyList()) {
            elements.add(new AllyDraw(al));
        }

        for (Obstacle ob : gameModel.getObstacleList()) {
            if (ob instanceof Bush) {
                elements.add(new BushDraw((Bush) ob));
            } else if (ob instanceof SmallStoneWall) {
                elements.add(new SmallStoneWallDraw((SmallStoneWall) ob));
            } else if (ob instanceof SmallWoodenWall) {
                elements.add(new SmallWoodenWallDraw((SmallWoodenWall) ob));
            }
        }

        for (PowerUp pu : gameModel.getPowerUpList()) {
            if (pu instanceof ExtraHealth) {
                elements.add(new ExtraHealthDraw((ExtraHealth) pu));
            } else if (pu instanceof ExtraAim) {
                elements.add(new ExtraAimDraw((ExtraAim) pu));
            } else if (pu instanceof ExtraDamage) {
                elements.add(new ExtraDamageDraw((ExtraDamage) pu));
            }
        }

        return elements;
    }

    /**
     * Desenha o fundo da arena.
     *
     * @param textGraphics O objeto responsável por desenhar no ecrã.
     * @param width A largura do ecrã.
     * @param height A altura do ecrã.
     */
    private void drawBackground(TextGraphics textGraphics, int width, int height) {
        GameModel gameModel = (GameModel) super.getModel();
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.setForegroundColor(TextColor.ANSI.GREEN);
        textGraphics.fillRectangle(
            new TerminalPosition(gameModel.getArenaStartPoint(), 0),
            new TerminalSize(width, height),
            '~'
        );
    }

    /**
     * Desenha todos os elementos do jogo.
     *
     * @param textGraphics O objeto responsável por desenhar no ecrã.
     */
    private void drawElements(TextGraphics textGraphics) {
        List<Drawable> elements = createElements();
        for (Drawable el : elements) {
            el.draw(textGraphics);
        }
    }

    /**
     * Destaca a posição do alvo na arena.
     *
     * @param textGraphics O objeto responsável por desenhar no ecrã.
     * @param position A posição do alvo.
     */
    private void drawTargetSelection(TextGraphics textGraphics, Position position) {
        textGraphics.setForegroundColor(TextColor.ANSI.YELLOW_BRIGHT);
        textGraphics.setCharacter(new TerminalPosition(position.getX(), position.getY()), 'T');
    }

    /**
     * Desenha informações detalhadas sobre o lutador ativo e o alvo na área lateral do ecrã.
     *
     * @param textGraphics O objeto responsável por desenhar no ecrã.
     * @param fighter O lutador ativo.
     * @param target O alvo do lutador ativo.
     */
    private void drawSideInfo(TextGraphics textGraphics, Fighter fighter, Fighter target) {
        GameModel gameModel = (GameModel) super.getModel();
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);

        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(1, 2), "Active Player");
        textGraphics.disableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(1, 4), "HP: " + fighter.getHealth());
        textGraphics.putString(new TerminalPosition(1, 6), "Base Damage: " + fighter.getDamage());
        textGraphics.putString(new TerminalPosition(1, 8), "Real Damage: " + gameModel.damageCalculator(fighter, target.getPosition()));
        textGraphics.putString(new TerminalPosition(1, 10), "Base Aim: " + fighter.getAim());
        textGraphics.putString(new TerminalPosition(1, 12), "Real Aim: " + gameModel.aimCalculator(fighter, target.getPosition()));

        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(1, 16), "Target");
        textGraphics.disableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(1, 18), "HP: " + target.getHealth());
        textGraphics.putString(new TerminalPosition(1, 20), "Damage: " + target.getDamage());
        textGraphics.putString(new TerminalPosition(1, 22), "Aim: " + target.getAim());
    }
}
