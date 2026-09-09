package me.melkx.veloroute.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "ez_statistics")
public class EzStatisticEntity {
    @EmbeddedId
    @EqualsAndHashCode.Include
    private EzStatisticsKey id;

    @Column(name = "visit_count", nullable = false)
    private Integer visitCount = 0;

    @Column(name = "total_rating", nullable = false)
    private Double totalRating = 0.0;

    @CreationTimestamp
    @Column(name = "first_visited", nullable = false, updatable = false)
    private LocalDateTime firstVisited;

    @Column(name = "last_visited")
    private LocalDateTime lastVisited;
}
