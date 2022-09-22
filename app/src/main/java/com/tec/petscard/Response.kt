package com.tec.petscard

data class Response(var cp: String,
                    var asentamiento: Array<String>,
                    var tipoAsentamiento: String,
                    var municipio: String,
                    var estado: String,
                    var ciudad: String,
                    var pais: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Response

        if (cp != other.cp) return false

        return true
    }

    override fun hashCode(): Int {
        return cp.hashCode()
    }
}