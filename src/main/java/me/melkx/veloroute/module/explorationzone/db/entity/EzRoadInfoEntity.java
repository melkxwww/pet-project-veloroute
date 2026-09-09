package me.melkx.veloroute.explorationzone.db.entity;

import jakarta.persistence.*;
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
@Table(name = "ez_roads_info", uniqueConstraints =
    @UniqueConstraint(name = "uk_ez_roads_info_zone_osm", columnNames = {"zone_id", "osm_id"}))
public class EzRoadInfoEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id", nullable = false)
    private ExplorationZoneEntity zone;

    @Column(name = "osm_id", nullable = false)
    private Long osmId;

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
