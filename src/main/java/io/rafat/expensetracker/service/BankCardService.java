package io.rafat.expensetracker.service;

import io.rafat.expensetracker.dto.bank_card.AddBankCardRequest;
import io.rafat.expensetracker.dto.bank_card.BankCardResponse;
import io.rafat.expensetracker.dto.bank_card.BankCardTypeResponse;

import java.util.List;

public interface BankCardService {
    List<BankCardTypeResponse> getBankCardTypes();
    List<BankCardResponse> getAllBankCards();
    BankCardResponse addNewBankCard(AddBankCardRequest cardRequest);
}
