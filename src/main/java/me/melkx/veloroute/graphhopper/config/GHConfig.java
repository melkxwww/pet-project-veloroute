package me.melkx.veloroute.graphhopper.config;

import com.graphhopper.GraphHopper;
import com.graphhopper.config.LMProfile;
import com.graphhopper.config.Profile;
import com.graphhopper.reader.dem.SRTMProvider;
import me.melkx.veloroute.graphhopper.CustomGraphHopper;
import me.melkx.veloroute.graphhopper.CustomWeighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableConfigurationProperties(GHConfigurationProperties.class)
public class GHConfig {
    private static final String PROFILE_NAME = "custom_profile";
    private static final String NAVIGATION_MODE_KEY = "navigation_mode";
    private static final String NAVIGATION_MODE = "bike";
    private static final String SRTM_PROVIDER_CACHE_DIR_NAME = "srtm";

    private final GHConfigurationProperties properties;

    @Autowired
    public GHConfig(GHConfigurationProperties properties) {
        this.properties = properties;
    }

    @Bean
    public GraphHopper graphHopper() {
        GraphHopper hopper = new CustomGraphHopper()
                .setOSMFile(properties.osmFilePath())
                .setGraphHopperLocation(properties.graphCacheDir())
                .setProfiles(new Profile(PROFILE_NAME)
                        .setWeighting(CustomWeighting.NAME)
                        .putHint(NAVIGATION_MODE_KEY, NAVIGATION_MODE)
                );

        hopper.getCHPreparationHandler().setCHProfiles(List.of());
        hopper.getLMPreparationHandler().setLMProfiles(
                Collections.singletonList(new LMProfile(PROFILE_NAME))
        );

        File srtmCacheDir = new File(properties.graphCacheDir(), SRTM_PROVIDER_CACHE_DIR_NAME);
        if (!srtmCacheDir.exists()) {
            srtmCacheDir.mkdirs();
        }
        SRTMProvider srtmProvider = new SRTMProvider(srtmCacheDir.getAbsolutePath());
        hopper.setElevationProvider(srtmProvider);
        hopper.setElevation(true);

        hopper.importOrLoad();

        return hopper;
    }
}
