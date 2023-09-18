package com.linuxh2o.connectq.data.models

open class Event<out T>(private val content: T) {
  var isHandled = false
    private set

  fun getContentOrNull(): T? {
    return if (isHandled){
      null
    }else{
      isHandled = true
      content
    }
  }
}