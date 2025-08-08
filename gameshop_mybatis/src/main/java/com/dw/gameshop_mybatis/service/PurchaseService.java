package com.dw.gameshop_mybatis.service;

import com.dw.gameshop_mybatis.dto.PurchaseDTO;
import com.dw.gameshop_mybatis.exception.InvalidRequestException;
import com.dw.gameshop_mybatis.exception.PermissionDeniedException;
import com.dw.gameshop_mybatis.exception.ResourceNotFoundException;
import com.dw.gameshop_mybatis.mapper.GameMapper;
import com.dw.gameshop_mybatis.mapper.PurchaseMapper;
import com.dw.gameshop_mybatis.mapper.UserMapper;
import com.dw.gameshop_mybatis.model.Game;
import com.dw.gameshop_mybatis.model.Purchase;
import com.dw.gameshop_mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseService {
    // !! @Transactional 클래스에 직접 선언하면 모든 메서드에 트랜잭션이 적용됨
    // 하지만 세밀한 조정이 불가능하므로 좋은 방법이 아님.
    @Autowired
    PurchaseMapper purchaseMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    GameMapper gameMapper;

    /* @Transactional 구매 정보를 저장하는 메서드 (트랜잭션 적용)
    메서드 내의 모든 DB작업이 하나의 단위(=트랜잭션)로
    묶이도폭 해야함 (서비스레이어에 반드시 사용!!)
    이렇게 하면 중간에 오류가 발생 시
    모든 작업이 롤백(취소)되어 데이터 일관성을 보장
     */
    @Transactional
    public List<PurchaseDTO> savePurchaseList(
            List<PurchaseDTO> purchaseDTOList) {
        if (purchaseDTOList == null || purchaseDTOList.isEmpty()) {
            throw new InvalidRequestException("구매 목록이 비었습니다.");
        }

        String userName = purchaseDTOList.getFirst().getUser().getUserName();
        User user = userMapper.getUserByUserName(userName);
        if (user == null) {
            throw new ResourceNotFoundException(
                    "해당 유저가 없습니다: " + userName);
        }
        // PurchaseDTO => Purchase 로 변형
        List<Purchase> purchases = purchaseDTOList.stream()
                .map(dto -> {
                    Game game = gameMapper.getGameById(dto.getGame().getId());
                    if (game == null)
                        throw new ResourceNotFoundException("해당 게임이 없습니다.");
                    return new Purchase(
                            0,
                            game,
                            user,
                            LocalDateTime.now()
                    );
                })
                .toList();
        purchaseMapper.savePurchaseList(purchases);
        return purchaseDTOList;
    }

    /* @Transactional(readOnly = true)
    - 읽기작업도 트랜잭션이 필요함. 읽는중에 데이터가 다른 틀랜잭션에 의해서 변경되면
        데이터의 일관성을 유지할 수 없음(Dirty Read)
    - readOnly로 세팅을 하면 실제 네트워크에서는 읽기전용 DB서버를 이용하므로
        더 빠르게 서비스를 수행할 수 있음
    - 여러 readOnly 트랙잭션은 동시에 읽기작업도 가능함
    - 클래스에 @Transactionaldmf 선언하면 읽기작업을 할때도 쓰기작업할때처럼 아래의
        트랙잭션 작업을 수행하게 되므로 자원의 낭비가 심하게 됨.
        1) 쓰기락(lock)
        2) 트랙잭션 로그생성
        3) 롤백을 위한 정보유지
     */
    // 모든 구매 내역을 DTO 리스트로 가져오는 메서드
    @Transactional(readOnly = true)
    public List<PurchaseDTO> getAllPurchases(User currentUser) {
        if (currentUser == null) {
            throw new InvalidRequestException("세션이 없습니다");
        }
        // 관리자 여부 확인
        if (!currentUser.getAuthority().getAuthorityName().equals("ADMIN")) {
            throw new PermissionDeniedException("권한이 없습니다.");
        }
        return purchaseMapper.getAllPurchases().stream()
                .map(Purchase::toDto).toList();
    }

    // 특정 사용자의 구매 내역을 DTO 리스트로 가져오는 메서드
    public List<PurchaseDTO> getPurchaseListByUserName(
            String userName, User currentUser) {
        if (currentUser == null) {
            throw new InvalidRequestException("세션이 없습니다");
        }
        // 관리자 여부 확인
        if (!currentUser.getAuthority().getAuthorityName().equals("ADMIN")) {
            throw new PermissionDeniedException("권한이 없습니다.");
        }
        User user = userMapper.getUserByUserName(userName);
        if (user == null) {
            throw new ResourceNotFoundException("해당 유저가 없습니다.");
        }
        return purchaseMapper.getPurchaseListByUserName(userName)
                .stream()
                .map(Purchase::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PurchaseDTO> getPurchaseListByCurrentUser(User currentUser) {
        if (currentUser == null) {
            throw new InvalidRequestException("세션이 없습니다.");
        }
        return purchaseMapper
                .getPurchaseListByUserName(currentUser.getUserName())
                .stream()
                .map(Purchase::toDto)
                .toList();
    }
}


//    @Transactional
//    public List<PurchaseDTO> savePurchaseList(
//            String userName, Long gameId) {
//        // 사용자 및 게임 정보가 존재하는지 확인 (Optional 적용)
//        User user = userMapper.getUserByUserName(userName);
//        Game game = gameMapper.getGameById(gameId);
//
//        // 만약 사용자가 게임을 이미 구매했다면 예외 처리
//        if (purchaseMapper.countPurchaseByUserNameAndGameId(userName, gameId) > 0) {
//            throw new IllegalStateException("User already purchased this game.");
//        }
//    }


// 모든 구매 내역을 DTO 리스트로 가져오는 메서드
//    public List<PurchaseDTO> getAllPurchases() {
//        return purchaseMapper.getAllPurchases().stream()
//                .map(Purchase::toDto)
//                .collect(Collectors.toList());
//    }


// 특정 사용자의 구매 내역을 DTO 리스트로 가져오는 메서드
//    public List<PurchaseDTO> getPurchaseListByUserName(String userName) {
//        return purchaseMapper.getPurchaseListByUserName(userName ).stream()
//                .map(Purchase::toDto)
//                .collect(Collectors.toList());
//    }

//        // Purchase 객체 생성
//        Purchase newPurchase = new Purchase();
//        newPurchase.setUser(user);
//        newPurchase.setGame(game);
//        newPurchase.setPurchaseTime(LocalDateTime.now());
//
//        // 구매 정보 저장
//        purchaseMapper.savePurchase(newPurchase);


