package com.example.demo.application.infrastructure.flink;

import com.example.demo.application.domain.JobSubmitter;
import org.apache.flink.api.common.JobID;
import org.apache.flink.api.common.JobSubmissionResult;
import org.apache.flink.client.program.PackagedProgram;
import org.apache.flink.client.program.rest.RestClusterClient;
import org.apache.flink.runtime.jobmaster.JobResult;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class FlinkJobSubmitter implements JobSubmitter {

    private static final String JOB_ARGS_PREFIX = "job.args.";
    private static final String JOB_NAME_KEY = "jobName";
    private final FlinkProperties flinkProperties;
    private final RestClusterClient restClusterClient;
    private final PackagedProgramFactory packagedProgramFactory;


    public FlinkJobSubmitter(FlinkProperties flinkProperties, RestClusterClient restClusterClient, PackagedProgramFactory packagedProgramFactory) {
        this.flinkProperties = flinkProperties;
        this.restClusterClient = restClusterClient;
        this.packagedProgramFactory = packagedProgramFactory;
    }

    @Override
    public String submitJobDetached(String jobName, Map<String, String> jobArgs) throws Exception {
        File jobJar = Paths.get(flinkProperties.getJobJarRootPath(), flinkProperties.getJobJarName()).toAbsolutePath().toFile();
        jobArgs.put(JOB_NAME_KEY, jobName);
        PackagedProgram packagedProgram = packagedProgramFactory.getInstance(jobJar, buildCliArgs(jobArgs));
        JobSubmissionResult jobSubmissionResult = restClusterClient.run(packagedProgram, flinkProperties.getDefaultParallelism());
        return jobSubmissionResult.getJobID().toString();
    }

    @Override
    public String submitJob(String jobName, Map<String, String> jobArgs) throws Exception {
        String jobId = submitJobDetached(jobName, jobArgs);
        getJobResult(jobId).get();
        return jobId;
    }

    private CompletableFuture<JobResult> getJobResult(String jobId) {
        return restClusterClient.requestJobResult(JobID.fromHexString(jobId));
    }

    private String[] buildCliArgs(Map<String, String> jobArgs) {

        return jobArgs.entrySet().stream()
            .map(entry -> buildArgString(JOB_ARGS_PREFIX + entry.getKey(), entry.getValue()))
            .toArray(String[]::new);
    }

    private String buildArgString(String key, String value) {
        return "--" + key + "=" + value;
    }
}