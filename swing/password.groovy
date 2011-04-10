#!/usr/bin/env groovy
/**
 * Swing example from "Groovy in Action"
 *
 * @author Warwick Hunter
 * @since  2011-04-10
 */
import groovy.swing.SwingBuilder

swing = new SwingBuilder()
frame = swing.frame(title:'Password') {
    passwordField(columns:10, actionPerformed: { event ->
        println event.source.text
        System.exit(0)
        
    })
}
frame.pack()
frame.show()
