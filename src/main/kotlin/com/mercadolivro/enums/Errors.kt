package com.mercadolivro.enums

enum class Errors (val code : String, val message : String){
    ML001("ML001", "Invalid Request"),
    ML101("ML-101", "Book [%s] not exists"),
    ML201("ML-201",  "Customer [%s] not exists"),
    ML102("ML102", "Cannot update book with Status [%s]")
}