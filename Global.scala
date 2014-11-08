import play.api._
import play.api.mvc._
import play.filters.headers.SecurityHeadersFilter
import play.filters.csrf._

object Global extends WithFilters( CSRFFilter()) with GlobalSettings {
    // onStart, onStop etc...
}
