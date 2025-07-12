package io.rafat.expensetracker.service;

import io.rafat.expensetracker.dto.bank_card.BankCardTypeResponse;
import io.rafat.expensetracker.model.BankCardType;
import io.rafat.expensetracker.repository.BankCardTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BankCardServiceImpl implements BankCardService {
    private BankCardTypeRepository bankCardTypeRepository;

    @Override
    public List<BankCardTypeResponse> getBankCardTypes() {
        List<BankCardType> bankCards = bankCardTypeRepository.findAll();

        return bankCards.stream()
                .map(bankCardType -> BankCardTypeResponse.builder()
                        .id(bankCardType.getId())
                        .name(bankCardType.getName())
                        .value(bankCardType.getValue())
                        .build()
                ).toList();
    }
}
