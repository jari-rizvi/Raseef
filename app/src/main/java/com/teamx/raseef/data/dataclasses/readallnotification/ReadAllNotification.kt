package com.teamx.raseef.dataclasses.readallnotification

import androidx.annotation.Keep

 
@Keep
data class ReadAllNotification(
    val acknowledged: Boolean,
    val matchedCount: Int,
    val modifiedCount: Int,
    val upsertedCount: Int,
    val upsertedId: Any
)