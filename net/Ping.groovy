/**
 * Ping subnets on my home network
 */
def subnets = ["192.168.1","192.168.2","10.0.0"]

subnets.each { net ->
    def cmd = String.format("ping -b -n -c 2 %s.255", net)
    println cmd
    println cmd.execute().text
}

return 0