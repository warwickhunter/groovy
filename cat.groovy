def number = 0
new File('/etc/fstab').eachLine { line ->
    number++
    println "$number: $line"
}