package com.menucard.menucard.controller

import com.menucard.menucard.exception.Response
import com.menucard.menucard.model.MenuItems
import com.menucard.menucard.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@RestController
@RequestMapping("/order")
class OrderController {

    @Autowired
    lateinit var orderService: OrderService

    @PostMapping("/createOrder")
    fun createOrder(@RequestBody menuItems: MenuItems): ResponseEntity<Response> {
        val createOrder = orderService.createOrder(menuItems)
        return ResponseEntity.ok(createOrder)
    }

    @GetMapping("/fetchOrder")
    fun fetchOrderDetails(@RequestParam id: Long): ResponseEntity<String> {
        val fetchOrderDetails = orderService.fetchOrderDetails(id)
        return ResponseEntity.ok(fetchOrderDetails.toString())
    }

    @PutMapping("/updateOrder")
    fun updateOrder(@RequestParam id: Long, @RequestParam productName: String): ResponseEntity<Response> {
        var updateOrder = orderService.updateOrder(id, productName)
        return ResponseEntity.ok(updateOrder)
    }

    @DeleteMapping("/deleteOrder")
    fun deleteOrder(@RequestParam id: Long): ResponseEntity<Response> {
        val deleteOrder = orderService.deleteOrder(id)
        return ResponseEntity.ok(deleteOrder)
    }

    @GetMapping("getId")
    fun getIdOfLastData(): Long {
        val idOfLastData = orderService.getIdOfLastData()
        return idOfLastData
    }
}
