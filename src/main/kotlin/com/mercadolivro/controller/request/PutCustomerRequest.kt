package com.mercadolivro.controller.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class PutCustomerRequest(

    @field:NotEmpty(message = "Field name must be informed")
    var name: String,

    @field:Email(message = "Field email must be valid")
    var email: String
)