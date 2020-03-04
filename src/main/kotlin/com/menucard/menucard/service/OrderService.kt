package com.menucard.menucard.service

import com.menucard.menucard.exception.OrderNotCreate
import com.menucard.menucard.exception.Response
import com.menucard.menucard.model.MenuItems
import com.menucard.menucard.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService {
    @Autowired
    lateinit var orderRepository: OrderRepository

    fun createOrder(menuItems: MenuItems): Response {
        val save = orderRepository.save(menuItems)
        if (save.equals(menuItems)) {
            return Response("order successfully deliver...")
        } else
            throw OrderNotCreate("order not created please try again")
    }

    fun fetchOrderDetails(id: Long): String? {
        val findById = orderRepository.findById(id)
        if (findById.isPresent) {
            return findById.get().productName
        } else
            throw OrderNotCreate("wrong id number please try again")

    }

    fun updateOrder(id: Long, productName: String): Response {
        val findById = orderRepository.findById(id)
        if (findById.isPresent) {
            findById.get().productName = productName
            orderRepository.save(findById.get())
            return Response("order successfully update...")
        } else
            throw OrderNotCreate("Wrong id number please try again")
    }

    fun deleteOrder(id: Long): Response {
        val findById = orderRepository.findById(id)
        if (findById.isPresent) {
            orderRepository.delete(findById.get())
            return Response("order successfully deleted")
        } else
            throw OrderNotCreate("Wrong id please try again")
    }

    fun getIdOfLastData(): Long {
        val findAll1 = orderRepository.findAll()
        val id12id12 = findAll1.get((findAll1.size-1)).id
        val findAll = orderRepository.findById(id12id12)
        return findAll.get().id
    }
}
