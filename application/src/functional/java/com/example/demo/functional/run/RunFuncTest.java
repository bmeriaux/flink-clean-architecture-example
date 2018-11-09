package com.example.demo.functional.run;

import com.example.demo.application.FlinkCleanArchitectureApplication;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.minicluster.MiniCluster;
import org.apache.flink.runtime.minicluster.MiniClusterConfiguration;
import org.apache.flink.runtime.minicluster.RpcServiceSharing;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"pretty", "html:build/reports/cucumber"},
    features = "src/functional/resources/feature",
    glue = "com/example/demo/functional/steps"
)
public class RunFuncTest {
    public static Integer serverPort;

    private static final Logger LOGGER = LoggerFactory.getLogger(RunFuncTest.class);

    private static ConfigurableApplicationContext configurableApplicationContext;
    private static WebServer webServer;
    private static MiniCluster miniCluster;

    @BeforeClass
    public static void init() throws Exception {
        setupMiniLocalFlinkCluster();
        setupSpringBootApplication();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        miniCluster.close();
        webServer.stop();
        configurableApplicationContext.stop();
        configurableApplicationContext.close();
    }

    private static void setupSpringBootApplication() {
        configurableApplicationContext = SpringApplication.run(FlinkCleanArchitectureApplication.class);
        webServer = ((AnnotationConfigServletWebServerApplicationContext) configurableApplicationContext).getWebServer();
        serverPort = webServer.getPort();
        if (configurableApplicationContext.isRunning()) {
            LOGGER.info("embedded server started on port: {}", serverPort);
        }
    }

    private static void setupMiniLocalFlinkCluster() throws Exception {
        Configuration configuration = new Configuration();
        MiniClusterConfiguration cfg = new MiniClusterConfiguration.Builder()
            .setRpcServiceSharing(RpcServiceSharing.SHARED)
            .setNumSlotsPerTaskManager(5)
            .setConfiguration(configuration)
            .build();
        miniCluster = new MiniCluster(cfg);
        miniCluster.start();
    }
}