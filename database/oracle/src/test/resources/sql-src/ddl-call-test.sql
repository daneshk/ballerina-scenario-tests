CREATE OR REPLACE PROCEDURE "CALL_TEST_IN_INTEGER_TYPES" (id IN NUMBER, int_val IN NUMBER) IS
BEGIN
    INSERT INTO SELECT_UPDATE_INTEGER_TYPES VALUES(id, int_val);
END;
/
CREATE OR REPLACE PROCEDURE "CALL_TEST_OUT_INTEGER_TYPES" (integer_id IN NUMBER, int_val OUT NUMBER) IS
BEGIN
    SELECT INT_VAL INTO int_val FROM SELECT_UPDATE_INTEGER_TYPES WHERE ID = integer_id;
END;
/
CREATE OR REPLACE PROCEDURE "CALL_TEST_INOUT_INTEGER_TYPES" (integer_id_1 IN NUMBER, int_val IN OUT NUMBER) IS
BEGIN
    INSERT INTO SELECT_UPDATE_INTEGER_TYPES VALUES(integer_id_1, int_val);
    SELECT INT_VAL INTO int_val FROM SELECT_UPDATE_INTEGER_TYPES WHERE ID = integer_id_1;
END;
/
CREATE OR REPLACE PROCEDURE "CALL_IN_FIXEDPOINT_TYPES" (id IN NUMBER, numeric_val IN NUMBER, decimal_val IN NUMBER) IS
BEGIN
    INSERT INTO SELECT_UPDATE_FIXEDPOINT_TYPES VALUES(id, numeric_val, decimal_val);
END;
/
CREATE OR REPLACE PROCEDURE "CALL_OUT_FIXEDPOINT_TYPES" (fix_point_id IN NUMBER, numeric_val OUT NUMBER, decimal_val OUT NUMBER) IS
BEGIN
    SELECT NUMERIC_VAL INTO numeric_val FROM SELECT_UPDATE_FIXEDPOINT_TYPES WHERE ID = fix_point_id;
    SELECT DECIMAL_VAL INTO decimal_val FROM SELECT_UPDATE_FIXEDPOINT_TYPES WHERE ID = fix_point_id;
END;
/
CREATE OR REPLACE PROCEDURE "CALL_INOUT_FIXEDPOINT_TYPES" (fix_point_id_1 IN NUMBER, numeric_val IN OUT NUMBER, decimal_val IN OUT NUMBER) IS
BEGIN
    INSERT INTO SELECT_UPDATE_FIXEDPOINT_TYPES VALUES(fix_point_id_1, numeric_val, decimal_val);
    SELECT NUMERIC_VAL INTO numeric_val FROM SELECT_UPDATE_FIXEDPOINT_TYPES WHERE ID = fix_point_id_1;
    SELECT DECIMAL_VAL INTO decimal_val FROM SELECT_UPDATE_FIXEDPOINT_TYPES WHERE ID = fix_point_id_1;
END;
/
CREATE OR REPLACE PROCEDURE "CALL_IN_FLOAT_TYPES" (id IN NUMBER, float_val IN BINARY_FLOAT, double_val IN BINARY_DOUBLE) IS
BEGIN
    INSERT INTO SELECT_UPDATE_FLOAT_TYPES VALUES(id, float_val, double_val);
END;
/
CREATE OR REPLACE PROCEDURE "CALL_OUT_FLOAT_TYPES" (float_id IN NUMBER, float_val OUT BINARY_FLOAT, double_val OUT BINARY_DOUBLE) IS
BEGIN
    SELECT FLOAT_VAL INTO float_val FROM SELECT_UPDATE_FLOAT_TYPES WHERE ID = float_id;
    SELECT DOUBLE_VAL INTO double_val FROM SELECT_UPDATE_FLOAT_TYPES WHERE ID = float_id;
END;
/
CREATE OR REPLACE PROCEDURE "CALL_INOUT_FLOAT_TYPES" (float_id_1 IN NUMBER, float_val IN OUT BINARY_FLOAT, double_val IN OUT BINARY_DOUBLE) IS
BEGIN
    INSERT INTO SELECT_UPDATE_FLOAT_TYPES VALUES(float_id_1, float_val, double_val);
    SELECT FLOAT_VAL INTO float_val FROM SELECT_UPDATE_FLOAT_TYPES WHERE ID = float_id_1;
    SELECT DOUBLE_VAL INTO double_val FROM SELECT_UPDATE_FLOAT_TYPES WHERE ID = float_id_1;
