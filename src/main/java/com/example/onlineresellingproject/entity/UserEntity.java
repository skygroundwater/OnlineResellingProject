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

/**
 * Класс {@code UserEntity} представляет собой сущность пользователя в системе онлайн-продаж.
 *
 * <p>Пользователи могут создавать объявления и оставлять комментарии.
 *
 * @see ProjectEntity
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    /**
     * Уникальный идентификатор пользователя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Логин пользователя.
     */
    @Column(name = "username")
    private String username;

    /**
     * Хэш пароля пользователя.
     */
    @Column(name = "password")
    private String password;

    /**
     * Имя пользователя.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Фамилия пользователя.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Номер телефона пользователя в формате +7 (XXX) XXX-XX-XX.
     */
    @Column(name = "phone")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    /**
     * Роль пользователя (USER или ADMIN).
     */
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    /**
     * Путь к изображению пользователя.
     */
    @Column(name = "image")
    private String image;

    /**
     * Дата и время регистрации пользователя.
     */
    @Column(name = "reg_date")
    private LocalDateTime registrationDate = LocalDateTime.now();

    /**
     * Список объявлений, созданных пользователем.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AdEntity> ads;

    /**
     * Список комментариев, оставленных пользователем.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentEntity> comments;

    /**
     * Флаг, указывающий, не истек ли срок действия учетной записи пользователя.
     */
    @Column(name = "non_expired")
    private boolean nonExpired = true;

    /**
     * Флаг, указывающий, не заблокирована ли учетная запись пользователя.
     */
    @Column(name = "non_locked")
    private boolean nonLocked = true;

    /**
     * Флаг, указывающий, не истек ли срок действия учетных данных пользователя.
     */
    @Column(name = "non_credentials_expired")
    private boolean nonCredentialsExpired = true;

    /**
     * Флаг, указывающий, включена ли учетная запись пользователя.
     */
    @Column(name = "is_enabled")
    private boolean isEnabled = true;

    /**
     * Конструктор для инициализации основных полей пользователя.
     *
     * @param id       Уникальный идентификатор пользователя.
     * @param username Логин пользователя.
     * @param password Хэш пароля пользователя.
     * @param firstName Имя пользователя.
     * @param lastName Фамилия пользователя.
     * @param phone    Номер телефона пользователя в формате +7 (XXX) XXX-XX-XX.
     * @param role     Роль пользователя (USER или ADMIN).
     * @param image    Путь к изображению пользователя.
     */
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

    /**
     * Метод сравнения пользователей по их полям.
     *
     * @param o Объект для сравнения.
     * @return {@code true}, если объекты равны, {@code false} в противном случае.
     */
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

    /**
     * Метод для вычисления хэш-кода пользователя.
     *
     * @return Хэш-код пользователя.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstName,
                lastName, phone, role, image, registrationDate,
                nonExpired, nonLocked, nonCredentialsExpired, isEnabled);
    }
}
