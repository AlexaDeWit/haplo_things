package haplothings.boundaries.datamodels

import org.joda.time._

case class HaploSaid( id: Int, what_said: String, context_note: Option[String],
                      created_at: Option[DateTime], who_said: String )
