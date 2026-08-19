package me.melkx.veloroute.graphhopper.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "veloroute.graphhopper")
public record GHConfigurationProperties(String osmFilePath, String graphCacheDir) {
}
