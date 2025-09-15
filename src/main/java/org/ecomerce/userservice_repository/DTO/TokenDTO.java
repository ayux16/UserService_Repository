package org.ecomerce.userservice_repository.DTO;

import lombok.Getter;
import lombok.Setter;
import org.ecomerce.userservice_repository.Models.Token;

import java.util.Date;

@Getter
@Setter
public class TokenDTO {
    private String value;
    private Date expryAt;
    private String email;

    public static TokenDTO from(Token token){
        if(token == null) return null;
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setValue(token.getValue());
        tokenDTO.setExpryAt(token.getExpryAt());
        tokenDTO.setEmail(token.getUser().getEmail());
        return tokenDTO;
    }
}
