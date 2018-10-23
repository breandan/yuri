plugins {
    application
    kotlin("jvm") version "1.3.0-rc-190"
    //id("yuri-plugin") version "0.1-SNAPSHOT" apply true
}

application {
    mainClassName = "samples.HelloYuriKt"
}

dependencies {
    compile(kotlin("stdlib"))
}

repositories {
    jcenter()
}
