package controller

import com.googlecode.lanterna.input.KeyType
import spock.lang.Specification

class TestMainMenuController extends Specification{
    def screen = Mock(screen)

    def "Test options"(){
        given:

        screen.readInput() >>> [com.googlecode.lanterna.input.KeyStroke(KeyType.Character, 'h'),
        com.googlecode.lanterna.input.KeyStroke(KeyType.Character, 'g'),
        com.googlecode.lanterna.input.KeyStroke(KeyType.Character, 'q')]

        def helpscreen = Mock(HelpScreenController)
        def gamecontroller = Mock(GameController)

        MainMenuController mainMenuController = new MainMenuController(screen, gamecontroller, helpscreen)

        when:
        mainMenuController.run()

        then:
        1 * helpscreen.run()
        1 * gamecontroller.run()
        2 * screen.clear()
        2 * screen.refresh()
    }
}
