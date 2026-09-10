package me.melkx.veloroute.module.generatorpreset.db.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.melkx.veloroute.core.enums.RouteType;
import me.melkx.veloroute.core.model.GeneratorPreferences;
import me.melkx.veloroute.core.model.GeneratorWeights;
import me.melkx.veloroute.module.user.db.entity.UserEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "generator_presets")
public class GeneratorPresetEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "route_type", nullable = false, length = 20)
    private RouteType routeType;

    @JdbcTypeCode(SqlTypes.GEOMETRY)
    @Column(name = "target_point")
    private Point targetPoint;

    @Column(name = "desired_distance_km", nullable = false)
    private Integer desiredDistanceKm;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "preferences", nullable = false)
    private GeneratorPreferences preferences;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "weights", nullable = false)
    private GeneratorWeights weights;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
