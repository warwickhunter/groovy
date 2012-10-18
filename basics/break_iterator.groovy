/**
 * An experiment with java.text.BreakIterator 
 *
 * @author Warwick Hunter
 * @since  2012-10-18
 */
import java.text.*;

source = "Warwick's house is blue"

BreakIterator it = BreakIterator.getWordInstance();
it.setText(source)

int start = it.first();
for (int end = it.next(); end != BreakIterator.DONE; ) {
    println source.substring(start, end)
    start = end
    end = it.next()
}