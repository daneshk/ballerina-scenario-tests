package org.ballerinalang.scenario.test.database.postgresql;

import org.ballerinalang.config.ConfigRegistry;
import org.ballerinalang.launcher.util.BCompileUtil;
import org.ballerinalang.launcher.util.BRunUtil;
import org.ballerinalang.launcher.util.CompileResult;
import org.ballerinalang.model.values.*;
import org.ballerinalang.scenario.test.common.ScenarioTestBase;
import org.ballerinalang.scenario.test.common.database.DatabaseUtil;
import org.ballerinalang.scenario.test.database.util.AssertionUtil;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Properties;


public class CallTest extends ScenarioTestBase {
    private CompileResult callCompilerResult;
    private String jdbcUrl;
    private String userName;
    private String password;
    private Path resourcePath;
    private static long date;
    private static long time;
    private static long timez;
    private static long timestamp;
    private static long timestampz;

    @BeforeClass
    public void setup() throws Exception {
        Properties deploymentProperties = getDeploymentProperties();
        jdbcUrl = deploymentProperties.getProperty(org.ballerinalang.scenario.test.database.postgresql.Constants.POSTGRES_JDBC_URL_KEY);
        userName = deploymentProperties.getProperty(Constants.POSTGRES_JDBC_USERNAME_KEY);
        password = deploymentProperties.getProperty(Constants.POSTGRES_JDBC_PASSWORD_KEY);

        ConfigRegistry registry = ConfigRegistry.getInstance();
        HashMap<String, String> configMap = new HashMap<>(3);
        configMap.put(Constants.POSTGRES_JDBC_URL_KEY, jdbcUrl);
        configMap.put(Constants.POSTGRES_JDBC_USERNAME_KEY, userName);
        configMap.put(Constants.POSTGRES_JDBC_PASSWORD_KEY, password);
        registry.initRegistry(configMap, null, null);

        resourcePath = Paths.get("src", "test", "resources").toAbsolutePath();
        DatabaseUtil.executeSqlFile(jdbcUrl, userName, password,
                Paths.get(resourcePath.toString(), "sql-src", "postgresql", "ddl-select-update-test.sql"));
        DatabaseUtil.executeSqlFile(jdbcUrl, userName, password,
                Paths.get(resourcePath.toString(), "sql-src", "postgresql", "ddl-call-test.sql"));
        DatabaseUtil.executeSqlFile(jdbcUrl, userName, password,
                Paths.get(resourcePath.toString(), "sql-src", "postgresql", "dml-call-test.sql"));
        callCompilerResult = BCompileUtil
                .compileAndSetup(Paths.get(resourcePath.toString(), "bal-src", "postgresql", "call-test.bal").toString());
        setupDateTimeData();
    }

    private void setupDateTimeData() {
        BValue[] returns = BRunUtil.invokeStateful(callCompilerResult, "setupDatetimeData");
        date = ((BInteger) returns[0]).intValue();
        time = ((BInteger) returns[1]).intValue();
        timez = ((BInteger) returns[2]).intValue();
        timestamp = ((BInteger) returns[3]).intValue();
        timestampz = ((BInteger) returns[4]).intValue();
    }


    @Test(description = "Test integer type OUT params")
    public void testCallOutParamIntegerTypes() {
        BValue[] returns = BRunUtil.invokeStateful(callCompilerResult, "testCallOutParamIntegerTypes");
        AssertionUtil.assertCallQueryReturnValue(returns[0]);
        Assert.assertTrue(returns[1] instanceof BValueArray, "Invalid type");
        BValueArray integerValues = (BValueArray) returns[1];
        Assert.assertEquals(((BInteger) integerValues.getRefValue(0)).intValue(), 32765);
        Assert.assertEquals(((BInteger) integerValues.getRefValue(1)).intValue(), 8388603);
        Assert.assertEquals(((BInteger) integerValues.getRefValue(2)).intValue(), 2147483649L);
    }

    @Test(description = "Test integer type INOUT params")
    public void testCallInOutParamIntegerTypes() {
        BValue[] returns = BRunUtil.invokeStateful(callCompilerResult, "testCallInOutParamIntegerTypes");
        AssertionUtil.assertCallQueryReturnValue(returns[0]);
        Assert.assertTrue(returns[1] instanceof BValueArray, "Invalid type");
        BValueArray integerValues = (BValueArray) returns[1];
        Assert.assertEquals(((BInteger) integerValues.getRefValue(0)).intValue(), 32765);
        Assert.assertEquals(((BInteger) integerValues.getRefValue(1)).intValue(), 8388603);
        Assert.assertEquals(((BInteger) integerValues.getRefValue(2)).intValue(), 2147483649L);
    }

    @Test(description = "Test fixed point types OUT params")
    public void testCallOutParamFixedPointTypes() {
        BValue[] returns = BRunUtil.invokeStateful(callCompilerResult, "testCallOutParamFixedPointTypes");
        AssertionUtil.assertCallQueryReturnValue(returns[0]);
        BValueArray fixedPointValues = (BValueArray) returns[1];
        Assert.assertTrue(returns[1] instanceof BValueArray, "Invalid type");
        Assert.assertEquals(((BFloat) fixedPointValues.getRefValue(0)).floatValue(), 123496789.1234567D, 0.00001);
        Assert.assertEquals(((BFloat) fixedPointValues.getRefValue(1)).floatValue(), 12345678.12345D, 0.00000001);
    }

