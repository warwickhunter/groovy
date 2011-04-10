#!/usr/bin/env groovy
/**
 * Swing table example from "Groovy in Action"
 *
 * @author Warwick Hunter
 * @since  2011-04-10
 */
import groovy.swing.SwingBuilder
import javax.swing.*

data = [
    [nick:'Captain Slow',     full:'James May'],
    [nick:'Insufferable Oaf', full:'Jeremy Clarkson'],
    [nick:'Hamster',          full:'Richard Hammond'],
]

swing = new SwingBuilder()

frame = swing.frame(title:'Table Demo') {
    panel {
        scrollPane {
            table() {
                tableModel(list:data) {
                    propertyColumn(header:'Nickname', propertyName:'nick')
                    propertyColumn(header:'Full Name', propertyName:'full')
                    closureColumn(header:'First Name', read:{it.full.tokenize()[0]})
                }
            }
        }
    }
}
frame.pack()
frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
frame.show()
