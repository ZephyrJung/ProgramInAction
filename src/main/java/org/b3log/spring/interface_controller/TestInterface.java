package org.b3log.spring.interface_controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/spring")
public interface TestInterface {
    @GetMapping("/say/hello")
    String sayHello();
}