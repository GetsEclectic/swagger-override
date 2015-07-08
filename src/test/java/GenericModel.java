import org.joda.time.DateTime;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class GenericModel {
        public static final String INVALID_POSTAL_CODE = "no postal code or invalid postal code provided";
        private static final Pattern US_ZIP_PATTERN = Pattern.compile("^\\d{5}(-\\d{4})?$");
        public static final int CVV_MIN_LENGTH = 3;
        public static final int CVV_MAX_LENGTH = 4;
        private String name;
        private String firstName;
        private String lastName;
        private String address1;
        private String address2;
        private String city;
        private String state;
        private String postalCode;
        private String country;
        private boolean useStratus = false;
        private String number;
        private String lastFourDigits;
        private DateTime expirationDate;
        private String groupId;
        private String cvv;
        private String cardAlias;
        private Boolean defaultInstrument;
        // this amount is not used for stratus, the caller should not be determining the amount
        private String amount;
        private BigDecimal updateCreditCardId;
        private BigDecimal updatePaypalId;
        // this is for testing purposes only
        private Boolean forceStratusFailure = false;
        private String currencyCode = "USD";
        private ASubClass aSubClass;


        public class ASubClass {
                public int number;
        }
}
