#!/usr/bin/env groovy
package net
/**
 * Groovy sample from "Groovy in Action" book. Scraping a web site.
 *
 * @author Warwick Hunter
 * @since  2011-08-27
 */
import java.util.regex.*;

@Grab(group='net.sourceforge.nekohtml', module='nekohtml', version='[1.9.,)')
import org.cyberneko.html.parsers.SAXParser

@Grab(group='httpunit', module='httpunit', version='[1.7.,)')
import com.meterware.httpunit.*

@Grab(group='rhino', module='js', version='[1.,)')
import org.mozilla.javascript.Scriptable

def url = "https://www.mlc.com.au/masterkeyWeb/execute/FramesetUnitPrices"

HttpUnitOptions.scriptingEnabled = false
def client = new WebConversation()
def response = client.getResponse(url)
def frame = client.getFrameContents("selection")

pattern = Pattern.compile(/"MLC Platinum Global Fund \(closed\),MasterKey Unit Trust,([^,]+),([^,]+),/)
matcher = pattern.matcher(frame.text)
if (matcher.find() && matcher.groupCount() > 1) {
    printf '%s $%s %n', matcher.group(1), matcher.group(2)    
}

return 0
