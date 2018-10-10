package org.b3log.spring.core_technologies._3_7_Spring_Validation;

import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;

/**
 * @author : yu.zhang
 * Date : 2018/10/9 下午2:46
 * Email : yu.zhang@7fresh.com
 **/
@Service
public class MyService {

    public void validate(PersonForm personForm, Validator validator) {
        DataBinder binder = new DataBinder(personForm);
        binder.setValidator(validator);
        binder.validate();
        System.out.println(binder.getBindingResult());
    }
}
