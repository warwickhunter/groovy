
void doFile(File inFile, File outFile) {
    blankLineCount = 0
    inFile.eachLine() {
       if (it ==~ "^\$") {
           blankLineCount++
       } else {
           if (blankLineCount > 1) {
               outFile.append("\n")
           }
           blankLineCount = 0
           outFile.append(it)
           outFile.append("\n")
       }
   }
}

new File('solution/source').eachFileRecurse { 
    if (it.directory || it.absolutePath.endsWith("txt")) {
        return;
    }
    outFile = new File("/tmp", it.name)
    printf "%s -> %s%n", it.name, outFile.absolutePath
    doFile(it, outFile)
}
