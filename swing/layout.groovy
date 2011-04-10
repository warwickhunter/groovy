#!/usr/bin/env groovy
/**
 * Swing example from "Groovy in Action"
 *
 * @author Warwick Hunter
 * @since  2011-04-10
 */
import groovy.swing.SwingBuilder
import java.awt.BorderLayout as BL
import javax.swing.*

swing = new SwingBuilder()

printAction = swing.action(name:'Print', closure: {
    println swing.mytext.text
})

frame = swing.frame(title:'Layout Demo') {
    panel {
        vbox {
            panel(layout: new BL()) {
                button(constraints: BL.NORTH,  'North')
                button(constraints: BL.CENTER, 'Centre')
                button(constraints: BL.SOUTH,  'South')
                button(constraints: BL.EAST,   'East')
                button(constraints: BL.WEST,   'West')
            }
            vstrut()
            panel {
                textField(id:'mytext', columns:10) // Show the use of addressing by id
                button(action: printAction)
            }
            vstrut()
            panel {
                tableLayout {
                    tr {
                        td { button 'One' }
                        td { button 'Two' }
                    }
                    tr {
                        td(colspan:2, colfill:true) { button(action:printAction) }
                    }
                }
            }
        }
    }
}
frame.pack()
frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
frame.show()
