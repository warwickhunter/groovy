#!/usr/bin/env groovy
/**
 * Scan Android strings.xml files looking for duplicated string resources.
 * 
 * @author Warwick Hunter
 * @since  2011-05-21
 */
files = ["strings.xml"]
if (args.length > 0) {
    files = args
}
for (file in files) {
    def stringResources = [:] // empty map
    def stringsFile = new XmlParser().parse(new File(file))
    int count = 0
    int duplicates = 0
    for (string in stringsFile) {
        if (string.name() == 'string') {
            count++
            str = new StringBuilder()
            for (stringValue in string) {
                str.append(stringValue)
            }
            if (stringResources.containsKey(string.@name)) {
                if (stringResources[string.@name] == str.toString()) {
                    printf "%s: Duplicate string resource name=%s value='%s' %n", file, string.@name, str
                    duplicates++
                } else {
                    printf "%s: Duplicate string resource name=%s value1='%s' value2='%s' %n", file, string.@name, stringResources[string.@name], str
                    duplicates++
                }
            }
            stringResources[string.@name] = str.toString()
        }
    }
    printf "%s: %d string resource(s) checked, %d duplicates %n", file, count, duplicates
}