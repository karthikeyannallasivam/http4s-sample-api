package com.example.quickstart

import cats._
import cats.effect._
import cats.implicits._

import org.http4s._
import org.http4s.circe.jsonOf
import org.http4s.client.blaze._

import io.circe.generic.auto._

import scala.concurrent.ExecutionContext.Implicits.global

object Client {
  val httpClient = Http1Client[IO]().unsafeRunSync

  implicit def userDecoder: EntityDecoder[IO, User] = jsonOf[IO, User]

  def getUser(id: String): IO[User] = {
    val target = Uri.uri("http://localhost:8080/external/users/") / id
    httpClient.expect[User](target)
  }
}
