package br.com.apirestfull.apigateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class GreetingController {

    private static final String TEMPLATE = "Olá, %s!";
    private final AtomicInteger atomicInteger = new AtomicInteger();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String nome){
        return new Greeting(String.format(TEMPLATE, nome), atomicInteger.incrementAndGet());
    }

}
