import java.net.*

InetSocketAddress socketAddress = new InetSocketAddress(InetAddress.getByName("239.255.255.250"), 1900)
MulticastSocket socket = new MulticastSocket(null)
try {
    socket.bind(new InetSocketAddress("0.0.0.0", 1901))
    socket.setSoTimeout(1000)
    StringBuilder packet = new StringBuilder()
    packet.append("M-SEARCH * HTTP/1.1\n" )
    packet.append("HOST: 239.255.255.250:1900\n" )
    packet.append("MAN: \"ssdp:discover\"\n" )
    packet.append("ST: roku:ecp\n\n")
    byte[] data = packet.toString().bytes
    println "send"
    socket.send(new DatagramPacket(data, data.length, socketAddress))

    for (int i = 0; i < 2; ++i) {
        byte[] buf = new byte[2048]
        DatagramPacket input = new DatagramPacket(buf, buf.length)
        try {
            println "receive"
            socket.receive(input)
            String originaldata = new String(input.data)
            println originaldata
            break
        } catch (SocketTimeoutException e) {
            e.printStackTrace()
        }
    }

} catch (IOException e) {
    e.printStackTrace()
} finally {
    socket.disconnect()
    socket.close()
}


return 0