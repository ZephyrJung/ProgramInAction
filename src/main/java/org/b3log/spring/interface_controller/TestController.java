package org.b3log.spring.interface_controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController implements TestInterface {
    public String sayHello() {
        return "Hello world";
    }
}
