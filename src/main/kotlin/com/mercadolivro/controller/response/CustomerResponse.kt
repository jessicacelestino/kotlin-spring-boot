package com.mercadolivro.controller.response

import com.mercadolivro.enums.CustomerStatusEnum

data class CustomerResponse(
    var id: Int? = null,
    var name: String,
    var email: String,
    var status : CustomerStatusEnum
)
