package com.facundo.taskflow_api.model

import jakarta.persistence.*

@Entity
@Table(name = "categories")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String = "",

    @ManyToOne//fk
    @JoinColumn(name = "user_id")
    val user: User? = null
)