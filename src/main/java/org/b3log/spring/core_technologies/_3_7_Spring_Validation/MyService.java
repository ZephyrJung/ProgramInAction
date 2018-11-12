package org.b3log.spring.core_technologies._3_7_Spring_Validation;

import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import java.util.Set;

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

    public void hiberValidate(PersonForm personForm, javax.validation.Validator validator){
        Set<ConstraintViolation<PersonForm>> result =  validator.validate(personForm);
        result.forEach(r-> System.out.println(r.getMessage()));
    }
}
