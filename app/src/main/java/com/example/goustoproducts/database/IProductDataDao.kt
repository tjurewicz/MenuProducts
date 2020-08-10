package com.example.goustoproducts.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IProductDataDao {
    @Query("SELECT * FROM dbproductdata")
    fun getAll(): List<DBProductData>

    @Query("SELECT * FROM dbproductdata WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<DBProductData>

    @Query("SELECT * FROM dbproductdata WHERE title LIKE :first")
    fun findByName(title: String): DBProductData

    @Insert
    fun insertAll(vararg products: DBProductData)

    @Delete
    fun delete(product: DBProductData)
}