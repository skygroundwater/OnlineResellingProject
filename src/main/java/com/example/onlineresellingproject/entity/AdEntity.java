package com.example.onlineresellingproject.entity;

import com.example.onlineresellingproject.dto.ad.CreateOrUpdateAd;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;

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

@Entity
@Table(name = "ads")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdEntity extends ProjectEntity {

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

    public final AdEntity setFieldsAndReturnEntity(CreateOrUpdateAd createOrUpdateAd) {
        this.setDescription(createOrUpdateAd.getDescription());
        this.setTitle(createOrUpdateAd.getTitle());
        this.setPrice(createOrUpdateAd.getPrice());
        return this;
    }
}
