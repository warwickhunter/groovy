#!/usr/bin/env groovy
/**
 * Bulk renaming of files.
 */
def harmless = false
new File('.').eachFileRecurse { file ->
    if (file.name =~ 'HRA') {
        def newName = file.name.replaceAll("HRA", "DA")
        def cmd = String.format("p4 move %s %s", file.name, newName)
        println cmd
        if (!harmless) {
            Runtime.runtime.exec(cmd).waitFor()
        }
    }
}