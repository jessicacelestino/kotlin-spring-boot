package com.mercadolivro.service

import com.mercadolivro.enums.CustomerStatusEnum
import com.mercadolivro.enums.RolesEnum
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

@ExtendWith(MockitoExtension::class)
class CustomerServiceTest {

    @Mock
    private lateinit var customerRepository: CustomerRepository

    @Mock
    private lateinit var bookService: BookService

    @Mock
    private lateinit var bCrypt: BCryptPasswordEncoder

    @InjectMocks
    private lateinit var customerService: CustomerService

    @Test
    fun shouldReturnAllCustomers() {
        val fakeCustomers = listOf(buildCustomer(), buildCustomer())
        `when`(customerRepository.findAll()).thenReturn(fakeCustomers)
        val customers = customerService.getAll(null)
        assertEquals(fakeCustomers, customers)
    }

    @Test
    fun shouldReturnAllCustomersByName() {
        val fakeCustomers = listOf(buildCustomer(), buildCustomer())
        `when`(customerRepository.findByNameContaining(anyString())).thenReturn(fakeCustomers)
        val customers = customerService.getAll(anyString())
        assertEquals(fakeCustomers, customers)
    }

    @Test
    fun createCustomerAndEncryptPassword() {
        val fakeCustomer = buildCustomer()
        val fakePassword = UUID.randomUUID().toString()
        `when`(bCrypt.encode(any())).thenReturn(fakePassword)
        `when`(customerRepository.save(any())).thenReturn(fakeCustomer)
        customerService.create(fakeCustomer)
        assertEquals(fakeCustomer, fakeCustomer)
    }

    @Test
    fun findCustomerByIdNotNull() {
        val id = Random().nextInt()
        val fakeCustomer = buildCustomer(id = id)
        `when`(customerRepository.findById(id)).thenReturn(Optional.of(fakeCustomer))
        val customer = customerService.findById(id)
        assertEquals(fakeCustomer, customer)
    }

    @Test
    fun findCustomerById() {
        val id = Random().nextInt()
        `when`(customerRepository.findById(id)).thenReturn(Optional.empty())
        assertThrows<NotFoundException> { customerService.findById(id) }
    }

    @Test
    fun updateCustomerExistsId() {
        val fakeCustomer = buildCustomer()
        `when`(customerRepository.save(any())).thenReturn(fakeCustomer)
        `when`(customerRepository.existsById(anyInt())).thenReturn(true)
        customerService.update(fakeCustomer)
        assertEquals(fakeCustomer, fakeCustomer)
    }

    @Test
    fun updateCustomerNotExistsId() {
        val fakeCustomer = buildCustomer()
        `when`(customerRepository.existsById(anyInt())).thenReturn(false)
        assertThrows<NotFoundException> {  customerService.update(fakeCustomer) }
    }

    @Test
    fun deleteCustomerById() {
        val id = Random().nextInt()
        val status = CustomerStatusEnum.INACTIVE
        val fakeCustomer = buildCustomer(status = status)

        `when`(customerRepository.findById(id)).thenReturn(Optional.of(fakeCustomer))
        bookService.deleteByCustomer(fakeCustomer)
        customerRepository.deleteById(id)
        customerService.delete(id)
        assertEquals(fakeCustomer, fakeCustomer)
    }

    @Test
    fun emailAvailable() {
        val email = "${Random().nextInt().toString()}@test.com"
        `when`(customerRepository.existsByEmail(email)).thenReturn(false)

        val emailAvailable  = customerService.emailAvailable(email)
        assertTrue(emailAvailable)
    }

    @Test
    fun emailAvailableFalse() {
        val email = "${Random().nextInt().toString()}@test.com"
        `when`(customerRepository.existsByEmail(email)).thenReturn(true)

        val emailAvailable  = customerService.emailAvailable(email)
        assertFalse(emailAvailable)
    }


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