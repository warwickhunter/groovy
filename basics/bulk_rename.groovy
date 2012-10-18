#!/usr/bin/env groovy
/**
 * Bulk renaming of files.
 */
def harmless = false
int index = 1
new File('.').eachFileRecurse { file ->
    if (file.name =~ '.eml') {
        def newName = String.format("mail_%06d.eml", index++)
        def modName = file.name.replaceAll("@", "%40")
        def cmd = String.format("p4 move '%s' %s", modName, newName)
        println cmd
        if (!harmless) {
            Runtime.runtime.exec(cmd).waitFor()
        }
    }
}