package com.sa98077.sp_boot_board_reply_0714.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@Log4j2
public class SampleController {

    // TEST용 DTO
    class SampleDTO {

        private String p1,p2,p3;

        public String getP1() {
            return p1;
        }
        public String getP2() {
            return p2;
        }
        public String getP3() {
            return p3;
        }

        @Override
        public String toString() {
            return "SampleDTO{" +
                    "p1='" + p1 + '\'' +
                    ", p2='" + p2 + '\'' +
                    ", p3='" + p3 + '\'' +
                    '}';
        }
    }
    @Operation(summary = "hello")
    @GetMapping("/hello")
    public void hello(Model model){
        log.info("hello");
        model.addAttribute("msg","Hello Spring Boot");
    }
    @Operation(summary = "/ex/ex1")
    @GetMapping("/ex/ex1")
    public void ex1(Model model){
        log.info("ex1>>>>>>>>>>");
        List<String> list = Arrays.asList("AAA", "BBB", "CCC", "DDD");
        model.addAttribute("list",list);
    }
    @GetMapping("/ex/ex2")
    public void ex2(Model model){
        log.info("ex2>>>>>>>>>>");

        // list 추가
        List<String> strList = IntStream.range(0,10)
                .mapToObj(i->"Data"+i)
                .collect(Collectors.toList());

        model.addAttribute("list",strList);

        // Map 추가

        Map<String,String> map = new HashMap<>();

        map.put("A","AAAA");
        map.put("B","BBBB");

        model.addAttribute("map",map);

        // DTO 추가

        SampleDTO sampleDTO = new SampleDTO();

        sampleDTO.p1 = "value -- p1";
        sampleDTO.p2 = "value -- p2";
        sampleDTO.p3 = "value -- p3";

        model.addAttribute("dto",sampleDTO);
    }

    @GetMapping("/ex/ex3")
    public void ex3(Model model){
        log.info("ex3>>>>>>>>>>");
        model.addAttribute("arr",new String[]{"AAA","BBB","CCC"});
    }

}
