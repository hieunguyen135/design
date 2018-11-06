// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide.button

import anduin.style.Style

import japgolly.scalajs.react.vdom.TagMod

private[button] object ButtonStyle {

  def getFull(button: Button): TagMod = {
    val shadow = button.color match {
      case Button.Color.White => Style.shadow.blur1Light
      case _ => Style.shadow.blur1Dark
    }
  }


}
