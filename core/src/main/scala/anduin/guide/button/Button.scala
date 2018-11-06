// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide.button

import anduin.component.button.ButtonStyle.StyleMinimal
import anduin.component.icon.Icon
import anduin.style.{Style => SStyle}

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class Button(
  tpe: Button.Tpe = Button.Tpe.Button,
  color: Button.Color = Button.Color.White,
  size: Button.Size = Button.Size.Fix32,
  style: Button.Style = Button.Style.Full,
  isFullWidth: Boolean = false,
  isSelected: Boolean = false,
  isDisabled: Boolean = false,
  icon: Option[Icon.Name] = None,
  autoFocus: Boolean = false,
  onClick: Callback = Callback.empty
) {
  def apply(children: VdomNode*): VdomElement = {
    Button.component(this)(children: _*)
  }
}

object Button {

  private abstract class Tpe(private[Button] val mod: TagMod)

  object Tpe {
    // https://developer.mozilla.org/en-US/docs/Web/HTML/Element/a
    case class Link(href: String, target: TagMod) extends Tpe(TagMod(^.href := href, target))
    // https://developer.mozilla.org/en-US/docs/Web/HTML/Element/button#attr-type
    private abstract class ButtonCommon(private[Button] val value: String) extends Tpe(^.tpe := value)
    case object Button extends ButtonCommon("button")
    case object Submit extends ButtonCommon("submit")
    case object Reset extends ButtonCommon("reset")
  }


  private[Button] abstract class Color(text: TagMod, bg: TagMod, border: TagMod, full: TagMod) {
    def getMods(style: Style): TagMod = style match {
      case Style.Link    => this.text
      case Style.Minimal => TagMod(this.text, this.bg)
      case Style.Ghost   => TagMod(this.text, this.bg, this.border)
      case Style.Full    => this.full
    }
  }

  object Color {
    case object White
        extends Color(
          text = SStyle.color.white,
          bg = SStyle.hover.backgroundGray7.active.backgroundGray6,
          border = SStyle.borderColor.gray6,
          full = SStyle.color.gray8.shadow.blur1Light.backgroundColor.white.hover.backgroundWhite.active.backgroundGray2
        )
    private abstract class ColorNonWhite(text: TagMod, bg: TagMod, border: TagMod, fullBg: TagMod)
        extends Color(text, bg, border, TagMod(SStyle.color.white.shadow.blur1Dark, fullBg))
    case object Black
        extends ColorNonWhite(
          text = SStyle.color.gray8,
          bg = SStyle.hover.backgroundGray3.active.backgroundGray4,
          border = SStyle.borderColor.gray4,
          fullBg = SStyle.backgroundColor.gray7.hover.backgroundGray6.active.backgroundGray8
        )
    case object Red
        extends ColorNonWhite(
          text = SStyle.color.danger4,
          bg = SStyle.hover.backgroundDanger1.active.backgroundDanger2,
          border = SStyle.borderColor.danger4,
          fullBg = SStyle.backgroundColor.danger4.hover.backgroundDanger3.active.backgroundDanger5
        )
  }

  private[Button] abstract class Style
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
