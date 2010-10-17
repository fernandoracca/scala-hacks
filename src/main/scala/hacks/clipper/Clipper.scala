package hacks.clipper

/**
 * Dev: Fernando Racca (fracca@gmail.com)
 * Creation Date: Oct 16, 2010 12:38:16 AM
 */

case class Parameter(
	short: Option[String],
	long: Option[String],
	required: Boolean)

class ParamBuilder {
	private var shortParam: Option[String] = None
	private var longParam:  Option[String] = None
	private var requiredParam = false

	def short(short: String) = {
		this.shortParam = Some(short)
		this
	}

	def long(long: String) = {
		this.longParam = Some(long)
		this
	}

	def required(required: Boolean) = {
		this.requiredParam = required
		this
	}

	def build() = new Parameter(shortParam, longParam, requiredParam)
}

object ParamBuilder {
	def apply(): ParamBuilder = new ParamBuilder()
}

case class CommandLineArguments(config: Map[Parameter, Option[Any]])  {
	val size = config.size

	def findByShortParameter(shortParam: String): Option[Parameter] = {
		config.keys.find(_.short.get == shortParam)
	}
}

object Clipper {
	def parse(parameters: List[Parameter], arguments: Seq[String]): CommandLineArguments = {
		var config = Map[Parameter, Option[Any]]()

		for(parameter <- parameters) {
			val argument: Option[Any] = arguments.find(_ == parameter.short)
			config += (parameter -> argument)
		}
		new CommandLineArguments(config)
	}
}