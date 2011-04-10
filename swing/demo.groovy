#!/usr/bin/env groovy
/**
 * Swing example from "Groovy in Action"
 *
 * @author Warwick Hunter
 * @since  2011-04-10
 */
import groovy.swing.SwingBuilder
import javax.swing.*
import java.awt.event.*

swing = new SwingBuilder()
frame = swing.frame(title:'Demo') {
    menuBar {
        menu('File') {
            menuItem(label:'New', actionPerformed: { println "New file" })
            menuItem(label:'Open', actionPerformed: { println "Open file" })
        }
    }
    panel {
        label 'Label 1'
        slider(stateChanged:{ println "Slide $it.source.value" })
        comboBox(items:['one','two','three'], itemStateChanged:{ event ->
            if (event.stateChange == ItemEvent.SELECTED) {
                println "$event.source.selectedItem"
            } 
        })
    }
}
frame.pack()
frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
frame.show()
