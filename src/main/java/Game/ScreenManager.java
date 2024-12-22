package Game;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

// Classe responsável por gerir o ecrã do jogo utilizando a biblioteca Lanterna
public class ScreenManager {

    private final Screen screen; // Instância do ecrã gerido pela Lanterna

    /**
     * Construtor que inicializa o gestor do ecrã com o tamanho e o tipo de fonte especificados.
     * 
     * @param width Largura do terminal (número de colunas).
     * @param height Altura do terminal (número de linhas).
     * @param fontPath Caminho para o ficheiro da fonte a ser utilizada.
     * @throws IOException Caso ocorra um erro ao criar o terminal ou o ecrã.
     * @throws FontFormatException Caso o ficheiro da fonte esteja num formato inválido.
     * @throws URISyntaxException Caso o caminho para a fonte seja inválido.
     */
    public ScreenManager(int width, int height, String fontPath) throws IOException, FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration cfg = useFont(fontPath); // Configuração da fonte
        Terminal terminal = createTerminal(width, height, cfg); // Criação do terminal
        screen = createScreen(terminal); // Criação do ecrã associado ao terminal
    }

    /**
     * Método privado para criar o terminal com as configurações especificadas.
     * 
     * @param width Largura do terminal (número de colunas).
     * @param height Altura do terminal (número de linhas).
     * @param cfg Configuração da fonte para o terminal.
     * @return Um objeto Terminal configurado.
     * @throws IOException Caso ocorra um erro ao criar o terminal.
     */
    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration cfg) throws IOException {
        Terminal terminal = new DefaultTerminalFactory()
                .setTerminalEmulatorFontConfiguration(cfg) // Define a fonte configurada
                .setForceAWTOverSwing(true) // Força o uso de AWT em vez de Swing
                .setInitialTerminalSize(new TerminalSize(width, height)) // Define o tamanho inicial
                .createTerminal(); // Cria o terminal
        return terminal;
    }

    /**
     * Método privado para criar o ecrã associado ao terminal.
     * 
     * @param terminal Terminal ao qual o ecrã será associado.
     * @return Um objeto Screen configurado e inicializado.
     * @throws IOException Caso ocorra um erro ao inicializar o ecrã.
     */
    private Screen createScreen(Terminal terminal) throws IOException {
        Screen screen = new TerminalScreen(terminal); // Cria um ecrã com base no terminal
        screen.setCursorPosition(null); // Remove o cursor, pois não é necessário
        screen.startScreen(); // Inicia o ecrã
        screen.doResizeIfNecessary(); // Ajusta o tamanho, caso necessário
        return screen;
    }

    /**
     * Método privado para configurar e aplicar uma fonte personalizada.
     * 
     * @param fontPath Caminho para o ficheiro da fonte a ser utilizada.
     * @return Configuração de fonte para o terminal.
     * @throws URISyntaxException Caso o caminho para o ficheiro da fonte seja inválido.
     * @throws IOException Caso ocorra um erro ao carregar o ficheiro da fonte.
     * @throws FontFormatException Caso o ficheiro da fonte esteja num formato inválido.
     */
    private AWTTerminalFontConfiguration useFont(String fontPath) throws URISyntaxException, IOException, FontFormatException {
        URL resource = getClass().getClassLoader().getResource(fontPath); // Obtém o recurso de fonte
        File fontFile = new File(resource.toURI()); // Converte o recurso num ficheiro
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile); // Carrega a fonte no formato TrueType
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font); // Regista a fonte no ambiente gráfico
        Font newfont = font.deriveFont(Font.PLAIN, 18); // Ajusta o tamanho e o estilo da fonte

        return AWTTerminalFontConfiguration.newInstance(newfont); // Retorna a configuração da fonte
    }

    /**
     * Método público para obter o ecrã configurado.
     * 
     * @return O objeto Screen associado ao gestor.
     */
    public Screen getScreen() {
        return screen;
    }

    /**
     * Método público para fechar o ecrã e libertar os recursos associados.
     * 
     * @throws IOException Caso ocorra um erro ao fechar o ecrã.
     */
    public void closeScreen() throws IOException {
        screen.close(); // Fecha o ecrã
    }
}