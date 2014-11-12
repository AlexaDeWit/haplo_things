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

  def index = DBAction {  implicit rs =>
    //replace this with pagination
    Ok( html.haplo_said.index( HaploSaids.shitHaploSaid( 15 ), haploSaidForm ) )
  }

  def submit = DBAction { implicit rs =>
    val token = CSRF.getToken( rs )
    val haploData = haploSaidForm.bindFromRequest.get
    HaploSaids.insert( haploData )
    Redirect( routes.HaploSaidController.index ).flashing("success" -> "Saved!" )
  }

  def show( id: Int ) = DBAction { implicit rs =>
    Ok( html.haplo_said.show( HaploSaids.find( id ) ) )
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
