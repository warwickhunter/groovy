#!/usr/bin/env groovy
/**
 * Experimenting with java.util.Scanner, which is a really useful beast.
 *
 * @author Warwick Hunter
 * @since  2011-06-09
 */
def s = new Scanner("1 2 3 4")
while (s.hasNext()) {
    printf "%06d%n", s.nextInt()
}