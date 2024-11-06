package com.example.memo;

import org.springframework.stereotype.Repository;

import java.util.List;


public interface MemoRepository {
    public List<Memo> allList();
    public int insertMemo(Memo memo);
    public int updateMemo(Memo memo);
    public int deleteMemo(int idx);
    public Memo findByIdx(int idx);
}
