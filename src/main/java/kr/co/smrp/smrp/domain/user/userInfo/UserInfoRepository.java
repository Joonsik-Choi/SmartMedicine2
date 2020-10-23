package kr.co.smrp.smrp.domain.user.userInfo;
// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    Optional<UserInfo> findByUserIdAndUserPw(String userId, String passWord);
    Optional<UserInfo> findByUserId(String findUserId);
    Optional<UserInfo> findByUserIdAndEmail(String userId, String email);
}