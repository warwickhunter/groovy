/**
 * Guess the name of a baby boy from a list. We have been told the baby's name
 * is between 3 and 9 letters with two vowels.
 *
 * @author Warwick Hunter
 * @since  2013-11-21
 */
names = new File("names_unique.txt")

boolean verbose = false
int matchCount = 0
int rejectCount = 0

String vowels = "[aeiou]+"

names.eachLine { line ->
    name = line.trim()
    if (name.length() < 3 || name.length() > 9) {
        if (verbose) printf "Reject %s%n", name
        rejectCount++
        return
    }
    String[] parts = name.split(vowels)
    if (parts.length > 0) {
        StringBuilder nonVowels = new StringBuilder()
        for (String p : parts) {
            nonVowels.append(p)
        }
        int numVowels = name.length() - nonVowels.toString().length()
        if (numVowels > 1) {
            if (verbose) {
                printf "Accept %s%n", name
            } else {
                println name
            }
            matchCount++
        } else {
            if (verbose) printf "Reject %s%n", name
            rejectCount++
        }    
    }
}

printf "Matches: %d%n", matchCount
printf "Rejects: %d%n", rejectCount
