package com.mercadolivro.service

import com.mercadolivro.enums.BookStatusEnum
import com.mercadolivro.enums.CustomerStatusEnum
import com.mercadolivro.enums.RolesEnum
import com.mercadolivro.events.PurchaseEvent
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.model.PurchaseModel
import com.mercadolivro.repository.PurchaseRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.capture
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationEventPublisher
import java.math.BigDecimal
import java.util.*

@ExtendWith(MockitoExtension::class)
class PurchaseServiceTest {

    @InjectMocks
    private lateinit var purchaseService: PurchaseService

    @Mock
    private lateinit var purchaseRepository: PurchaseRepository

    @Mock
    private lateinit var applicationEventPublisher: ApplicationEventPublisher

    @Captor
    private lateinit var captor: ArgumentCaptor<PurchaseEvent>


    @Test
    fun created() {
        val purchase = buildPurchase()

       `when`(purchaseRepository.save(purchase)).thenReturn(purchase)

        purchaseService.created(purchase)

        verify(applicationEventPublisher).publishEvent(captor.capture());

        assertEquals(purchase, captor.value.purchaseModel)

    }


    private fun buildPurchase(
        id: Int? = 1,
        customer: CustomerModel = buildCustomer(),
        books: MutableList<BookModel> = mutableListOf(),
        nfe: String = UUID.randomUUID().toString(),
        price: BigDecimal = BigDecimal.TEN
    ) = PurchaseModel(
        id = id,
        customer = customer,
        books = books,
        nfe = nfe,
        price = price
    )

//    private fun buildBookModel(
//        id: Int? = 1,
//        name: String = "test",
//        price: BigDecimal = BigDecimal.TEN,
//        customer: CustomerModel = buildCustomer(),
//        status: BookStatusEnum = BookStatusEnum.ACTIVE
//    ) = BookModel (
//        id = id,
//        name = name,
//        price = price,
//        customer = customer,
//        status = status
//    )

    private fun buildCustomer(
        id: Int? = 1,
        name: String = "test",
        email: String = "test@test.com",
        password: String = "1234",
        status: CustomerStatusEnum = CustomerStatusEnum.ACTIVE
    ) = CustomerModel(
        id = id,
        name = name,
        email = email,
        status = CustomerStatusEnum.ACTIVE,
        password = password,
        roles = setOf(RolesEnum.CUSTOMER)
    )
}