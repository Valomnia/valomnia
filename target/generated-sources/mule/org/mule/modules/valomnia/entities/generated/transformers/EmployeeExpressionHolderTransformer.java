
package org.mule.modules.valomnia.entities.generated.transformers;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.endpoint.ImmutableEndpoint;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.transformer.DataType;
import org.mule.api.transformer.DiscoverableTransformer;
import org.mule.api.transformer.MessageTransformer;
import org.mule.api.transformer.TransformerException;
import org.mule.api.transformer.TransformerMessagingException;
import org.mule.config.i18n.CoreMessages;
import org.mule.devkit.processor.ExpressionEvaluatorSupport;
import org.mule.modules.valomnia.entities.Employee;
import org.mule.modules.valomnia.entities.generated.holders.EmployeeExpressionHolder;
import org.mule.transformer.types.DataTypeFactory;

@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.8.0", date = "2016-05-10T10:57:41+01:00", comments = "Build UNNAMED.2762.e3b1307")
public class EmployeeExpressionHolderTransformer
    extends ExpressionEvaluatorSupport
    implements DiscoverableTransformer, MessageTransformer
{

    private int weighting = DiscoverableTransformer.DEFAULT_PRIORITY_WEIGHTING;
    private ImmutableEndpoint endpoint;
    private MuleContext muleContext;
    private String name;

    public int getPriorityWeighting() {
        return weighting;
    }

    public void setPriorityWeighting(int weighting) {
        this.weighting = weighting;
    }

    public boolean isSourceTypeSupported(Class<?> aClass) {
        return (aClass == EmployeeExpressionHolder.class);
    }

    public boolean isSourceDataTypeSupported(DataType<?> dataType) {
        return (dataType.getType() == EmployeeExpressionHolder.class);
    }

    public List<Class<?>> getSourceTypes() {
        return Arrays.asList(new Class<?> [] {EmployeeExpressionHolder.class });
    }

    public List<DataType<?>> getSourceDataTypes() {
        return Arrays.asList(new DataType<?> [] {DataTypeFactory.create(EmployeeExpressionHolder.class)});
    }

    public boolean isAcceptNull() {
        return false;
    }

    public boolean isIgnoreBadInput() {
        return false;
    }

    public Object transform(Object src)
        throws TransformerException
    {
        throw new UnsupportedOperationException();
    }

    public Object transform(Object src, String encoding)
        throws TransformerException
    {
        throw new UnsupportedOperationException();
    }

    public void setReturnClass(Class<?> theClass) {
        throw new UnsupportedOperationException();
    }

    public Class<?> getReturnClass() {
        return Employee.class;
    }

    public void setReturnDataType(DataType<?> type) {
        throw new UnsupportedOperationException();
    }

    public DataType<?> getReturnDataType() {
        return DataTypeFactory.create(Employee.class);
    }

    public void setEndpoint(ImmutableEndpoint ep) {
        endpoint = ep;
    }

    public ImmutableEndpoint getEndpoint() {
        return endpoint;
    }

    public void dispose() {
    }

    public void initialise()
        throws InitialisationException
    {
    }

    public void setMuleContext(MuleContext context) {
        muleContext = context;
    }

    public void setName(String newName) {
        name = newName;
    }

    public String getName() {
        return name;
    }

    public Object transform(Object src, MuleEvent event)
        throws TransformerMessagingException
    {
        return transform(src, null, event);
    }

    public Object transform(Object src, String encoding, MuleEvent event)
        throws TransformerMessagingException
    {
        if (src == null) {
            return null;
        }
        EmployeeExpressionHolder holder = ((EmployeeExpressionHolder) src);
        Employee result = new Employee();
        try {
            final String _transformedId = ((String) evaluateAndTransform(this.muleContext, event, EmployeeExpressionHolder.class.getDeclaredField("_idType").getGenericType(), null, holder.getId()));
            result.setId(_transformedId);
            final String _transformedLastName = ((String) evaluateAndTransform(this.muleContext, event, EmployeeExpressionHolder.class.getDeclaredField("_lastNameType").getGenericType(), null, holder.getLastName()));
            result.setLastName(_transformedLastName);
            final String _transformedOrganization = ((String) evaluateAndTransform(this.muleContext, event, EmployeeExpressionHolder.class.getDeclaredField("_organizationType").getGenericType(), null, holder.getOrganization()));
            result.setOrganization(_transformedOrganization);
            final String _transformedLastUpdated = ((String) evaluateAndTransform(this.muleContext, event, EmployeeExpressionHolder.class.getDeclaredField("_lastUpdatedType").getGenericType(), null, holder.getLastUpdated()));
            result.setLastUpdated(_transformedLastUpdated);
            final String _transformedDateCreated = ((String) evaluateAndTransform(this.muleContext, event, EmployeeExpressionHolder.class.getDeclaredField("_dateCreatedType").getGenericType(), null, holder.getDateCreated()));
            result.setDateCreated(_transformedDateCreated);
            final String _transformedManagerReference = ((String) evaluateAndTransform(this.muleContext, event, EmployeeExpressionHolder.class.getDeclaredField("_managerReferenceType").getGenericType(), null, holder.getManagerReference()));
            result.setManagerReference(_transformedManagerReference);
            final String _transformedAvatar = ((String) evaluateAndTransform(this.muleContext, event, EmployeeExpressionHolder.class.getDeclaredField("_avatarType").getGenericType(), null, holder.getAvatar()));
            result.setAvatar(_transformedAvatar);
            final String _transformedFirstName = ((String) evaluateAndTransform(this.muleContext, event, EmployeeExpressionHolder.class.getDeclaredField("_firstNameType").getGenericType(), null, holder.getFirstName()));
            result.setFirstName(_transformedFirstName);
            final String _transformedEmployeeGroupReference = ((String) evaluateAndTransform(this.muleContext, event, EmployeeExpressionHolder.class.getDeclaredField("_employeeGroupReferenceType").getGenericType(), null, holder.getEmployeeGroupReference()));
            result.setEmployeeGroupReference(_transformedEmployeeGroupReference);
            final String _transformedReference = ((String) evaluateAndTransform(this.muleContext, event, EmployeeExpressionHolder.class.getDeclaredField("_referenceType").getGenericType(), null, holder.getReference()));
            result.setReference(_transformedReference);
            final String _transformedMobile = ((String) evaluateAndTransform(this.muleContext, event, EmployeeExpressionHolder.class.getDeclaredField("_mobileType").getGenericType(), null, holder.getMobile()));
            result.setMobile(_transformedMobile);
        } catch (NoSuchFieldException e) {
            throw new TransformerMessagingException(CoreMessages.createStaticMessage("internal error"), event, this, e);
        } catch (TransformerException e) {
            throw new TransformerMessagingException(e.getI18nMessage(), event, this, e);
        }
        return result;
    }

    public MuleEvent process(MuleEvent event) {
        return null;
    }

    public String getMimeType() {
        return null;
    }

    public String getEncoding() {
        return null;
    }

    public MuleContext getMuleContext() {
        return muleContext;
    }

}
