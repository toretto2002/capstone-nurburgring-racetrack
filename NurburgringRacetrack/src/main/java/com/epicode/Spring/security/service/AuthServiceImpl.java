package com.epicode.Spring.security.service;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.epicode.Spring.entities.Address;
import com.epicode.Spring.entities.Licence;
import com.epicode.Spring.enumerators.Genre;
import com.epicode.Spring.repositories.AddressRepository;
import com.epicode.Spring.repositories.LicenceRepository;
import com.epicode.Spring.security.entity.ERole;
import com.epicode.Spring.security.entity.Role;
import com.epicode.Spring.security.entity.User;
import com.epicode.Spring.security.exception.MyAPIException;
import com.epicode.Spring.security.payload.LoginDto;
import com.epicode.Spring.security.payload.RegisterDto;
import com.epicode.Spring.security.payload.TokenAndId;
import com.epicode.Spring.security.repository.RoleRepository;
import com.epicode.Spring.security.repository.UserRepository;
import com.epicode.Spring.security.security.JwtTokenProvider;



@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired @Qualifier("userBean") private ObjectProvider<User> userProvider;
	@Autowired @Qualifier("addressBean") private ObjectProvider<Address> addressProvider;
	@Autowired @Qualifier("licenceBean") private ObjectProvider<Licence> licenceProvider;
	
	@Autowired AddressRepository addressRepo;
	@Autowired LicenceRepository licenceRepo;

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;


    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public TokenAndId login(LoginDto loginDto) {
    	
    	if(loginDto.getUsername() == null || loginDto.getUsername().isEmpty()) {
    		throw new MyAPIException(HttpStatus.BAD_REQUEST, "Username is required.");
    	}
    	
    	if(loginDto.getPassword() == null || loginDto.getPassword().isEmpty()) {
    		throw new MyAPIException(HttpStatus.BAD_REQUEST, "Password is required.");
    	}
    	
    	Authentication authentication; 
    	
        
    	try {
    		
	    	authentication = authenticationManager.authenticate(
	        		new UsernamePasswordAuthenticationToken(
	        				loginDto.getUsername(), loginDto.getPassword()
	        		)
	        );
	    	
	    	SecurityContextHolder.getContext().setAuthentication(authentication);

	    	String token = jwtTokenProvider.generateToken(authentication);
	    	
	    	User user = userRepository.findByEmail(loginDto.getUsername()).get();
	    	
	    	TokenAndId tokenAndI = new TokenAndId(token, user.getId());

	    	return tokenAndI; 	
	    	
    	}catch(AuthenticationException e) {
    		 throw new MyAPIException(HttpStatus.BAD_REQUEST, e.getMessage());
    		 
    	}
    }

    @Override
    public String register(RegisterDto registerDto) {
    	
    	if(registerDto.getName() == null) {
    		throw new MyAPIException(HttpStatus.BAD_REQUEST, "name not found!!");
    	}else {
    		System.out.println(registerDto);
    	}

        // add check for email exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }
        
        Address address = addressProvider.getObject();
        
        address.setStreet(registerDto.getAddress().getStreet());
        address.setStreetNumber(registerDto.getAddress().getStreetNumber());
        address.setCity(registerDto.getAddress().getCity());
        address.setProvince(registerDto.getAddress().getProvince());
        address.setPostalCode(registerDto.getAddress().getPostalCode());
        address.setCountry(registerDto.getAddress().getCountry());
        
        addressRepo.save(address);
        
        Licence licence = licenceProvider.getObject();
        
        licence.setLicenceNumber(registerDto.getLicence().getLicenceNumber());
        
        try {
        	LocalDate dateOfIssue = LocalDate.parse(registerDto.getLicence().getDateOfIssue());
        	licence.setDateOfIssue(dateOfIssue);
        }catch (MyAPIException e) {
        	throw new MyAPIException(HttpStatus.BAD_REQUEST, "Error in date format (licence date of issue)!");
        }
        
        try {
        	LocalDate expirationDate = LocalDate.parse(registerDto.getLicence().getExpirationDate());
        	licence.setExpirationDate(expirationDate);
        }catch (MyAPIException e) {
        	throw new MyAPIException(HttpStatus.BAD_REQUEST, "Error in date format (licence date of issue)!");
        }
        
        licence.setCategory(registerDto.getLicence().getCategory());
        licence.setIssuingAuthority(registerDto.getLicence().getIssuingAuthority());
        licence.setLicenceNumber(registerDto.getLicence().getLicenceNumber());
        
        licenceRepo.save(licence);
        
        
        User user = userProvider.getObject();
        
        user.setName(registerDto.getName());
        user.setLastname(registerDto.getLastname());
        user.setAddress(address);
        user.setMobile(registerDto.getMobile());
        
        try {
            LocalDate birthDate = LocalDate.parse(registerDto.getBirthDate()); // the date needs to be like yyyy-MM-dd
            user.setBirthDate(birthDate);
        } catch (MyAPIException e) {
        	throw new MyAPIException(HttpStatus.BAD_REQUEST, "Error in date format (birth date)!");
        } 
        
        try {
            Genre genre = Genre.valueOf(registerDto.getGenre());
            user.setGenre(genre);
        } catch (MyAPIException e) {
        	throw new MyAPIException(HttpStatus.BAD_REQUEST, "Error in gender selection!");
        }
        
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        
        if(registerDto.getRoles() != null) {
	        registerDto.getRoles().forEach(role -> {
	        	Role userRole = roleRepository.findByRoleName(getRole(role)).get();
	        	roles.add(userRole);
	        });
        } else {
        	Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER).get();
        	roles.add(userRole);
        }
        
        user.setRoles(roles);
        user.setLicence(licence);
        System.out.println(user);
        userRepository.save(user);
        
        return "user registrato";
    }
    
    public ERole getRole(String role) {
    	if(role.equals("ADMIN")) return ERole.ROLE_ADMIN;
    	else if(role.equals("MODERATOR")) return ERole.ROLE_MODERATOR;
    	else return ERole.ROLE_USER;
    }
    
    
    
    
}
