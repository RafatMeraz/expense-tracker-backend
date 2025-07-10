package io.rafat.expensetracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bank_cards")
public class BankCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String number;

    private String holder;

    private Date date;

    private String type;

    private String cvc;

    @ManyToOne(fetch = FetchType.LAZY)
    private BankCardType bankCardType;
}
