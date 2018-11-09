package com.example.demo.application.infrastructure.flink;


import org.apache.flink.client.program.rest.RestClusterClient;
import org.apache.flink.configuration.JobManagerOptions;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.configuration.SecurityOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlinkConfig {

    @Bean
    public RestClusterClient clusterClient(FlinkProperties flinkProperties) throws Exception {
        org.apache.flink.configuration.Configuration config = new org.apache.flink.configuration.Configuration();
        config.setBoolean(SecurityOptions.SSL_INTERNAL_ENABLED, false);
        config.setBoolean(SecurityOptions.SSL_REST_ENABLED, false);
        config.setString(JobManagerOptions.ADDRESS, flinkProperties.getManagerHost());
        config.setInteger(JobManagerOptions.PORT, flinkProperties.getManagerRpcPort());
        config.setString(RestOptions.ADDRESS, flinkProperties.getManagerHost());
        RestClusterClient restClusterClient = new RestClusterClient<>(config, "clean-archi-demo-rest-client");
        restClusterClient.setDetached(true);
        return restClusterClient;
    }
}
