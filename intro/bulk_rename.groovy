#!/usr/bin/env groovy
/**
 * Bulk renaming of files.
 */
def harmless = false
new File('.').eachFileRecurse { file ->
    if (file.name =~ '.gy') {
        def newName = file.name.replaceAll('.gy','.groovy')
        def cmd = String.format("git mv %s %s", file.name, newName)
        println cmd
        if (!harmless) {
            Runtime.runtime.exec(cmd)
        }
    }
}