package com.example.orm.memo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@org.springframework.stereotype.Service
public class MemoService {
    // 하던대로구현
    @Autowired
    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public Page<Memo> getMemos(Pageable pageable){
        return memoRepository.findAll(pageable);
        // 페이징 처리가 된 쿼리
    }

    public Optional<Memo> getMemoInfo(Long idx){
        return memoRepository.findById(idx);
    }

    public void deleteMemo(Memo memo){
        memoRepository.delete(memo);
    }

}
