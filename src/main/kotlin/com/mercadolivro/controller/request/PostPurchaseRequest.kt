package com.mercadolivro.controller.request

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class PostPurchaseRequest(

    @field:NotNull
    @field:Positive
    @JsonProperty("customer_id")
    val customerId: Int,

    @field:NotNull
    @JsonProperty("book_ids")
    val bookIds : Set<Int>
)