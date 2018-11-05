// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide.button

import anduin.component.icon.Icon

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class ButtonT(
  tpe: ButtonT.Tpe = ButtonT.Tpe.Button,
  color: ButtonT.Color = ButtonT.Color.White,
  size: ButtonT.Size = ButtonT.Size.Fix32,
  style: ButtonT.Style = ButtonT.Style.Full,
  isFullWidth: Boolean = false,
  isSelected: Boolean = false,
  isDisabled: Boolean = false,
  icon: Option[Icon.Name] = None,
  autoFocus: Boolean = false,
  onClick: Callback = Callback.empty
) {
  def apply(children: VdomNode*): VdomElement = {
    ButtonT.component(this)(children: _*)
  }
}

object ButtonT {

  private abstract class Tpe
  object Tpe {
    private abstract class Input
    case object Button extends Tpe
    case object Submit extends Tpe
    case object Reset extends Tpe
    case class Link(href: String, target: TagMod) extends Tpe

    private[Button] def getMod(tpe: Tpe): TagMod = {

    }
  }

  private[button] abstract class Color
  object Color {
    case object White extends Color
    case object Black extends Color
    case object Red extends Color
    case object Orange extends Color
    case object Green extends Color
    case object Blue extends Color
  }

  private[button] abstract class Style
  object Style {
    case object Link extends Style
    private abstract class Button extends Style
    case object Full extends Style.Button
    case object Ghost extends Style.Button
    case object Minimal extends Style.Button
  }

  private[button] abstract class Size
  object Size {
    case object Fix24 extends Size
    case object Fix32 extends Size
    case object Fix40 extends Size
    case object Free extends Size
  }

}
