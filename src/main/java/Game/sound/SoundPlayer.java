package Game.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

// Classe responsável pela reprodução de sons no jogo
public class SoundPlayer {

    // Construtor vazio, inicializa uma instância do SoundPlayer
    public SoundPlayer() {
    }

    /**
     * Método que reproduz o som de "acerto" (hit).
     * 
     * @throws LineUnavailableException Caso a linha de áudio não esteja disponível.
     * @throws UnsupportedAudioFileException Caso o ficheiro de áudio não seja suportado.
     * @throws IOException Caso ocorra um erro de entrada/saída ao carregar o ficheiro de áudio.
     */
    public void hitSound() throws LineUnavailableException, UnsupportedAudioFileException, IOException {

        // Localização do ficheiro de som para "acerto"
        File soundFile = new File("src/main/resources/sounds/hit.wav");

        // Carrega o ficheiro de som como um fluxo de áudio
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

        // Obtém um objeto Clip para reproduzir o som
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);

        // Ajusta o volume do som utilizando o controlo de ganho
        FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(-12.0f); // Reduz o volume em 12 decibéis

        // Inicia a reprodução do som
        clip.start();
        clip.drain(); // Aguarda até que o som termine
        clip.close(); // Liberta recursos associados ao Clip
        audioInputStream.close(); // Fecha o fluxo de áudio
    }

    /**
     * Método que reproduz o som de "falha" (miss).
     * 
     * @throws UnsupportedAudioFileException Caso o ficheiro de áudio não seja suportado.
     * @throws IOException Caso ocorra um erro de entrada/saída ao carregar o ficheiro de áudio.
     * @throws LineUnavailableException Caso a linha de áudio não esteja disponível.
     */
    public void missSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        // Localização do ficheiro de som para "falha"
        File soundFile = new File("src/main/resources/sounds/miss.wav");

        // Carrega o ficheiro de som como um fluxo de áudio
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

        // Obtém um objeto Clip para reproduzir o som
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);

        // Ajusta o volume do som utilizando o controlo de ganho
        FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(-12.0f); // Reduz o volume em 12 decibéis

        // Inicia a reprodução do som
        clip.start();
        clip.drain(); // Aguarda até que o som termine
        clip.close(); // Liberta recursos associados ao Clip
        audioInputStream.close(); // Fecha o fluxo de áudio
    }
}
