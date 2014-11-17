package haplothings.interactors

import haplothings.boundaries._
import haplothings.boundaries.requests._
import haplothings.entities.HaploSaidEntity


abstract class CreateHaploSaid( request: CreateHaploSaidRequest ) {

  val requestObj = request

  def getResponse
  def validateRequest


}
