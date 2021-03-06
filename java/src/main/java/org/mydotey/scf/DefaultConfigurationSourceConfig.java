package org.mydotey.scf;

/**
 * @author koqizhao
 *
 * May 17, 2018
 */
public class DefaultConfigurationSourceConfig implements ConfigurationSourceConfig, Cloneable {

    private String _name;

    protected DefaultConfigurationSourceConfig() {

    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public DefaultConfigurationSourceConfig clone() {
        DefaultConfigurationSourceConfig copy = null;
        try {
            copy = (DefaultConfigurationSourceConfig) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return copy;
    }

    @Override
    public String toString() {
        return String.format("%s { name: %s }", getClass().getSimpleName(), _name);
    }

    public static class Builder
            extends DefaultAbstractBuilder<ConfigurationSourceConfig.Builder, ConfigurationSourceConfig>
            implements ConfigurationSourceConfig.Builder {

    }

    @SuppressWarnings("unchecked")
    public static abstract class DefaultAbstractBuilder<B extends ConfigurationSourceConfig.AbstractBuilder<B, C>, C extends ConfigurationSourceConfig>
            implements ConfigurationSourceConfig.AbstractBuilder<B, C> {

        private DefaultConfigurationSourceConfig _config;

        protected DefaultAbstractBuilder() {
            _config = (DefaultConfigurationSourceConfig) newConfig();
        }

        protected C newConfig() {
            return (C) new DefaultConfigurationSourceConfig();
        }

        protected C getConfig() {
            return (C) _config;
        }

        @Override
        public B setName(String name) {
            _config._name = name;
            return (B) this;
        }

        @Override
        public C build() {
            if (_config._name == null || _config._name.trim().isEmpty())
                throw new IllegalArgumentException("name is null or empty");
            _config._name = _config._name.trim();

            return (C) _config.clone();
        }

    }

}
