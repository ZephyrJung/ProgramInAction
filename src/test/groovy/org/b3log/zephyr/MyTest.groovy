package org.b3log.zephyr

import spock.lang.Specification

/**
 * @author : yu.zhang
 * Date : 2018/11/19 6:53 PM
 * Email : yu.zhang@7fresh.com
 *
 * */
class MyTest extends Specification {
    def "test"() {
        expect:
        println(name + " " + family)
        where:
        name     | family
        "Zephyr" | "Jung"
        "Yu"     | "Zhang"
    }
}
