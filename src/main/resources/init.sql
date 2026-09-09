CREATE TABLE users (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    is_activated BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,

    CONSTRAINT uk_users_email UNIQUE (email)
);

CREATE TABLE exploration_zones (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    name VARCHAR(100) NOT NULL,
    boundary GEOMETRY(POLYGON, 4326) NOT NULL,
    roads_count INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE ez_roads_info (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    zone_id BIGINT NOT NULL REFERENCES exploration_zones(id) ON DELETE CASCADE,
    osm_id BIGINT NOT NULL,
    visit_count INT NOT NULL,
    total_rating DOUBLE PRECISION NOT NULL,
    first_visited TIMESTAMP NOT NULL,
    last_visited TIMESTAMP,

    CONSTRAINT uk_ez_roads_info_zone_osm UNIQUE (zone_id, osm_id)
);

CREATE TABLE ez_statistics (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    zone_id BIGINT NOT NULL REFERENCES exploration_zones(id) ON DELETE CASCADE,
    total_routes INTEGER NOT NULL,
    total_trips INTEGER NOT NULL,
    total_distance_km DOUBLE PRECISION NOT NULL,
    total_duration_min DOUBLE PRECISION NOT NULL,
    average_rating DOUBLE PRECISION NOT NULL,
    favorite_count INTEGER NOT NULL,
    weekly_distance_km DOUBLE PRECISION NOT NULL,
    monthly_distance_km DOUBLE PRECISION NOT NULL,
    yearly_distance_km DOUBLE PRECISION NOT NULL,
    longest_route_distance_km DOUBLE PRECISION NOT NULL,
    last_trip_at TIMESTAMP,
    statistic_at TIMESTAMP NOT NULL,

    CONSTRAINT uk_ez_statistics_zone_id UNIQUE (zone_id)
);

CREATE TABLE generator_presets (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    name VARCHAR(100) NOT NULL,
    route_type VARCHAR(20) NOT NULL,
    target_point GEOMETRY(POINT, 4326),
    desired_distance_km INTEGER NOT NULL,
    preferences JSONB NOT NULL,
    weights JSONB NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE routes (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    zone_id BIGINT NOT NULL REFERENCES exploration_zones(id) ON DELETE CASCADE,
    preset_snapshot JSONB NOT NULL,
    path GEOMETRY(LINESTRING, 4326) NOT NULL,
    distance_km DOUBLE PRECISION NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE saved_routes (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    route_id BIGINT NOT NULL REFERENCES routes(id) ON DELETE CASCADE UNIQUE,
    name VARCHAR(100) NOT NULL,
    is_favorited BOOLEAN NOT NULL DEFAULT FALSE,
    usage_count INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE trips (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    route_id BIGINT NOT NULL REFERENCES routes(id) ON DELETE CASCADE,
    status VARCHAR(20) NOT NULL,
    rating INTEGER,
    distance_km_done DOUBLE PRECISION,
    duration_min_done DOUBLE PRECISION,
    completed_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE INDEX idx_routes_user_id ON routes(user_id);
CREATE INDEX idx_routes_zone_id ON routes(zone_id);
CREATE INDEX idx_saved_routes_route_id ON saved_routes(route_id);
CREATE INDEX idx_trips_route_id ON trips(route_id);
CREATE INDEX idx_ez_roads_info_zone_id ON ez_roads_info(zone_id);
CREATE INDEX idx_ez_statistics_zone_id ON ez_statistics(zone_id);
CREATE INDEX idx_exploration_zones_user_id ON exploration_zones(user_id);
CREATE INDEX idx_generator_presets_user_id ON generator_presets(user_id);
CREATE INDEX idx_ez_roads_info_osm_id ON ez_roads_info(osm_id);
CREATE INDEX idx_ez_statistics_statistic_at ON ez_statistics(statistic_at);