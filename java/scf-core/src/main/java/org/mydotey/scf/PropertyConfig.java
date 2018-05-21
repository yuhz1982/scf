package org.mydotey.scf;

import java.util.Collection;
import java.util.function.Function;

import org.mydotey.scf.type.TypeConverter;

/**
 * @author koqizhao
 *
 * May 17, 2018
 */
@SuppressWarnings("rawtypes")
public interface PropertyConfig<K, V> {

    K getKey();

    Class<V> getValueType();

    V getDefaultValue();

    Collection<TypeConverter> getValueConverters();

    Collection<Function<V, V>> getValueFilters();

    public interface Builder<K, V> extends AbstractBuilder<K, V, Builder<K, V>> {

    }

    public interface AbstractBuilder<K, V, B extends AbstractBuilder<K, V, B>> {

        B setKey(K key);

        B setValueType(Class<V> valueType);

        B setDefaultValue(V value);

        B setValueConverters(Collection<TypeConverter> valueConverters);

        B setValueFilters(Collection<Function<V, V>> valueFilters);

        PropertyConfig<K, V> build();
    }

}