import io.swagger.converter.ModelConverter;
import io.swagger.converter.ModelConverterContext;
import io.swagger.jackson.AbstractModelConverter;
import io.swagger.models.Model;
import io.swagger.models.ModelImpl;
import io.swagger.models.properties.Property;
import io.swagger.models.properties.RefProperty;
import io.swagger.util.Json;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map.Entry;

public class GericModelConverter extends AbstractModelConverter {

    protected GericModelConverter() {
        super(Json.mapper());
    }

    @Override
    public Property resolveProperty(Type type, ModelConverterContext context, Annotation[] annotations, Iterator<ModelConverter> chain) {
        return chain.next().resolveProperty(type, context, annotations, chain);
    }

    @Override
    public Model resolve(Type type, ModelConverterContext context, Iterator<ModelConverter> converters) {
        if (type instanceof Class<?>) {
            Class<?> cls = (Class<?>) type;
            System.out.println("cls.getName(): " + cls.getName());
            if (GenericModel.class.isAssignableFrom(cls)) {
                String dataImplName = cls.getSimpleName() + "Data";

                ModelImpl dataImpl = new ModelImpl();
                dataImpl.setName(dataImplName);

                for (Field field : cls.getDeclaredFields()) {
                    System.out.println("field.getName(): " + field.getName());
                    System.out.println(field.getType());
                    dataImpl.addProperty(field.getName(), context.resolveProperty(field.getType(), null));
                }


                context.defineModel(dataImplName, dataImpl);

                ModelImpl genericImpl = new ModelImpl();
                dataImpl.setName(cls.getSimpleName());
                RefProperty dataProperty = new RefProperty(dataImplName);
                genericImpl.addProperty("data", dataProperty);
                context.defineModel(cls.getSimpleName(), genericImpl);
                return genericImpl;
            }
        }
        Model resolved = null;
        if (converters.hasNext()) {
            ModelConverter converter = converters.next();
            resolved = converter.resolve(type, context, converters);
        }
        return resolved;
    }
}