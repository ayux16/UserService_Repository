package org.ecomerce.userservice_repository.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailDTO {
     private String to;
     private String subject;
     private String body;
}
