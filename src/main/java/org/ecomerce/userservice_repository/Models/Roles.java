package org.ecomerce.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="roles")
public class Roles extends BaseModel {
    private String value;
}
