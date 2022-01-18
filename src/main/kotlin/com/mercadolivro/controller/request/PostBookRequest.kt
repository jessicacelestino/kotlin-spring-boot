package com.mercadolivro.controller.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class PostBookRequest(

    @field:NotEmpty(message = "Field name must be informed")
    var name: String,

    @field:NotNull(message = "Field price must be informed")
    var price: BigDecimal,

    @JsonProperty(value = "customer_id")
    var customerId: Int
)
