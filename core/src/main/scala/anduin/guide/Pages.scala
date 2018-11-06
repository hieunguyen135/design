package anduin.guide

object Pages {
  sealed trait Page {}
  // entry
  case object Welcome extends Page
  case object WIP extends Page
  // brand
  sealed trait BrandT extends Page
  case class Logo(hash: String = "") extends BrandT
  // style
  sealed trait StyleT extends Page
  case class Style(hash: String = "") extends StyleT
  sealed trait LayoutT extends StyleT
  case class Space(hash: String = "") extends LayoutT
  case class Flexbox(hash: String = "") extends LayoutT
  case class Color(hash: String = "") extends StyleT
  sealed trait TypographyT extends StyleT
  case class Typography(hash: String = "") extends TypographyT
  case class FixedLineHeight(hash: String = "") extends TypographyT
  // component
  sealed trait ComponentT extends Page
  case class Component(hash: String = "") extends ComponentT
  // - button
  sealed trait ButtonT extends ComponentT
  case class Button(hash: String = "") extends ButtonT
  case class ButtonStyle(hash: String = "") extends ButtonT
  case class ButtonLink(hash: String = "") extends ButtonT
  case class ButtonTest(hash: String = "") extends ComponentT
  // - icon
  sealed trait IconT extends ComponentT
  case class Icon(hash: String = "") extends IconT
  case class Illus(hash: String = "") extends IconT
  case class IllusFolder(hash: String = "") extends IconT
  // - input
  sealed trait InputT extends ComponentT
  case class Radio(hash: String = "") extends InputT
  case class Checkbox(hash: String = "") extends InputT
  case class Suggest(hash: String = "") extends InputT
  case class MultiSuggest(hash: String = "") extends InputT
  case class TextBox(hash: String = "") extends InputT
  // - dropdown
  sealed trait DropdownT extends ComponentT
  case class Dropdown(hash: String = "") extends DropdownT
  case class MultiDropdown(hash: String = "") extends DropdownT
  // - portal
  sealed trait PortalT extends ComponentT
  case class Modal(hash: String = "") extends PortalT
  case class Popover(hash: String = "") extends PortalT
  // - others
  case class Stepper(hash: String = "") extends ComponentT
  case class Field(hash: String = "") extends ComponentT
  case class Card(hash: String = "") extends ComponentT
  case class Toggle(hash: String = "") extends ComponentT
  case class Tab(hash: String = "") extends ComponentT
  case class Table(hash: String = "") extends ComponentT
  case class Well(hash: String = "") extends ComponentT
}
