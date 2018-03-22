package http

import com.softwaremill.sttp._
import com.softwaremill.sttp.{SttpBackend, asByteArray}
import com.softwaremill.sttp.asynchttpclient.future.AsyncHttpClientFutureBackend

import scala.concurrent.Future

object Client {

  implicit val backend: SttpBackend[Future, Nothing] =
    AsyncHttpClientFutureBackend()

  def getAsString(url: String): Future[Response[String]] =
    sttp.get(uri"$url").response(asString).send()

  def getAsByteArray(url: String): Future[Response[Array[Byte]]] =
    sttp.get(uri"$url").response(asByteArray).send()

}
