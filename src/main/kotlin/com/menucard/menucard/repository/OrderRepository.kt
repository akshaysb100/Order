package com.menucard.menucard.repository

import com.menucard.menucard.model.MenuItems
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<MenuItems,Long> {

}
