package alicoding

import spock.lang.Specification
import spock.lang.Unroll

class BowlingTest extends Specification {
    @Unroll
    def "test"() {
        given:
        def bowling = new Bowling();
        when:
        def list = scoreList
        list.forEach({ l -> bowling.roll(l) })
        then:
        println(bowling.getScore())
        bowling.getScore() == bowling.getScore2()
        where:
        score | scoreList
        300   | [10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10]
        1     | [5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5]
        1     | [5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 10, 5, 5]
        1     | [5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2]
        1     | [5, 2, 10, 5, 2, 5, 5, 5, 2, 5, 2, 5, 2, 10, 5, 5, 5, 2]
    }
}
