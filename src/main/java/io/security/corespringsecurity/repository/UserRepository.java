package io.security.corespringsecurity.repository;

import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Account, Long> {
  Account findByUsername(String username);
  int countByUsername(String username);

  default Account editByUser(Long userId, Account updatedAccount) {
    Optional<Account> existingAccountOptional = findById(userId);
    if (existingAccountOptional.isPresent()) {
      Account existingAccount = existingAccountOptional.get();

      // 수정할 내용 업데이트
      System.out.println("setFilepath" + updatedAccount.getFilepath());
      existingAccount.setUsername(updatedAccount.getUsername());
      existingAccount.setFilename(updatedAccount.getFilename());
      existingAccount.setFilepath(updatedAccount.getFilepath());
      // ... 다른 필드들도 업데이트

      // 수정된 엔터티 저장
      return save(existingAccount);
    }
    return null; // 해당 ID에 해당하는 게시물이 없을 경우
  }


}