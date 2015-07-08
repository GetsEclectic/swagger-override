import io.swagger.converter.ModelConverters;

import io.swagger.models.Model;
import io.swagger.util.Json;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Map;

public class OverrideTest {
    @Test
    public void test() {
        // create new instead of use singleton
        ModelConverters converters = new ModelConverters();
        converters.addConverter(new GericModelConverter());
        Map<String, Model> read = converters.readAll(GenericModel.class);

        Json.prettyPrint(read);
    }
}
