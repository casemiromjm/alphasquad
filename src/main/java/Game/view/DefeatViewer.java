package Game.view;

import Game.model.DefeatModel;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

// Classe responsável por renderizar o ecrã de derrota no jogo
public class DefeatViewer extends Viewer {

    /**
     * Construtor que inicializa o visualizador de derrota com o modelo fornecido.
     *
     * @param defeatModel O modelo que contém os dados relacionados ao estado de derrota.
     */
    public DefeatViewer(DefeatModel defeatModel) {
        super(defeatModel);
    }

    /**
     * Método responsável por desenhar o ecrã de derrota no terminal.
     * Mostra a mensagem de derrota no centro do ecrã com estilo visual específico.
     *
     * @param screen O ecrã onde a mensagem de derrota será desenhada.
     * @throws IOException Caso ocorra um erro ao desenhar no ecrã.
     */
    @Override
    public void draw(Screen screen) throws IOException {
        // Obtém o modelo de derrota associado ao visualizador
        DefeatModel defeatModel = (DefeatModel) super.getModel();

        // Limpa o ecrã antes de desenhar
        screen.clear();

        // Configura o estilo do texto
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK); // Fundo preto
        textGraphics.setForegroundColor(TextColor.ANSI.RED_BRIGHT); // Texto vermelho brilhante
        textGraphics.enableModifiers(SGR.BOLD, SGR.BORDERED); // Estilo com negrito e bordas

        // Calcula a posição central para a mensagem de derrota
        TerminalPosition position = new TerminalPosition(
            (screen.getTerminalSize().getColumns() / 2) - (defeatModel.getDefeatMsg().length() / 2),
            screen.getTerminalSize().getRows() / 2
        );

        // Desenha a mensagem de derrota no centro do ecrã
        textGraphics.putString(position, defeatModel.getDefeatMsg());

        // Atualiza o ecrã para exibir a mensagem desenhada
        screen.refresh();
    }
}
