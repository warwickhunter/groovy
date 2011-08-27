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

@Grab(group='httpunit', module='httpunit', version='[1.7.,)')
import com.meterware.httpunit.*

@Grab(group='rhino', module='js', version='[1.,)')
import org.mozilla.javascript.Scriptable

def url = "http://www.smh.com.au"

//
// Scrape using NekoHTML
//
def html = new XmlSlurper(new SAXParser()).parse(url)

def bolded = html.'**'.findAll { it.name() == 'B' }
bolded.each{ println "bold $it" }

def divs = html.'**'.findAll { it.name() == 'DIV' }
divs.each{ println "div $it" }

//
// Scrape using HttpUnit
//
HttpUnitOptions.scriptingEnabled = false
def client = new WebConversation()
def response = client.getResponse(url)
response.images.each { image ->
    if (image.link && image.link.URLString) {
        println "image $image.link.URLString"
    } 
}
