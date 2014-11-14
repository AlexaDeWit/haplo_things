package haplothings.interactors

import haplothings.boundaries._
import haplothings.entities.HaploSaid

case class CreateHaploSaidRequest( what_said: String, context_note: Option[String],
                                   who_said: String )


class CreateHaploSaid( request: CreateHaploSaidRequest ) extends RequestBoundary {

  val requestObj = request

  def getResponse = {
  }

  def validateRequest = {
  }


}
