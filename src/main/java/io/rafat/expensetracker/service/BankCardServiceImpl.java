package io.rafat.expensetracker.service;

import io.rafat.expensetracker.dto.bank_card.AddBankCardRequest;
import io.rafat.expensetracker.dto.bank_card.BankCardResponse;
import io.rafat.expensetracker.dto.bank_card.BankCardTypeResponse;
import io.rafat.expensetracker.model.BankCard;
import io.rafat.expensetracker.model.BankCardType;
import io.rafat.expensetracker.model.Users;
import io.rafat.expensetracker.repository.BankCardRepository;
import io.rafat.expensetracker.repository.BankCardTypeRepository;
import io.rafat.expensetracker.utils.UserUtils;
import io.rafat.expensetracker.utils.exception.AlreadyExistsException;
import io.rafat.expensetracker.utils.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BankCardServiceImpl implements BankCardService {
    private final BankCardRepository bankCardRepository;
    private final BankCardTypeRepository bankCardTypeRepository;

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

    @Override
    public List<BankCardResponse> getAllBankCards() {
        Users currentUser = UserUtils.getCurrentUser();
        List<BankCard> bankCards = bankCardRepository.findAllByUser(currentUser);

        return bankCards.stream()
                .map(bankCard -> BankCardResponse.builder()
                        .cardType(BankCardTypeResponse.builder()
                                .id(bankCard.getBankCardType().getId())
                                .name(bankCard.getBankCardType().getName())
                                .value(bankCard.getBankCardType().getValue())
                                .build())
                        .cardNo(bankCard.getNumber())
                        .holderName(bankCard.getHolder())
                        .build())
                .toList();
    }

    @Override
    public BankCardResponse addNewBankCard(AddBankCardRequest request) {
        Users currentUser = UserUtils.getCurrentUser();
        Optional<BankCardType> bankCardType = bankCardTypeRepository.findById(currentUser.getId());
        if (bankCardType.isEmpty()) {
            throw new NotFoundException("Bank card type not found");
        }

        Optional<BankCard> bankCardWithNo = bankCardRepository.findAllByNumber(request.cardNo());
        if (bankCardWithNo.isPresent()) {
            throw new AlreadyExistsException("Bank card already exists");
        }

        BankCard bankCard = BankCard.builder()
                .bankCardType(bankCardType.get())
                .cvc(request.cvc())
                .expiryDate(request.expiryDate())
                .holder(request.holderName())
                .number(request.cardNo())
                .user(currentUser)
                .build();

        bankCard = bankCardRepository.save(bankCard);

        return BankCardResponse.builder()
                .cardType(BankCardTypeResponse.builder()
                        .id(bankCardType.get().getId())
                        .name(bankCardType.get().getName())
                        .value(bankCardType.get().getValue())
                        .build())
                .cardNo(bankCard.getNumber())
                .holderName(bankCard.getHolder())
                .build();
    }
}
