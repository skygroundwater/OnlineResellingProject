package com.example.onlineresellingproject.entity;

import com.example.onlineresellingproject.dto.user.Role;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "image")
    private String image;

    @Column(name = "reg_date")
    private LocalDateTime registrationDate = LocalDateTime.now();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AdEntity> ads;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentEntity> comments;

    @Column(name = "non_expired")
    private boolean nonExpired = true;

    @Column(name = "non_locked")
    private boolean nonLocked = true;

    @Column(name = "non_credentials_expired")
    private boolean nonCredentialsExpired = true;

    @Column(name = "is_enabled")
    private boolean isEnabled = true;

    public UserEntity(Long id, String username,
                      String password, String firstName,
                      String lastName, String phone,
                      Role role, String image) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return nonExpired == user.nonExpired && nonLocked == user.nonLocked
                && nonCredentialsExpired == user.nonCredentialsExpired
                && isEnabled == user.isEnabled && Objects.equals(id, user.id)
                && Objects.equals(username, user.username) && Objects.equals(password, user.password)
                && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName)
                && Objects.equals(phone, user.phone) && role == user.role && Objects.equals(image, user.image)
                && Objects.equals(registrationDate, user.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstName,
                lastName, phone, role, image, registrationDate,
                nonExpired, nonLocked, nonCredentialsExpired, isEnabled);
    }
}
