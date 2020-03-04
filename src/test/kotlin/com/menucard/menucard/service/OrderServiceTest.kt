package com.menucard.menucard.service

import com.menucard.menucard.exception.Response
import com.menucard.menucard.model.MenuItems
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Test
import org.junit.Before
import reactor.core.publisher.whenComplete
import java.util.*
import com.menucard.menucard.repository.OrderRepository as OrderRepository

class OrderServiceTest {
    @InjectMockKs
    lateinit var orderService: OrderService

    @MockK
    lateinit var orderRepository: OrderRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun createOrder() {
        val menuItem = MenuItems()
        menuItem.productName = "book 3IT"
        var response = Response("order successfully deliver...")
        every { orderRepository.save(menuItem) } returns menuItem
        val createOrder = orderService.createOrder(menuItem)
        verify { orderRepository.save(menuItem) }
        Assert.assertEquals(response, createOrder)
    }

    @Test
    fun fetchOrderDetails() {
        val menuItems = MenuItems()
        menuItems.productName = "book III"
        every { orderRepository.findById(1) } returns Optional.of(menuItems)
        val fetchOrderDetails = orderService.fetchOrderDetails(1)
        verify { orderRepository.findById(1) }
        Assert.assertEquals("book III", fetchOrderDetails)
    }

    @Test
    fun updateOrder() {
        val menuItem = MenuItems()
        menuItem.productName = "book 3IT"
        var response = Response("order successfully update...")
        every { orderRepository.findById(1) } returns Optional.of(menuItem)
        every { orderRepository.delete(menuItem) } just Runs
        every { orderRepository.save(menuItem) } returns menuItem
        val updateOrder = orderService.updateOrder(1, "book 3IT")
        verify { orderRepository.findById(1) }
        Assert.assertEquals(response.message, updateOrder.message)
    }

    @Test
    fun deleteOrder() {
        val menuItem = MenuItems()
        menuItem.productName = "book 3IT"
        var response = Response("order successfully deleted")
        every { orderRepository.findById(1) } returns Optional.of(menuItem)
        every { orderRepository.delete(menuItem) } just Runs
        val deleteOrder = orderService.deleteOrder(1)
        Assert.assertEquals(response.message, deleteOrder.message)
    }
//
//    @Test
//    fun getIdOfLastData() {
//        var menuItem = MenuItems()
//        menuItem.productName = ""
//        var listOfMenuItems = mutableListOf<MenuItems>()
//        every { orderRepository.findAll() } returns listOfMenuItems
//        every { orderRepository.findById(1) } returns Optional.of(menuItem)
//        val idOfLastData = orderService.getIdOfLastData()
//        Assert.assertEquals(1,idOfLastData)
//    }
}