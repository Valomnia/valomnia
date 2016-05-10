
package org.mule.modules.valomnia.generated.transformers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Generated;
import org.mule.api.transformer.DiscoverableTransformer;
import org.mule.api.transformer.TransformerException;
import org.mule.config.i18n.MessageFactory;
import org.mule.transformer.AbstractTransformer;
import org.mule.transformer.types.DataTypeFactory;

@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.8.0", date = "2016-05-10T10:44:20+01:00", comments = "Build UNNAMED.2762.e3b1307")
public class StringToDateTransformer
    extends AbstractTransformer
    implements DiscoverableTransformer
{

    private final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
    private int priorityWeighting = 1;

    public StringToDateTransformer() {
        registerSourceType(DataTypeFactory.create(String.class));
        setReturnClass(Date.class);
        setName("StringToDateTransformer");
    }

    protected Object doTransform(Object src, String encoding)
        throws TransformerException
    {
        try {
            return SIMPLE_DATE_FORMAT.parse(src.toString());
        } catch (ParseException e) {
            throw new TransformerException(MessageFactory.createStaticMessage(String.format("Could not parse %s using the format %s", src, SIMPLE_DATE_FORMAT.toPattern())), this, e);
        }
    }

    /**
     * Retrieves priorityWeighting
     * 
     */
    public int getPriorityWeighting() {
        return this.priorityWeighting;
    }

    /**
     * Sets priorityWeighting
     * 
     * @param value Value to set
     */
    public void setPriorityWeighting(int value) {
        this.priorityWeighting = value;
    }

}
