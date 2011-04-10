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
                borderLayout() // A different way to attach a border layout
                button(constraints: BL.NORTH,  'N')
                button(constraints: BL.CENTER, 'C')
                button(constraints: BL.SOUTH,  'S')
                button(constraints: BL.EAST,   'E')
                button(constraints: BL.WEST,   'W')
            }
            vstrut()
            panel {
                tableLayout {
                    tr {
                        td { button 'One' }
                        td { button 'Two' }
                    }
                    tr {
                        td(colspan:2, colfill:true) { button 'Three' }
                    }
                }
            }
        }
    }
}
frame.pack()
frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
frame.show()
