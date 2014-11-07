package controllers

import models._
import views._
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
  val haplo_said = TableQuery[HaploSaids]
  def shit_haplo_said = haplo_said.sortBy( _.created_at.desc ).take( 15 )

  def index = DBAction {  implicit rs =>
    //replace this with pagination
    Ok( html.haplo_said.index( shit_haplo_said.list, haploSaidForm ) )
  }

  def submit = DBAction { implicit rs =>
    val haploData = haploSaidForm.bindFromRequest.fold(
      formWithErrors => BadRequest( html.haplo_said.index( shit_haplo_said.list, formWithErrors ) ),
      said => {
        HaploSaids.insert( said )
        Redirect( routes.HaploSaidController.index ).flashing("success" -> "Saved!" )
      }
    )
    Redirect( routes.HaploSaidController.index ).flashing("success" -> "Saved!" )
  }

  /**
   * Form for creating haplo-things
   */
  val haploSaidForm = Form(
    mapping(
      "id" -> number,
      "what_said" -> nonEmptyText, 
      "context_note" -> optional(text),
      "created_at" ->  jodaDate
    )(HaploSaid.apply)(HaploSaid.unapply)
  )

}
