package com.mercadolivro.repository

import com.mercadolivro.enums.BookStatusEnum
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface BookRepository : JpaRepository<BookModel, Int> {
    fun findAllByStatus(status : BookStatusEnum, pageable: Pageable): Page<BookModel>
    fun findByCustomer(customer: CustomerModel) : List<BookModel>


}
