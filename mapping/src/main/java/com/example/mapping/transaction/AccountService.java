package com.example.mapping.transaction;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class AccountService {
    // Transaction 처리는 service에서 진행!
    private final AccountRepository accountRepository;
    // RequiredArgs를 넣으면 final에 대신 생성자를 넣어준다.

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String transfer(TransferForm transferForm){
        String message = "송금실패";

        // 보내는 사람 account를 가져와
        Optional<Account> senderAccount = accountRepository.findById(transferForm.getSender());
        Optional<Account> receiverAccount = accountRepository.findById(transferForm.getReceiver());

        if (!senderAccount.isEmpty() && !receiverAccount.isEmpty()){
            Account sender = senderAccount.get();
            Account receiver = receiverAccount.get();

            sender.setBalance(sender.getBalance().subtract(transferForm.getBalance()));
            accountRepository.save(sender);
            // 송금한 사람의 금액을 빼고, 그 데이터를 저장한다.

            receiver.setBalance(receiver.getBalance().add(transferForm.getBalance()));
            accountRepository.save(receiver);
            // 송금받은 사람의 금액에 더하고, 그 데이터를 저장한다.

            message = "이체가 완료되었습니다.";

        }
        return message;
    }
}
