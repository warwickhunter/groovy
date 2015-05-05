#!/usr/bin/env groovy
/**
 * Bulk rename of text files
 */
int messageId = 1000

new File("/ws/whunter/tmp/gmail_family").eachFileRecurse { 
    newName = String.format("mail_%06d.txt", messageId++)
    printf "Rename: %s -> %s %n", it.name, newName
    if (!it.renameTo(newName)) {
        printf "Rename failed: %s -> %s %n", it.name, newName
    }
}
