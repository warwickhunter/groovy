#!/usr/bin/env groovy
/**
 * Find any audio files that are longer than 15 seconds.
 *
 * @author Warwick Hunter 
 * @since  2015-08-31
 */
import javax.sound.sampled.*

def MAX_LENGTH_SECONDS = 10.0

new File(args[0]).eachFileRecurse { file ->
    if (!file.directory && file.name =~ "wav") {
        AudioFileFormat aff = AudioSystem.getAudioFileFormat(file)
        float lengthInSeconds = aff.frameLength / aff.format.frameRate
        if (lengthInSeconds > MAX_LENGTH_SECONDS) {
            printf "%f %s %n", lengthInSeconds, file.absolutePath 
        }
    }
}
