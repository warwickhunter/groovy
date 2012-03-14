#!/usr/bin/env groovy
package net
/**
 * Scrape some information from a web site.
 *
 * @author Warwick Hunter
 * @since  2012-03-14
 */
import java.util.regex.*;

@Grab(group='httpunit', module='httpunit', version='[1.7.,)')
import com.meterware.httpunit.*

@Grab(group='rhino', module='js', version='[1.,)')
import org.mozilla.javascript.Scriptable

url = "https://www.mlc.com.au/masterkeyWeb/execute/FramesetUnitPrices"

HttpUnitOptions.scriptingEnabled = false
client = new WebConversation()
response = client.getResponse(url)
frame = client.getFrameContents("selection")

pattern = Pattern.compile(/"MLC Platinum Global Fund \(closed\),MasterKey Unit Trust,([^,]+),([^,]+),/)
matcher = pattern.matcher(frame.text)
if (matcher.find() && matcher.groupCount() > 1) {
    printf '%s $%s %n', matcher.group(1), matcher.group(2)    
}

return 0
