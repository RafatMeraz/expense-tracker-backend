package io.rafat.expensetracker.controller;

import io.rafat.expensetracker.dto.bank_card.BankCardTypeResponse;
import io.rafat.expensetracker.service.BankCardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bank-cards")
@AllArgsConstructor
public class BankCardController {
    private final BankCardService bankCardService;

    @GetMapping("/types")
    public ResponseEntity<?> getBankCard() {
        List<BankCardTypeResponse> bankCardTypeResponseList = bankCardService.getBankCardTypes();
        return ResponseEntity.ok(bankCardTypeResponseList);
    }
}
