package org.b3log.pattern.chain;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Zhang Yu
 * Date: 18年4月2日
 * Email: yu.zhang@7fresh.com
 */
@Service
@Data
public class Transformer implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private List<TransformerAction> transformerActions;

    @PostConstruct
    public void init() {
        Map<String, TransformerAction> transformerActionMap = applicationContext.getBeansOfType(TransformerAction.class);
        this.transformerActions = transformerActionMap.values().stream().sorted(Comparator.comparing(TransformerAction::getOrder)).collect(Collectors.toList());
    }

    public void transform() {
        for (TransformerAction action : transformerActions) {
            if (action.isDone(ActionEnum.TRANSFORM.getValue())) {
                System.out.println(action.getClass().getSimpleName() + " action => transform completed");
                continue;
            }
            action.execute(ActionEnum.TRANSFORM.getValue());
            action.done(ActionEnum.TRANSFORM.getValue());
        }
    }

    public void fly() {
        for (TransformerAction action : transformerActions) {
            if (action.isDone(ActionEnum.FLY.getValue())) {
                System.out.println(action.getClass().getSimpleName() + " action => fly completed");
                continue;
            }
            action.execute(ActionEnum.FLY.getValue());
            action.done(ActionEnum.FLY.getValue());
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
