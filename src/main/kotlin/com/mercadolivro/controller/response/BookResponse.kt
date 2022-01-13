package com.mercadolivro.controller.response

import com.mercadolivro.enums.BookStatusEnum
import com.mercadolivro.model.CustomerModel
import java.math.BigDecimal
import javax.persistence.*

data class BookResponse(

    var id: Int? = null,
    var name: String,
    var price: BigDecimal,
    var customer: CustomerModel? = null,
    var status: BookStatusEnum? = null
)
