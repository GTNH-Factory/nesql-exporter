
plugins {
    id("com.gtnewhorizons.gtnhconvention")
    id("com.google.protobuf") version "0.10.0"
}

// Protocol buffer code generation for the hashed entity IDs.
protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${project.property("protoBufferVersion")}"
    }
}
