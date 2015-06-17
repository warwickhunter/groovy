process = Runtime.runtime.exec("sleep 10")

for (int i = 0; i < 5; ++i) {
    println "waiting"
    Thread.sleep(1)
}

println "waitFor"
process.waitFor()
println "finished"