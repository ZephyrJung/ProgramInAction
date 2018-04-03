package org.b3log.pattern.chain;

import com.google.common.collect.Maps;
import lombok.Data;
import org.springframework.core.Ordered;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author Zhang Yu
 * Date: 18年4月3日
 * Email: yu.zhang@7fresh.com
 */
@Data
public abstract class TransformerAction implements ITransformer,Ordered {

    protected Map<String,Boolean> stateMap;

    @PostConstruct
    public void init(){
        this.stateMap = Maps.newHashMap();
    }

    @Override
    public boolean isDone(String action) {
        return stateMap.get(action) != null && stateMap.get(action);
    }

    @Override
    public void done(String action) {
        stateMap.put(action,true);
    }
}
