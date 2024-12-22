package Game.view;

import Game.model.VictoryModel;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

// Classe responsável por renderizar o estado de vitória no ecrã
public class VictoryViewer extends Viewer {

    /**
     * Construtor que inicializa o visualizador de vitória com o modelo de vitória.
     *
     * @param victoryModel O modelo que contém os dados e mensagens de vitória.
     */
    public VictoryViewer(VictoryModel victoryModel) {
        super(victoryModel);
    }

    /**
     * Método responsável por desenhar o ecrã de vitória.
     * Exibe a mensagem de vitória ao centro do ecrã com estilos visuais específicos.
     *
     * @param screen O ecrã onde os elementos serão desenhados.
     * @throws IOException Caso ocorra um erro ao renderizar o ecrã.
     */
    @Override
    public void draw(Screen screen) throws IOException {
        VictoryModel victoryModel = (VictoryModel) super.getModel(); // Obtém o modelo associado
        screen.clear(); // Limpa o ecrã antes de desenhar

        // Configura as propriedades de estilo do texto
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK); // Define o fundo como preto
        textGraphics.setForegroundColor(TextColor.ANSI.BLUE_BRIGHT); // Define o texto como azul brilhante
        textGraphics.enableModifiers(SGR.BOLD, SGR.BORDERED); // Aplica negrito e bordas ao texto

        // Calcula a posição central no ecrã para a mensagem de vitória
        TerminalPosition position = new TerminalPosition(
            (screen.getTerminalSize().getColumns() / 2) - (victoryModel.getVictoryMsg().length() / 2),
            screen.getTerminalSize().getRows() / 2
        );

        // Coloca a mensagem de vitória no ecrã
        textGraphics.putString(position, victoryModel.getVictoryMsg());

        screen.refresh(); // Atualiza o ecrã para exibir os elementos desenhados
    }
}