package com.example.demo.application.infrastructure.flink;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("flink")
public class FlinkProperties {

    private Integer defaultParallelism;
    private String managerHost;
    private Integer managerRpcPort;
    private String restAddress;
    private Integer restPort;
    private String jobJarRootPath;
    private String jobJarName;

    public Integer getDefaultParallelism() {
        return defaultParallelism;
    }

    public void setDefaultParallelism(Integer defaultParallelism) {
        this.defaultParallelism = defaultParallelism;
    }

    public String getManagerHost() {
        return managerHost;
    }

    public void setManagerHost(String managerHost) {
        this.managerHost = managerHost;
    }

    public Integer getManagerRpcPort() {
        return managerRpcPort;
    }

    public void setManagerRpcPort(Integer managerRpcPort) {
        this.managerRpcPort = managerRpcPort;
    }

    public String getRestAddress() {
        return restAddress;
    }

    public void setRestAddress(String restAddress) {
        this.restAddress = restAddress;
    }

    public Integer getRestPort() {
        return restPort;
    }

    public void setRestPort(Integer restPort) {
        this.restPort = restPort;
    }

    public String getJobJarRootPath() {
        return jobJarRootPath;
    }

    public void setJobJarRootPath(String jobJarRootPath) {
        this.jobJarRootPath = jobJarRootPath;
    }

    public String getJobJarName() {
        return jobJarName;
    }

    public void setJobJarName(String jobJarName) {
        this.jobJarName = jobJarName;
    }
}
