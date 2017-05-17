package cn.linzehao.gradle

import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.BaseVariant
import org.gradle.api.DomainObjectCollection
import com.android.build.gradle.AppPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.internal.DefaultDomainObjectSet

public class PluginImpl implements Plugin<Project> {
    private static final String HASH_TXT = "hash.txt"

    void apply(Project project) {
        println(3333)

        DefaultDomainObjectSet<ApplicationVariant> variants
        if (project.getPlugins().hasPlugin(AppPlugin)) {
            variants = project.android.applicationVariants;

//            project.extensions.create(EXTENSION_NAME, RocooFixExtension);
            applyTask(project, variants);
        }
    }

    private void applyTask(Project project, DomainObjectCollection<BaseVariant> variants) {
        project.afterEvaluate {
            println(4444)
            variants.all {

                println(55555)

            }
        }

    }
}