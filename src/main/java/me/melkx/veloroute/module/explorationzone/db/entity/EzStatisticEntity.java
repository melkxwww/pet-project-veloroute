package me.melkx.veloroute.explorationzone.db.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "ez_statistics", uniqueConstraints =
        @UniqueConstraint(name = "uk_ez_statistics_zone_id", columnNames = "zone_id"))
public class EzStatisticEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id", nullable = false)
    private ExplorationZoneEntity zone;

    @Column(name = "total_routes", nullable = false)
    private Integer totalRoutes = 0;

    @Column(name = "total_trips", nullable = false)
    private Integer totalTrips = 0;

    @Column(name = "total_distance_km", nullable = false)
    private Double totalDistanceKm = 0.0;

    @Column(name = "total_duration_min", nullable = false)
    private Double totalDurationMin = 0.0;

    @Column(name = "average_rating", nullable = false)
    private Double averageRating = 0.0;

    @Column(name = "favorite_count", nullable = false)
    private Integer favoriteCount = 0;

    @Column(name = "weekly_distance_km", nullable = false)
    private Double weekly_distance_km = 0.0;

    @Column(name = "monthly_distance_km", nullable = false)
    private Double monthlyDistanceKm = 0.0;

    @Column(name = "yearly_distance_km", nullable = false)
    private Double yearlyDistanceKm = 0.0;

    @Column(name = "longest_route_distance_km", nullable = false)
    private Double longestRouteDistanceKm = 0.0;

    @Column(name = "last_trip_at")
    private LocalDateTime lastTripAt;

    @UpdateTimestamp
    @Column(name = "statistic_at")
    private LocalDateTime statisticAt;
}
