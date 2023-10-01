package com.epicode.Spring.payload;



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
public class AddressDto {

	private String street;
    private String streetNumber;
    private String city;
    private String province;
    private String postalCode;
    private String country;
}



