package com.example.memo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoService {
    @Autowired
    private MemoRepository JdbcMemoRepository;

    public List<Memo> allList(){
        return JdbcMemoRepository.allList();
    }

    public String saveMemo(Memo memo){
        if (JdbcMemoRepository.insertMemo(memo) == 1){
            return "제대로 들어갔습니다";
        } else{
            return "제대로 안들어갔는데요?";
        }
    }
}
