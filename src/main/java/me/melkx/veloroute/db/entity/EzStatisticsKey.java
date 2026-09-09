package me.melkx.veloroute.db.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.sql.DataSource;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EzStatisticsKey implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id", nullable = false)
    private ExplorationZoneEntity zone;

    @Column(name = "osm_id")
    @EqualsAndHashCode.Include
    private Long osmId;
}
