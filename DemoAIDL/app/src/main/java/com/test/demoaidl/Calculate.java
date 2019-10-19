package com.test.demoaidl;

public class Calculate {

    public static final int type_add = 1;
    public static final int type_subtract = 2;
    public static final int type_multiply = 3;
    public static final int type_divide = 4;

    public static String calculateResult(float numA, float numB, int type) {
        String result = "result = null";

        switch (type) {
            case type_add:
                result = numA + numB + "";
                break;

            case type_subtract:
                result = numA - numB + "";
                break;

            case type_multiply:
                result = numA * numB + "";
                break;

            case type_divide:
                if (0 != numB) {
                    result = numA / numB + "";
                } else {
                    result = "second num is invalid";
                }
                break;
        }
        return result;
    }
}
