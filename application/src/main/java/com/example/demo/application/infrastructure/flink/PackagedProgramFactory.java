package com.example.demo.application.infrastructure.flink;

import org.apache.flink.client.program.PackagedProgram;
import org.apache.flink.client.program.ProgramInvocationException;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class PackagedProgramFactory {

    public PackagedProgram getInstance(File jarFile, String... args) throws ProgramInvocationException {
        return new PackagedProgram(jarFile, args);
    }
}
