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
public class LicenceDto {

	private String licenceNumber;
	private String dateOfIssue;
	private String expirationDate;
	private String category;
	private String issuingAuthority;
}
