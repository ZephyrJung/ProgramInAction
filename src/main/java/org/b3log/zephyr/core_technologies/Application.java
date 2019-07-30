package org.b3log.zephyr.core_technologies;

import org.b3log.zephyr.core_technologies._3_7_Spring_Validation.MyService;
import org.b3log.zephyr.core_technologies._3_7_Spring_Validation.PersonForm;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;


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
        validate(context);
    }

    public static void validate(ApplicationContext context) {
        PersonForm personForm = new PersonForm();
        personForm.setAge(-1);
        personForm.setName("Zephyr");
        MyService service = context.getBean(MyService.class);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        service.hiberValidate(personForm,validatorFactory.getValidator());
    }
}