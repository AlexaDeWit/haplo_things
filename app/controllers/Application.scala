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
import play.filters.csrf.CSRF


object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

}
