package com.route.todoappc40gsat.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Todo")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val date: Date? = null,
    var isDone: Boolean? = false,

)


// ORM ( Object Relation Mapping )