package view_helpers

import play.twirl.api.HtmlFormat
import play.twirl.api.Html

object HaploHelpers {

  def nl2br( text: String ): Html = {
    Html( HtmlFormat.escape( text ).toString.replace( "\n", "<br />" ) )
  }
}
