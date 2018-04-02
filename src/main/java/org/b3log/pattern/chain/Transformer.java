package org.b3log.pattern.chain;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Zhang Yu
 * Date: 18年4月2日
 * Email: yu.zhang@7fresh.com
 */
@Service
@Data
public class Transformer {
    private Map<String,Boolean> taskState;

    public String transform(){
        return "";
    }
}
