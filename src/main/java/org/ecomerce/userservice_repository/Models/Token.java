package org.ecomerce.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Table (name="tokens")
public class Token extends BaseModel {
    private String value;
    private Date expryAt;
    @ManyToOne
    private User User;
}
