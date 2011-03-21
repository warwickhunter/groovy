import java.util.regex.*;

pattern = Pattern.compile("[Bb]ugs?:?\\s*(?:(\\d+)\\D)?(?:(\\d+)\\D)?(?:(\\d+)\\D)?(?:(\\d+)\\D)?");

matcher = pattern.matcher("Bugs 1111 2222, 3333,4444 la de dah");
while (matcher.find())
{
    System.out.printf("matchCount=%d%n", matcher.groupCount());
    for (int i = 1; i < matcher.groupCount(); ++i)
    {
        bugNumber = matcher.group(i);
        if (bugNumber != null)
        {
            System.out.printf("match=\"%s\"%n", bugNumber);
        }
    }
}