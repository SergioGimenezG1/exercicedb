package com.example.exercicedb.controller;

public final class ConstantsPaths {

    public static final String PRIVATE_V1 = "/private/v1";
    public static final String PRIVATE_V1_CLIENT = "/private/v1/client";
    public static final String PUBLIC_V1 = "/public/v1";

    public static class Paths {

        public static final String PATH_ID = "/{id}";

        public static class Rate {

            public static final String RATE = PUBLIC_V1 + "/rate";

            public static final String UPDATE_PRICE = PATH_ID + "/{price}";

        }

        public static class Currency {

            public static final String CURRENCIES = "/v1/currencies";
            public static final String CURRENCY_BY_CODE = "/v1/currencies/{currencyCode}";

        }

    }

    public static class Tags {
        public static final String RATE = "Rate";

    }

}
