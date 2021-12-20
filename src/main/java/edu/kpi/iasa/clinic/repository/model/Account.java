package edu.kpi.iasa.clinic.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_list")
@Builder
@AllArgsConstructor
public class Account {
    public static final Account ACCOUNT = new Account("anonymous");

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private boolean enabled = true;
    private LocalDate dateCreated;
    private Collection<Role> roles = new HashSet<>();

    public Account() {
    }

    public Account(String email) {
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @NotNull(message = "validation.text.error.required.field")
    @Email(message = "validation.text.email.error.field")
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(message = "validation.text.error.required.field")
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message = "validation.text.error.required.field")
    @Size(
            min = 3,
            max = 50,
            message = "validation.text.error.from.three.to.fifty")
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull(message = "validation.text.error.required.field")
    @Size(
            min = 3,
            max = 50,
            message = "validation.text.error.from.three.to.fifty")
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Transient
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    @NotNull(message = "validation.text.error.required.field")
    @Pattern(regexp = "\\+\\d{12}", message = "validation.text.phone.error.sample")
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "benabled")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = {
                    @JoinColumn(
                            name = "nuser",
                            referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "nrole",
                            referencedColumnName = "id")})
    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    @Column(name = "date_created")
    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Transient
    public Collection<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.addAll(getRoles());
        return authorities;
    }

    public boolean hasRole(String role) {
        for (Role r : getRoles()) {
            if (r.getCode().equals(role)) return true;
        }
        return false;
    }

    public String toString() {
        return email;
    }

}