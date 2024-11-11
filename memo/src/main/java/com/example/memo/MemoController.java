package com.example.memo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemoController {

    @Autowired
    private MemoService memoService;

    //주소로 직접이동하는경우
    @GetMapping("/save")
    public String getForm(){
        return "memoForm";
        //templates 밑에 memoForm.html로 이동함.
    }

    // save를 post로 입력한 경우
    @PostMapping("/save")
    public String saveData(@ModelAttribute Memo memo, Model model){
        //getParameter 대신 @modelAttribute 어노테이션을 통해 memo 객체로 자동으로 받아옴.
        System.out.println("memo객체:" + memo);
        model.addAttribute("result", memoService.saveMemo(memo));
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("memos", memoService.allList());
        return "list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") int id, Model model){
        // 패러미터로 받은 id를 매개변수로 사용해야 할 경우 @PathVariable 사용
        model.addAttribute("memo", memoService.getMemo(id));
        return "detail";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Memo memo, Model model){
        String result = memoService.updateMemo(memo);
        model.addAttribute("result", result);
        return "redirect:/detail/"+memo.getId();
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id, Model model){
        model.addAttribute("result", memoService.deleteMemo(id));
        return "redirect:/list";
    }
}
