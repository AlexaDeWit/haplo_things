package models

import scala.slick.driver.PostgresDriver.simple._
import scala.slick.lifted.{ProvenShape, ForeignKeyQuery}
import com.github.tototoshi.slick.PostgresJodaSupport._
import org.joda.time._

//A HaploSaid table storing shit haplo says.
class HaploSaid(tag: Tag)
  extends Table[( Int, String, DateTime )]( tag, "HAPPLOSAID" ){

    def id          = column[Int]( "id", O.PrimaryKey )
    def what_said   = column[String]( "what_said" )
    def context_note= column[String]( "context_note" )
    def created_at  = column[DateTime]("created_at")
    def *           = ( id, what_said, created_at )


  }


