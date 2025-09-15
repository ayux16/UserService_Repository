package org.ecomerce.userservice_repository.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table (name="tokens")
public class Token extends BaseModel {
    private String value;
    private Date expryAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
/**
 token value should be JWT
 as of now we are not generating JWT
 so token value will be any random valuue of 128 characters-->> for this or any string related operations
 we use Apache commons lang3 library
 */