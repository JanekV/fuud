package service;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import parsers.BitStopParser;

@RestController
public class ResponseController {

    /*private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/getfuud")
    public Greeting getfuud(@RequestParam(value="name", defaultValue="World") String name, @RequestParam(value="age", defaultValue="10") String age) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name), age);
    }*/

   @RequestMapping("/bitstop")
    public foodData bitstop() {
       try {
           return new foodData(BitStopParser.getBitStopDataList());
       } catch (IOException e) {
           e.printStackTrace();
           return null;
       }
   }
}