END;
/
CREATE OR REPLACE PROCEDURE "CALL_IN_STRING_TYPES" (id IN NUMBER, char_val IN CHAR, nchar_val IN NCHAR,
varchar2_val IN VARCHAR2, nvarchar_val IN NVARCHAR2) IS
BEGIN
    INSERT INTO SELECT_UPDATE_STRING_TYPES VALUES(id, char_val, nchar_val, varchar2_val, nvarchar_val);
END;
/
CREATE OR REPLACE PROCEDURE "CALL_OUT_STRING_TYPES" (string_id IN NUMBER, char_val OUT CHAR, nchar_val OUT NCHAR,
                                                     varchar2_val OUT VARCHAR2, nvarchar_val OUT NVARCHAR2) IS
BEGIN
    SELECT CHAR_VAL INTO char_val FROM SELECT_UPDATE_STRING_TYPES WHERE ID = string_id;
    SELECT NCHAR_VAL INTO nchar_val FROM SELECT_UPDATE_STRING_TYPES WHERE ID = string_id;
    SELECT VARCHAR_VAL INTO varchar2_val FROM SELECT_UPDATE_STRING_TYPES WHERE ID = string_id;
    SELECT NVARCHAR_VAL INTO nvarchar_val FROM SELECT_UPDATE_STRING_TYPES WHERE ID = string_id;
END;
/
CREATE OR REPLACE PROCEDURE "CALL_INOUT_STRING_TYPES" (string_id_1 IN NUMBER, char_val IN OUT CHAR, nchar_val IN OUT NCHAR,
                                                     varchar2_val IN OUT VARCHAR2, nvarchar_val IN OUT NVARCHAR2) IS
BEGIN
    INSERT INTO SELECT_UPDATE_STRING_TYPES VALUES(string_id_1, char_val, nchar_val, varchar2_val, nvarchar_val);
    SELECT CHAR_VAL INTO char_val FROM SELECT_UPDATE_STRING_TYPES WHERE ID = string_id_1;
    SELECT NCHAR_VAL INTO nchar_val FROM SELECT_UPDATE_STRING_TYPES WHERE ID = string_id_1;
    SELECT VARCHAR_VAL INTO varchar2_val FROM SELECT_UPDATE_STRING_TYPES WHERE ID = string_id_1;
    SELECT NVARCHAR_VAL INTO nvarchar_val FROM SELECT_UPDATE_STRING_TYPES WHERE ID = string_id_1;
END;
/
CREATE OR REPLACE PROCEDURE "CALL_IN_COMPLEX_TYPES" (id IN NUMBER, blob_val IN BLOB, clob_val IN CLOB, nclob_val IN NCLOB) IS
BEGIN
    INSERT INTO SELECT_UPDATE_COMPLEX_TYPES VALUES(id, blob_val, clob_val, nclob_val);
END;
/
CREATE OR REPLACE PROCEDURE "CALL_OUT_COMPLEX_TYPES" (complex_id IN NUMBER, blob_val OUT BLOB, clob_val OUT CLOB, nclob_val OUT NCLOB) IS
BEGIN
    SELECT BLOB_VAL INTO blob_val FROM SELECT_UPDATE_COMPLEX_TYPES WHERE ID = complex_id;
    SELECT CLOB_VAL INTO clob_val FROM SELECT_UPDATE_COMPLEX_TYPES WHERE ID = complex_id;
    SELECT NCLOB_VAL INTO nclob_val FROM SELECT_UPDATE_COMPLEX_TYPES WHERE ID = complex_id;
END;
/
CREATE OR REPLACE PROCEDURE "CALL_INOUT_COMPLEX_TYPES" (complex_id_1 IN NUMBER, blob_val IN OUT BLOB, clob_val IN OUT CLOB, nclob_val IN OUT NCLOB) IS
BEGIN
    INSERT INTO SELECT_UPDATE_COMPLEX_TYPES VALUES(complex_id_1, blob_val, clob_val, nclob_val);
    SELECT BLOB_VAL INTO blob_val FROM SELECT_UPDATE_COMPLEX_TYPES WHERE ID = complex_id_1;
    SELECT CLOB_VAL INTO clob_val FROM SELECT_UPDATE_COMPLEX_TYPES WHERE ID = complex_id_1;
    SELECT NCLOB_VAL INTO nclob_val FROM SELECT_UPDATE_COMPLEX_TYPES WHERE ID = complex_id_1;
END;
/