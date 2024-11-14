package com.example.orm.memo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MemoController {

    @Autowired
    private final MemoService memoService;

    public MemoController(MemoService memoService){
        this.memoService = memoService;
    }


    @GetMapping("list/{page}")
    public String paging(@PathVariable("page") int page,
            @PageableDefault(page=0, size=3, sort= "id", direction = Sort.Direction.ASC) Pageable pageable, Model model){
        Pageable pageable1 = PageRequest.of(page -1, pageable.getPageSize(), pageable.getSort());
        Page<Memo> p;
        model.addAttribute("paging", memoService.getMemos(pageable1));
        return "list";
    }
    // 들어오는 패러미터를 처리하지 않더라도 ?page=1을 자동으로 처리해줌.
//    @GetMapping("list")
//    public String paging(@PageableDefault(page=0, size=3, sort="id", direction = Sort.Direction.ASC) Pageable pageable, Model model){
//        model.addAttribute("paging", memoService.getMemos(pageable));
//        return "list";
//    }


    @GetMapping("detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model){
        model.addAttribute("memo", memoService.getMemoInfo(id));
        return "detail";
    }

    @GetMapping("delete")
    public String delete(@ModelAttribute Memo memo, Model model){
        memoService.deleteMemo(memo);
        return "redirect:/list";
    }


}
