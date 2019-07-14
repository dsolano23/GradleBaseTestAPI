package com.gdt.exception.constants;

public enum ErrorsEnum {

    //ERRORS FOR TOKEN USE 100 TO 199
    TOKEN_GENERIC_ERROR(100,"100 - token logic error: token.genericError","Token Generic Error"),
    TOKEN_EXPIRED(101,"101 - token error: token.expired", "Token has expired"),
    TOKEN_NOT_ALLOWED(102,"102 - token error: token.notAllowed", "User has no permission"),
    TOKEN_NOT_FOUND(103,"103 - token error: token.notFound", "Not Token found"),
    //ERRORS FOR VERSION USE 200 TO 299
    VERSION_GENERIC_ERROR(200,"200 - version error: version.genericError","Version Generic Error"),
    //ERRORS FOR REQUEST USE 300 TO 399

    //ERRORS FOR VALIDATION DATA USE 300 TO 399
    VALIDATION_DATA_ANY_REQUIRED(301,"301 - validation data: any.required", " is required"),
    VALIDATION_DATA_ANY_INVALID(302,"302 - validation data: any.invalid", "change"),
    VALIDATION_DATA_ANY_UNKNOWN(303,"303 - validation data: any.unknown", "change"),
    VALIDATION_DATA_ANY_EMPTY(304,"304 - validation data: any.empty", " is not allowed to be empty"),
    VALIDATION_DATA_ANY_DEFAULT(305,"305 - validation data: any.default", "change"),
    VALIDATION_DATA_ANY_ALLOW_ONLY(306,"306 - validation data: any.allowOnly", "change"),
    VALIDATION_DATA_STRING_ALPHA_NUM(307,"307 - validation data: string.alphanum", "must only contain alpha-numeric characters"),
    VALIDATION_DATA_STRING_BASE64(308,"308 - validation data: string.base64", "change"),
    VALIDATION_DATA_STRING_BASE(309,"309 - validation data: string.base", "change"),
    VALIDATION_DATA_STRING_CREDIT_CARD(310,"310 - validation data: string.creditCard", "change"),
    VALIDATION_DATA_STRING_DATA_URI(311,"311 - validation data: string.dataUri", "change"),
    VALIDATION_DATA_STRING_EMAIL(312,"312 - validation data: string.email", " must be a valid email"),
    VALIDATION_DATA_STRING_GUID(313,"313 - validation data: string.guid", "change"),
    VALIDATION_DATA_STRING_HEX_ALIGN(314,"314 - validation data: string.hexAlign", "change"),
    VALIDATION_DATA_STRING_HEX(315,"315 - validation data: string.hex", "change"),
    VALIDATION_DATA_STRING_HOSTNAME(316,"316 - validation data: string.hostname", "change"),
    VALIDATION_DATA_STRING_IP_VERSION(317,"317 - validation data: string.ipVersion", "change"),
    VALIDATION_DATA_STRING_IP(318,"318 - validation data: string.ip", "change"),
    VALIDATION_DATA_STRING_ISO_DATE(319,"319 - validation data: string.isoDate", "change"),
    VALIDATION_DATA_STRING_LENGTH(320,"320 - validation data: string.length", "change"),
    VALIDATION_DATA_STRING_LOWERCASE(321,"321 - validation data: string.lowercase", "change"),
    VALIDATION_DATA_STRING_MAX(322,"322 - validation data: string.max", " length must be less than or equal to "),
    VALIDATION_DATA_STRING_MIN(323,"323 - validation data: string.min", " length must be at least "),
    VALIDATION_DATA_STRING_NORMALIZE(324,"324 - validation data: string.normalize", "change"),
    VALIDATION_DATA_STRING_REF(325,"325 - validation data: string.ref", "change"),
    VALIDATION_DATA_STRING_REGEX_BASE(326,"326 - validation data: string.regex.base", "fails to match the required pattern"),
    VALIDATION_DATA_STRING_REGEX_NAME(327,"327 - validation data: string.regex.name", "change"),
    VALIDATION_DATA_STRING_REGEX_INVERT_BASE(328,"328 - validation data: string.regex.invert.base", "change"),
    VALIDATION_DATA_STRING_REGEX_INVERT_NAME(329,"329 - validation data: string.regex.invert.name", "change"),
    VALIDATION_DATA_STRING_TOKEN(330,"330 - validation data: string.token", "change"),
    VALIDATION_DATA_STRING_TRIM(331,"331 - validation data: string.trim", "change"),
    VALIDATION_DATA_STRING_UPPERCASE(332,"332 - validation data: string.uppercase", "change"),
    VALIDATION_DATA_STRING_URI(333,"333 - validation data: string.uri", "change"),
    VALIDATION_DATA_STRING_URI_CUSTOM_SCHEME(334,"334 - validation data: string.uriCustomScheme", "change"),
    VALIDATION_DATA_STRING_URI_RELATIVE_ONLY(335,"335 - validation data: string.uriRelativeOnly", "change"),
    VALIDATION_DATA_DATE_BASE(336,"336 - validation data: date.base", "change"),
    VALIDATION_DATA_DATE_GREATER(337,"337 - validation data: date.greater", "change"),
    VALIDATION_DATA_DATE_ISO_DATE(338,"338 - validation data: date.isoDate", "change"),
    VALIDATION_DATA_DATE_LESS(339,"339 - validation data: date.less", "change"),
    VALIDATION_DATA_DATE_MAX(340,"340 - validation data: date.max", "change"),
    VALIDATION_DATA_DATE_MIN(341,"341 - validation data: date.min", "change"),
    VALIDATION_DATA_DATE_REF(342,"342 - validation data: date.ref", "change"),
    VALIDATION_DATA_DATE_STRICT(343,"343 - validation data: date.strict", "change"),
    VALIDATION_DATA_DATE_TIMESTAMP_JAVASCRIPT(344,"344 - validation data: date.timestamp.javascript", "change"),
    VALIDATION_DATA_DATE_TIMESTAMP_UNIX(345,"345 - validation data: date.timestamp.unix", "change"),
    VALIDATION_DATA_NUMBER_BASE(346,"346 - validation data: number.base", "change"),
    VALIDATION_DATA_NUMBER_GREATER(347,"347 - validation data: number.greater", "change"),
    VALIDATION_DATA_NUMBER_INTEGER(348,"348 - validation data: number.integer", "change"),
    VALIDATION_DATA_NUMBER_LESS(349,"349 - validation data: number.less", "change"),
    VALIDATION_DATA_NUMBER_MAX(350,"350 - validation data: number.max", "must be less than or equal to"),
    VALIDATION_DATA_NUMBER_MIN(351,"351 - validation data: number.min", "must be larger than or equal to"),
    VALIDATION_DATA_NUMBER_MULTIPLE(352,"352 - validation data: number.multiple", "change"),
    VALIDATION_DATA_NUMBER_NEGATIVE(353,"353 - validation data: number.negative", "change"),
    VALIDATION_DATA_NUMBER_PORT(354,"354 - validation data: number.port", "change"),
    VALIDATION_DATA_NUMBER_POSITIVE(355,"355 - validation data: number.positive", "change"),
    VALIDATION_DATA_NUMBER_PRECISION(356,"356 - validation data: number.precision", "change"),
    VALIDATION_DATA_NUMBER_REF(357,"357 - validation data: number.ref", "change"),
    VALIDATION_DATA_NUMBER_UNSAFE(358,"358 - validation data: number.unsafe", "change"),
    VALIDATION_DATA_ARRAY_BASE(359,"359 - validation data: array.base", "change"),
    VALIDATION_DATA_ARRAY_EXCLUDES(360,"360 - validation data: array.excludes", "change"),
    VALIDATION_DATA_ARRAY_EXCLUDES_SINGLE(361,"361 - validation data: array.excludesSingle", "change"),
    VALIDATION_DATA_ARRAY_INCLUDES_REQUIRED_BOTH(362,"362 - validation data: array.includesRequiredBoth", "change"),
    VALIDATION_DATA_ARRAY_INCLUDES_REQUIRED_KNOWNS(363,"363 - validation data: array.includesRequiredKnowns", "change"),
    VALIDATION_DATA_ARRAY_INCLUDES_REQUIRED_UNKNOWNS(364,"364 - validation data: array.includesRequiredUnknowns", "change"),
    VALIDATION_DATA_ARRAY_INCLUDES(365,"365 - validation data: array.includes", "change"),
    VALIDATION_DATA_ARRAY_INCLUDES_SINGLE(366,"366 - validation data: array.includesSingle", "change"),
    VALIDATION_DATA_ARRAY_LENGTH_VALIDATION_ERROR(367,"367 - validation data: array.lengthvalidationError", "change"),
    VALIDATION_DATA_ARRAY_MAX(368,"368 - validation data: array.max", "change"),
    VALIDATION_DATA_ARRAY_MIN(369,"369 - validation data: array.min", "must contain at least 1"),
    VALIDATION_DATA_ARRAY_ORDERED_LENGTH(370,"370 - validation data: array.orderedLength", "change"),
    VALIDATION_DATA_ARRAY_REF(371,"371 - validation data: array.ref", "change"),
    VALIDATION_DATA_ARRAY_SPARSE(372,"372 - validation data: array.sparse", "change"),
    VALIDATION_DATA_ARRAY_UNIQUE(373,"373 - validation data: array.unique", "change"),
    VALIDATION_DATA_ARRAY_HAS_KNOWN(374,"374 - validation data: array.hasKnown", "change"),

