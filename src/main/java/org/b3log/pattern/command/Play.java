package org.b3log.pattern.command;

import com.google.common.collect.Lists;
import org.b3log.pattern.command.model.AttackEnum;
import org.b3log.pattern.command.model.MoveEnum;

import java.util.List;

/**
 * @author : yu.zhang
 * Date : 2018/10/1 上午10:29
 * Email : yu.zhang@7fresh.com
 **/
public class Play {
    public static void main(String[] args) {
        List<Command> commandList = Lists.newArrayList(
                new MoveCommand(MoveEnum.MOVE_DOWN),
                new AttackCommand(AttackEnum.NORMAL_ATTACK),
                new MoveCommand(MoveEnum.MOVE_RIGHT),
                new AttackCommand(AttackEnum.FINAL_SKILL),
                new MoveCommand(MoveEnum.MOVE_UP)
        );

        commandList.forEach(Command::execute);
    }
}
