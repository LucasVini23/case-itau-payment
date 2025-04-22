package br.com.lucas.santos.case_itau_payment.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {

    private Long id;
    private Long proposalId;
    private Long cpf;
    private Double amount;
    private String method;
    private Integer installments;
    private CardUser card;
    private String status;

}
