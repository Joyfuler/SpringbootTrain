package com.example.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/list")
    public String allList(Model model){
        model.addAttribute("members", memberService.allList());
        return "memberList";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable ("id") int id, Model model){
        model.addAttribute("member", memberService.getMemberInfo(id));
        return "detail";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Member member, Model model){
        model.addAttribute("updateResult", memberService.update(member));
        return "redirect:/detail/"+member.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model){
        model.addAttribute("deleteResult", memberService.delete(id));
        return "redirect:/list";
    }

}
