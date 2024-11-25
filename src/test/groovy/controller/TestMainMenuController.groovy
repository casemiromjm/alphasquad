package controller

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.screen.Screen
import spock.lang.Specification

class TestMainMenuController extends Specification{

    def "Test only help"(){
        given:
        def screen = Stub(Screen){
            readInput() >> new KeyStroke((Character) 'q', false, false)
        }

        def helpscreen = Mock(HelpScreenController)
        def gamecontroller = Mock(GameController)

        MainMenuController mainMenuController = new MainMenuController(screen, gamecontroller, helpscreen)

        when:
        mainMenuController.run()

        then:
        0 * helpscreen.run()
        0 * gamecontroller.run()
    }

    def "Test all options"(){

        given:
        def screen = Stub(Screen){
                readInput() >>> [new KeyStroke((Character) 'h', false, false),
                                 new KeyStroke((Character) 'p', false, false),
                                 new KeyStroke((Character) 'q', false, false)]
        }

        def helpscreen = Mock(HelpScreenController)
        def gamecontroller = Mock(GameController)

        MainMenuController mainMenuController = new MainMenuController(screen, gamecontroller, helpscreen)

        when:
        mainMenuController.run()

        then:
        1 * helpscreen.run()
        1 * gamecontroller.run()
    }

    def "Test only help"(){
        given:
        def screen = Stub(Screen){
            readInput() >>> [new KeyStroke((Character) 'h', false, false),
                             new KeyStroke((Character) 'q', false, false)]
        }

        def helpscreen = Mock(HelpScreenController)
        def gamecontroller = Mock(GameController)

        MainMenuController mainMenuController = new MainMenuController(screen, gamecontroller, helpscreen)

        when:
        mainMenuController.run()

        then:
        1 * helpscreen.run()
        0 * gamecontroller.run()
    }

    def "Test only play"(){
        given:
        def screen = Stub(Screen){
            readInput() >>> [new KeyStroke((Character) 'p', false, false),
                             new KeyStroke((Character) 'q', false, false)]
        }

        def helpscreen = Mock(HelpScreenController)
        def gamecontroller = Mock(GameController)

        MainMenuController mainMenuController = new MainMenuController(screen, gamecontroller, helpscreen)

        when:
        mainMenuController.run()

        then:
        0 * helpscreen.run()
        1 * gamecontroller.run()
    }
}
