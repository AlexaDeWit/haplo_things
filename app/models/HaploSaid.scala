package models

import scala.slick.driver.PostgresDriver.simple._
import com.github.tototoshi.slick.PostgresJodaSupport._
import org.joda.time._

//A HaploSaid table storing shit haplo says.

case class HaploSaid( id: Option[Int], what_said: String, context_note: Option[String],
                      created_at: DateTime )

class HaploSaids(tag: Tag)
  extends Table[( Option[Int], String, Option[String], DateTime )]( tag, "haplosaid" ){

    def id          = column[Option[Int]]( "id", O.PrimaryKey, O.AutoInc )
    def what_said   = column[String]( "what_said" )
    def context_note= column[Option[String]]( "context_note" )
    def created_at  = column[DateTime]( "created_at" )
    def * = ( id, what_said, context_note, created_at )


}

object HaploSaids {
  val haploSaids = TableQuery[HaploSaids]

  def insert( haploSaid: HaploSaid )( implicit s: Session ){
    haploSaids.map( s => ( s.id, s.what_said, s.context_note, s.created_at ) ).
      forceInsert( HaploSaid.unapply( haploSaid ).get ).run
  }

}


