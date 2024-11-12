package com.example.Member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/save")
	public String getForm()
	{
		return "save";
	}
	
	@PostMapping("/save")
	public String add(@ModelAttribute Member m, Model model)
	{
		String result = memberService.saveMember(m);
		model.addAttribute("msg", result);
		
		return "redirect:/list";
	}
	
	@GetMapping("/list")
	public String list(Model model)
	{
		List<Member> members = memberService.getMembers();
		model.addAttribute("members",members);
		return "list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Long id, Model model)
	{
		model.addAttribute("member", memberService.getMemberById(id));		
		return "detail";
	}
	
	@PostMapping("/update")
	public String updateMember(@ModelAttribute Member m, Model model)	
	{
		String result = memberService.updateMember(m);
		model.addAttribute("msg", result);
		return "redirect:/list";
	}
	
	
	@GetMapping("/delete")
	public String deleteMember(@RequestParam("id") Long id, Model model)
	{
		String result = memberService.deleteMember(id);
		model.addAttribute("msg", result);
		return "redirect:/list";
	}
	
}
