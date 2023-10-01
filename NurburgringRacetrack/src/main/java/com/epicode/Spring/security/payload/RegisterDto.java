package com.epicode.Spring.security.payload;

import java.util.Set;

import com.epicode.Spring.payload.AddressDto;
import com.epicode.Spring.payload.LicenceDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
	
    private String name;
    private String lastname;
    private AddressDto address;
    private String mobile;
    private String birthDate;
    private String genre;
    private String email;
    private String password;
    private Set<String> roles;
    private LicenceDto licence;
}
