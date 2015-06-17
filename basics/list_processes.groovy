/**
 * List the processes on a machine
 */
ProcessBuilder pb = new ProcessBuilder("/usr/bin/ps", "-e",  "-o", "pid,args", "--no-headers")
Process p = pb.start();

BufferedReader stdout = new BufferedReader(new InputStreamReader(p.getInputStream()));
for (line = stdout.readLine(); line != null; line = stdout.readLine()) {
    if (line.contains("java")) {
        println line
    }
}

BufferedReader stderr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
for (line = stderr.readLine(); line != null; line = stderr.readLine()) {
    println line
}