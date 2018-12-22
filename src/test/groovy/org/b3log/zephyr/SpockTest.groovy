package groovy.org.b3log.zephyr


import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author : yu.zhang
 * Date : 2018/11/19 6:53 PM
 * Email : zephyrjung@126.com
 *
 * */
class SpockTest extends Specification {
    @Unroll("#name #family")
    def "test"() {
        expect:
        family + " " + name == fullName
        10.0+2.5-3.6 == 2.8 + 1.2
        where:
        name  | family || fullName
        "Bai" | "Li"   || "Li Bai"
        "Ban" | "Lu"   || "Lu Ban"
        "Fu"  | "Du"   || "Da Fu"
    }
}
