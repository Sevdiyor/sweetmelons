package controllers

import models.{MembersDBTables, MembersDB}
import play.api.Logger
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import play.api.mvc._

import scala.slick.lifted.TableQuery

class Members extends Controller{
  val membersDB = TableQuery[MembersDBTables]

  def membersList = DBAction { implicit rs =>
    Logger.info(s"SHOW_ALL = ${membersDB.list}")
    Ok(views.html.membersList(membersDB.list))
  }
  def testResults = DBAction { implicit rs =>
    Logger.info(s"SHOW_ALL = ${membersDB.list}")
    Ok(views.html.testResultList(membersDB.list))
  }
  def membersListAdmin = DBAction { implicit rs =>
    Logger.info(s"SHOW_ALL = ${membersDB.list}")
    Ok(views.html.membersListAdmin(membersDB.list))
  }


  def showAddForm = DBAction { implicit rs =>
    Ok(views.html.addMembers())
  }

  def addMembers = DBAction { implicit request =>
    val formParams = request.body.asFormUrlEncoded
    val name = formParams.get("name")(0)
    val surname = formParams.get("surname")(0)
    val secondname = formParams.get("secondname")(0)
    val adress = formParams.get("adress")(0)
    val login = formParams.get("login")(0)
    val pass = formParams.get("pass")(0)
    val confPass = formParams.get("confPass")(0)

    val membersId = (membersDB returning membersDB.map(_.id)) += MembersDB(None, name, surname, secondname, adress, login, pass, confPass)
    Redirect(routes.Members.membersListAdmin())
  }


  def remove(id: Int) = DBAction { implicit request =>
    membersDB.filter(_.id === id).delete
    Redirect(routes.Members.membersList())
  }
  def updateMembers(id: Int) = DBAction { implicit request =>
    val formParams = request.body.asFormUrlEncoded
    val name = formParams.get("name")(0)
    val surname = formParams.get("surname")(0)
    val secondname = formParams.get("secondname")(0)
    val adress = formParams.get("adress")(0)
    val login = formParams.get("login")(0)
    val pass = formParams.get("pass")(0)
    val confPass = formParams.get("confPass")(0)

      val member = MembersDB(Some(id), name, surname, secondname, adress, login, pass, confPass)
      membersDB.filter(_.id === id).update(member)

      Redirect(routes.Members.membersList())
    }

    def editFormMembersList(memberId: Int) = DBAction { implicit request =>
      val byId = membersDB.findBy(_.id)
      val member = byId(memberId).list.head

      Ok(views.html.editMember(member))
    }

}
