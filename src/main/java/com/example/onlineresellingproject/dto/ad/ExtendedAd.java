package com.example.onlineresellingproject.dto.ad;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Builder
@Data
public class ExtendedAd extends AdDTO {

    private Integer pk;

    private String authorFirstName;

    private String authorLastName;

    private String description;

    private String email;

    private String image;

    private String phone;

    private Integer price;

    private String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtendedAd that = (ExtendedAd) o;
        return Objects.equals(pk, that.pk) && Objects.equals(authorFirstName, that.authorFirstName) && Objects.equals(authorLastName, that.authorLastName) && Objects.equals(description, that.description) && Objects.equals(email, that.email) && Objects.equals(image, that.image) && Objects.equals(phone, that.phone) && Objects.equals(price, that.price) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, authorFirstName, authorLastName, description, email, image, phone, price, title);
    }
}
