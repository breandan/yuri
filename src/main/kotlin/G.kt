// This file was generated by Yuri, a type-safe URI builder for Kotlin.
    package main.kotlin
    import main.kotlin.G.*

    abstract class G private constructor(path: String) {
    open class project_ {
@JvmName("_dot_") operator fun div(a: _dot_.Companion) = _dot_<project_>("/")

@JvmName("out") operator fun div(a: out.Companion) = out<project_>("/")

@JvmName("settings_dot_gradle") operator fun div(a: settings_dot_gradle.Companion) = settings_dot_gradle<project_>("/")

@JvmName("build") operator fun div(a: build.Companion) = build<project_>("/")

@JvmName("build_dot_gradle_dot_kts") operator fun div(a: build_dot_gradle_dot_kts.Companion) = build_dot_gradle_dot_kts<project_>("/")

@JvmName("_dot_gradle") operator fun div(a: _dot_gradle.Companion) = _dot_gradle<project_>("/")

@JvmName("README_dot_md") operator fun div(a: README_dot_md.Companion) = README_dot_md<project_>("/")

@JvmName("gradlew_dot_bat") operator fun div(a: gradlew_dot_bat.Companion) = gradlew_dot_bat<project_>("/")

@JvmName("src") operator fun div(a: src.Companion) = src<project_>("/")

@JvmName("_dot_idea") operator fun div(a: _dot_idea.Companion) = _dot_idea<project_>("/")

@JvmName("gradlew") operator fun div(a: gradlew.Companion) = gradlew<project_>("/")

@JvmName("gradle") operator fun div(a: gradle.Companion) = gradle<project_>("/")

    companion object: project_()
  }


open class _dot_<T>(path: String): G(path) { companion object }

open class `out`<T>(path: String): G(path) { companion object }

open class production<T>(path: String): G(path) { companion object }

open class classes<T>(path: String): G(path) { companion object }

open class settings_dot_gradle<T>(path: String): G(path) { companion object }

open class build<T>(path: String): G(path) { companion object }

open class generated<T>(path: String): G(path) { companion object }

open class source<T>(path: String): G(path) { companion object }

open class java<T>(path: String): G(path) { companion object }

open class kotlin<T>(path: String): G(path) { companion object }

open class resources<T>(path: String): G(path) { companion object }

open class main<T>(path: String): G(path) { companion object }

open class sessions<T>(path: String): G(path) { companion object }

open class compileKotlin<T>(path: String): G(path) { companion object }

open class tmp<T>(path: String): G(path) { companion object }

open class compileJava<T>(path: String): G(path) { companion object }

open class pluginDescriptors<T>(path: String): G(path) { companion object }

open class co_dot_ndan_dot_yuri_dot_properties<T>(path: String): G(path) { companion object }

open class build_dot_gradle_dot_kts<T>(path: String): G(path) { companion object }

open class _dot_gradle<T>(path: String): G(path) { companion object }

open class buildOutputCleanup<T>(path: String): G(path) { companion object }

open class outputFiles_dot_bin<T>(path: String): G(path) { companion object }

open class cache_dot_properties<T>(path: String): G(path) { companion object }

open class buildOutputCleanup_dot_lock<T>(path: String): G(path) { companion object }

open class vcsWorkingDirs<T>(path: String): G(path) { companion object }

open class gc_dot_properties<T>(path: String): G(path) { companion object }

open class README_dot_md<T>(path: String): G(path) { companion object }

open class gradlew_dot_bat<T>(path: String): G(path) { companion object }

open class src<T>(path: String): G(path) { companion object }

open class _dot_idea<T>(path: String): G(path) { companion object }

open class modules_dot_xml<T>(path: String): G(path) { companion object }

open class yuri_dot_iml<T>(path: String): G(path) { companion object }

open class workspace_dot_xml<T>(path: String): G(path) { companion object }

open class gradle_dot_xml<T>(path: String): G(path) { companion object }

open class compiler_dot_xml<T>(path: String): G(path) { companion object }

open class vcs_dot_xml<T>(path: String): G(path) { companion object }

open class markdown_dash_navigator<T>(path: String): G(path) { companion object }

open class profiles_settings_dot_xml<T>(path: String): G(path) { companion object }

open class markdown_dash_navigator_dot_xml<T>(path: String): G(path) { companion object }

open class modules<T>(path: String): G(path) { companion object }

open class yuri_test_dot_iml<T>(path: String): G(path) { companion object }

open class yuri_main_dot_iml<T>(path: String): G(path) { companion object }

open class libraries<T>(path: String): G(path) { companion object }

open class misc_dot_xml<T>(path: String): G(path) { companion object }

open class gradlew<T>(path: String): G(path) { companion object }

open class gradle<T>(path: String): G(path) { companion object }

open class wrapper<T>(path: String): G(path) { companion object }

open class gradle_dash_wrapper_dot_properties<T>(path: String): G(path) { companion object }

open class gradle_dash_wrapper_dot_jar<T>(path: String): G(path) { companion object }
internal open val path: String = "$path/${javaClass.simpleName}"
  override fun toString() = path
  companion object {
    fun uri(path: Any) = println(path)
}
}@JvmName("2") operator fun <S: `out`<project_>> S.div(a: production.Companion) = production<S>(path)
@JvmName("3") operator fun <S: production<`out`<project_>>> S.div(a: classes.Companion) = classes<S>(path)
@JvmName("4") operator fun <S: build<project_>> S.div(a: generated.Companion) = generated<S>(path)
@JvmName("5") operator fun <S: generated<build<project_>>> S.div(a: source.Companion) = source<S>(path)
@JvmName("6") operator fun <S: build<project_>> S.div(a: classes.Companion) = classes<S>(path)
@JvmName("7") operator fun <S: classes<build<project_>>> S.div(a: java.Companion) = java<S>(path)
@JvmName("8") operator fun <S: classes<build<project_>>> S.div(a: kotlin.Companion) = kotlin<S>(path)
@JvmName("9") operator fun <S: build<project_>> S.div(a: resources.Companion) = resources<S>(path)
@JvmName("10") operator fun <S: resources<build<project_>>> S.div(a: main.Companion) = main<S>(path)
@JvmName("11") operator fun <S: build<project_>> S.div(a: kotlin.Companion) = kotlin<S>(path)
@JvmName("12") operator fun <S: kotlin<build<project_>>> S.div(a: sessions.Companion) = sessions<S>(path)
@JvmName("13") operator fun <S: kotlin<build<project_>>> S.div(a: compileKotlin.Companion) = compileKotlin<S>(path)
@JvmName("14") operator fun <S: build<project_>> S.div(a: tmp.Companion) = tmp<S>(path)
@JvmName("15") operator fun <S: tmp<build<project_>>> S.div(a: compileJava.Companion) = compileJava<S>(path)
@JvmName("16") operator fun <S: build<project_>> S.div(a: pluginDescriptors.Companion) = pluginDescriptors<S>(path)
@JvmName("17") operator fun <S: pluginDescriptors<build<project_>>> S.div(a: co_dot_ndan_dot_yuri_dot_properties.Companion) = co_dot_ndan_dot_yuri_dot_properties<S>(path)
@JvmName("18") operator fun <S: _dot_gradle<project_>> S.div(a: buildOutputCleanup.Companion) = buildOutputCleanup<S>(path)
@JvmName("19") operator fun <S: buildOutputCleanup<_dot_gradle<project_>>> S.div(a: outputFiles_dot_bin.Companion) = outputFiles_dot_bin<S>(path)
@JvmName("20") operator fun <S: buildOutputCleanup<_dot_gradle<project_>>> S.div(a: cache_dot_properties.Companion) = cache_dot_properties<S>(path)
@JvmName("21") operator fun <S: buildOutputCleanup<_dot_gradle<project_>>> S.div(a: buildOutputCleanup_dot_lock.Companion) = buildOutputCleanup_dot_lock<S>(path)
@JvmName("22") operator fun <S: _dot_gradle<project_>> S.div(a: vcsWorkingDirs.Companion) = vcsWorkingDirs<S>(path)
@JvmName("23") operator fun <S: vcsWorkingDirs<_dot_gradle<project_>>> S.div(a: gc_dot_properties.Companion) = gc_dot_properties<S>(path)
@JvmName("24") operator fun <S: src<project_>> S.div(a: main.Companion) = main<S>(path)
@JvmName("25") operator fun <S: main<src<project_>>> S.div(a: java.Companion) = java<S>(path)
@JvmName("26") operator fun <S: main<src<project_>>> S.div(a: kotlin.Companion) = kotlin<S>(path)
@JvmName("27") operator fun <S: _dot_idea<project_>> S.div(a: modules_dot_xml.Companion) = modules_dot_xml<S>(path)
@JvmName("28") operator fun <S: _dot_idea<project_>> S.div(a: yuri_dot_iml.Companion) = yuri_dot_iml<S>(path)
@JvmName("29") operator fun <S: _dot_idea<project_>> S.div(a: workspace_dot_xml.Companion) = workspace_dot_xml<S>(path)
@JvmName("30") operator fun <S: _dot_idea<project_>> S.div(a: gradle_dot_xml.Companion) = gradle_dot_xml<S>(path)
@JvmName("31") operator fun <S: _dot_idea<project_>> S.div(a: compiler_dot_xml.Companion) = compiler_dot_xml<S>(path)
@JvmName("32") operator fun <S: _dot_idea<project_>> S.div(a: vcs_dot_xml.Companion) = vcs_dot_xml<S>(path)
@JvmName("33") operator fun <S: _dot_idea<project_>> S.div(a: markdown_dash_navigator.Companion) = markdown_dash_navigator<S>(path)
@JvmName("34") operator fun <S: markdown_dash_navigator<_dot_idea<project_>>> S.div(a: profiles_settings_dot_xml.Companion) = profiles_settings_dot_xml<S>(path)
@JvmName("35") operator fun <S: _dot_idea<project_>> S.div(a: markdown_dash_navigator_dot_xml.Companion) = markdown_dash_navigator_dot_xml<S>(path)
@JvmName("36") operator fun <S: _dot_idea<project_>> S.div(a: modules.Companion) = modules<S>(path)
@JvmName("37") operator fun <S: modules<_dot_idea<project_>>> S.div(a: yuri_test_dot_iml.Companion) = yuri_test_dot_iml<S>(path)
@JvmName("38") operator fun <S: modules<_dot_idea<project_>>> S.div(a: yuri_main_dot_iml.Companion) = yuri_main_dot_iml<S>(path)
@JvmName("39") operator fun <S: _dot_idea<project_>> S.div(a: libraries.Companion) = libraries<S>(path)
@JvmName("40") operator fun <S: _dot_idea<project_>> S.div(a: misc_dot_xml.Companion) = misc_dot_xml<S>(path)
@JvmName("41") operator fun <S: gradle<project_>> S.div(a: wrapper.Companion) = wrapper<S>(path)
@JvmName("42") operator fun <S: wrapper<gradle<project_>>> S.div(a: gradle_dash_wrapper_dot_properties.Companion) = gradle_dash_wrapper_dot_properties<S>(path)
@JvmName("43") operator fun <S: wrapper<gradle<project_>>> S.div(a: gradle_dash_wrapper_dot_jar.Companion) = gradle_dash_wrapper_dot_jar<S>(path)
