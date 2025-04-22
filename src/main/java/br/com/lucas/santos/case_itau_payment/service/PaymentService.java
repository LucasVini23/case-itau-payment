package br.com.lucas.santos.case_itau_payment.service;

import br.com.lucas.santos.case_itau_payment.entities.PaymentEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final SqsTemplate sqsTemplate;

    @SqsListener("fila-appPayment")
    private void paymentListener(String message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        PaymentEntity payment = mapper.readValue(message, PaymentEntity.class);
        validatePayment(payment);
    }

    private void validatePayment(PaymentEntity payment) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        if (payment.getMethod().equals("Cartão") && !payment.getCard().getTokenCard().isEmpty()) {
            // teria uma conexão com alguma outra aplicação de pagamento para que fosse validado o pagamento
            payment.setStatus("PAGO");
        }
        else {
            payment.setStatus("NÃO PAGO");
        }
        String processedPayment = mapper.writeValueAsString(payment);
        sqsTemplate.send("fila-appProcessedPayment", processedPayment);
    }

}
