package io.boxtape.core;

import java.io.File;
import java.io.IOException;
import java.util.List;
import io.boxtape.core.configuration.BoxtapeSettings;
import io.boxtape.core.configuration.Configuration;
import org.apache.maven.cli.MavenCli;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

public class MavenDependencyCollectorTest {

    MavenDependencyCollector collector;
    File sampleProjectHome = new File("./sample-project");
    Project sampleProject;
    @Before
    public void setup() throws IOException {
        MavenCli cli = new MavenCli();
        collector = new MavenDependencyCollector(cli);
        assertThat(sampleProjectHome.exists(), is(true));
        sampleProject = new Project(sampleProjectHome.getCanonicalPath() , new Configuration(), new BoxtapeSettings());

    }

    @Test
    public void givenSampleProject_shouldResolveDependencies() throws IOException {
        List<Dependency> dependencies = collector.collect(sampleProject);
        Dependency mySql = new Dependency("mysql","mysql-connector-java","5.1.34");
        assertThat(dependencies, not(empty()));
        assertThat(dependencies, hasItem(mySql));
    }


}
