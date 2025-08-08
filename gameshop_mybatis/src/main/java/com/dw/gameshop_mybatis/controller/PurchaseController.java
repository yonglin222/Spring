package com.dw.gameshop_mybatis.controller;

import com.dw.gameshop_mybatis.dto.BoardDTO;
import com.dw.gameshop_mybatis.dto.GameDTO;
import com.dw.gameshop_mybatis.dto.PurchaseDTO;
import com.dw.gameshop_mybatis.model.User;
import com.dw.gameshop_mybatis.service.GameService;
import com.dw.gameshop_mybatis.service.PurchaseService;
import com.dw.gameshop_mybatis.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/purchase")
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;
    @Autowired
    UserService userService;
    @Autowired
    GameService gameService;

    @PostMapping("/save/list")
    public ResponseEntity<List<PurchaseDTO>> savePurchaseList(
            @RequestBody List<PurchaseDTO> purchaseDTOList) {
        return new ResponseEntity<>(
                purchaseService.savePurchaseList(purchaseDTOList),
                HttpStatus.CREATED);
    }

    @GetMapping("/current-user")
    public ResponseEntity<List<PurchaseDTO>> getPurchaseListByCurrentUser(
            HttpServletRequest request) {
        User currentUser = userService.getCurrentUser(request);
        return new ResponseEntity<>(
                purchaseService.getPurchaseListByCurrentUser(currentUser),
                HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PurchaseDTO>> getAllPurchases(
            HttpServletRequest request
    ) {User currentUser = userService.getCurrentUser(request);
        return new ResponseEntity<>(
                purchaseService.getAllPurchases(currentUser),
                HttpStatus.OK
        );
    }
    @GetMapping("user/{userName}")
    public ResponseEntity<List<PurchaseDTO>> getPurchaseListByUserName(
            @PathVariable String userName, HttpServletRequest request)
    {User currentUser = userService.getCurrentUser(request);
        return new ResponseEntity<>(
                purchaseService.getPurchaseListByUserName(userName,currentUser),
                HttpStatus.OK
        );
    }
}