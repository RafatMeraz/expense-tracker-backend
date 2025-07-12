package io.rafat.expensetracker.controller;

import io.rafat.expensetracker.dto.bank_card.AddBankCardRequest;
import io.rafat.expensetracker.dto.bank_card.BankCardResponse;
import io.rafat.expensetracker.dto.bank_card.BankCardTypeResponse;
import io.rafat.expensetracker.service.BankCardService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<?> addBankCard(@Valid @RequestBody AddBankCardRequest addBankCardRequest) {
        BankCardResponse bankCard = bankCardService.addNewBankCard(addBankCardRequest);
        return new ResponseEntity<>(bankCard, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllBankCards() {
        List<BankCardResponse> bankCards = bankCardService.getAllBankCards();
        return ResponseEntity.ok(bankCards);
    }
}
