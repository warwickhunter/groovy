#!/usr/bin/env groovy
// Experimenting with a regex pattern to extract an SMS recipient and message body

import java.util.regex.*;

pattern = Pattern.compile("((?:[\\w]+[\\W]*){2})(([\\w]+[\\W]*)+)");

matcher = pattern.matcher("tell assembled and have a beer");
while (matcher.find()) {
    printf "matchCount=%d%n", matcher.groupCount()
    for (int i = 1; i < matcher.groupCount(); ++i) {
        text = matcher.group(i);
        if (text != null) {
            printf "match[%d]=\"%s\"%n", i, text
        }
    }
}
