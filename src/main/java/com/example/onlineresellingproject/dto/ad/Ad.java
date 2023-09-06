package com.example.onlineresellingproject.dto.ad;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ad {

    private Integer pk;

    private Integer author;

    private String image;

    private Integer price;

    private String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return Objects.equals(pk, ad.pk) && Objects.equals(author, ad.author) && Objects.equals(image, ad.image) && Objects.equals(price, ad.price) && Objects.equals(title, ad.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, author, image, price, title);
    }
}