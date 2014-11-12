package models

import scala.slick.driver.PostgresDriver.simple._
import com.github.tototoshi.slick.PostgresJodaSupport._
import org.joda.time._

//A HaploSaid table storing shit haplo says.

case class HaploSaid( id: Option[Int], what_said: String, context_note: Option[String],
                      created_at: Option[DateTime], who_said: String )

class HaploSaids(tag: Tag)
  extends Table[HaploSaid]( tag, "haplosaid" ){

    def id          = column[Option[Int]]( "id", O.PrimaryKey, O.AutoInc )
    def what_said   = column[String]( "what_said" )
    def context_note= column[Option[String]]( "context_note" )
    def created_at  = column[Option[DateTime]]( "created_at" )
    def who_said    = column[String]("who_said", O.Default( "Haplo" ) )
    def * =  ( id, what_said, context_note, created_at, who_said ) <> ( HaploSaid.tupled, HaploSaid.unapply )

}

object HaploSaids {
  val haploSaids = TableQuery[HaploSaids]

  def insert( haploSaid: HaploSaid )( implicit s: Session ){
    val tupled = HaploSaid.unapply( haploSaid ).get
    haploSaids.map( h => ( h.what_said, h.context_note, h.who_said ) ) += (tupled._2, tupled._3, tupled._5 )
  }

  def shitHaploSaid( limit: Int )( implicit s: Session ): List[HaploSaid] = {
    page( limit, 1 )
  }

  def find( id: Int )( implicit s: Session ): HaploSaid = {
    haploSaids.filter( _.id === id ).firstOption.get
  }

  def page( pageSize: Int, pageNum: Int )( implicit s: Session ): List[HaploSaid] = {
    val offset = ( pageNum - 1 ) * pageSize
    haploSaids.sortBy( _.created_at.desc ).drop( offset ).take( pageSize ).list
  }

  def more( lastIndex: Int, count: Int )(implicit s: Session): List[HaploSaid] = {
    val lastItem = find( lastIndex )
    val dropCount = haploSaids.sortBy( _.created_at.desc ).
      filter( _.created_at > lastItem.created_at ).length.run
    haploSaids.sortBy( _.created_at.desc ).drop( dropCount ).take( count ).list
  }

}


