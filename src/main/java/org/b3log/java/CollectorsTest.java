package org.b3log.java;

import org.b3log.pattern.proxy.Player;
import org.b3log.utils.Printer;
import org.b3log.utils.TestUtils;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author : yu.zhang
 * Date : 2018/9/18 下午2:44
 * Email : yu.zhang@7fresh.com
 **/
public class CollectorsTest {
    public static void main(String[] args) {
        List<Player> playerList = TestUtils.randomPlayers();
        Printer.printCollection(playerList.stream().map(p -> (Object) p).collect(Collectors.toList()));

        List<String> playerIds = playerList.stream().map(Player::getId).collect(Collectors.toList());
        Printer.printCollection(playerIds.stream().map(p -> (Object) p).collect(Collectors.toList()));

        Set<String> idSet = playerList.stream().map(Player::getId).collect(Collectors.toCollection(TreeSet::new));
        Printer.printCollection(idSet.stream().map(p->(Object)p).collect(Collectors.toList()));

        String joined = playerList.stream().map(Player::getId).collect(Collectors.joining(","));
        System.out.println(joined);

//        Map<Hero, List<Player>> heroMap = playerList.stream().collect(Collectors.groupingBy(Player::getHero));
//        Map<Hero, Long> heroCount = playerList.stream().collect(Collectors.groupingBy(Player::getHero, Collectors.counting()));
//        Map<Boolean, List<Player>> passingFailing = playerList.stream().collect(Collectors.partitioningBy(p -> p.getMoney() >= 50));
    }
}