    @Test(description = "Test fixed point types OUT params")
    public void testCallInOutParamFixedPointTypes() {
        BValue[] returns = BRunUtil.invokeStateful(callCompilerResult, "testCallInOutParamFixedPointTypes");
        AssertionUtil.assertCallQueryReturnValue(returns[0]);
        BValueArray fixedPointValues = (BValueArray) returns[1];
        Assert.assertTrue(returns[1] instanceof BValueArray, "Invalid type");
        Assert.assertEquals(((BFloat) fixedPointValues.getRefValue(0)).floatValue(), 123496789.1234567D, 0.00001);
        Assert.assertEquals(((BFloat) fixedPointValues.getRefValue(1)).floatValue(), 12345678.12345D, 0.00000001);
    }

    @Test(description = "Test string type OUT params")
    public void testCallOutParamStringTypes() {
        BValue[] returns = BRunUtil.invokeStateful(callCompilerResult, "testCallOutParamStringTypes");
        AssertionUtil.assertCallQueryReturnValue(returns[0]);
        Assert.assertTrue(returns[1] instanceof BValueArray, "Invalid type");
        BValueArray stringValues = (BValueArray) returns[1];
        String[] fieldValues = {"Varchar column", "Text column"};
        for (int i = 0; i < stringValues.size(); i++) {
            Assert.assertEquals(stringValues.getRefValue(i).stringValue(), fieldValues[i]);
        }
    }

    @Test(description = "Test string type INOUT params")
    public void testCallInOutParamStringTypes() {
        BValue[] returns = BRunUtil.invokeStateful(callCompilerResult, "testCallInOutParamStringTypes");
        AssertionUtil.assertCallQueryReturnValue(returns[0]);
        Assert.assertTrue(returns[1] instanceof BValueArray, "Invalid type");
        BValueArray stringValues = (BValueArray) returns[1];
        String[] fieldValues = {"Varchar column", "Text column"};
        for (int i = 0; i < stringValues.size(); i++) {
            Assert.assertEquals(stringValues.getRefValue(i).stringValue(), fieldValues[i]);
        }
    }

    @Test(description = "Test datetime type OUT params")
    public void testCallOutParamDateTimeValues() {
        BValue[] returns = BRunUtil.invokeStateful(callCompilerResult, "testCallOutParamDateTimeValues");
        AssertionUtil.assertCallQueryReturnValue(returns[0]);
        BValueArray valueArray = (BValueArray) returns[1];
        assertDateStringValues(valueArray, date, time, timez, timestamp, timestampz);
    }

    @Test(description = "Test datetime type INOUT params")
    public void testCallInOutParamDateTimeValues() {
        BValue[] returns = BRunUtil.invokeStateful(callCompilerResult, "testCallInOutParamDateTimeValues");
        AssertionUtil.assertCallQueryReturnValue(returns[0]);
        BValueArray valueArray = (BValueArray) returns[1];
        assertDateStringValues(valueArray, date, time, timez, timestamp, timestampz);
    }


    public static void assertDateStringValues(BValueArray datetimeRecord, long dateInserted, long timeInserted,
                                              long timezInserted, long timestampInserted, long timestampzInserted) {
        try {
            DateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
            String dateReturned = datetimeRecord.getRefValue(0).stringValue();
            long dateReturnedEpoch = dfDate.parse(dateReturned).getTime();
            Assert.assertEquals(dateReturnedEpoch, dateInserted);

            DateFormat dfTime = new SimpleDateFormat("HH:mm:ss.SSS");
            String timeReturned = datetimeRecord.getRefValue(1).stringValue();
            long timeReturnedEpoch = dfTime.parse(timeReturned).getTime();
            Assert.assertEquals(timeReturnedEpoch, timeInserted);

            DateFormat dfTimez = new SimpleDateFormat("HH:mm:ss.SSS");
            String timezReturned = datetimeRecord.getRefValue(2).stringValue();
            long timezReturnedEpoch = dfTimez.parse(timezReturned).getTime();
            Assert.assertEquals(timezReturnedEpoch, timezInserted);

            DateFormat dfTimestmap = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            String timestampReturned = datetimeRecord.getRefValue(3).stringValue();
            long timestampReturnedEpoch = dfTimestmap.parse(timestampReturned).getTime();
            Assert.assertEquals(timestampReturnedEpoch, timestampInserted);

            DateFormat dfTimestmapz = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            String timestampzReturned = datetimeRecord.getRefValue(4).stringValue();
            long timestampzReturnedEpoch = dfTimestmapz.parse(timestampzReturned).getTime();
            Assert.assertEquals(timestampzReturnedEpoch, timestampzInserted);

        } catch (ParseException e) {
            Assert.fail("Parsing the returned date/time/timestamp value has failed", e);
        }
    }


    @AfterClass(alwaysRun = true)
    public void cleanup() throws Exception {
        BRunUtil.invokeStateful(callCompilerResult, "stopDatabaseClient");
        DatabaseUtil.executeSqlFile(jdbcUrl, userName, password,
                Paths.get(resourcePath.toString(), "sql-src", "postgresql", "cleanup-call-test.sql"));
        DatabaseUtil.executeSqlFile(jdbcUrl, userName, password,
                Paths.get(resourcePath.toString(), "sql-src", "postgresql", "cleanup-select-test.sql"));
    }

}
