package models

/*import models.BaseEnum
import models.UserRoleEnum._*/
import play.api.db.slick.Config.driver.simple._




case class MembersDB(id: Option[Int],
                     name: String,
                     surname: String,
                     secondname: String,
                     adress: String,
                     login: String,
                     pass: String,
                     confPass: String/*,
                     role: UserRoleEnum.UserRole*/)

class MembersDBTables(tag: Tag) extends Table[MembersDB](tag, "Members") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def name = column[String]("NAME", O.Default(""))

  def surname = column[String]("SURNAME", O.Default(""))

  def secondname = column[String]("SECONDNAME", O.Default(""))

  def adress = column[String]("ADRESS", O.Default(""))

  def login = column[String]("LOGIN", O.Default(""))

  def pass = column[String]("PASS", O.Default(""))

  def confPass = column[String]("CONFPASS", O.Default(""))

  /*def role = column[UserRoleEnum.UserRole]("ROLE", O.NotNull)*/

  def * = (id.?, name, surname , secondname, adress, login, pass/*, role*/, confPass) <>(MembersDB.tupled,MembersDB.unapply _)

}



