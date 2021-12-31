package com.carlonzo

import com.google.cloud.functions.HttpFunction
import com.google.cloud.functions.HttpRequest
import com.google.cloud.functions.HttpResponse

class Application : HttpFunction {

  override fun service(request: HttpRequest, response: HttpResponse) {

    request.path.let {
      when (it) {
        "/" -> {
          response.writer.write("Hey there!\n")
        }

        else -> {
          response.setStatusCode(404)
          response.writer.write("Not Found!!1!")
        }
      }
    }

  }
}