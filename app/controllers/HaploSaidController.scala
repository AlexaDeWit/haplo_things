package controllers

import models._
import play.api._
import play.api.mvc._
import play.api.db.slick._
import slick.driver.PostgresDriver.simple._
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._
import play.api.Play.current
import play.api.mvc.BodyParsers._
import play.api.libs.json.Json
import play.api.libs.json.Json._

object HaploSaidController extends Controller {

  //query interface for things haplo says.
  val haplo_said = TableQuery[HaploSaid]

  def index = DBAction {  implicit rs =>
    val shit_haplo_said = haplo_said.sortBy( _.created_at.desc ).take( 15 ).list
    //replace this with pagination
    Ok( views.html.haplo_said.index( shit_haplo_said ) )
  }

  def create = DBAction { implicit rs =>
  }

}
