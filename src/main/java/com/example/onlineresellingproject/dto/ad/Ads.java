package com.example.onlineresellingproject.dto.ad;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ads {

    private Integer count;

    private List<Ad> results;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ads ads = (Ads) o;
        return Objects.equals(count, ads.count) && Objects.equals(results, ads.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, results);
    }
}
