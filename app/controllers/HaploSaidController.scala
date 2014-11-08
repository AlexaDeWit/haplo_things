package controllers

import models._
import views._
import play.api._
import play.api.mvc._
import play.filters.headers.SecurityHeadersFilter
import play.api.db.slick._
import slick.driver.PostgresDriver.simple._
import play.api.data._
import play.api.data.Forms._
import play.api.Play.current
import play.api.mvc.BodyParsers._
import play.api.libs.json.Json
import play.api.libs.json.Json._
import play.filters.csrf._
import play.filters.csrf.CSRF



object HaploSaidController extends Controller {

  //query interface for things haplo says.
  val haplo_said = TableQuery[HaploSaids]
  def shit_haplo_said = haplo_said.sortBy( _.created_at.desc ).take( 15 )

  def index = DBAction {  implicit rs =>
    //replace this with pagination
    Ok( html.haplo_said.index( shit_haplo_said.list, haploSaidForm ) )
  }

  def submit = DBAction { implicit rs =>
    val token = CSRF.getToken( rs )
    Logger.info( rs.toString )
    val haploData = haploSaidForm.bindFromRequest.get
    haplo_said.insert( HaploSaid.unapply( haploData ).get )
    Redirect( routes.HaploSaidController.index ).flashing("success" -> "Saved!" )
  }

  /**
   * Form for creating haplo-things
   */
  val haploSaidForm = Form(
    mapping(
      "id" -> optional(number),
      "what_said" -> nonEmptyText, 
      "context_note" -> optional(text),
      "created_at" ->  optional(jodaDate),
      "who_said"   ->  default(text, "Haplo" )
    )(HaploSaid.apply)(HaploSaid.unapply _ )
  )

}
