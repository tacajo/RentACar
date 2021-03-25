package com.agent.model;

import com.agent.registration.VerificationToken;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.*;
import org.joda.time.DateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected @SQLInjectionSafe String username;

    @JsonIgnore
    @Column(nullable = false)
    protected String password;

    @Column
    protected String firstName;

    @Column
    protected String lastName;

    @Column
    protected String email;

    @Column
    protected Timestamp lastPasswordResetDate;

    @Column
    protected boolean enabled;

    @Column
    private boolean enabledAccount;

    @Column
    private boolean disableReservation;

    @Column
    private Double obligation;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private VerificationToken verificationToken;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Singular("roles")
    protected List<Role> roles;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RentACar> rentACars;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(!this.roles.isEmpty()){
            Role r = roles.iterator().next();
            List<Permission> permissions = new ArrayList<Permission>();
            for(Permission p : r.getPermission()){
                permissions.add(p);
            }
            return permissions;
        }
        return null;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        Timestamp now = new Timestamp(DateTime.now().getMillis());
        this.setLastPasswordResetDate(now);
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public List<RentACar> getRentACars() {
        return rentACars;
    }

    public void setRentACars(List<RentACar> rentACars) {
        this.rentACars = rentACars;
    }
}
