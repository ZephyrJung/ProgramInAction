package org.b3log.pattern;

import com.google.common.collect.Lists;
import org.b3log.pattern.command.AttackCommand;
import org.b3log.pattern.command.Command;
import org.b3log.pattern.command.MoveCommand;
import org.b3log.pattern.command.model.AttackEnum;
import org.b3log.pattern.command.model.MoveEnum;
import org.b3log.pattern.observer.NormalBattleGround;
import org.b3log.pattern.strategy.HeroFactory;
import org.b3log.pattern.strategy.Player;
import org.b3log.pattern.strategy.strategies.HeroEnum;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Zhang Yu
 * Date: 18年3月2日
 * Email: 2895205695@qq.com
 */
@Configuration
@ComponentScan
public class Application {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(Application.class);
        NormalBattleGround battleGround = context.getBean(NormalBattleGround.class);

        Player player1 = initPlayer(context, HeroEnum.FASHI);
        Player player2 = initPlayer(context, HeroEnum.SHESHOU);

        battleGround.init(Lists.newArrayList(player1, player2));

        player1.getHeroProxy().speak();
        player2.getHeroProxy().speak();
        List<Command> commandList = Lists.newArrayList(
                new AttackCommand(AttackEnum.NORMAL_ATTACK, player1),
                new AttackCommand(AttackEnum.FINAL_SKILL, player1),
                new MoveCommand(MoveEnum.MOVE_UP, player2),
                new MoveCommand(MoveEnum.MOVE_LEFT, player1),
                new AttackCommand(AttackEnum.NORMAL_ATTACK, player2),
                new AttackCommand(AttackEnum.NORMAL_ATTACK, player2),
                new AttackCommand(AttackEnum.NORMAL_ATTACK, player2),
                new AttackCommand(AttackEnum.NORMAL_ATTACK, player2),
                new AttackCommand(AttackEnum.FIRST_SKILL, player2),
                new AttackCommand(AttackEnum.NORMAL_ATTACK, player1),
                new AttackCommand(AttackEnum.NORMAL_ATTACK, player1),
                new AttackCommand(AttackEnum.NORMAL_ATTACK, player1),
                new AttackCommand(AttackEnum.NORMAL_ATTACK, player1),
                new AttackCommand(AttackEnum.SECOND_SKILL, player2),
                new AttackCommand(AttackEnum.NORMAL_ATTACK, player1),
                new AttackCommand(AttackEnum.FINAL_SKILL, player2),
                new AttackCommand(AttackEnum.NORMAL_ATTACK, player1),
                new AttackCommand(AttackEnum.NORMAL_ATTACK, player1),
                new AttackCommand(AttackEnum.NORMAL_ATTACK, player2),
                new AttackCommand(AttackEnum.NORMAL_ATTACK, player2),
                new AttackCommand(AttackEnum.FIRST_SKILL, player2),
                new AttackCommand(AttackEnum.SECOND_SKILL, player1),
                new AttackCommand(AttackEnum.GO_BACK_HOME, player1),
                new AttackCommand(AttackEnum.GO_BACK_HOME, player2),
                new MoveCommand(MoveEnum.MOVE_DOWN, player1));

        battleGround.playing(commandList);
    }

    public static Player initPlayer(ApplicationContext context, HeroEnum heroEnum) {
        HeroFactory heroFactory = context.getBean(HeroFactory.class);
        Player player = new Player();
        player.setId("test");
        player.setName(heroEnum.name());
        player.setHeroProxy(heroFactory.getHero(heroEnum));
//        player.setHero(heroFactory.getHero(HeroEnum.CIKE));
//        player.setHero(heroFactory.getHero(HeroEnum.SHESHOU));
        return player;
    }


}