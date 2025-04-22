package br.com.lucas.santos.case_itau_payment.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardUser {

    private String tokenCard;
    private String lastDigit;

}
