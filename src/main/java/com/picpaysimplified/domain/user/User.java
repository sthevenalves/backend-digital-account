package com.picpaysimplified.domain.user;

import com.picpaysimplified.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firsName;
    private String lastName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String password;
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(UserDTO dto){
        this.firsName = dto.firsName();
        this.lastName = dto.lastName();
        this.document = dto.document();
        this.email = dto.email();
        this.balance = dto.balance();
        this.password = dto.password();
        this.userType = dto.userType();
    }

}
