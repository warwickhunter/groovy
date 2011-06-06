#!/usr/bin/env groovy
// Experimenting with a regex pattern for detecting bug references in commit comments

import java.util.regex.*;

pattern = Pattern.compile("[Bb]ugs?:?\\s*(?:(\\d+)\\D)?(?:(\\d+)\\D)?(?:(\\d+)\\D)?(?:(\\d+)\\D)?");

matcher = pattern.matcher("Bugs 1111 2222, 3333,4444 la de dah");
while (matcher.find()) {
    printf "matchCount=%d%n", matcher.groupCount()
    for (int i = 1; i < matcher.groupCount(); ++i) {
        bugNumber = matcher.group(i);
        if (bugNumber != null) {
            printf "match=\"%s\"%n", bugNumber
        }
    }
}

// Experiment with regexs to validate an acoustic model name
newChecker = /[\p{Alnum}-_\.]{1,128}/
assert ("A_b.c.d-e1" ==~ newChecker)

oldChecker = /[\p{Alnum}-_]{1,128}/
assert !"A_b.c.d-e1" ==~ oldChecker

