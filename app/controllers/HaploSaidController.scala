package controllers

import models._
import play.api._
import play.api.mvc._
import slick.driver.PostgresDriver.simple._
import play.api.Play.current
import play.api.mvc.BodyParsers._

object HaploSaidController extends Controller {

  //query interface for things haplo says.
  val haplo_said = TableQuery[HaploSaid]

  def index = Action {  implicit rs =>
    val shit_haplo_said = haplo_said.sortBy( _.created_at.desc ).take( 15 ).list
    //replace this with pagination
    Ok( views.html.index( shit_haplo_said ) )
  }

}
