package com.samtuga.rmufilemanagement.customIdGenerator;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Properties;

public class PrefixedCustomId extends SequenceStyleGenerator {
    public static final String PREFIX_VALUE = "prefix";
    public static final String DEFAULT_PREFIX_VALUE = "";
    private String prefixValue;

    public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
    public static final String NUMBER_FORMAT_DEFAULT = "%d";
    private String numberFormat;
    private String dateFormat = LocalDate.now().toString();
    private String dateString = dateFormat.replace("-","");

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object)
            throws HibernateException {
        return prefixValue + dateString + String.format(numberFormat,super.generate(session, object));
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry)
            throws MappingException {
        super.configure(LongType.INSTANCE, params, serviceRegistry);
        prefixValue = ConfigurationHelper.getString(PREFIX_VALUE, params, DEFAULT_PREFIX_VALUE);
        numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER, params, NUMBER_FORMAT_DEFAULT);

    }
}
