package com.um.premium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class LottoController {

    @GetMapping("/lotto")
    public String lotto(Model model) {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            numbers.add(random.nextInt(45) + 1);
        }
        model.addAttribute("numbers", numbers);
        return "main/lotto";
    }
}