    // ERRORS FOR ACCESS LOGIC USE 400 TO 499
    LOGIC_GENERIC_ERROR(400,"400 - access logic error: logic.genericError","Logic Access Generic Error"),
    LOGIC_GENERIC_USER_NOT_FOUND(401,"401 - access logic error: logic.genericError","The logged in user does not have permission to access this command"),
    LOGIC_GENERIC_NOT_RECOGNIZED(402,"402 - access logic error: logic.genericError","Can not recognized command"),

    // ERRORS FOR FUNCTIONALITY LOGIC USE 500 TO 599
    USER_GENERIC_ERROR(500,"500 - functionality user.genericError","Functionality Generic Error"),
    USER_USER_NOT_FOUND(501,"501 - functionality user.userNotFound","No user found"),
    USER_NOT_ACTIVE(502,"502 - functionality error: user.userNotActive","User not active"),
    ITEM_NOT_FOUND(503,"503 - functionality error: item.itemNotFound","Not items found with this ID"),
    ITEMS_DUPLICATED(504,"504 - functionality error: item.itemsDuplicated","Items duplicated in the request is not allowed"),
    PHARMACY_NOT_FOUND(505,"505 - functionality error: pharmacy.pharmacyNotFound","Not pharmacy found with this ID"),
    PHARMACY_NOT_SELECTED(506,"506 - functionality error: pharmacy.pharmacyNotSelected","Not pharmacy can be selected with this data"),
    NOT_CREAATED_BOOKING(507,"507 - functionality error: pharmacy.pharmacyNotSelected","The booking has not be created, some data is not valid"),
    NO_COORDINATES_FOUND(515,"515 - functionality error: booking.findingStockPharmaciesNoCoordinates","Error finding stock of pharmacies. There must be coordinates");

    public final int code;
    public final String type;
    public final String detail;

    private ErrorsEnum(int code, String type, String detail) {
        this.code = code;
        this.type = type;
        this.detail = detail;
    }

    public String getType() {
        return type;
    }

    public String getDetail() {
        return detail;
    }

    public int getCode() {
        return code;
    }


    public static ErrorsEnum getByCode(Integer code){
        ErrorsEnum error = null;
        for(ErrorsEnum e: ErrorsEnum.values()){
            if(e.getCode() == code){
                error = e;
            }
        }
        return error;

    }

    @Override
    public String toString() {
        return "ErrorsEnum{" +
                "type='" + type + '\'' +
                ", code=" + code +
                ", description='" + detail + '\'' +
                '}';
    }
}
