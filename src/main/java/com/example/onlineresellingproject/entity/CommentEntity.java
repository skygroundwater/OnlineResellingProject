package com.example.onlineresellingproject.entity;

import com.example.onlineresellingproject.dto.comment.CreateOrUpdateComment;
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
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "comments")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private AdEntity ad;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "text")
    private String text;

    /**
     * Устанавливает поля сущности на основе данных из объекта CreateOrUpdateComment.
     *
     * @param createOrUpdateComment объект с данными для обновления сущности.
     * @return текущая сущность CommentEntity.
     */
    public final CommentEntity setFieldsAndReturnEntity(CreateOrUpdateComment createOrUpdateComment) {
        this.setText(createOrUpdateComment.getText());
        return this;
    }

    /**
     * Устанавливает поля сущности на основе данных из объекта CreateOrUpdateComment
     * и связывает сущность с пользователем и объявлением.
     *
     * @param user                  пользователь, оставивший комментарий.
     * @param adEntity              объявление, к которому оставлен комментарий.
     * @param createOrUpdateComment объект с данными для обновления сущности.
     * @return текущая сущность CommentEntity.
     */
    public final CommentEntity setFieldsAndReturnEntity(
            UserEntity user, AdEntity adEntity, CreateOrUpdateComment createOrUpdateComment) {
        this.setText(createOrUpdateComment.getText());
        this.setUser(user);
        this.setAd(adEntity);
        this.setCreatedAt(LocalDateTime.now());
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(createdAt, that.createdAt) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, text);
    }
}