package com.example.onlineresellingproject.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Класс {@code UserWrapper} представляет обертку для объекта {@link UserEntity} и реализует интерфейс {@link UserDetails}
 * для интеграции с Spring Security.
 *
 * <p>Этот класс позволяет использовать объекты типа {@code UserEntity} как объекты пользователя Spring Security.
 *
 * @see UserEntity
 * @see UserDetails
 */
public class UserWrapper implements UserDetails {

    private final UserEntity userEntity;

    /**
     * Конструктор класса {@code UserWrapper}.
     *
     * @param userEntity Объект типа {@link UserEntity}, который будет обернут.
     */
    public UserWrapper(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    /**
     * Возвращает коллекцию ролей пользователя. В данной реализации возвращается одна роль,
     * соответствующая роли пользователя в объекте {@link UserEntity}.
     *
     * @return Коллекция объектов типа {@link GrantedAuthority} представляющих роли пользователя.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority(this.userEntity.getRole().toString())
        );
    }

    /**
     * Возвращает пароль пользователя из объекта {@link UserEntity}.
     *
     * @return Пароль пользователя.
     */
    @Override
    public String getPassword() {
        return this.userEntity.getPassword();
    }

    /**
     * Возвращает имя пользователя (логин) из объекта {@link UserEntity}.
     *
     * @return Имя пользователя (логин).
     */
    @Override
    public String getUsername() {
        return this.userEntity.getUsername();
    }

    /**
     * Проверяет, не истек ли срок действия учетной записи пользователя.
     *
     * @return {@code true}, если срок действия учетной записи пользователя не истек, иначе {@code false}.
     */
    @Override
    public boolean isAccountNonExpired() {
        return this.userEntity.isNonExpired();
    }

    /**
     * Проверяет, не заблокирована ли учетная запись пользователя.
     *
     * @return {@code true}, если учетная запись пользователя не заблокирована, иначе {@code false}.
     */
    @Override
    public boolean isAccountNonLocked() {
        return this.userEntity.isNonLocked();
    }

    /**
     * Проверяет, не истек ли срок действия учетных данных пользователя.
     *
     * @return {@code true}, если срок действия учетных данных пользователя не истек, иначе {@code false}.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return this.userEntity.isNonCredentialsExpired();
    }

    /**
     * Проверяет, активирована ли учетная запись пользователя.
     *
     * @return {@code true}, если учетная запись пользователя активирована, иначе {@code false}.
     */
    @Override
    public boolean isEnabled() {
        return this.userEntity.isEnabled();
    }
}
