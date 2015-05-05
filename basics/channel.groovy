import java.nio.*
import java.nio.channels.*

String toString(ByteBuffer b) {
    return String.format("pos=%d rem=%d lim=%d cap=%d", b.position(), b.remaining(), b.limit(), b.capacity());
}

ByteBuffer utt = ByteBuffer.allocate(640000)
for (int i = 0; i < 93660; ++i) {
    utt.put((byte)42)
}
println "utt " + toString(utt)
utt.flip()
println "utt " + toString(utt)

ReadableByteChannel inChannel = Channels.newChannel(new ByteArrayInputStream(utt.array(), utt.position(), utt.remaining()))
WritableByteChannel outChannel = Channels.newChannel(new FileOutputStream("/tmp/wasa.txt"))

int byteCount = 0
ByteBuffer buffer = ByteBuffer.allocate(4096)

int n = 0;
int count = 0;
for (n = inChannel.read(buffer); (n >= 0 || buffer.position() != 0); n = inChannel.read(buffer)) {
    byteCount += n
    printf "%d bytes read, total %d, %s %n", n, byteCount, toString(buffer)
    buffer.flip()
    int w = outChannel.write(buffer)
    buffer.compact()
    //printf "%d bytes written, pos=%d rem=%d%n", w, buffer.position(), buffer.remaining()
    if (++count > 50) {
        printf "safety brake %d%n", count
        break
    }
}
printf "%d bytes read, total %d, %s %n", n, byteCount, toString(buffer)
