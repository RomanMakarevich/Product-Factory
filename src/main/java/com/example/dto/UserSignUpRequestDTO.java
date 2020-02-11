package com.example.dto;


import lombok.Data;


/**
 * @author Wladimir Litvinov
 */
@Data
public class UserSignUpRequestDTO {
    private String email;
    private String password;
    private String fio;
    private String companyName;
    private String adress;
    private String accountNumber;
}
