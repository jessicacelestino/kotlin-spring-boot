package com.mercadolivro.exception

class AuthenticationFailException(override val message : String, val errorCode: String) : Exception(){
}