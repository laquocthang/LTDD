package vn.edu.itdlu.a1610207.calculator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Weeks;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoreFunctions {

    String[] Currency =
            {"AFN ؋", "ALL Lek", "AMD", "ANG ƒ", "AOA", "ARS $", "AUD $", "AWG ƒ", "AZN \u20BC", "BAM KM", "BBD $", "BDT",
                    "BGN лв", "BHD", "BIF", "BND $", "BOB $b", "BRL R$", "BSD $", "BTC", "BTN", "BWP P", "BYN Br",
                    "BYR", "BZD BZ$", "CAD $", "CDF", "CHF CHF", "CLP $", "CNY ¥", "COP $", "CRC ₡", "CUP ₱", "CVE",
                    "CZK Kč", "DJF", "DKK kr", "DOP RD$", "DZD", "EGP £", "ERN", "ETB", "EUR €", "FJD $", "FKP £",
                    "GBP £", "GEL", "GHS ¢", "GIP £", "GMD", "GNF", "GTQ Q", "GYD $", "HKD $", "HNL L", "HRK kn",
                    "HTG", "HUF Ft", "IDR Rp", "ILS ₪", "INR ₹", "IQD", "IRR ﷼", "ISK kr", "JMD J$", "JOD", "JPY ¥",
                    "KES", "KGS лв", "KHR ៛", "KMF", "KPW ₩", "KRW ₩", "KWD", "KYD $", "KZT лв", "LAK ₭", "LBP £",
                    "LKR ₨", "LRD $", "LSL", "LVL", "LYD", "MAD", "MDL", "MGA", "MKD ден", "MMK", "MNT ₮",
                    "MOP", "MRO", "MUR ₨", "MVR", "MWK", "MXN $", "MYR RM", "MZN MT", "NAD $", "NGN ₦", "NIO C$",
                    "NOK kr", "NPR ₨", "NZD $", "OMR ﷼", "PAB B/.", "PEN S/.", "PGK", "PHP ₱", "PKR ₨", "PLN zł", "PYG Gs",
                    "QAR ﷼", "RON lei", "RSD Дин.", "RUB \u20BD", "RWF", "SAR ﷼", "SBD $", "SCR ₨", "SDG", "SEK kr", "SGD $",
                    "SHP £", "SLL", "SOS S", "SRD $", "STD", "SYP £", "SZL", "THB ฿", "TJS", "TMT", "TND",
                    "TOP", "TRY ₺", "TTD TT$", "TWD NT$", "TZS", "UAH ₴", "UGX", "USD $", "UYU $U", "UZS лв", "VEF Bs",
                    "VND ₫", "VUV", "WST", "XAF", "XCD $", "XDR", "XOF", "XPF", "YER ﷼", "ZAR R", "ZMW"};

    /**
     * Apply decimal format for double value
     */
    String pattern = ".#########";
    Locale locale = Locale.US;

    /**
     * Length converter
     * The base unit in the International System of Units (SI) is the metre (or meter)
     */
    String[] Length =
            {"Nanometers", "10^-9", "Microns", "10^-6", "Millimeters", "10^-3",
                    "Centimeters", "10^-2", "Meters", "1", "Kilometers", "10^3",
                    "Inches", "0.0254", "Feet", "0.3048", "Yards", "0.91", "Miles", "1610"};

    /**
     * Volume converter
     * According to the SI System, the base unit for measuring volume is the metre and
     * volumes are thus measured in cubic metres
     */
    String[] Volume =
            {"Milliliters", "10^-6", "Cubic centimeters", "10^-6",
                    "Liters", "10^-3", "Cubic meters", "1", "Cubic inches", "0.000016387064",
                    "Cubic feet", "0.0283168466", "Cubic yards", "0.764554858"};

    /**
     * Mass converter
     * The basic SI unit of mass is the kilogram (kg)
     */
    String[] Weight_Mass =
            {"Carats", "0.0002", "Milligrams", "10^-6", "Centigrams", "10^-5", "Decigrams", "10^-4",
                    "Grams", "10^-3", "Dekagrams", "10^-2", "Hectograms", "10^-1", "Kilograms", "1",
                    "Ounces", "0.0283495231", "Pounds", "0.45359237"};

    /**
     * Temperature converter
     * The basic unit of temperature in the International System of Units (SI) is the kelvin
     */
    String[] Temperature = {"Celsius", "Fahrenheit", "Kelvin"};

    /**
     * Energy converter
     * the SI unit for energy is the same as the unit of work – the joule (J)
     */
    String[] Energy =
            {"Electron volts", "1.60217662×10^-19", "Joules", "1", "Kilojoules", "1000",
                    "Thermal calories", "4184", "Food calories", "4184", "Foot-pounds", "1.35581795"};

    /**
     * Data converter
     * The most commonly used units of data storage capacity are the bit
     */
    String[] Data = {"Bits", "1", "Bytes", "8",
            "Kilobits", "1000", "Kibibits", "1024", "Kilobytes", "8000", "Kibibytes", "8192",
            "Megabits", "10^6", "Mebibits", "2^20", "Megabytes", "8×10^6", "Mebibytes", "8×2^20",
            "Gigabits", "10^9", "Gibibits", "2^30", "Gigabytes", "8×10^9", "Gibibytes", "8×2^30",
            "Terabits", "10^12", "Tebibits", "2^40", "Terabytes", "8×10^12", "Tebibytes", "8×2^40",
            "Petabits", "10^15", "Pebibits", "2^50", "Petabytes", "8×10^15", "Pebibytes", "8×2^50",
            "Exabits", "10^18", "Exbibits", "2^60", "Exabytes", "8×10^18", "Exbibytes", "8×2^60",
            "Zetabits", "10^21", "Zebibits", "2^70", "Zetabytes", "8×10^21", "Zebibytes", "8×2^70",
            "Yottabits", "10^24", "Yotbibits", "2^80", "Yottabytes", "8×10^24", "Yottbibytes", "8×2^80"};

    /**
     * Area converter
     * In the International System of Units (SI), the standard unit of area is the square metre
     */
    String[] Area =
            {"Square millimeters", "10^-6", "Square centimeters", "0.0001", "Square meters", "1",
                    "Hectares", "10^4", "Square kilometers", "10^6", "Square inches", "0.00064516",
                    "Square feet", "0.09290304", "Square yards", "0.83612736", "Acres", "4046.85642",
                    "Square miles", "2589988.11"};

    /**
     * Speed converter
     * The SI unit of speed is the metre per second
     */
    String[] Speed =
            {"Centimeters per second", "0.01", "Meters per second", "1",
                    "Kilometers per hour", "0.277777778", "Feet per second", "0.3048",
                    "Miles per hour", "0.44704", "Knots", "0.514444444", "Mach", "340.29"};

    /**
     * Time converter
     * The base unit of time in the International System of Units (SI), and by extension most of the Western world, is the second
     */
    String[] Time =
            {"Microseconds", "10^-6", "Milliseconds", "0.001", "Seconds", "1", "Minutes", "60",
                    "Hours", "3600", "Days", "86400", "Weeks", "604800", "Years", "31556926"};

    /**
     * Power converter
     * In the International System of Units, the unit of power is the joule per second (J/s), known as the watt in honour of James Watt
     */
    String[] Power =
            {"Watts", "1", "Kilowatts", "1000", "Horsepower (US)", "745.699872",
                    "Foot-pounds/minute", "0.0225969658", "BTUs/minute", "17.5842642"};

    /**
     * Pressure converter
     * The unit of pressure in the SI system is the pascal (Pa), defined as a force of one Newton per square meter
     */
    String[] Pressure =
            {"Atmospheres", "101325", "Bars", "10^5", "Kilopascals", "10^3",
                    "Millimeters of mercury", "133.322368", "Pascals", "1",
                    "Pounds per square inch", "6894.75729"};

    /**
     * Angle converter
     * The SI unit of angular measure is the radian
     */
    String[] Angle = {"Degrees", "pi/180", "Radians", "1", "Gradians", "pi/200"};


    /**************************************Calculator**************************************/

    /**
     * x^y
     * reciprocal 1/x (x^-1)
     * sqr x^2
     * 10^x
     * e^x
     */
    Object pow(double x, double y) {
        return fixType(Math.pow(x, y));
    }

    /**
     * Percentage (%)
     */
    Object percentage(double n) {
        return fixType(n / 100);
    }

    /**
     * Square root of x
     */
    Object sqrt(double x) {
        return fixType(Math.sqrt(x));
    }

    /**
     * nth root of x
     */
    Object nroot(double x, double n) {
        return pow(x, 1 / n);
    }

    /**
     * Negative number
     */
    Object neg(double x) {
        return fixType(-x);
    }

    /**
     * Convert degrees to radians
     */
    Object deg2Rad(double x) {
        return fixType(Math.toRadians(x));
    }

    /**
     * Convert radians to degrees
     */
    Object rad2Deg(double x) {
        return fixType(Math.toDegrees(x));
    }

    /**
     * Common trigonometric functions
     * Such as: sin, cos, tan
     * isRadians: true if x is radians, false if x is degrees
     */
    Object trigonometric(String t, double value, boolean isRadians) {
        if (!isRadians)
            value = (double) deg2Rad(value);
        switch (t) {
            case "sin":
                return Math.sin(value);
            case "cos":
                return Math.cos(value);
            case "tan":
                return Math.tan(value);
        }
        return fixType(value);
    }

    /**
     * Common inverse trigonometric functions
     */
    Object inverse_trigonometric(String t, double value, boolean isRadians) {
        if (!isRadians)
            value = (double) deg2Rad(value);
        switch (t) {
            case "asin":
                return Math.asin(value);
            case "acos":
                return Math.acos(value);
            case "atan":
                return Math.atan(value);
        }
        return fixType(value);
    }

    /**
     * The natural logarithm (base e) of a double value
     */
    Object ln(double x) {
        return fixType(Math.log(x));
    }

    /**
     * The base 10 logarithm of a double value
     */
    Object log10(double x) {
        return fixType(Math.log10(x));
    }

    /**
     * Exponential function
     * The method returns the base of the natural logarithms, e, to the power of the argument
     */
    Object exp(double x) {
        return fixType(Math.exp(x));
    }

    /**
     * Convert decimal degrees to Degrees Minutes Seconds
     */
    String dms(double dd) {
        int d = (int) dd;
        int m = (int) ((dd - d) * 60);
        int s = (int) ((dd - d - m / 60) * 3600);
        return String.format("{0}°{1}'{2}\"", d, m, s);
    }

    /**
     * Convert Degrees Minutes Seconds to decimal degrees
     */
    double dd(String dms) {
        int d, m, s;
        d = Integer.parseInt(dms.substring(0, dms.indexOf("°") - 1));
        m = Integer.parseInt(dms.substring(dms.indexOf("°") + 1, dms.indexOf("'") - 1));
        s = Integer.parseInt(dms.substring(dms.indexOf("'") + 1, dms.indexOf("\"") - 1));
        double decimal = ((m * 60) + s) / 3600;
        return d + decimal;
    }

    /**
     * The mathematical constant π
     */
    double pi() {
        return format(Math.PI);
    }

    /**
     * The mathematical constant e
     */
    double e() {
        return Math.E; //return exp(1.0);
    }

    /**
     * Factorial of x
     */
    long factorial(int x) {
        if (x > 0)
            return x * factorial(x - 1);
        else return 1;
    }

    /**
     * Convert from base to another base
     */
    String convertFromBaseToBase(String str, int fromBase, int toBase) {
        return Long.toString(Long.parseLong(str, fromBase), toBase).toUpperCase();
    }

    /**
     * Difference between 2 dates
     */
    String different(String start, String end) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        String value = "";
        try {
            DateTime dt1 = formatter.parseDateTime(start);
            DateTime dt2 = formatter.parseDateTime(end);
            if (Years.yearsBetween(dt1, dt2).getYears() != 0)
                value += Years.yearsBetween(dt1, dt2).getYears() + " years, ";
            if (Months.monthsBetween(dt1, dt2).getMonths() != 0)
                value += Months.monthsBetween(dt1, dt2).getMonths() + " months, ";
            if (Weeks.weeksBetween(dt1, dt2).getWeeks() != 0)
                value += Weeks.weeksBetween(dt1, dt2).getWeeks() + " weeks, ";
            if (Days.daysBetween(dt1, dt2).getDays() != 0)
                value += Days.daysBetween(dt1, dt2).getDays() + " days";
            if (dt1.equals(dt2))
                value = "Same day";
        } catch (Exception e) {
            return null;
        }
        return value;
    }

    /**
     * Date1 add or subtract date2
     */
    String changeDay(String date1, Map<Character, Integer> date2, char c) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        DateTime dt1;
        try {
            dt1 = formatter.parseDateTime(date1);
            switch (c) {
                case '+':
                    dt1 = dt1.plusDays(date2.get('d'));
                    dt1 = dt1.plusMonths(date2.get('m'));
                    dt1 = dt1.plusYears(date2.get('y'));
                    break;
                case '-':
                    dt1 = dt1.minusDays(date2.get('d'));
                    dt1 = dt1.minusMonths(date2.get('m'));
                    dt1 = dt1.minusYears(date2.get('y'));
                    break;
            }
        } catch (Exception e) {
            return null;
        }
        return formatter.print(dt1);
    }

    /**
     * Get HTML source from link
     */
    String getHTMLSource(String url) throws IOException {
        HttpClient httpclient = new DefaultHttpClient(); // Create HTTP Client
        HttpGet httpget = new HttpGet(url); // Set the action you want to do
        HttpResponse response = httpclient.execute(httpget); // Executeit
        HttpEntity entity = response.getEntity();
        InputStream is = entity.getContent(); // Create an InputStream with the response
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) // Read line by line
            sb.append(line + "\n");

        String resString = sb.toString(); // Result is here

        is.close(); // Close the stream
        return resString;
    }

    /**************************************Converter**************************************/

    /**
     * Try to fix proper type of value
     */
    Object fixType(double value) {
        double decimal = value - (long) value;
        if (decimal == 0f)
            return (long) value;
        else return format(value);
    }

    /**
     * Return a string whose value was formatted by decimalFormat
     */
    double format(double value) {
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern(pattern);
        String temp = decimalFormat.format(value);
        return Double.parseDouble(temp);
    }

    /**
     * Get the exchange rates from website
     */
    double getExchangeRate(String unit1, String unit2) {
        String url = "https://free.currencyconverterapi.com/api/v6/convert?q=" + unit1 + "_" + unit2 + "&compact=y";
        String content;
        try {
            content = getHTMLSource(url);
        } catch (IOException i) {
            content = null;
        }
        Pattern pattern = Pattern.compile("([\\d.]+)");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find())
            return Double.parseDouble(matcher.group());
        else return 0;
    }

    /**
     * Convert string to double, especially if it have power symbol
     */
    Object convertString(String string) {
        try {
            if (string.contains("^")) {
                if (string.contains("×")) {     //s1 × 10 ^ s2
                    String s1 = string.split("\\^")[0].split("×")[0];
                    String s2 = string.split("\\^")[1];
                    double power = (double) pow(10, Double.parseDouble(s2));
                    return fixType(Double.parseDouble(s1) * power);
                } else {
                    String t = string.split("\\^")[1];
                    return pow(10, Double.parseDouble(t));
                }
            } else if (string.contains("/")) {
                String[] temp = string.split("/");
                return pi() / Integer.parseInt(temp[1]);
            } else return fixType(Double.parseDouble(string));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /************************Rule of function***********************
     ****** array is the reference unit for converter function******
     ************ id1 is index of the unit of the value*************
     ************ id2 is index of the unit of result****************/

    /**
     * The temperature converter function
     */
    Object temperatureConverter(int id1, Object value, int id2) {
        if (id1 == id2)
            return value;
        else {
            double _value = Double.parseDouble("" + value);
            if (id1 == 0) {
                return id2 == 1 ? 9 / 5 * _value + 32 : _value + 273;
            } else if (id1 == 1) {
                return id2 == 0 ? 5 / 9 * (_value - 32) : 5 / 9 * (_value - 32) + 273;
            } else {
                return id2 == 0 ? _value - 273 : 9 / 5 * (_value - 273) + 32;
            }
        }
    }

    /**
     * Find text in array
     */
    int findIndexInArray(String[] array, String text) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            if (array[i].equals(text))
                return i;
        }
        return -1;
    }

    /**
     * Other unit converter function
     */
    Object otherConverter(String[] array, int id1, Object value, int id2) {
        if (id1 == id2)
            return value;
        else {
            //1 unit of id1 = ? unit of base
            double base1 = Double.parseDouble("" + convertString(array[id1 + 1]));
            //1 unit of id2 = ? unit of base
            double base2 = Double.parseDouble("" + convertString(array[id2 + 1]));
            //Get crosshair value
            return fixType(Double.parseDouble("" + value) * base1 / base2);
        }
    }
}
