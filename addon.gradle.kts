// The database/ORM libraries ship as a separate jar placed next to the mod jar. They cannot be shaded into the mod
// jar: Hibernate references its classes indirectly and breaks under relocation and minimization, and including the
// mod code in the same jar caused slf4j to go missing at runtime.
val depsJar = tasks.register<Jar>("depsJar") {
    group = "build"
    description = "Assembles the runtime libraries into NESQL-Exporter-<version>-deps.jar."
    archiveClassifier.set("deps")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    isZip64 = true

    val depsClasspath = configurations.getByName("depsImplementation") + configurations.getByName("depsRuntime")
    dependsOn(depsClasspath)
    from({ depsClasspath.map { if (it.isDirectory) it else zipTree(it) } })

    // Keep nested jars, drop manifests/signatures and anything at the archive root.
    exclude { element ->
        val path = element.path
        val keep = path.endsWith(".jar")
        val remove = !path.contains("/") || path.startsWith("META-INF")
        remove && !keep
    }
}

// SQL schema classes only, consumed by NESQL Server.
val sqlJar = tasks.register<Jar>("sqlJar") {
    group = "build"
    description = "Assembles the SQL entity classes into NESQL-Exporter-<version>-sql.jar."
    archiveClassifier.set("sql")
    val mainSourceSet = project.the<SourceSetContainer>().getByName("main")
    from(mainSourceSet.output)
    exclude("com/github/dcysteine/nesql/exporter")
    exclude("*.proto")
    exclude("META-INF")
    exclude("mcmod.info")
}

tasks.named("assemble") {
    dependsOn(depsJar, sqlJar)
}
