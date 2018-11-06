// Copyright (C) 2014-2018 Anduin Transactions Inc.

package anduin.guide.button

import anduin.component.icon.Icon
import anduin.style.{Style => SStyle}

// scalastyle:off underscore.import
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
// scalastyle:on underscore.import

final case class Button(
  tpe: Button.Tpe = Button.Tpe.TpeButton,
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

  private type Props = Button

  abstract class Tpe(private[Button] val value: TagMod)
  object Tpe {
    // https://developer.mozilla.org/en-US/docs/Web/HTML/Element/a
    case class Link(href: String, target: TagMod) extends Tpe(TagMod(^.href := href, target))
    // https://developer.mozilla.org/en-US/docs/Web/HTML/Element/button#attr-type
    abstract class NonLink(private[Button] val text: String) extends Tpe(^.tpe := text)
    case object TpeButton extends NonLink("button")
    case object Submit extends NonLink("submit")
    case object Reset extends NonLink("reset")
  }

  case class ColorPart(color: TagMod = TagMod.empty, bg: TagMod, selectedBg: TagMod) {
    def getBg(isSelected: Boolean): TagMod = if (isSelected) selectedBg else bg
  }
  abstract class Color(light: ColorPart, heavy: ColorPart) {
    def getMods(style: Style, isDisabled: Boolean, isSelected: Boolean): TagMod = {
      if (isDisabled) {
        TagMod(
          SStyle.color.gray5.borderColor.gray3,
          style match {
            case Style.Link => TagMod.empty
            case _          => SStyle.backgroundColor.gray1
          }
        )
      } else {
        style match {
          case Style.Link                  => this.light.color
          case Style.Minimal | Style.Ghost => TagMod(this.light.color, this.light.getBg(isSelected))
          case Style.Full                  => TagMod(this.heavy.color, this.heavy.getBg(isSelected))
        }
      }
    }
  }
  object Color {
    case object White
        extends Color(
          light = ColorPart(
            color = SStyle.color.white.borderColor.gray6,
            bg = SStyle.hover.backgroundGray7.active.backgroundGray6,
            selectedBg = SStyle.backgroundColor.gray6
          ),
          heavy = ColorPart(
            color = SStyle.color.gray8.borderColor.gray4.shadow.blur1Light,
            bg = SStyle.backgroundColor.gray1.hover.backgroundWhite.active.backgroundGray2,
            selectedBg = SStyle.backgroundColor.gray2
          )
        )
    abstract class ColorNonWhite(light: ColorPart, heavy: ColorPart)
        extends Color(light, heavy.copy(color = TagMod(heavy.color, SStyle.color.white.shadow.blur1Dark)))
    case object Black
        extends ColorNonWhite(
          light = ColorPart(
            color = SStyle.borderColor.gray5.color.gray8,
            bg = SStyle.hover.backgroundGray3.active.backgroundGray4,
            selectedBg = SStyle.backgroundColor.gray4
          ),
          heavy = ColorPart(
            color = SStyle.borderColor.gray8,
            bg = SStyle.backgroundColor.gray7.hover.backgroundGray6.active.backgroundGray8,
            selectedBg = SStyle.backgroundColor.gray8
          )
        )
    case object Red
        extends ColorNonWhite(
          light = ColorPart(
            color = SStyle.borderColor.danger5.color.danger4,
            bg = SStyle.hover.backgroundDanger1.active.backgroundDanger2,
            selectedBg = SStyle.backgroundColor.danger2
          ),
          heavy = ColorPart(
            color = SStyle.borderColor.danger5,
            bg = SStyle.backgroundColor.danger4.hover.backgroundDanger3.active.backgroundDanger5,
            selectedBg = SStyle.backgroundColor.danger5
          )
        )
    case object Orange
        extends ColorNonWhite(
          light = ColorPart(
            color = SStyle.borderColor.warning5.color.warning4,
            bg = SStyle.hover.backgroundWarning1.active.backgroundWarning2,
            selectedBg = SStyle.backgroundColor.warning2
          ),
          heavy = ColorPart(
            color = SStyle.borderColor.warning5,
            bg = SStyle.backgroundColor.warning4.hover.backgroundWarning3.active.backgroundWarning5,
            selectedBg = SStyle.backgroundColor.warning5
          )
        )
    case object Green
        extends ColorNonWhite(
          light = ColorPart(
            color = SStyle.borderColor.success5.color.success4,
            bg = SStyle.hover.backgroundSuccess1.active.backgroundSuccess2,
            selectedBg = SStyle.backgroundColor.success2
          ),
          heavy = ColorPart(
            color = SStyle.borderColor.success5,
            bg = SStyle.backgroundColor.success4.hover.backgroundSuccess3.active.backgroundSuccess5,
            selectedBg = SStyle.backgroundColor.success5
          )
        )
    case object Blue
        extends ColorNonWhite(
          light = ColorPart(
            color = SStyle.borderColor.primary5.color.primary4,
            bg = SStyle.hover.backgroundPrimary1.active.backgroundPrimary2,
            selectedBg = SStyle.backgroundColor.primary2
          ),
          heavy = ColorPart(
            color = SStyle.borderColor.primary5,
            bg = SStyle.backgroundColor.primary4.hover.backgroundPrimary3.active.backgroundPrimary5,
            selectedBg = SStyle.backgroundColor.primary5
          )
        )
  }

  abstract class Style(private[Button] val value: TagMod)
  object Style {
    case object Link extends Style(SStyle.hover.underline)
    private[Button] abstract class NonLink(style: TagMod)
        extends Style(
          TagMod(
            style,
            SStyle.lineHeight.px16.fontWeight.medium.whiteSpace.noWrap,
            SStyle.focus.outline.transition.allWithOutline.borderRadius.px2,
            SStyle.flexbox.flex.flexbox.itemsCenter.flexbox.justifyCenter
          )
        )
    case object Full extends NonLink(SStyle.border.all)
    case object Ghost extends NonLink(SStyle.border.all)
    case object Minimal extends NonLink(TagMod.empty)
  }

  abstract class Size(private[Button] val value: TagMod)
  object Size {
    case object Fix24 extends Size(SStyle.height.px40.padding.hor16.fontSize.px16)
    case object Fix32 extends Size(SStyle.height.px32.padding.hor12.fontSize.px13)
    case object Fix40 extends Size(SStyle.height.px24.padding.hor8.fontSize.px12)
    case object Free extends Size(TagMod.empty)
  }

  private def getStyle(props: Props): TagMod = TagMod(
    props.style.value,
    props.color.getMods(props.style, props.isDisabled, props.isSelected),
    props.style match {
      case Style.Link => TagMod.empty
      case _ =>
        TagMod(
          if (props.isFullWidth) SStyle.width.pc100 else SStyle.width.maxContent,
          props.size.value
        )
    }
  )

  private def render(props: Props, children: PropsChildren): VdomElement = {
    val everything = TagMod(
      getStyle(props),
      props.tpe.value,
      ^.disabled := props.isDisabled,
      ^.autoFocus := props.autoFocus,
      TagMod.when(!props.isDisabled) { ^.onClick --> props.onClick },
      // content
      props.icon.map(name => {
        val margin = TagMod.when(children.nonEmpty)(SStyle.margin.right8)
        <.span(margin, Icon(name = name)())
      }),
      children
    )
    props.tpe match {
      case _: Tpe.Link => <.a(everything)
      case _           => <.button(everything)
    }
  }

  private val component = ScalaComponent
    .builder[Props](this.getClass.getSimpleName)
    .stateless
    .render_PC(render)
    .build
}
