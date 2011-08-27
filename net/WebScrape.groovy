#!/usr/bin/env groovy
package net
/**
 * Groovy sample from "Groovy in Action" book. Scraping a web site.
 *
 * @author Warwick Hunter
 * @since  2011-08-27
 */

@Grab(group='net.sourceforge.nekohtml', module='nekohtml', version='[1.9.,)')
import org.cyberneko.html.parsers.SAXParser

def url = "http://www.smh.com.au"

def html = new XmlSlurper(new SAXParser()).parse(url)

def bolded = html.'**'.findAll { it.name() == 'B' }
bolded.each{ println "bold $it" }

def divs = html.'**'.findAll { it.name() == 'DIV' }
divs.each{ println "div $it" }
