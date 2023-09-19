package com.linuxh2o.connectq.data.models

data class User(
  val id: String? = "",
  val name: String? = "",
  val number: String? = "",
  val imageUrl: String? = ""
){

  fun toMap() = mapOf(
    "id" to id,
    "name" to name,
    "number" to number,
    "imageUrl" to imageUrl
  )

}
