CREATE TABLE users(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    is_activated BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE exploration_zones(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    name VARCHAR(100) NOT NULL,
    boundary GEOMETRY(POLYGON, 4326) NOT NULL,
    roads_count INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE ez_statistics(
    zone_id BIGINT NOT NULL REFERENCES exploration_zones(id) ON DELETE CASCADE,
    osm_id BIGINT NOT NULL,
    visit_count INT NOT NULL,
    total_rating DOUBLE PRECISION NOT NULL, -- обновляется по расписанию и при различных событиях
    first_visited TIMESTAMP NOT NULL,
    last_visited TIMESTAMP,
    PRIMARY KEY (zone_id, osm_id)
);

CREATE TABLE generator_presets(
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
    zone_id BIGINT NOT NULL REFERENCES exploration_zones(id),
    preset_snapshot JSONB NOT NULL,
    path GEOMETRY(LINESTRING, 4326) NOT NULL,
    distance_km DOUBLE PRECISION NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE saved_routes (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    route_id BIGINT REFERENCES routes(id) ON DELETE CASCADE UNIQUE,
    name VARCHAR(100) NOT NULL,
    is_favorited BOOLEAN NOT NULL,
    usage_count INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE trips (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    route_id BIGINT REFERENCES routes(id) ON DELETE CASCADE NOT NULL,
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
CREATE INDEX idx_ez_statistics_zone_id ON ez_statistics(zone_id);
CREATE INDEX idx_exploration_zones_user_id ON exploration_zones(user_id);
CREATE INDEX idx_generator_presets_user_id ON generator_presets(user_id);