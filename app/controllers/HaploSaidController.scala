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
import haplothings.boundaries.datamodels.HaploSaid



object HaploSaidController extends Controller {

  def index( page: Int ) = DBAction {  implicit rs =>
    //replace this with pagination
    val shitHaploSaid = (new HaploSaidRepository).take( 20 )
    Ok( html.haplo_said.index( shitHaploSaid, haploSaidForm ) )
  }

  def submit = DBAction { implicit rs =>
    val token = CSRF.getToken( rs )
    val haploData = haploSaidForm.bindFromRequest.get
    (new HaploSaidRepository).insert( haploData )
    Redirect( routes.HaploSaidController.index(1) ).flashing("success" -> "Saved!" )
  }

  def show( id: Int ) = DBAction { implicit rs =>
    val haploSaid = (new HaploSaidRepository).find( id )
    Ok( html.haplo_said.show( haploSaid ) )
  }

  /**
   * Form for creating haplo-things
   */
  val haploSaidForm = Form(
    mapping(
      "id" -> ignored(0),
      "what_said" -> nonEmptyText,
      "context_note" -> optional(text),
      "created_at" ->  optional(jodaDate),
      "who_said"   ->  default(text, "Haplo" )
    )(HaploSaid.apply)(HaploSaid.unapply _ )
  )

}
