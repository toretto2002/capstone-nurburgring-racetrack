package com.epicode.Spring.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.epicode.Spring.entities.Address;
import com.epicode.Spring.entities.Licence;
import com.epicode.Spring.enumerators.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users", uniqueConstraints = {
		@UniqueConstraint(columnNames = "email") })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String lastname;
    
    @OneToOne
    private Address address;
    
    @Column(nullable = false)
    private String mobile;
    
    @Column(nullable = false)
    private LocalDate birthDate;
    
    @Enumerated
    private Genre genre;
        
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();
    
    @OneToOne(fetch = FetchType.EAGER)
    private Licence licence;
}
