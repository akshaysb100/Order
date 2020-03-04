package com.menucard.menucard.controller

import com.menucard.menucard.exception.Response
import com.menucard.menucard.model.MenuItems
import com.menucard.menucard.service.OrderService
import io.mockk.MockKAnnotations
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class OrderControllerTest {
    @InjectMockKs
    lateinit var orderController: OrderController

    @MockK
    lateinit var orderService: OrderService

    @Before
    internal fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun whenOrderIsCreated_ShouldReturnSuccessfullyMessage() {
        val menuItem = MenuItems()
        menuItem.productName = "book 3IT"
        var response = Response("order successfully deliver...")
        every { orderService.createOrder(menuItem) } returns response
        val createOrder = orderController.createOrder(menuItem)
        verify { orderService.createOrder(menuItem) }
        confirmVerified(orderService)
        Assert.assertEquals(response, createOrder.body)
    }

    @Test
    fun giveOrderID_ShouldReturnProductName() {
        every { orderService.fetchOrderDetails(1) } returns "10 story"
        val fetchOrderDetails = orderController.fetchOrderDetails(1)
        verify { orderService.fetchOrderDetails(1) }
        confirmVerified(orderService)
        Assert.assertEquals("10 story", fetchOrderDetails.body)
    }

    @Test
    fun givenOrderUpdate_ShouldReturnUpdatedProductName() {
        var response = Response("order successfully update...")
        var productName = "book 3IT"
        every { orderService.updateOrder(1, productName) } returns response
        val updateOrder = orderController.updateOrder(1, productName)
        Assert.assertEquals(response, updateOrder.body)
    }

    @Test
    fun givenOrderDelete_ShouldReturnDeletedOrderName() {
        var response = Response("order successfully update...")
        every { orderService.deleteOrder(1) } returns response
        val deleteOrder = orderController.deleteOrder(1)
        Assert.assertEquals(response, deleteOrder.body)
    }

    @Test
    fun givenDataBase_ShouldReturnIndexNumberOfLastData() {
        every { orderService.getIdOfLastData() } returns 3
        val idOfLastData = orderController.getIdOfLastData()
        verify { orderService.getIdOfLastData() }
        confirmVerified(orderService)
        Assert.assertEquals(3,idOfLastData)
    }
}