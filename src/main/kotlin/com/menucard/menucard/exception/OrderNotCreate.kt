package com.menucard.menucard.exception

class OrderNotCreate : RuntimeException {
    override val message: String?

    constructor(message: String) : super(message) {
        this.message = message
    }

}
