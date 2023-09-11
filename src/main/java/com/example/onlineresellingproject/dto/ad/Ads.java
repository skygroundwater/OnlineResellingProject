package com.example.onlineresellingproject.dto.ad;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import java.util.List;
import java.util.Objects;

/**
 * Класс, представляющий список объявлений.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ads {

    /**
     * Количество объявлений в списке.
     */
    private Integer count;

    /**
     * Список объявлений.
     */
    private List<Ad> results;

    /**
     * Переопределенный метод для сравнения объектов класса Ads.
     *
     * @param o Объект, с которым выполняется сравнение.
     * @return true, если объекты равны, и false в противном случае.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ads ads = (Ads) o;
        return Objects.equals(count, ads.count) && Objects.equals(results, ads.results);
    }

    /**
     * Переопределенный метод для вычисления хэш-кода объекта.
     *
     * @return Хэш-код объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(count, results);
    }
}
