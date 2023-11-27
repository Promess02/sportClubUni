package mikolaj.project.backendapp.DTO;

import lombok.Data;
import mikolaj.project.backendapp.model.CreditCard;

@Data
public class CreditCardForm {
    private String email;
    private CreditCard creditCard;
}
