package org.mydotey.scf;

import java.util.Collection;

import org.mydotey.scf.threading.TaskExecutor;

/**
 * @author koqizhao
 *
 * May 17, 2018
 */
public interface ConfigurationManagerConfig {

    String getName();

    Collection<ConfigurationSource> getSources();

    TaskExecutor getTaskExecutor();

    public interface Builder extends AbstractBuilder<Builder> {

    }

    public interface AbstractBuilder<B extends AbstractBuilder<B>> {

        B setName(String name);

        B setSources(Collection<ConfigurationSource> sources);

        B setTaskExecutor(TaskExecutor taskExecutor);

        ConfigurationManagerConfig build();

    }

}