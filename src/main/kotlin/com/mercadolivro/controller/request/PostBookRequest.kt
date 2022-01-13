package com.mercadolivro.controller.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

class PostBookRequest(

    var name: String,
    var price: BigDecimal,

    @JsonProperty(value = "customer_id")
    var customerId: Int
)
