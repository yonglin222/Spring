package com.dw.gameshop_mybatis.mapper;

import com.dw.gameshop_mybatis.model.Purchase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PurchaseMapper {
    // 구매 정보를 한 건 저장
    void savePurchase(@Param("purchase") Purchase purchase);
    // 여러 건의 구매 정보를 한 번에 저장 <foreach> 구문을 사용해서 리스트에 있는 각 항목을 순회하며 INSERT문을 반복 실행
    void savePurchaseList(
            @Param("purchaseList") List<Purchase> purchaseList);
    // 모든 구매 정보를 가져오는 쿼리
    List<Purchase> getAllPurchases();
    // 특정 사용자의 모든 구매 정보를 가져오기
    List<Purchase> getPurchaseListByUserName(
            @Param("userName") String userName);
    // 특정 사용자가 특정 게임을 구매했는지 확인
    int countPurchaseByUserNameAndGameId(
            @Param("userName") String userName,
            @Param("gameId") Long gameId);
}
