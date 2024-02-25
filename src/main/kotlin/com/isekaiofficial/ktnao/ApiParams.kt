package com.isekaiofficial.ktnao

object ApiParams {
    const val OUTPUT_TYPE = "output_type"

    enum class OutputType(val value: String) {
        HTML("0"),
        XML("1"),
        JSON("2"),
    }

    const val API_KEY = "api_key"

    const val TEST_MODE = "testmode"

    const val DB_MASK = "dbmask"

    const val DB_MASK_INVERT = "dbmaski"

    /**
     * search a specific index number or all without needing to generate a bitmask.
     */
    const val DB = "db"

    const val DBS = "dbs[]"

    /**
     * Change the number of results requested.
     */
    const val NUMBER_OF_RESULTS = "numres"

    const val DEDUPLICATE = "dedupe"

    enum class Deduplicate(val value: String) {
        SHOW_ALL("0"),
        /**
         * consolidate booru results and dedupe by item identifier
         */
        CONSOLIDATE_AND_DEDUPE("1"),
        DEDUPE_ALL("2"),
    }

    /**
     * This controls the hidden field of results based on result content info.
     */
    const val HIDE = "hide"

    enum class Hide(val value: String) {
        SHOW_ALL("0"),
        HIDE_EXPECTED("1"),
        HIDE_EXPECTED_AND_SUSPECTED("2"),
        HIDE_ALL_BUT_SAFE("3"),
    }

    /**
     * image ref url
     */
    const val URL = "url"

    const val MIN_SIM = "minsim"
}
