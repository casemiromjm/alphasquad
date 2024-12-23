package Game.view;

import Game.model.HelpScreenModel;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.List;

// Classe responsável por renderizar o ecrã de ajuda no jogo
public class HelpScreenViewer extends Viewer {

    /**
     * Construtor que inicializa o visualizador do ecrã de ajuda com o modelo fornecido.
     *
     * @param helpScreenModel O modelo que contém o texto do ecrã de ajuda.
     */
    public HelpScreenViewer(HelpScreenModel helpScreenModel) {
        super(helpScreenModel);
    }

    /**
     * Método responsável por desenhar o conteúdo do ecrã de ajuda.
     * Cada linha de texto do modelo é exibida no ecrã.
     *
     * @param screen O ecrã onde o texto será desenhado.
     * @throws IOException Caso ocorra um erro ao desenhar no ecrã.
     */
    @Override
    public void draw(Screen screen) throws IOException {
        // Obtém o modelo associado e o texto a ser exibido
        HelpScreenModel helpScreenModel = (HelpScreenModel) super.getModel();
        List<String> text = helpScreenModel.getText();

        // Limpa o ecrã antes de desenhar
        screen.clear();

        // Configura as cores de fundo e texto
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);

        // Desenha cada linha de texto no ecrã, começando na posição (1,1)
        for (int i = 0; i < text.size(); i++) {
            textGraphics.putString(new TerminalPosition(1, i + 1), text.get(i));
        }

        // Atualiza o ecrã para exibir o conteúdo desenhado
        screen.refresh();
    }
}

