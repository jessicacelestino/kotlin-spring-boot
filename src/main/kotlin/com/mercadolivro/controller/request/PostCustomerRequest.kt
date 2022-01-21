package com.mercadolivro.controller.request

import com.mercadolivro.validation.EmailAvailable
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class PostCustomerRequest (

    @field:NotEmpty(message = "Field name must be informed")
    var name: String,

    @EmailAvailable
    @field:Email(message = "Field email must be valid")
    var email: String,

    @field:NotEmpty(message = "Inform the password")
    var password : String
)