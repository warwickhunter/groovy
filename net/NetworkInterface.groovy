// Experiment with java.net.NetworkInterface to see what it provides

import java.net.*

java.net.NetworkInterface.getNetworkInterfaces().each { intf ->
    if (!intf.isLoopback() && !intf.isVirtual()) {
        printf "%s / %s %n", intf.displayName, intf.name
        intf.inetAddresses.each { printf "    %s%n", it }
    }
}

return 0