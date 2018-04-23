package com.example.quickstart

import io.circe.Json
import io.circe.syntax._

object UserEncoder {
  def encode(user: User): Json =
    Json.obj(
      "fullname" -> Json.fromString(s"${user.title} ${user.firstName} ${user.lastName}")
    )
}
