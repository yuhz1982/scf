package org.mydotey.scf;

import org.junit.Assert;
import org.junit.Test;
import org.mydotey.scf.ConfigurationManager;
import org.mydotey.scf.ConfigurationManagerConfig;
import org.mydotey.scf.Property;
import org.mydotey.scf.facade.ConfigurationManagers;
import org.mydotey.scf.source.stringproperties.propertiesfile.PropertiesFileConfigurationSourceConfig;

import com.google.common.collect.Lists;

/**
 * @author koqizhao
 *
 * May 17, 2018
 */
public class ConfigurationManagerTest {

    protected ConfigurationManager createManager(String fileName) {
        PropertiesFileConfigurationSourceConfig sourceConfig = ConfigurationManagers
                .newPropertiesFileSourceConfigBuilder().setName("properties-source").setPriority(1)
                .setFileName(fileName).build();
        System.out.println("source config: " + sourceConfig + "\n");
        ConfigurationManagerConfig managerConfig = ConfigurationManagers.newManagerConfigBuilder().setName("test")
                .setSources(Lists.newArrayList(ConfigurationManagers.newPropertiesFileSource(sourceConfig))).build();
        System.out.println("manager config: " + managerConfig + "\n");
        return ConfigurationManagers.newManager(managerConfig);
    }

    @Test
    public void testGetProperties() {
        ConfigurationManager manager = createManager("test.properties");
        PropertyConfig<String, String> propertyConfig = ConfigurationManagers
                .<String, String> newPropertyConfigBuilder().setKey("not-exist").setValueType(String.class).build();
        Property<String, String> property = manager.getProperty(propertyConfig);
        System.out.println("property: " + property + "\n");
        Assert.assertEquals(null, property.getValue());

        propertyConfig = ConfigurationManagers.<String, String> newPropertyConfigBuilder().setKey("not-exist2")
                .setValueType(String.class).setDefaultValue("default").build();
        property = manager.getProperty(propertyConfig);
        System.out.println("property: " + property + "\n");
        Assert.assertEquals("default", property.getValue());

        propertyConfig = ConfigurationManagers.<String, String> newPropertyConfigBuilder().setKey("exist")
                .setValueType(String.class).setDefaultValue("default").build();
        property = manager.getProperty(propertyConfig);
        System.out.println("property: " + property + "\n");
        Assert.assertEquals("ok", property.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSameKeyDifferentConfig() {
        ConfigurationManager manager = createManager("test.properties");
        PropertyConfig<String, String> propertyConfig = ConfigurationManagers
                .<String, String> newPropertyConfigBuilder().setKey("not-exist").setValueType(String.class).build();
        Property<String, String> property = manager.getProperty(propertyConfig);
        System.out.println("property: " + property + "\n");
        Assert.assertEquals(null, property.getValue());

        propertyConfig = ConfigurationManagers.<String, String> newPropertyConfigBuilder().setKey("not-exist")
                .setValueType(String.class).setDefaultValue("default").build();
        manager.getProperty(propertyConfig);
    }

}