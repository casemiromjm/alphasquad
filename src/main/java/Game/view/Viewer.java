package Game.view;

import Game.model.Model;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

// Classe abstrata que representa um visualizador genérico no jogo
public abstract class Viewer {
    private final Model model; // Modelo associado ao visualizador

    /**
     * Construtor que inicializa o visualizador com o modelo especificado.
     *
     * @param model O modelo que contém os dados e a lógica a serem exibidos pelo visualizador.
     */
    public Viewer(Model model) {
        this.model = model;
    }

    /**
     * Método que retorna o modelo associado ao visualizador.
     *
     * @return O modelo do visualizador.
     */
    public Model getModel() {
        return model;
    }

    /**
     * Método abstrato que desenha os elementos no ecrã.
     * Este método deve ser implementado pelas subclasses para renderizar os dados do modelo no ecrã.
     *
     * @param screen O ecrã onde os elementos serão desenhados.
     * @throws IOException Caso ocorra um erro ao desenhar no ecrã.
     */
    public abstract void draw(Screen screen) throws IOException;
}
