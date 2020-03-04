package com.menucard.menucard.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class MenuItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    var productName: String? = null

    constructor()

//    constructor(productName: String) {
//        this.productName = productName
//    }
}
