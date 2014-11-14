package haplothings.boundaries

abstract class Repository[T] {

  def insert( element: T )

  def take( limit: Int, skip: Int = 0  ): List[T]

  //I can't figure out how to make this generic at this time
  def find( id : Int ) : T
}
