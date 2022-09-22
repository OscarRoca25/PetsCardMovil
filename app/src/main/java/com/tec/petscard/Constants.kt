package com.tec.petscard

object Constants {
    const val BASE_COD_POSTAL_URL = "https://api.copomex.com/query/info_cp/"
    const val PARAMS_COD_POSTAL_URL = "?token=a57740a0-60c7-4839-9350-c26d04bb98be&type=simplified"
    /*const val TOKEN = "pruebas"
    const val TYPE = "simplified"

    const val QUERY_PATH = "/query"
    const val INFO_CP_PATH = "/info_cp/"

    const val TYPE_PARAM = "type"
    const val TOKEN_PARAM = "token"*/

    const val RESPONSE_PROPERTY = "response"
    /*const val CP_PROPERTY = "cp"
    const val ASENTAMIENTO_PROPERTY = "asentamiento"
    const val TIPO_ASENTAMIENTO_PROPERTY = "tipo_asentamiento"
    const val MUNICIPIO_PROPERTY = "municipio"
    const val ESTADO_PROPERTY = "estado"
    const val CIUDAD_PROPERTY = "ciudad"
    const val PAIS_PROPERTY = "pais"*/

    const val MAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,6}\$"

    const val USER_NOT_FOUND_MESSAGE = "There is no user record corresponding to this identifier. The user may have been deleted."
    const val PASSWORD_NOT_VALID_MESSAGE = "The password is invalid or the user does not have a password."

}