package com.example.onlineresellingproject.entity;

import com.example.onlineresellingproject.dto.ad.CreateOrUpdateAd;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Класс, представляющий сущность объявления в приложении.
 * Этот класс соответствует таблице "ads" в базе данных и используется
 * для хранения информации о объявлениях пользователей.
 */
@Entity
@Table(name = "ads")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private UserEntity user;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentEntity> comments;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private Integer price;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    /**
     * Метод для установки полей объекта AdEntity из DTO и пути к изображению.
     *
     * @param userEntity    Сущность пользователя, создавшего объявление.
     * @param dto           DTO с информацией об объявлении.
     * @param pathToImage   Путь к изображению.
     * @return              Обновленная сущность объявления.
     */
    public final AdEntity setFieldsAndReturnEntity(UserEntity userEntity,
                                                   CreateOrUpdateAd dto,
                                                   String pathToImage) {
        this.setUser(userEntity);
        this.setDescription(dto.getDescription());
        this.setPrice(dto.getPrice());
        this.setTitle(dto.getTitle());
        this.setImage(pathToImage);
        return this;
    }

    /**
     * Метод для установки полей объекта AdEntity из DTO.
     *
     * @param createOrUpdateAd   DTO с информацией об объявлении.
     * @return                  Обновленная сущность объявления.
     */
    public final AdEntity setFieldsAndReturnEntity(CreateOrUpdateAd createOrUpdateAd) {
        this.setDescription(createOrUpdateAd.getDescription());
        this.setTitle(createOrUpdateAd.getTitle());
        this.setPrice(createOrUpdateAd.getPrice());
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdEntity adEntity = (AdEntity) o;
        return Objects.equals(id, adEntity.id) && Objects.equals(createdAt, adEntity.createdAt) && Objects.equals(image, adEntity.image) && Objects.equals(price, adEntity.price) && Objects.equals(title, adEntity.title) && Objects.equals(description, adEntity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, image, price, title, description);
    }
}
